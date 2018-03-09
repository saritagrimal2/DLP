package ast;

import java.util.List;

public class sentenciaIf extends AbstractNodoAST implements Sentencia {
	
	private Expresion expresion;
	private List<Sentencia> sentencias;
	private List<Sentencia> sentenciaElse;

	public sentenciaIf(int linea, int columna, Expresion expresion,List<Sentencia> sentencias,List<Sentencia> sentenciaElse  ) {
		super(linea, columna);
		this.expresion = expresion;
		this.sentencias = sentencias;
		this.sentenciaElse = sentenciaElse;
	}

	@Override
	public String toString() {
		return "sentenciaIf [expresion=" + expresion + ", sentencias=" + sentencias + ", sentenciaElse=" + sentenciaElse
				+ "]";
	}

}
