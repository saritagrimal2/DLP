package ast;

import visitor.Visitor;

public class LiteralReal extends ExpresionAbstracta {

	private double valor;

	public LiteralReal(int linea, int columna, double valor) {
		super(linea, columna);
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return "LiteralReal [valor=" + valor + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}
}