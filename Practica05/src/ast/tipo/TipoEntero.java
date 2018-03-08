package ast.tipo;

import ast.NodoAST;

public class TipoEntero implements NodoAST, Tipo {
	
	private int linea;
	private int columna;

	private static TipoEntero instance = new TipoEntero();

	private TipoEntero() {
	}

	public static TipoEntero getInstance() {
		if (instance == null)
			instance = new TipoEntero();
		return instance;
	}

	@Override
	public String toString() {
		return "TipoEntero []";
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
