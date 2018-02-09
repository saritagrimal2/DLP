package ast;

public class Escritura extends AbstractNodoAST implements Sentencia {
	
	private Expresion expresion;

	public Escritura(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.setExpresion(expresion);
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

}
