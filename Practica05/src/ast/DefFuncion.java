package ast;

import java.util.List;

import ast.tipo.Tipo;

public class DefFuncion extends AbstractNodoAST implements Definicion{

	private String identificador;
	private Tipo tipo;
	private List<Sentencia> sentencias;
	
	public DefFuncion(int linea, int columna, String identificador, Tipo tipo, List<Sentencia> sentencias) {
		super(linea, columna);
		this.identificador = identificador;
		this.tipo = tipo;
		this.sentencias = sentencias;
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
		return "DefFuncion [identificador=" + identificador + ", tipo=" + tipo + ", sentencias=" + sentencias + "]";
	}
	
	

}
