package ast;

import java.util.List;

import visitor.Visitor;

public class sentenciaWhile extends AbstractNodoAST implements Sentencia {

	private Expresion expresion;
	private List<Sentencia> sentencias;

	public sentenciaWhile(int linea, int columna, Expresion expresion, List<Sentencia> sentencias) {
		super(linea, columna);
		this.expresion = expresion;
		this.sentencias = sentencias;
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	@Override
	public String toString() {
		return "sentenciaWhile [expresion=" + expresion + ", sentencias=" + sentencias + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
