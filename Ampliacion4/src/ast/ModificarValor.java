package ast;

import visitor.Visitor;

public class ModificarValor extends NodoASTAbstracto implements Sentencia {

	private Expresion expresion;
	private String operador;

	public ModificarValor(int linea, int columna, Expresion expresion, String operador) {
		super(linea, columna);
		this.expresion = expresion;
		this.operador = operador;
	}

	public Expresion getExpresion() {
		return expresion;
	}


	public String getOperador() {
		return operador;
	}

	@Override
	public String toString() {
		return "ModificarValor [exp=" + expresion + ", operador=" + operador + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}