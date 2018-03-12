package ast;

import java.util.List;

import ast.tipo.Tipo;
import visitor.Visitor;

public class DefFuncion extends AbstractNodoAST implements Definicion {

	private String identificador;
	private Tipo tipo;
	private List<Sentencia> sentencias;

	public DefFuncion(int linea, int columna, String identificador, Tipo tipo, List<Sentencia> sentencias) {
		super(linea, columna);
		this.identificador = identificador;
		this.tipo = tipo;
		this.sentencias = sentencias;
	}

	@Override
	public String getIdentificador() {
		return identificador;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}

	@Override
	public String toString() {
		return "DefFuncion [identificador=" + identificador + ", tipo=" + tipo + ", sentencias=" + sentencias + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
