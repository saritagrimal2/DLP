package semantico;

import ast.AccesoArray;
import ast.AccesoCampo;
import ast.Aritmetica;
import ast.Asignacion;
import ast.Cast;
import ast.Comparacion;
import ast.DefFuncion;
import ast.DefVariable;
import ast.Definicion;
import ast.Escritura;
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
import ast.Programa;
import ast.Return;
import ast.Sentencia;
import ast.sentenciaIf;
import ast.sentenciaWhile;
import ast.tipo.Campo;
import ast.tipo.TipoArray;
import ast.tipo.TipoCaracter;
import ast.tipo.TipoEntero;
import ast.tipo.TipoError;
import ast.tipo.TipoFloat;
import ast.tipo.TipoFuncion;
import ast.tipo.TipoRegistro;
import ast.tipo.TipoVoid;
import visitor.Visitor;

public abstract class VisitorAbstracto implements Visitor {

	@Override
	public Object visitar(sentenciaWhile w, Object param) {
		w.getExpresion().aceptar(this, param);
		for (Sentencia s : w.getSentencias()) {
			s.aceptar(this, param);
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

		return null;
	}

	@Override
	public Object visitar(Return r, Object param) {
		r.getExpresion().aceptar(this, param);

		return null;
	}

	@Override
	public Object visitar(Escritura e, Object param) {
		e.getExpresion().aceptar(this, param);
		return null;
	}

	@Override
	public Object visitar(InvocacionFuncionSent f, Object param) {
		f.getIdentificador().aceptar(this, param);
		for (Expresion e : f.getArgumentos()) {
			e.aceptar(this, param);
		}
		return null;
	}

	@Override
	public Object visitar(Programa p, Object param) {
		for (Definicion d : p.getDefinicones()) {
			d.aceptar(this, param);
		}
		return null;
	}

	@Override
	public Object visitar(TipoArray a, Object param) {
		a.getTipo().aceptar(this, param);
		return null;
	}

	@Override
	public Object visitar(TipoCaracter c, Object param) {
		return null;
	}

	@Override
	public Object visitar(TipoEntero e, Object param) {
		return null;
	}

	@Override
	public Object visitar(TipoError e, Object param) {
		return null;
	}

	@Override
	public Object visitar(TipoFloat f, Object param) {
		return null;
	}

	@Override
	public Object visitar(TipoFuncion f, Object param) {
		f.getTipoRetorno().aceptar(this, param);
		for (DefVariable d : f.getArgumentos()) {
			d.aceptar(this, param);
		}
		return null;
	}

	@Override
	public Object visitar(TipoRegistro r, Object param) {
		for (Campo c : r.getCampos()) {
			c.aceptar(this, param);
		}
		return null;
	}

	@Override
	public Object visitar(TipoVoid v, Object param) {
		return null;
	}

	@Override
	public Object visitar(DefFuncion f, Object param) {
		for (Sentencia s : f.getSentencias())
			s.aceptar(this, param); 
		f.getTipo().aceptar(this, param);
		return null;
	}

	@Override
	public Object visitar(DefVariable v, Object param) {
		v.getTipo().aceptar(this,param);
		return null;
	}

	@Override
	public Object visitar(Lectura l, Object param) {
		l.getExpresion().aceptar(this, param);
		return null;
	}

	@Override
	public Object visitar(Asignacion a, Object param) {
		a.getExp1().aceptar(this, param);
		a.getExp2().aceptar(this, param);
		return null;
	}

	@Override
	public Object visitar(Aritmetica a, Object param) {
		a.getExp1().aceptar(this, param);
		a.getExp2().aceptar(this, param);
		return null;
	}

	@Override
	public Object visitar(Comparacion c, Object param) {
		c.getExp1().aceptar(this, param);
		c.getExp2().aceptar(this, param);
		return null;
	}

	@Override
	public Object visitar(Logica l, Object param) {
		l.getExp1().aceptar(this, param);
		l.getExp2().aceptar(this, param);
		return null;
	}

	@Override
	public Object visitar(Negacion n, Object param) {
		n.getExpresion().aceptar(this, param);
		return null;
	}

	@Override
	public Object visitar(MenosUnario m, Object param) {
		m.getExpresion().aceptar(this, param);
		return null;
	}

	@Override
	public Object visitar(AccesoCampo c, Object param) {
		c.getExpresion().aceptar(this, param);
		return null;
	}

	@Override
	public Object visitar(Campo c, Object param) {
		c.getTipo().aceptar(this, param);
		return null;
	}

	@Override
	public Object visitar(AccesoArray a, Object param) {
		a.getExp1().aceptar(this, param);
		a.getExp2().aceptar(this, param);
		return null;
	}

	@Override
	public Object visitar(Cast c, Object param) {
		c.getExpresion().aceptar(this, param);
		c.getTipoCast().aceptar(this, param);
		return null;
	}

	@Override
	public Object visitar(InvocacionFuncionExp f, Object param) {
		f.getIdentificador().aceptar(this, param);
		for (Expresion e : f.getArgumentos()) {
			e.aceptar(this, param);
		}
		return null;
	}

	@Override
	public Object visitar(LiteralEntero e, Object param) {
		return null;
	}

	@Override
	public Object visitar(LiteralCaracter c, Object param) {
		return null;
	}

	@Override
	public Object visitar(LiteralReal r, Object param) {
		return null;
	}

	@Override
	public Object visitar(Identificador i, Object param) {
		return null;
	}

	@Override
	public Object visitar(ModificarValor i, Object param) {
		i.getExpresion().aceptar(this, param);
		return null;
	}
	
	@Override
	public Object visitar(ModificarValorConcreto mv, Object param) {
		mv.getExp1().aceptar(this, param);
		mv.getExp2().aceptar(this, param);
		return null;
	}
}
