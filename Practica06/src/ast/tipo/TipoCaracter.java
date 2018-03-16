package ast.tipo;

import ast.NodoASTAbstracto;
import visitor.Visitor;

public class TipoCaracter extends NodoASTAbstracto implements Tipo {
	
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
	
	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
