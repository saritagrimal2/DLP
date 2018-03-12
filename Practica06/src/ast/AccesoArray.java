package ast;

import visitor.Visitor;

public class AccesoArray extends AbstractNodoAST implements Expresion{
	
	private Expresion exp1;
	private Expresion exp2;

	public AccesoArray(int linea, int columna, Expresion exp1, Expresion exp2) {
		super(linea, columna);
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	
	public Expresion getExp1() {
		return exp1;
	}

	public Expresion getExp2() {
		return exp2;
	}

	@Override
	public String toString() {
		return "AccesoArray [exp1=" + exp1 + ", exp2=" + exp2 + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
