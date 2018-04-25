package generacioncodigo;

import java.io.IOException;

import ast.Asignacion;
import ast.DefFuncion;
import ast.DefVariable;
import ast.Definicion;
import ast.Escritura;
import ast.Lectura;
import ast.Programa;
import ast.Sentencia;
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
	}	 
	

	@Override
	public Object visitar(Programa p, Object param) {

		gc.source(fentrada);

		for (Definicion d : p.getDefinicones()) {
			if (d instanceof DefVariable) {
				d.aceptar(this, param);
			}
			d.aceptar(this, param);
		}

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
//		try {
//			System.in.read();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		gc.store(l.getExpresion().getTipo());
		return null;
	}

	@Override
	public Object visitar(Asignacion a, Object param) {
		a.getExp1().aceptar(direccion, param);
		a.getExp2().aceptar(valor, param);
		gc.store(a.getExp1().getTipo());
		return null;
	}

	@Override
	public Object visitar(DefFuncion f, Object param) {

		gc.etiqueta(f.getIdentificador());
		
		//Calcular offset total de locales
		int local = 0;
		for (Sentencia s : f.getSentencias()) {
			if (s instanceof DefVariable) {
				local += ((DefVariable) s).getOffset();
			}
		}
		
		gc.enter(local);
		
		for (Sentencia s : f.getSentencias()) {
			if (!(s instanceof DefVariable)) {
				s.aceptar(this, param);
			}
		}
		
		//Calcular offset total de parametros
		int p = 0;
		for (DefVariable v: ((TipoFuncion)f.getTipo()).getArgumentos()) {
			p += v.getOffset();
		}
		
		
		if (f.getTipo() instanceof TipoVoid) {
			gc.ret(0, local, p);
		}else {
			gc.ret(f.getTipo().numeroBytes(), local, p);
		}

		return null;
	}

}
