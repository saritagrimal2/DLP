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
		return "TipoFloat []";
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
			return new TipoError(0,0,"Tipo no compatible");
		}
	}
	
	@Override
	public Tipo aritmetica() {
		return this;
	}
	
//	@Override
//	public Tipo comparacion(Tipo expresion) {
//		if (expresion instanceof TipoFloat) {
//			return this;
//		} else if (expresion instanceof TipoError) {
//			return expresion;
//		} else {
//			return new TipoError(0,0,"Tipo no compatible");
//		}
//	}
//	
//	@Override
//	public Tipo comparacion() {
//		return this;
//	}
	
	@Override
	public Tipo cast(Tipo expresion) {
		if (expresion instanceof TipoFloat) {
			return this;
		} else if (expresion instanceof TipoError) {
			return expresion;
		} else {
			return new TipoError(0,0,"Tipo no compatible");
		}
	}
	
	@Override
	public Tipo equivalente(Tipo tipo) {
		if (tipo instanceof TipoFloat) {
			return this;
		} else if (tipo instanceof TipoError) {
			return tipo;
		} else {
			return new TipoError(0,0,"Tipo no compatible");
		}
	}
	
}
