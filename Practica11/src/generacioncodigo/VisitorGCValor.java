package generacioncodigo;

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
		//gc.convertir(a.getExp1().getTipo(), a.get)
		a.getExp2().aceptar(this, param);
		
		if (a.getOperador().equals("+") ) {
			gc.add(a.getTipo());
		} else if (a.getOperador().equals("-") ) {
			gc.sub(a.getTipo());
		} else if (a.getOperador().equals("*")) {
			gc.mul(a.getTipo());
		} else if (a.getOperador().equals("/")) {
			gc.div(a.getTipo());
		} else if (a.getOperador().equals("%")) {
			gc.mod(a.getTipo());
		}
		
		return null;
	}

	@Override
	public Object visitar(Comparacion c, Object param) {
		c.getExp1().aceptar(this, param);
		c.getExp2().aceptar(this, param);
		
		if (c.getOperador().equals(">")) {
			gc.gt(c.getTipo());
		}else if (c.getOperador().equals("<")) {
			gc.lt(c.getTipo());
		}else if (c.getOperador().equals(">=")) {
			gc.ge(c.getTipo());
		}else if (c.getOperador().equals("<=")) {
			gc.le(c.getTipo());
		}else if (c.getOperador().equals("==")) {
			gc.eq(c.getTipo());
		}else if (c.getOperador().equals("!=")) {
			gc.ne(c.getTipo());
		}
		
		return null;
	}

	@Override
	public Object visitar(Logica l, Object param) {
		l.getExp1().aceptar(this, param);
		l.getExp2().aceptar(this, param);
		
		if (l.getOperador().equals("&&")) {
			gc.and();
		}else if (l.getOperador().equals("||")) {
			gc.or();
		}else if(l.getOperador().equals("!")) {
			gc.not();
		}
		
		return null;
	}


}
