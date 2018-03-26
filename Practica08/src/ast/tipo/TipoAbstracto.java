package ast.tipo;

import java.util.List;

import ast.NodoASTAbstracto;

public abstract class TipoAbstracto extends NodoASTAbstracto implements Tipo {

	public TipoAbstracto(int linea, int columna) {
		super(linea, columna);
	}
	
	@Override
	public Tipo aritmetica(Tipo expresion) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Tipo aritmetica() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tipo comparacion(Tipo expresion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tipo comparacion() {
		return this;
	}

	@Override
	public Tipo logica(Tipo expresion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tipo cast(Tipo expresion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tipo corchetes(Tipo indice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tipo punto(String campo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tipo parentises(List<Tipo> tipos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tipo equivalente(Tipo tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean esLogico() {
		// TODO Auto-generated method stub
		return false;
	}

}
