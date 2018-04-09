package ast;

import visitor.Visitor;

public class MenosUnario extends ExpresionAbstracta {

	private Expresion expresion;

	public MenosUnario(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	public Expresion getExpresion() {
		return expresion;
	}

	@Override
	public String toString() {
		return "MenosUnario [expresion=" + expresion + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
