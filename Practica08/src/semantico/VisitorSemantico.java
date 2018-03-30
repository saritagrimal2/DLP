package semantico;

import ast.AccesoArray;
import ast.AccesoCampo;
import ast.Aritmetica;
import ast.Asignacion;
import ast.Cast;
import ast.Comparacion;
import ast.Expresion;
import ast.Identificador;
import ast.InvocacionFuncionExp;
import ast.Lectura;
import ast.LiteralCaracter;
import ast.LiteralEntero;
import ast.LiteralReal;
import ast.Logica;
import ast.MenosUnario;
import ast.Negacion;
import ast.tipo.TipoError;

public class VisitorSemantico extends VisitorAbstracto {

	@Override
	public Object visitar(Lectura l, Object param) {
		l.getExpresion().aceptar(this, param);
		if (!l.getExpresion().getLValue()) {
			new TipoError(0, 0, "Se esperaba un Lvalue");
		}
		return null;
	}

	@Override
	public Object visitar(Asignacion a, Object param) {
		a.getExp1().aceptar(this, param);
		a.getExp2().aceptar(this, param);
		if (!a.getExp1().getLValue()) {
			new TipoError(0, 0, "Se esperaba un Lvalue");
		}
		return null;
	}

	@Override
	public Object visitar(Aritmetica a, Object param) {
		a.getExp1().aceptar(this, param);
		a.getExp2().aceptar(this, param);
		a.setLValue(false);

//		a.getExp1().setTipo(a.getExp1().getTipo().aritmetica());
//		a.getExp2().setTipo(a.getExp2().getTipo().aritmetica());
//		a.setTipo(a.getTipo().aritmetica());
		
		
//		a.getExp1().setTipo(TipoEntero.getInstance());
//		a.getExp2().setTipo(TipoEntero.getInstance());
//		a.setTipo(TipoEntero.getInstance());
		
		a.getTipo().aritmetica(a.getExp1().getTipo());
		a.getTipo().aritmetica(a.getExp2().getTipo());

		return null;
	}

	@Override
	public Object visitar(Comparacion c, Object param) {
		c.getExp1().aceptar(this, param);
		c.getExp2().aceptar(this, param);
		c.setLValue(false);

		return null;
	}

	@Override
	public Object visitar(Logica l, Object param) {
		l.getExp1().aceptar(this, param);
		l.getExp2().aceptar(this, param);
		l.setLValue(false);
		return null;
	}

	@Override
	public Object visitar(Negacion n, Object param) {
		n.getExpresion().aceptar(this, param);
		n.setLValue(false);
		return null;
	}

	@Override
	public Object visitar(MenosUnario m, Object param) {
		m.getExpresion().aceptar(this, param);
		m.setLValue(false);
		return null;
	}

	@Override
	public Object visitar(AccesoCampo c, Object param) {
		c.getExpresion().aceptar(this, param);
		if (c.getExpresion().getLValue() == true) {
			c.setLValue(true);
		} else {
			new TipoError(0, 0, "Se esperaba un Lvalue");
		}

		return null;
	}

	@Override
	public Object visitar(AccesoArray a, Object param) {
		a.getExp1().aceptar(this, param);
		a.getExp2().aceptar(this, param);
		if (a.getExp1().getLValue() == true) {
			a.setLValue(true);
		} else {
			new TipoError(0, 0, "Se esperaba un Lvalue");
		}
		return null;
	}

	@Override
	public Object visitar(Cast c, Object param) {
		c.getExpresion().aceptar(this, param);
		c.getTipo().aceptar(this, param);
		c.setLValue(false);
		return null;
	}

	@Override
	public Object visitar(InvocacionFuncionExp f, Object param) {
		f.getIdentificador().aceptar(this, param);
		for (Expresion e : f.getArgumentos()) {
			e.aceptar(this, param);
		}
		f.setLValue(false);
		return null;
	}

	@Override
	public Object visitar(LiteralEntero e, Object param) {
		e.setLValue(false);
		return null;
	}

	@Override
	public Object visitar(LiteralCaracter c, Object param) {
		c.setLValue(false);
		return null;
	}

	@Override
	public Object visitar(LiteralReal r, Object param) {
		r.setLValue(false);
		return null;
	}

	@Override
	public Object visitar(Identificador i, Object param) {
		i.setLValue(true);
		return null;
	}

}
