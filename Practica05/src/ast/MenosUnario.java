package ast;

public class MenosUnario extends AbstractNodoAST implements Expresion {
	
	private Expresion expresion;

	public MenosUnario(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "MenosUnario [expresion=" + expresion + "]";
	}


}
