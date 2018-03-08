package ast;

public class Escritura extends AbstractNodoAST implements Sentencia {
	
	private Expresion expresion;

	public Escritura(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Escritura [expresion=" + expresion + "]";
	}

}
