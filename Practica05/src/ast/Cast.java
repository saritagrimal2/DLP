package ast;

import ast.tipo.Tipo;

public class Cast extends AbstractNodoAST implements Expresion {

	private Tipo tipo;
	private Expresion expresion;
	
	public Cast(int linea, int columna, Tipo tipo, Expresion expresion) {
		super(linea, columna);
		this.tipo = tipo;
		this.expresion = expresion;
	}
	@Override
	public String toString() {
		return "Cast [tipo=" + tipo + ", expresion=" + expresion + "]";
	}
	
}
