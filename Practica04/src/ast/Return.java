package ast;

public class Return extends AbstractNodoAST implements Sentencia{

	private Expresion expresion;

	public Return(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Return [expresion=" + expresion + "]";
	}

}
