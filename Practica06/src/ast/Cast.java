package ast;

import ast.tipo.Tipo;
import visitor.Visitor;

public class Cast extends ExpresionAbstracta {

	private Tipo tipo;
	private Expresion expresion;

	public Cast(int linea, int columna, Tipo tipo, Expresion expresion) {
		super(linea, columna);
		this.tipo = tipo;
		this.expresion = expresion;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public Expresion getExpresion() {
		return expresion;
	}

	@Override
	public String toString() {
		return "Cast [tipo=" + tipo + ", expresion=" + expresion + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
