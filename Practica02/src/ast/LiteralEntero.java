package ast;

public class LiteralEntero extends AbstractNodoAST implements Expresion {
	
	private int valor;

	public LiteralEntero(int linea, int columna, int valor) {
		super(linea, columna);
		this.setValor(valor);
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public String toString() {
		return String.valueOf(this.valor);
	}

}
