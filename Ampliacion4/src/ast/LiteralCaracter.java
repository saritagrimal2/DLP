package ast;

import visitor.Visitor;

public class LiteralCaracter extends ExpresionAbstracta {

	private char valor;
//poner en constructor o donde sea el tipo
	
	public LiteralCaracter(int linea, int columna, char valor) {
		super(linea, columna);
		this.valor = valor;
	}

	public char getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return "LiteralCaracter [valor=" + valor + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}