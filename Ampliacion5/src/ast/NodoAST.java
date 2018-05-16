package ast;

import visitor.Visitor;

public interface NodoAST {
	
	int getLinea();
	int getColumna();
	
	public Object aceptar(Visitor visitor, Object param);

}
