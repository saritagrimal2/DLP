package ast.tipo;

import visitor.Visitor;

public class TipoCaracter extends TipoAbstracto {
	
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

	
	@Override
	public Tipo comparacion(Tipo expresion) {
		if (expresion instanceof TipoCaracter) {
			return this;
		} else if (expresion instanceof TipoError) {
			return expresion;
		} else {
			return new TipoError(0,0,"Tipo no compatible");
		}
	}
	
	@Override
	public Tipo comparacion() {
		return this;
	}
	
	@Override
	public Tipo logica(Tipo expresion) {
		if (expresion instanceof TipoCaracter) {
			return this;
		} else if (expresion instanceof TipoError) {
			return expresion;
		} else {
			return new TipoError(0,0,"Tipo no compatible");
		}
	}
	
	@Override
	public Tipo cast(Tipo expresion) {
		if (expresion instanceof TipoCaracter) {
			return this;
		} else if (expresion instanceof TipoError) {
			return expresion;
		} else {
			return new TipoError(0,0,"Tipo no compatible");
		}
	}
	
	@Override
	public Tipo equivalente(Tipo tipo) {
		if (tipo instanceof TipoCaracter) {
			return this;
		} else if (tipo instanceof TipoError) {
			return tipo;
		} else {
			return new TipoError(0,0,"Tipo no compatible");
		}
	}
	
	@Override
	public boolean esLogico() {
		return true;
	}

}
