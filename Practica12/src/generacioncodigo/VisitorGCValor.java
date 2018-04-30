package generacioncodigo;

import ast.AccesoArray;
import ast.AccesoCampo;
import ast.Aritmetica;
import ast.Comparacion;
import ast.Identificador;
import ast.LiteralCaracter;
import ast.LiteralEntero;
import ast.LiteralReal;
import ast.Logica;

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
		a.getExp2().aceptar(this, param);
		
		gc.aritmetica(a.getOperador(), a.getTipo());
		
		return null;
	}

	@Override
	public Object visitar(Comparacion c, Object param) {
		c.getExp1().aceptar(this, param);
		c.getExp2().aceptar(this, param);
		
		gc.comparacion(c.getOperador(), c.getTipo());
		
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
	


}
