package ast;

import ast.tipo.Tipo;

public abstract class ExpresionAbstracta extends NodoASTAbstracto implements Expresion{
	
	private boolean lValue;
	private Tipo tipo;

	public ExpresionAbstracta(int linea, int columna) {
		super(linea, columna);
	}

	@Override
	public boolean getLValue() {
		return lValue;
	}

	@Override
	public void setLValue(boolean lValue) {
		this.lValue = lValue;
	}
	
	@Override
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	
}
