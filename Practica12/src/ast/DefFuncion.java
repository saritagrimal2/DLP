package ast;

import java.util.List;

import ast.tipo.Tipo;
import ast.tipo.TipoFuncion;
import visitor.Visitor;

public class DefFuncion extends NodoASTAbstracto implements Definicion {

	private String identificador;
	private Tipo tipo;
	private List<Sentencia> sentencias;
	private int ambito=0;

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
	
	@Override
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
		
	}

	@Override
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
		
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

	@Override
	public int getAmbito() {
		return this.ambito;
	}

	@Override
	public void setAmbito(int ambito) {
		this.ambito = ambito;
	}

	public int numeroBytesLocales() {
		int local = 0;
		for (Sentencia s : getSentencias()) {
			if (s instanceof DefVariable) {
				local += ((DefVariable) s).getTipo().numeroBytes();
			}
		}
		
		return local;
	}

	public int  numeroBytesParam() {
		int p = 0;
		for (DefVariable v: ((TipoFuncion)getTipo()).getArgumentos()) {
			p += v.getTipo().numeroBytes();
		}
		return p;
	}



}
