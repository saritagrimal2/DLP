package ast.tipo;

import ast.NodoAST;

public class TipoFloat implements NodoAST, Tipo {
	
	private int linea;
	private int columna;

	private static TipoFloat instance = new TipoFloat();

	private TipoFloat() {
	}

	public static TipoFloat getInstance() {
		if (instance == null)
			instance = new TipoFloat();
		return instance;
	}


	@Override
	public String toString() {
		return "TipoFloat []";
	}
	
	@Override
	public int getLinea() {
		return linea;
	}

	@Override
	public int getColumna() {
		return columna;
	}
}
