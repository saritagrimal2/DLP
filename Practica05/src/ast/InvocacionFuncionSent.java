package ast;

import java.util.List;

public class InvocacionFuncionSent extends AbstractNodoAST implements Sentencia{

	private Identificador identificador;
	private List<Expresion> argumentos;
	
	public InvocacionFuncionSent(int linea, int columna, Identificador identificador, List<Expresion> argumentos) {
		super(linea, columna);
		this.identificador = identificador;
		this.argumentos = argumentos;
	}

	@Override
	public String toString() {
		return "InvocacionFuncionSent [identificador=" + identificador + ", argumentos=" + argumentos + "]";
	}

}
