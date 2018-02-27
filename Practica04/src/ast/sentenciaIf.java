package ast;

import java.util.List;

public class sentenciaIf extends AbstractNodoAST implements Sentencia {
	
	private Expresion expresion;
	private List<Sentencia> sentencias;

	public sentenciaIf(int linea, int columna, Expresion expresion,List<Sentencia> sentencias ) {
		super(linea, columna);
		this.expresion = expresion;
		this.sentencias = sentencias;
	}

	@Override
	public String toString() {
		return "sentenciaIf [expresion=" + expresion + ", sentencias=" + sentencias + "]";
	}

}
