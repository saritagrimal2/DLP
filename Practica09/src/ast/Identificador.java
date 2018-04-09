package ast;

import visitor.Visitor;

public class Identificador extends ExpresionAbstracta {

	private String nombre;
	
	private Definicion definicion;

	public Identificador(int linea, int columna, String nombre) {
		super(linea, columna);
		this.nombre = nombre;		
	}

	public String getNombre() {
		return nombre;
	}

	public Definicion getDefinicion() {
		return definicion;
	}
	
	public void setDefinicion(Definicion definicion) {
		this.definicion = definicion;
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
