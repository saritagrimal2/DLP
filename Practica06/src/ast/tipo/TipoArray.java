package ast.tipo;

import ast.NodoASTAbstracto;
import visitor.Visitor;

public class TipoArray extends NodoASTAbstracto implements Tipo {
	
	private int tama�o;
	private Tipo tipo;

	public TipoArray(int linea, int columna, int tama�o, Tipo tipo) {
		super(linea, columna);
		this.tama�o = tama�o;
		this.tipo = tipo;
	}

	public int getTama�o() {
		return tama�o;
	}

	public Tipo getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return "TipoArray [tama�o=" + tama�o + ", tipo=" + tipo + "]";
	}
	
	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
