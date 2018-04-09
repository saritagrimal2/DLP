package ast;

import ast.tipo.Tipo;

public interface Expresion extends NodoAST{

	public boolean getLValue();
	public void setLValue(boolean lValue);
	
	public Tipo getTipo();
	public void setTipo(Tipo tipo);
	
}
