package ast.tipo;

import java.util.List;

import ast.AbstractNodoAST;
import ast.DefVariable;

public class TipoFuncion extends AbstractNodoAST implements Tipo {
	
	private Tipo tipoRetorno; 
	private List<DefVariable> argumentos;
	
	public TipoFuncion(int linea, int columna, Tipo tipoRetorno, List<DefVariable> argumentos) {
		super(linea, columna);
		this.tipoRetorno = tipoRetorno;
		this.argumentos = argumentos;
	}

	@Override
	public String toString() {
		return "TipoFuncion [tipoRetorno=" + tipoRetorno + ", argumentos=" + argumentos + "]";
	}
}
