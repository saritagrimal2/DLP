package ast;

public class Aritmetica extends AbstractNodoAST implements Expresion {
	
	private Expresion exp1, exp2;
	private String operador;

	public Aritmetica(int linea, int columna, Expresion exp1, String operador, Expresion exp2) {
		super(linea, columna);
		this.setExp1(exp1);
		this.setOperador(operador);
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

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}
	
	
	public String toString() {
		return this.exp1 + this.operador + this.exp2;
	}
	

}
