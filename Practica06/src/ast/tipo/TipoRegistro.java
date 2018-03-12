package ast.tipo;

import java.util.List;

import ast.AbstractNodoAST;
import visitor.Visitor;

public class TipoRegistro extends AbstractNodoAST implements Tipo {
	
	List<Campo> campos;

	public TipoRegistro(int linea, int columna, List<Campo> campos) {
		super(linea, columna);
		this.campos = campos;
	}

	@Override
	public String toString() {
		return "TipoRegistro [campos=" + campos + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}
	
}
