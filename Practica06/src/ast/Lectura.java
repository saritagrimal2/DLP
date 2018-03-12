package ast;

import visitor.Visitor;

public class Lectura extends AbstractNodoAST implements Sentencia {

	private Expresion expresion;

	public Lectura(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	public Expresion getExpresion() {
		return expresion;
	}

	@Override
	public String toString() {
		return "Lectura [expresion=" + expresion + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}
}
