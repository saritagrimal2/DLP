package ast;

import java.util.List;

import visitor.Visitor;

public class InvocacionFuncionExp extends NodoASTAbstracto implements Expresion {

	private Identificador identificador;
	private List<Expresion> argumentos;

	public InvocacionFuncionExp(int linea, int columna, Identificador identificador, List<Expresion> argumentos) {
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
		return "InvocacionFuncionExp [identificador=" + identificador + ", argumentos=" + argumentos + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
