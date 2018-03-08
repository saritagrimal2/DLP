package ast;

public class AccesoArray extends AbstractNodoAST implements Expresion{
	
	private Expresion exp1;
	private Expresion exp2;

	public AccesoArray(int linea, int columna, Expresion exp1, Expresion exp2) {
		super(linea, columna);
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	public String toString() {
		return "AccesoArray [exp1=" + exp1 + ", exp2=" + exp2 + "]";
	}

}
