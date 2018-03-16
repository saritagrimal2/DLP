package ast;

import visitor.Visitor;

public class LiteralEntero extends ExpresionAbstracta{

	private int valor;

	public LiteralEntero(int linea, int columna, int valor) {
		super(linea, columna);
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return "LiteralEntero [valor=" + valor + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
