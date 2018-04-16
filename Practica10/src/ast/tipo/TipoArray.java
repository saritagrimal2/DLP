package ast.tipo;

import visitor.Visitor;

public class TipoArray extends TipoAbstracto {
	
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
	
	@Override
	public Tipo corchetes(Tipo indice) {
		if (indice instanceof TipoEntero) {
			return this.tipo;
		} else if (indice instanceof TipoError) {
			return indice;
		} else {
			return null;
		}
	}
	
	@Override
	public int numeroBytes() {
		return tipo.numeroBytes() * tama�o;
	}
	

}
