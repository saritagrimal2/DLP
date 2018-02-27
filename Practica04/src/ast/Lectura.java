package ast;

public class Lectura extends AbstractNodoAST implements Sentencia {
	
	private Expresion expresion;

	public Lectura(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Lectura [expresion=" + expresion + "]";
	}

}
