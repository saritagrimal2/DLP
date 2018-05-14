package ast.tipo;

import visitor.Visitor;

public class TipoFloat extends TipoAbstracto{
	
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
		return "float32";
	}
	
	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}
	
	@Override
	public Tipo aritmetica(Tipo expresion) {
		if (expresion instanceof TipoFloat) {
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
		if (expresion instanceof TipoFloat) {
			return this;
		} else if (expresion instanceof TipoError) {
			return expresion;
		} else {
			return null;
		}
	}
	

	@Override
	public Tipo cast(Tipo expresion) {
		if (expresion instanceof TipoFloat) {
			return this;
		}else if (expresion instanceof TipoEntero) {
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
		if (tipo instanceof TipoFloat) {
			return this;
		} else if (tipo instanceof TipoError) {
			return tipo;
		} else {
			return null;
		}
	}
	
	@Override
	public int numeroBytes() {
		return 4;
	}
	
	@Override
	public char sufijo() {
		return 'f';
	}
	
}
