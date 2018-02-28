package ast.tipo;

import java.util.List;

import ast.AbstractNodoAST;

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

}
