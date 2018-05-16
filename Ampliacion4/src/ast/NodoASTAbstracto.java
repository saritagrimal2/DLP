package ast;

public abstract class NodoASTAbstracto implements NodoAST{
	
	private int linea;
	private int columna;
	
	public NodoASTAbstracto(int linea, int columna) {
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
