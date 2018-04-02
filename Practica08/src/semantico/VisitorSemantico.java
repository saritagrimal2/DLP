package semantico;

import java.util.ArrayList;
import java.util.List;

import ast.AccesoArray;
import ast.AccesoCampo;
import ast.Aritmetica;
import ast.Asignacion;
import ast.Cast;
import ast.Comparacion;
import ast.Expresion;
import ast.Identificador;
import ast.InvocacionFuncionExp;
import ast.InvocacionFuncionSent;
import ast.Lectura;
import ast.LiteralCaracter;
import ast.LiteralEntero;
import ast.LiteralReal;
import ast.Logica;
import ast.MenosUnario;
import ast.Negacion;
import ast.Return;
import ast.Sentencia;
import ast.sentenciaIf;
import ast.sentenciaWhile;
import ast.tipo.Tipo;
import ast.tipo.TipoCaracter;
import ast.tipo.TipoEntero;
import ast.tipo.TipoError;
import ast.tipo.TipoFloat;
import ast.tipo.TipoFuncion;

public class VisitorSemantico extends VisitorAbstracto {

	@Override
	public Object visitar(sentenciaWhile w, Object param) {
		w.getExpresion().aceptar(this, param);
		for (Sentencia s : w.getSentencias()) {
			s.aceptar(this, param);
		}

		if (!w.getExpresion().getTipo().esLogico()) {
			w.getExpresion().setTipo(new TipoError(0, 0, "No es tipo entero o caracter"));
		}

		return null;
	}

	@Override
	public Object visitar(sentenciaIf i, Object param) {
		i.getExpresion().aceptar(this, param);
		for (Sentencia s : i.getSentenciaElse()) {
			s.aceptar(this, param);
		}
		for (Sentencia s : i.getSentencias()) {
			s.aceptar(this, param);
		}

		if (!i.getExpresion().getTipo().esLogico()) {
			i.getExpresion().setTipo(new TipoError(0, 0, "No es tipo entero o caracter"));
		}

		return null;
	}

	@Override
	public Object visitar(Return r, Object param) {
		r.getExpresion().aceptar(this, param);

		TipoFuncion t2 = (TipoFuncion) param;// Necesito el TipoFuncion, (El tipo de retorno del tipo funcion)
		// Lo tengo en definicion de funcion, se lo pasamos como parametro en ese object
		r.getExpresion().getTipo().equivalente(t2.getTipoRetorno());
		return null;
	}

	@Override
	public Object visitar(Lectura l, Object param) {
		l.getExpresion().aceptar(this, param);
		if (!l.getExpresion().getLValue()) {
			new TipoError(0, 0, "Se esperaba un Lvalue");
		}

//		Tipo t2 = (Tipo) param;
//		l.getExpresion().getTipo().equivalente(t2);

		return null;
	}

	@Override
	public Object visitar(Asignacion a, Object param) {
		a.getExp1().aceptar(this, param);
		a.getExp2().aceptar(this, param);
		if (!a.getExp1().getLValue()) {
			new TipoError(0, 0, "Se esperaba un Lvalue");
		}

		a.getExp1().getTipo().equivalente(a.getExp2().getTipo());

		return null;
	}

	@Override
	public Object visitar(Aritmetica a, Object param) {
		a.getExp1().aceptar(this, param);
		a.getExp2().aceptar(this, param);
		a.setLValue(false);

		Tipo inferido = a.getExp1().getTipo().aritmetica(a.getExp2().getTipo());
		a.setTipo(inferido);

		return null;
	}

	@Override
	public Object visitar(Comparacion c, Object param) {
		c.getExp1().aceptar(this, param);
		c.getExp2().aceptar(this, param);
		c.setLValue(false);

		Tipo inferido = c.getExp1().getTipo().comparacion(c.getExp2().getTipo());
		c.setTipo(inferido);

		return null;
	}

	@Override
	public Object visitar(Logica l, Object param) {
		l.getExp1().aceptar(this, param);
		l.getExp2().aceptar(this, param);
		l.setLValue(false);

		Tipo inferido = l.getExp1().getTipo().logica(l.getExp2().getTipo());
		l.setTipo(inferido);

		return null;
	}

	@Override
	public Object visitar(Negacion n, Object param) {
		n.getExpresion().aceptar(this, param);
		n.setLValue(false);

		Tipo inferido = n.getExpresion().getTipo().comparacion();
		n.setTipo(inferido);

		return null;
	}

	@Override
	public Object visitar(MenosUnario m, Object param) {
		m.getExpresion().aceptar(this, param);
		m.setLValue(false);

		Tipo inferido = m.getExpresion().getTipo().aritmetica();
		m.setTipo(inferido);

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

		Tipo inferido = c.getExpresion().getTipo().punto(c.getIdentificador());
		c.setTipo(inferido);

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

		Tipo inferido = a.getExp2().getTipo().corchetes(a.getExp1().getTipo());
		a.setTipo(inferido);

		return null;
	}

	@Override
	public Object visitar(Cast c, Object param) {
		c.getExpresion().aceptar(this, param);
		c.getTipo().aceptar(this, param);
		c.setLValue(false);

		Tipo inferido = c.getTipo().cast(c.getExpresion().getTipo());
		c.setTipo(inferido);

		return null;
	}

	@Override
	public Object visitar(InvocacionFuncionExp f, Object param) {
		f.getIdentificador().aceptar(this, param);

		List<Tipo> tipos = new ArrayList<Tipo>();
		for (Expresion e : f.getArgumentos()) {
			e.aceptar(this, param);
			tipos.add(e.getTipo());
		}
		f.setLValue(false);

		Tipo inferido = f.getIdentificador().getTipo().parentises(tipos);
		f.setTipo(inferido);

		return null;
	}
	
	@Override
	public Object visitar(InvocacionFuncionSent f, Object param) {
		f.getIdentificador().aceptar(this, param);
		
		List<Tipo> tipos = new ArrayList<Tipo>();
		for (Expresion e : f.getArgumentos()) {
			e.aceptar(this, param);
			tipos.add(e.getTipo());
		}
		
		f.getIdentificador().getTipo().parentises(tipos);
		
		return null;
	}

	@Override
	public Object visitar(LiteralEntero e, Object param) {
		e.setLValue(false);
		e.setTipo(TipoEntero.getInstance());
		return null;
	}

	@Override
	public Object visitar(LiteralCaracter c, Object param) {
		c.setLValue(false);
		c.setTipo(TipoCaracter.getInstance());
		return null;
	}

	@Override
	public Object visitar(LiteralReal r, Object param) {
		r.setLValue(false);
		r.setTipo(TipoFloat.getInstance());
		return null;
	}

	@Override
	public Object visitar(Identificador i, Object param) {
		i.setLValue(true);
		i.setTipo(i.getDefinicion().getTipo());
		return null;
	}

}
