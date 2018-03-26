package ast;

import visitor.Visitor;

public class Identificador extends ExpresionAbstracta {

	private String nombre;
	
	//Tipo de la definicion, no se puede en el constructor
	
	private DefVariable defvariable;

	public Identificador(int linea, int columna, String nombre) {
		super(linea, columna);
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public DefVariable getDefvariable() {
		return defvariable;
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
