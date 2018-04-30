package ast.tipo;

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
		return "int";
	}
	
	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

	@Override
	public Tipo aritmetica(Tipo expresion) {
		if (expresion instanceof TipoEntero) {
			return this;
		} else if (expresion instanceof TipoError) {
			return expresion;
		} else {
			return null;
		}
	}
	
	@Override
	public Tipo aritmetica() {
		return this;
	}
	
	@Override
	public Tipo comparacion(Tipo expresion) {
		if (expresion instanceof TipoEntero) {
			return this;
		} else if (expresion instanceof TipoError) {
			return expresion;
		} else {
			return null;
		}
	}
	
	@Override
	public Tipo logica() {
		return this;
	}
	
	@Override
	public Tipo logica(Tipo expresion) {
		if (expresion instanceof TipoEntero) {
			return this;
		} else if (expresion instanceof TipoError) {
			return expresion;
		} else {
			return null;
		}
	}
	
	@Override
	public Tipo cast(Tipo expresion) {
		if (expresion instanceof TipoEntero) {
			return this;
		} else if (expresion instanceof TipoFloat) {
			return this;
		}else if (expresion instanceof TipoCaracter) {
			return this;
		} else if (expresion instanceof TipoError) {
			return expresion;
		} else {
			return null;
		}
	}
	
	
	@Override
	public Tipo equivalente(Tipo tipo) {
		if (tipo instanceof TipoEntero) {
			return this;
		} else if (tipo instanceof TipoError) {
			return tipo;
		} else {
			return null;
		}
	}
	
	@Override
	public boolean esLogico() {
		return true;
	}
	
	@Override
	public int numeroBytes() {
		return 2;
	}
	
	@Override
	public char sufijo() {
		return 'i';
	}
	
}
