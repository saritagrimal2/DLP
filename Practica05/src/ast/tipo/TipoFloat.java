package ast.tipo;

import ast.AbstractNodoAST;

public class TipoFloat extends AbstractNodoAST implements Tipo{
	
	private static TipoFloat instance = new TipoFloat();

	private TipoFloat() {
		super(0,0);
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
	
}
