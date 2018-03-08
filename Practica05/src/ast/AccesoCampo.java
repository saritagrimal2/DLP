package ast;

public class AccesoCampo extends AbstractNodoAST implements Expresion{
	
	private Expresion expresion;
	private String identificador;

	public AccesoCampo(int linea, int columna, Expresion expresion, String identificador) {
		super(linea, columna);
		this.expresion = expresion;
		this.identificador = identificador;
	}

	@Override
	public String toString() {
		return "AccesoCampo [expresion=" + expresion + ", identificador=" + identificador + "]";
	}

}
