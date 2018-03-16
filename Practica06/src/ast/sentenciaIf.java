package ast;

import java.util.List;

import visitor.Visitor;

public class sentenciaIf extends NodoASTAbstracto implements Sentencia {

	private Expresion expresion;
	private List<Sentencia> sentencias;
	private List<Sentencia> sentenciaElse;

	public sentenciaIf(int linea, int columna, Expresion expresion, List<Sentencia> sentencias,
			List<Sentencia> sentenciaElse) {
		super(linea, columna);
		this.expresion = expresion;
		this.sentencias = sentencias;
		this.sentenciaElse = sentenciaElse;
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	public List<Sentencia> getSentenciaElse() {
		return sentenciaElse;
	}

	@Override
	public String toString() {
		return "sentenciaIf [expresion=" + expresion + ", sentencias=" + sentencias + ", sentenciaElse=" + sentenciaElse
				+ "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
