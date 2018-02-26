package ast;

public abstract class AbstractNodoAST implements NodoAST{
	
	private int linea;
	private int columna;
	
	public AbstractNodoAST(int linea, int columna) {
		this.linea = linea;
		this.columna = columna;
	}
	
	@Override
	public int getLinea() {
		return linea;
	}
	
	@Override
	public int getColumna() {
		return columna;
	}

}
