package ast;

public class Asignacion extends AbstractNodoAST implements Sentencia {
	
	private Expresion exp1;
	private Expresion exp2;

	public Asignacion(int linea, int columna, Expresion exp1, Expresion exp2) {
		super(linea, columna);
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	public String toString() {
		return "Asignacion [exp1=" + exp1 + ", exp2=" + exp2 + "]";
	}
	

}
