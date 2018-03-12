package ast;

import java.util.List;

import visitor.Visitor;

public class InvocacionFuncionSent extends AbstractNodoAST implements Sentencia {

	private Identificador identificador;
	private List<Expresion> argumentos;

	public InvocacionFuncionSent(int linea, int columna, Identificador identificador, List<Expresion> argumentos) {
		super(linea, columna);
		this.identificador = identificador;
		this.argumentos = argumentos;
	}

	public Identificador getIdentificador() {
		return identificador;
	}

	public List<Expresion> getArgumentos() {
		return argumentos;
	}

	@Override
	public String toString() {
		return "InvocacionFuncionSent [identificador=" + identificador + ", argumentos=" + argumentos + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
