package generacioncodigo;

import ast.Aritmetica;
import ast.Comparacion;
import ast.Identificador;
import ast.LiteralCaracter;
import ast.LiteralEntero;
import ast.LiteralReal;
import ast.Logica;
import ast.tipo.TipoEntero;

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
		gc.convertir(a.getExp1().getTipo());
		a.getExp2().aceptar(this, param);
		gc.convertir(a.getExp2().getTipo());
		
		if (a.getTipo().sufijo() == 'b') {
			gc.aritmetica(a.getOperador(), TipoEntero.getInstance());
		} else {
			gc.aritmetica(a.getOperador(), a.getTipo());
		}
		
		return null;
	}

	@Override
	public Object visitar(Comparacion c, Object param) {
		c.getExp1().aceptar(this, param);
		gc.convertir(c.getExp1().getTipo());
		c.getExp2().aceptar(this, param);
		gc.convertir(c.getExp2().getTipo());
		
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


}
