package ast;

import visitor.Visitor;

public class AccesoCampo extends NodoASTAbstracto implements Expresion{
	
	private Expresion expresion;
	private String identificador;

	public AccesoCampo(int linea, int columna, Expresion expresion, String identificador) {
		super(linea, columna);
		this.expresion = expresion;
		this.identificador = identificador;
	}

	
	public Expresion getExpresion() {
		return expresion;
	}


	public String getIdentificador() {
		return identificador;
	}


	@Override
	public String toString() {
		return "AccesoCampo [expresion=" + expresion + ", identificador=" + identificador + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
