package ast;

import ast.tipo.Tipo;

public class DefVariable extends AbstractNodoAST implements Definicion, Sentencia{

	private String identificador;
	private Tipo tipo;
	
	public DefVariable(int linea, int columna, String identificador, Tipo tipo) {
		super(linea, columna);
		this.identificador = identificador;
		this.tipo = tipo;
	}

	@Override
	public String getIdentificador() {
		return identificador;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return "DefVariable [identificador=" + identificador + ", tipo=" + tipo + "]";
	}
	
	

}
