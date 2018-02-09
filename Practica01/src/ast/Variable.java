package ast;

public class Variable extends AbstractNodoAST implements Expresion{
	
	private String nombre;

	public Variable(int linea, int columna, String nombre) {
		super (linea, columna);
		this.setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString() {
		return this.nombre;
	}
}
