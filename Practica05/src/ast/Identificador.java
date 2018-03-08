package ast;

public class Identificador extends AbstractNodoAST implements Expresion{
	
	private String nombre;

	public Identificador(int linea, int columna, String nombre) {
		super (linea, columna);
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Identificador [nombre=" + nombre + "]";
	}
}
