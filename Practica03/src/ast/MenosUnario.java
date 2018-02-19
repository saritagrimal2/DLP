package ast;

public class MenosUnario extends AbstractNodoAST implements Expresion {
	
	private Expresion expresion;

	public MenosUnario(int linea, int columna, Expresion expresion) {
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
		return "-" + this.expresion;
	}


}
