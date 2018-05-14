package ast.tipo;

import java.util.List;

import ast.NodoASTAbstracto;

public abstract class TipoAbstracto extends NodoASTAbstracto implements Tipo {

	public TipoAbstracto(int linea, int columna) {
		super(linea, columna);
	}
	
	@Override
	public Tipo aritmetica(Tipo expresion) {
		return null;
	}
	
	@Override
	public Tipo aritmetica() {
		return null;
	}

	@Override
	public Tipo comparacion(Tipo expresion) {
		return null;
	}

	@Override
	public Tipo logica() {
		return null;
	}

	@Override
	public Tipo logica(Tipo expresion) {
		return null;
	}

	@Override
	public Tipo cast(Tipo expresion) {
		return null;
	}

	@Override
	public Tipo corchetes(Tipo indice) {
		return null;
	}

	@Override
	public Tipo punto(String campo) {
		return null;
	}

	@Override
	public Tipo parentises(List<Tipo> tipos) {
		return null;
	}

	@Override
	public Tipo equivalente(Tipo tipo) {
		return null;
	}

	@Override
	public boolean esLogico() {
		return false;
	}
	
	@Override
	public int numeroBytes() {
		return 0;
	}
	
	@Override
	public char sufijo() {
		return 0;
	}
	
	@Override
	public Campo get(String nombre) {
		return null;
	}

}
