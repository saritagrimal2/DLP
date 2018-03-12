package ast;

import visitor.Visitor;

public class Identificador extends AbstractNodoAST implements Expresion {

	private String nombre;

	public Identificador(int linea, int columna, String nombre) {
		super(linea, columna);
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "Identificador [nombre=" + nombre + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}
}
