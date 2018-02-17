package ast;

import java.util.ArrayList;
import java.util.List;

public class Programa extends AbstractNodoAST{
	
	private List<Sentencia> sentencias = new ArrayList<Sentencia>();

	public Programa(int linea, int columna, List<Sentencia> sentencias) {
		super(linea, columna);
		this.sentencias = sentencias;
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	public void setSentencias(List<Sentencia> sentencias) {
		this.sentencias = sentencias;
	}

}
