package ast;

import visitor.Visitor;

public class Variable extends AbstractNodoAST implements Expresion{
	
	private String nombre;

	public Variable(int linea, int columna, String nombre) {
		super (linea, columna);
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	
	public String toString() {
		return this.nombre;
	}
	
	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}
}
