package ast;

import ast.tipo.Tipo;

public interface Definicion extends NodoAST{
	String getIdentificador();
	void setIdentificador(String identificador);
	Tipo getTipo();
	void setTipo(Tipo tipo);
	
	
	int getAmbito();
	void setAmbito(int ambito);
	
}
