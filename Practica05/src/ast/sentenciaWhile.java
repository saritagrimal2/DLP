package ast;

import java.util.List;

public class sentenciaWhile extends AbstractNodoAST implements Sentencia{
	
	private Expresion expresion;
	private List<Sentencia> sentencias;

	public sentenciaWhile(int linea, int columna, Expresion expresion,List<Sentencia> sentencias ) {
		super(linea, columna);
		this.expresion = expresion;
		this.sentencias = sentencias;
	}

	@Override
	public String toString() {
		return "sentenciaWhile [expresion=" + expresion + ", sentencias=" + sentencias + "]";
	}


}
