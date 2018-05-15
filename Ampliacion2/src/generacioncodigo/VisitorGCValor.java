package generacioncodigo;

import ast.AccesoArray;
import ast.AccesoCampo;
import ast.Aritmetica;
import ast.Cast;
import ast.Comparacion;
import ast.Expresion;
import ast.Identificador;
import ast.ModificarValor;
import ast.InvocacionFuncionExp;
import ast.LiteralCaracter;
import ast.LiteralEntero;
import ast.LiteralReal;
import ast.Logica;
import ast.MenosUnario;
import ast.Negacion;
import ast.tipo.Tipo;
import ast.tipo.TipoEntero;
import ast.tipo.TipoFuncion;

public class VisitorGCValor extends AbstractGC{
	
	private VisitorGCDireccion direccion;
	private GC gc;
	
	public VisitorGCValor(VisitorGCDireccion direccion, GC gc) {
		this.direccion = direccion;
		this.gc = gc;
	}

	@Override
	public Object visitar(LiteralEntero e, Object param) {
		gc.push(e.getTipo(), e.getValor());
		return null;
	}

	@Override
	public Object visitar(LiteralCaracter c, Object param) {
		gc.push(c.getTipo(), c.getValor());
		return null;
	}

	@Override
	public Object visitar(LiteralReal r, Object param) {
		gc.pushf(r.getTipo(), r.getValor());
		return null;
	}

	@Override
	public Object visitar(Identificador i, Object param) {
		i.aceptar(direccion, param);
		gc.load(i.getDefinicion().getTipo());
		return null;
	}
	
	@Override
	public Object visitar(Aritmetica a, Object param) {
		a.getExp1().aceptar(this, param);
		gc.convertir(a.getExp1().getTipo(), a.getTipo());
		a.getExp2().aceptar(this, param);
		gc.convertir(a.getExp2().getTipo(), a.getTipo());
		//Cuando son char, el tipo de aritmetca será de tipo entero
		//Cambiado en clase TipoCaracter
		gc.aritmetica(a.getOperador(), a.getTipo());
		
		return null;
	}
	
	@Override
	public Object visitar(MenosUnario m, Object param) {
		m.getExpresion().aceptar(this, param);
		gc.push(TipoEntero.getInstance(), -1);
		gc.convertir(TipoEntero.getInstance(), m.getTipo());
		gc.mul(m.getTipo());
		return null;
	}

	@Override
	public Object visitar(Comparacion c, Object param) {
		Tipo superTipo = c.getExp1().getTipo().superTipo(c.getExp2().getTipo());
		c.getExp1().aceptar(this, param);
		gc.convertir(c.getExp1().getTipo(), superTipo);
		c.getExp2().aceptar(this, param);
		gc.convertir(c.getExp2().getTipo(), superTipo);
		gc.comparacion(c.getOperador(), superTipo);
		
		return null;
	}

	@Override
	public Object visitar(Logica l, Object param) {
		l.getExp1().aceptar(this, param);
		l.getExp2().aceptar(this, param);
		
		gc.logica(l.getOperador());
		
		return null;
	}
	
	@Override
	public Object visitar(Negacion n, Object param) {
		n.getExpresion().aceptar(this, param);
		gc.not();
		return null;
	}
	
	@Override
	public Object visitar(AccesoArray a, Object param) {
		a.aceptar(direccion, param);
		gc.load(a.getTipo());
		return null;
	}
	
	@Override
	public Object visitar(AccesoCampo c, Object param) {
		c.aceptar(direccion, param);
		gc.load(c.getTipo());
		return null;
	}
	
	@Override
	public Object visitar(Cast c, Object param) {
		c.getExpresion().aceptar(this, param);
		gc.cast(c.getExpresion().getTipo(), c.getTipoCast());
		return null;
	}
	
	@Override
	public Object visitar(InvocacionFuncionExp f, Object param) {

		for (int i =0; i< f.getArgumentos().size(); i++) {
			f.getArgumentos().get(i).aceptar(this, param);
			gc.convertir(f.getArgumentos().get(i).getTipo(), 
					((TipoFuncion) f.getIdentificador().getTipo()).getArgumentos().get(i).getTipo());
		}
		
		
		gc.call(f.getIdentificador().getNombre());
		return null;
	}
	
	


}
