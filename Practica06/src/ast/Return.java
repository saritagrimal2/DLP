package ast;

import visitor.Visitor;

public class Return extends AbstractNodoAST implements Sentencia {

	private Expresion expresion;

	public Return(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	public Expresion getExpresion() {
		return expresion;
	}

	@Override
	public String toString() {
		return "Return [expresion=" + expresion + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}
}
