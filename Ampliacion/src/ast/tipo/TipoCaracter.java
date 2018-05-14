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
		return "char";
	}
	
	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}
	
	@Override
	public Tipo aritmetica(Tipo expresion) {
		if (expresion instanceof TipoCaracter) {
			return TipoEntero.getInstance();
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
		if (expresion instanceof TipoCaracter) {
			return TipoEntero.getInstance();
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
		if (expresion instanceof TipoCaracter) {
			return this;
		} else if (expresion instanceof TipoError) {
			return expresion;
		} else {
			return null;
		}
	}
	
	@Override
	public Tipo cast(Tipo expresion) {
		if (expresion instanceof TipoCaracter) {
			return this;
		}else if (expresion instanceof TipoEntero) {
			return this;
		}else if (expresion instanceof TipoFloat) {
			return this;
		}else if (expresion instanceof TipoError) {
			return expresion;
		} else {
			return null;
		}
	}
	
	@Override
	public Tipo promocionaA(Tipo tipo) {
		if (tipo instanceof TipoCaracter) {
			return this;
		} else {
			return tipo;
		}
	}
	
	@Override
	public boolean esLogico() {
		return true;
	}
	
	@Override
	public int numeroBytes() {
		return 1;
	}
	
	@Override
	public char sufijo() {
		return 'b';
	}
	
	@Override
	public Tipo superTipo(Tipo expresion) {
		if (expresion instanceof TipoEntero) {
			return TipoEntero.getInstance();
		} else if (expresion instanceof TipoFloat) {
			return TipoFloat.getInstance();
		} else {
			return this;
		}
	}

}
