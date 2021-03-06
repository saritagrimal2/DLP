package ast.tipo;

import ast.AbstractNodoAST;

public class TipoCaracter extends AbstractNodoAST implements Tipo {
	
	private static TipoCaracter instance = new TipoCaracter();

	private TipoCaracter() {
		super(0,0);
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

}
