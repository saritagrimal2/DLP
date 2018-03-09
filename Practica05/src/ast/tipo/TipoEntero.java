package ast.tipo;

import ast.AbstractNodoAST;

public class TipoEntero extends AbstractNodoAST implements Tipo {

	private static TipoEntero instance = new TipoEntero();

	private TipoEntero() {
		super(0,0);
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

}
