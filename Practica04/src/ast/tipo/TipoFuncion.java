package ast.tipo;

import java.util.List;

import ast.DefVariable;

public class TipoFuncion implements Tipo {
	
	private Tipo tipoRetorno; 
	private List<DefVariable> argumentos;
	
	public TipoFuncion(Tipo tipoRetorno, List<DefVariable> argumentos) {
		this.tipoRetorno = tipoRetorno;
		this.argumentos = argumentos;
	}

	@Override
	public String toString() {
		return "TipoFuncion [tipoRetorno=" + tipoRetorno + ", argumentos=" + argumentos + "]";
	}
}
