package ast;

import java.util.List;

public class InvocacionFuncionExp extends AbstractNodoAST implements Expresion {

	private Identificador identificador;
	private List<Expresion> argumentos;
	
	public InvocacionFuncionExp(int linea, int columna, Identificador identificador, List<Expresion> argumentos) {
		super(linea, columna);
		this.identificador = identificador;
		this.argumentos = argumentos;
	}

	@Override
	public String toString() {
		return "InvocacionFuncionExp [identificador=" + identificador + ", argumentos=" + argumentos + "]";
	}

}
