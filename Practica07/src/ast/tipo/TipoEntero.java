package ast.tipo;

import ast.NodoASTAbstracto;
import visitor.Visitor;

public class TipoEntero extends NodoASTAbstracto implements Tipo {

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
	
	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
