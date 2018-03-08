package ast;

import java.util.List;

public class sentenciaElse extends AbstractNodoAST implements Sentencia{
	
	private List<Sentencia> sentencias;

	public sentenciaElse(int linea, int columna, List<Sentencia> sentencias) {
		super(linea, columna);
		this.sentencias = sentencias;
	}

	@Override
	public String toString() {
		return "sentenciaElse [sentencias=" + sentencias + "]";
	}
}
