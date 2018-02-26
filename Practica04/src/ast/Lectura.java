package ast;

public class Lectura extends AbstractNodoAST implements Sentencia {
	
	private Expresion expresion;

	public Lectura(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.setExpresion(expresion);
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}
	
	public String toString() {
		return String.valueOf(this.expresion);
	}


}
