package ast;

import ast.tipo.Tipo;

public interface Definicion extends NodoAST{
	String getIdentificador();
	Tipo getTipo();
	
	int getAmbito();
	void setAmbito(int ambito);
	
}
