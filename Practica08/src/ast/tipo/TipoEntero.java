package ast.tipo;

import java.util.List;

import visitor.Visitor;

public class TipoEntero extends TipoAbstracto {

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

	//implementar esto en tipo entero, caracter y real
	@Override
	public Tipo aritmetica(Tipo expresion) {
		if (expresion.equals(this)) {
			return this;
		}
		
		//Si expresion es de tipo entero, return tipoEneto
		//Si es tipo error, retornas expresion, no creas uno nuevo, retornas el que hay
		//En las otras combinaciones, si que hay que crear un nuevo tipo error
		return null;
	}

	

}
