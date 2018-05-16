package semantico;

import java.util.ArrayList;
import java.util.List;

import ast.AccesoArray;
import ast.AccesoCampo;
import ast.Aritmetica;
import ast.Asignacion;
import ast.Cast;
import ast.Comparacion;
import ast.DefFuncion;
import ast.Expresion;
import ast.Identificador;
import ast.ModificarValor;
import ast.ModificarValorConcreto;
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

		if (w.getExpresion().getTipo() != null) {
			if (!w.getExpresion().getTipo().esLogico()) {
				w.getExpresion().setTipo(new TipoError(w.getExpresion().getLinea(), w.getExpresion().getColumna(),
						"[sentenciaWhile] No es expresión lógica."));
			}
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

		if (i.getExpresion().getTipo() != null) {
			if (!i.getExpresion().getTipo().esLogico()) {
				i.getExpresion().setTipo(new TipoError(i.getExpresion().getLinea(), i.getExpresion().getColumna(),
						"[SentenciaIf] No es expresión lógica."));
			}
		}
		return null;
	}

	@Override
	public Object visitar(DefFuncion f, Object param) {
		for (Sentencia s : f.getSentencias())
			s.aceptar(this, f.getTipo()); // le paso el tipo de retorno
		f.getTipo().aceptar(this, param);
		return null;
	}

	@Override
	public Object visitar(Return r, Object param) {
		r.getExpresion().aceptar(this, param);

		if (r.getExpresion().getTipo() != null) {
			TipoFuncion t2 = (TipoFuncion) param;// Necesito el TipoFuncion, (El tipo de retorno del tipo funcion)
			// Lo tengo en definicion de funcion, se lo pasamos como parametro en ese object
			r.getExpresion().getTipo().promocionaA(t2.getTipoRetorno());

			if (r.getExpresion().getTipo().promocionaA(t2.getTipoRetorno()) == null) {
				new TipoError(r.getLinea(), r.getColumna(), "[Return] No se puede promocionar ese tipo.");
			}
		}

		return null;
	}

	@Override
	public Object visitar(Lectura l, Object param) {
		l.getExpresion().aceptar(this, param);
		if (!l.getExpresion().getLValue()) {
			new TipoError(l.getLinea(), l.getColumna(), "[Lectura] Se esperaba un Lvalue.");
		}

		return null;
	}

	@Override
	public Object visitar(Asignacion a, Object param) {
		a.getExp1().aceptar(this, param);
		a.getExp2().aceptar(this, param);

		if (a.getExp1().getTipo() != null && a.getExp2().getTipo() != null) {
			a.getExp2().getTipo().promocionaA(a.getExp1().getTipo());

			if (a.getExp2().getTipo().promocionaA(a.getExp1().getTipo()) == null) {
				new TipoError(a.getLinea(), a.getColumna(), "[Asignacion] No se puede promocionar ese tipo.");
			}

			if (!a.getExp1().getLValue()) {
				new TipoError(a.getLinea(), a.getColumna(), "[Asignacion] Se esperaba un Lvalue.");
			}
		}
		return null;
	}

	@Override
	public Object visitar(Aritmetica a, Object param) {
		a.getExp1().aceptar(this, param);
		a.getExp2().aceptar(this, param);

		Tipo inferido = a.getExp1().getTipo().aritmetica(a.getExp2().getTipo());
		a.setTipo(inferido);

		if (a.getTipo() == null) {
			a.setTipo(new TipoError(a.getLinea(), a.getColumna(),
					"[Aritmetica] No es posible realizar la expresión aritmética."));
		}

		a.setLValue(false);

		return null;
	}

	@Override
	public Object visitar(Comparacion c, Object param) {
		c.getExp1().aceptar(this, param);
		c.getExp2().aceptar(this, param);

		if (c.getExp1().getTipo() != null && c.getExp2().getTipo() != null) {
			Tipo inferido = c.getExp1().getTipo().comparacion(c.getExp2().getTipo());
			c.setTipo(inferido);

			if (c.getTipo() == null) {
				c.setTipo(new TipoError(c.getLinea(), c.getColumna(),
						"[Comparacion] No es posible realizar la expresión de comparación."));
			}
		}

		c.setLValue(false);

		return null;
	}

	@Override
	public Object visitar(Logica l, Object param) {
		l.getExp1().aceptar(this, param);
		l.getExp2().aceptar(this, param);

		Tipo inferido = l.getExp1().getTipo().logica(l.getExp2().getTipo());
		l.setTipo(inferido);

		if (l.getTipo() == null) {
			l.setTipo(new TipoError(l.getLinea(), l.getColumna(),
					"[Logica] El tipo solo pueder ser entero o caracter y ser compatibles."));
		}

		l.setLValue(false);

		return null;
	}

	@Override
	public Object visitar(Negacion n, Object param) {
		n.getExpresion().aceptar(this, param);

		Tipo inferido = n.getExpresion().getTipo().logica();
		n.setTipo(inferido);

		if (n.getTipo() == null) {
			n.setTipo(
					new TipoError(n.getLinea(), n.getColumna(), "[Logica] El tipo solo pueder ser entero o caracter."));
		}

		n.setLValue(false);

		return null;
	}

	@Override
	public Object visitar(MenosUnario m, Object param) {
		m.getExpresion().aceptar(this, param);

		Tipo inferido = m.getExpresion().getTipo().aritmetica();
		m.setTipo(inferido);

		if (m.getTipo() == null) {
			m.setTipo(new TipoError(m.getLinea(), m.getColumna(),
					"[Aritmetica] El tipo solo pueder ser entero o double."));
		}

		m.setLValue(false);

		return null;
	}

	@Override
	public Object visitar(AccesoCampo c, Object param) {
		c.getExpresion().aceptar(this, param);

		Tipo inferido = c.getExpresion().getTipo().punto(c.getIdentificador());
		c.setTipo(inferido);

		if (c.getTipo() == null) {
			c.setTipo(new TipoError(c.getLinea(), c.getColumna(),
					"[AccesoCampo] No es posible acceder al campo del registro."));
		}

		if (c.getExpresion().getLValue() == true) {
			c.setLValue(true);
		} else {
			new TipoError(c.getLinea(), c.getColumna(), "[AccesoCampo] Se esperaba un Lvalue.");
		}

		return null;
	}

	@Override
	public Object visitar(AccesoArray a, Object param) {
		a.getExp1().aceptar(this, param);
		a.getExp2().aceptar(this, param);

		Tipo inferido = a.getExp1().getTipo().corchetes(a.getExp2().getTipo());
		a.setTipo(inferido);

		if (a.getTipo() == null) {
			a.setTipo(new TipoError(a.getLinea(), a.getColumna(), "[AccesoArray] No se puede acceder al array."));
		}

		if (a.getExp1().getLValue() == true) {
			a.setLValue(true);
		} else {
			new TipoError(a.getLinea(), a.getColumna(), "[AccesoArray] Se esperaba un Lvalue.");
		}

		return null;
	}

	@Override
	public Object visitar(Cast c, Object param) {
		c.getExpresion().aceptar(this, param);
		c.getTipoCast().aceptar(this, param);

		Tipo inferido = c.getTipoCast().cast(c.getExpresion().getTipo());
		c.setTipo(inferido);

		if (c.getTipo() == null) {
			c.setTipo(new TipoError(c.getLinea(), c.getColumna(),
					"[Cast] No se puede realizar cast a la siguiente expresión."));
		}

		c.setLValue(false);

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

		Tipo inferido = f.getIdentificador().getTipo().parentises(tipos);
		f.setTipo(inferido);

		if (f.getTipo() == null) {
			f.setTipo(new TipoError(f.getLinea(), f.getColumna(),
					"[InvocacionFuncionExp] Los argumentos de la invocación no son adecuados."));
		}

		f.setLValue(false);

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

		if (f.getIdentificador().getTipo().parentises(tipos) == null) {
			new TipoError(f.getLinea(), f.getColumna(),
					"[InvocacionFuncionSent] Los argumentos de la invocación no son adecuados.");
		}

		return null;
	}

	@Override
	public Object visitar(LiteralEntero e, Object param) {
		e.setTipo(TipoEntero.getInstance());
		e.setLValue(false);
		return null;
	}

	@Override
	public Object visitar(LiteralCaracter c, Object param) {
		c.setTipo(TipoCaracter.getInstance());
		c.setLValue(false);
		return null;
	}

	@Override
	public Object visitar(LiteralReal r, Object param) {
		r.setTipo(TipoFloat.getInstance());
		r.setLValue(false);
		return null;
	}

	@Override
	public Object visitar(Identificador i, Object param) {
		if (i.getDefinicion() != null) {
			i.setTipo(i.getDefinicion().getTipo());
		}
		i.setLValue(true);
		return null;
	}

	@Override
	public Object visitar(ModificarValor i, Object param) {
		i.getExpresion().aceptar(this, param);

		if (TipoEntero.getInstance().promocionaA(i.getExpresion().getTipo()) == null) {
			new TipoError(i.getLinea(), i.getColumna(), "[ModificarValor] No se puede promocionar ese tipo.");
		}

		if (!i.getExpresion().getLValue()) {
			new TipoError(i.getLinea(), i.getColumna(), "[ModificarValor] Se esperaba un Lvalue.");
		}

		return null;
	}

	@Override
	public Object visitar(ModificarValorConcreto mv, Object param) {
		mv.getExp1().aceptar(this, param);
		mv.getExp2().aceptar(this, param);

		if (mv.getExp2().getTipo().promocionaA(mv.getExp1().getTipo()) == null) {
			new TipoError(mv.getLinea(), mv.getColumna(), "[ModificarValorConcreto] No se puede promocionar ese tipo.");
		}

		if (!mv.getExp1().getLValue()) {
			new TipoError(mv.getLinea(), mv.getColumna(), "[ModificarValorConcreto] Se esperaba un Lvalue.");
		}

		return null;
	}

}
