package ast;

public class Asignacion extends AbstractNodoAST implements Sentencia {
	
	private Expresion exp1;
	private Expresion exp2;

	public Asignacion(int linea, int columna, Expresion exp1, Expresion exp2) {
		super(linea, columna);
		this.setExp1(exp1);
		this.setExp2(exp2);
	}

	public Expresion getExp1() {
		return exp1;
	}

	public void setExp1(Expresion exp1) {
		this.exp1 = exp1;
	}

	public Expresion getExp2() {
		return exp2;
	}

	public void setExp2(Expresion exp2) {
		this.exp2 = exp2;
	}
	
	public String toString() {
		return this.exp1 + "=" + this.exp2;
	}
	

}
