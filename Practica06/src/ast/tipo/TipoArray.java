package ast.tipo;

import ast.AbstractNodoAST;
import visitor.Visitor;

public class TipoArray extends AbstractNodoAST implements Tipo {
	
	private int tamaño;
	private Tipo tipo;

	public TipoArray(int linea, int columna, int tamaño, Tipo tipo) {
		super(linea, columna);
		this.tamaño = tamaño;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoArray [tamaño=" + tamaño + ", tipo=" + tipo + "]";
	}
	
	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
