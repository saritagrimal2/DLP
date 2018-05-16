package generacioncodigo;


import ast.Asignacion;
import ast.DefFuncion;
import ast.DefVariable;
import ast.Definicion;
import ast.Escritura;
import ast.ModificarValor;
import ast.ModificarValorConcreto;
import ast.InvocacionFuncionSent;
import ast.Lectura;
import ast.Programa;
import ast.Return;
import ast.Sentencia;
import ast.sentenciaIf;
import ast.sentenciaWhile;
import ast.tipo.Tipo;
import ast.tipo.TipoCaracter;
import ast.tipo.TipoEntero;
import ast.tipo.TipoFuncion;
import ast.tipo.TipoVoid;

public class VisitorGCEjecutar extends AbstractGC {

	private String fentrada;
	private VisitorGCValor valor;

	private VisitorGCDireccion direccion;
	private GC gc;

	public VisitorGCEjecutar(String fentrada, String fSalida) {
		this.fentrada = fentrada;
		gc = new GC(fSalida);
		direccion = new VisitorGCDireccion(gc);
		valor = new VisitorGCValor(direccion, gc);
		direccion.setValor(valor);
	}	 
	

	@Override
	public Object visitar(Programa p, Object param) {
		gc.source(fentrada);

		for (Definicion d : p.getDefinicones()) {
			if (d instanceof DefVariable) {
				d.aceptar(this, param);
				gc.comentarioVariables(d);
			}
		}
		gc.comentarioMain();
		gc.call("main");
		gc.halt();
		for (Definicion d : p.getDefinicones()) {
			if (d instanceof DefFuncion) {
				d.aceptar(this, param);
			}
		}
		return null;
	}

	@Override
	public Object visitar(Escritura e, Object param) {
		e.getExpresion().aceptar(valor, param);
		gc.out(e.getExpresion().getTipo());
		return null;
	}

	@Override
	public Object visitar(Lectura l, Object param){
		l.getExpresion().aceptar(direccion, param);
		gc.in(l.getExpresion().getTipo());
		gc.store(l.getExpresion().getTipo());
		return null;
	}

	@Override
	public Object visitar(Asignacion a, Object param) {
		a.getExp1().aceptar(direccion, param);
		a.getExp2().aceptar(valor, param);
		gc.convertir(a.getExp2().getTipo(), a.getExp1().getTipo());
		gc.store(a.getExp1().getTipo());
		
		return null;
	}

	@Override
	public Object visitar(DefFuncion df, Object param) {
		gc.etiqueta(df.getIdentificador());
		
		gc.comentarioParametros();
		for (DefVariable d : ((TipoFuncion)df.getTipo()).getArgumentos()) {
				gc.comentarioVariables(d);
		}
		gc.comentarioLocales();
		for (Sentencia s : df.getSentencias()) {
			if (s instanceof DefVariable) {
				gc.comentarioVariables((Definicion) s);
			}
		}
		
		gc.enter(df.numeroBytesLocales());
		gc.comentarioCuerpoFuncion();
		for (Sentencia s : df.getSentencias()) {
			if (!(s instanceof DefVariable)) {
				gc.comentarioLinea(s.getLinea());
				s.aceptar(this, df);
			}
		}
		
		if (((TipoFuncion) df.getTipo()).getTipoRetorno() instanceof TipoVoid){
			gc.comentarioReturnVoid();
			gc.ret(0, df.numeroBytesLocales(), df.numeroBytesParam());
		}

		return null;
	}
	
	@Override
	public Object visitar(sentenciaWhile w, Object param) {
		int etiqueta = gc.getEtiquetas(2);
		gc.etiqueta(etiqueta);
		w.getExpresion().aceptar(valor,(DefFuncion) param);
		gc.jz(etiqueta+1);
		
		for (Sentencia s: w.getSentencias()) {
			gc.comentarioLinea(s.getLinea());
			s.aceptar(this, (DefFuncion)param);
		}
		gc.jmp(etiqueta);
		gc.etiqueta(etiqueta+1);

		return null;
	}

	@Override
	public Object visitar(sentenciaIf i, Object param) {
		int etiqueta = gc.getEtiquetas(2);
		i.getExpresion().aceptar(valor, (DefFuncion)param);
		gc.jz(etiqueta);
		for (Sentencia s: i.getSentencias()) {
			gc.comentarioLinea(s.getLinea());
			s.aceptar(this,(DefFuncion) param);
		}
		gc.jmp(etiqueta+1);
		gc.etiqueta(etiqueta);
		for (Sentencia s: i.getSentenciaElse()) {
			gc.comentarioLinea(s.getLinea());
			s.aceptar(this, (DefFuncion)param);
		}
		gc.etiqueta(etiqueta+1);

		return null;
	}
	
	@Override
	public Object visitar(InvocacionFuncionSent f, Object param) {
//		for (Expresion e : f.getArgumentos()) {
//			e.aceptar(valor, param);
//		}
		
		for (int i =0; i< f.getArgumentos().size(); i++) {
			f.getArgumentos().get(i).aceptar(valor, param);
			gc.convertir(f.getArgumentos().get(i).getTipo(), 
					((TipoFuncion) f.getIdentificador().getTipo()).getArgumentos().get(i).getTipo());
		}
		
		gc.call(f.getIdentificador().getNombre());
		if (((TipoFuncion) f.getIdentificador().getTipo()).getTipoRetorno() != TipoVoid.getInstance() ) {
			gc.pop(((TipoFuncion) f.getIdentificador().getTipo()).getTipoRetorno());
		}
		return null;
	}
	
	@Override
	public Object visitar(Return r, Object param) {
		r.getExpresion().aceptar(valor, param);
		DefFuncion df = (DefFuncion) param;
		gc.convertir(r.getExpresion().getTipo(), ((TipoFuncion) df.getTipo()).getTipoRetorno());
		gc.comentarioReturn();
		gc.ret(((TipoFuncion) df.getTipo()).getTipoRetorno().numeroBytes(), 
				df.numeroBytesLocales(), df.numeroBytesParam());

		return null;
	}
	
	
	@Override
	public Object visitar(ModificarValor i, Object param) {
		i.getExpresion().aceptar(direccion, param);
		i.getExpresion().aceptar(valor, param);
		gc.push(TipoEntero.getInstance(), 1);
		gc.convertir(TipoEntero.getInstance(), i.getExpresion().getTipo());
		gc.modificarValor(i.getOperador(), i.getExpresion().getTipo());
		
		gc.store(i.getExpresion().getTipo());
		
		
		return null;
	}
	
	@Override
	public Object visitar(ModificarValorConcreto i, Object param) {
		Tipo superTipo = i.getExp1().getTipo().superTipo(i.getExp2().getTipo());
		i.getExp1().aceptar(direccion, param);
		i.getExp1().aceptar(valor, param);
		gc.convertir(i.getExp1().getTipo(), superTipo);
		i.getExp2().aceptar(valor, param);
		gc.convertir(i.getExp2().getTipo(), superTipo);
		
		gc.modificarValorConcreto(i.getOperador(), superTipo);
		
		if (i.getExp2().getTipo() instanceof TipoCaracter && i.getExp1().getTipo() instanceof TipoCaracter) {
			gc.i2b();
			gc.store(TipoCaracter.getInstance());
		} else {
			gc.store(superTipo);
		}

		return null;
	}


}
