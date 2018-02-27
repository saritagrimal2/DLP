package ast;

public class LiteralReal extends AbstractNodoAST implements Expresion {

	private float valor;

	public LiteralReal(int linea, int columna, float valor) {
		super(linea, columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LiteralReal [valor=" + valor + "]";
	}
	
}