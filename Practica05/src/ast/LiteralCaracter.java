package ast;

public class LiteralCaracter extends AbstractNodoAST implements Expresion {

	private char valor;

	public LiteralCaracter(int linea, int columna, char valor) {
		super(linea, columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LiteralCaracter [valor=" + valor + "]";
	}
	
}