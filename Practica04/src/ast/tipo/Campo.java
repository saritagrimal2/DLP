package ast.tipo;

import ast.AbstractNodoAST;
import ast.Identificador;

public class Campo extends AbstractNodoAST{
	
	private Identificador identificador;
	private Tipo tipo;
	
	public Campo(int linea, int columna, Identificador identificador, Tipo tipo) {
		super(linea, columna);
		this.identificador = identificador;
		this.tipo = tipo;
	}


	@Override
	public String toString() {
		return "Campo [identificador=" + identificador + ", tipo=" + tipo + "]";
	}

}
