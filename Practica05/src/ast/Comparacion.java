package ast;

public class Comparacion extends AbstractNodoAST implements Expresion {
	
	private Expresion exp1, exp2;
	private String operador;

	public Comparacion(int linea, int columna, Expresion exp1, String operador, Expresion exp2) {
		super(linea, columna);
		this.exp1 = exp1;
		this.operador = operador;
		this.exp2 = exp2;
	}

	@Override
	public String toString() {
		return "Comparacion [exp1=" + exp1 + ", exp2=" + exp2 + ", operador=" + operador + "]";
	}

}
