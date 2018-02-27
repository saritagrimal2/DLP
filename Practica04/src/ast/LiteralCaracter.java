package ast;

public class LiteralCaracter extends AbstractNodoAST implements Expresion {

	private String valor;

	public LiteralCaracter(int linea, int columna, String valor) {
		super(linea, columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LiteralCaracter [valor=" + valor + "]";
	}
	
}