package ast.tipo;

import ast.NodoAST;

public class TipoVoid implements NodoAST, Tipo {
	
	private int linea;
	private int columna;

	private static TipoVoid instance = new TipoVoid();

	private TipoVoid() {
	}

	public static TipoVoid getInstance() {
		if (instance == null)
			instance = new TipoVoid();
		return instance;
	}


	@Override
	public String toString() {
		return "TipoVoid []";
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
