package ast;

import visitor.Visitor;

public class Logica extends ExpresionAbstracta {

	private Expresion exp1, exp2;
	private String operador;

	public Logica(int linea, int columna, Expresion exp1, String operador, Expresion exp2) {
		super(linea, columna);
		this.exp1 = exp1;
		this.operador = operador;
		this.exp2 = exp2;
	}

	public Expresion getExp1() {
		return exp1;
	}

	public Expresion getExp2() {
		return exp2;
	}

	public String getOperador() {
		return operador;
	}

	@Override
	public String toString() {
		return "Logica [exp1=" + exp1 + ", exp2=" + exp2 + ", operador=" + operador + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
