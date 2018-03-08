package ast.tipo;

import ast.NodoAST;

public class TipoCaracter implements NodoAST, Tipo {
	
	private int linea;
	private int columna;
	
	private static TipoCaracter instance = new TipoCaracter();

	private TipoCaracter() {
	}

	public static TipoCaracter getInstance() {
		if (instance == null)
			instance = new TipoCaracter();
		return instance;
	}

	@Override
	public String toString() {
		return "TipoCaracter []";
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
