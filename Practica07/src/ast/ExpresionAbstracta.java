package ast;


public abstract class ExpresionAbstracta extends NodoASTAbstracto implements Expresion{

	public ExpresionAbstracta(int linea, int columna) {
		super(linea, columna);
	}

	private boolean lValue;

	@Override
	public boolean getLValue() {
		return lValue;
	}

	@Override
	public void setLValue(boolean lValue) {
		this.lValue = lValue;
	}

}
