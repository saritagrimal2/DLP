package ast.tipo;

import java.util.List;

import ast.NodoASTAbstracto;

public abstract class TipoAbstracto extends NodoASTAbstracto implements Tipo {

	public TipoAbstracto(int linea, int columna) {
		super(linea, columna);
	}
	
	@Override
	public Tipo aritmetica(Tipo expresion) {
		return new TipoError(0, 0, "El tipo no es tipo entero, double");
	}
	
	@Override
	public Tipo aritmetica() {
		return new TipoError(0, 0, "El tipo no es tipo entero, double");
	}

	@Override
	public Tipo comparacion(Tipo expresion) {
		return new TipoError(0, 0, "El tipo no es tipo entero o caracter");
	}

	@Override
	public Tipo logica() {
		return new TipoError(0, 0, "El tipo no es tipo entero o caracter");
	}

	@Override
	public Tipo logica(Tipo expresion) {
		return new TipoError(0, 0, "El tipo no es tipo entero o caracter");
	}

	@Override
	public Tipo cast(Tipo expresion) {
		return new TipoError(0, 0, "El tipo no es tipo entero, double o caracter");
	}

	@Override
	public Tipo corchetes(Tipo indice) {
		return new TipoError(0, 0, "No se puede acceder al array");
	}

	@Override
	public Tipo punto(String campo) {
		return new TipoError(0, 0, "Se debe acceder al tipo registro");
	}

	@Override
	public Tipo parentises(List<Tipo> tipos) {
		return new TipoError(0, 0, "Se debe acceder al tipo funcion");
	}

	@Override
	public Tipo equivalente(Tipo tipo) {
		return new TipoError(0, 0, "El tipo no es tipo entero, double o caracter");
	}

	@Override
	public boolean esLogico() {
		return false;
	}

}
