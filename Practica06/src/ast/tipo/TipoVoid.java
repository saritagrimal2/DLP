package ast.tipo;

import ast.AbstractNodoAST;
import visitor.Visitor;

public class TipoVoid extends AbstractNodoAST implements Tipo {
	

	private static TipoVoid instance = new TipoVoid();

	private TipoVoid() {
		super(0,0);
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
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
