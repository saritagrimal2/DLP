package ast;

import ast.tipo.Tipo;
import visitor.Visitor;

public class DefVariable extends NodoASTAbstracto implements Definicion, Sentencia{

	private String identificador;
	private Tipo tipo;
	private int ambito=0;
	private int offset=0;
	
	public DefVariable(int linea, int columna, String identificador, Tipo tipo) {
		super(linea, columna);
		this.identificador = identificador;
		this.tipo = tipo;
	}

	@Override
	public String getIdentificador() {
		return identificador;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}
	
	@Override
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
		
	}

	@Override
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
		
	}

	public int getAmbito() {
		return ambito;
	}

	public void setAmbito(int ambito) {
		this.ambito = ambito;
	}
	

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	@Override
	public String toString() {
		return "DefVariable [identificador=" + identificador + ", tipo=" + tipo + ", ambito=" + ambito + ", offset="
				+ offset + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}


}
