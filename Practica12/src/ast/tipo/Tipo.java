package ast.tipo;

import java.util.List;

import ast.NodoAST;

public interface Tipo extends NodoAST {

	public Tipo aritmetica(Tipo expresion);

	public Tipo aritmetica();

	public Tipo comparacion(Tipo expresion);

	public Tipo logica();

	public Tipo logica(Tipo expresion);

	public Tipo cast(Tipo expresion);

	public Tipo corchetes(Tipo indice);

	public Tipo punto(String campo);

	public Tipo parentises(List<Tipo> tipos);

	public Tipo equivalente(Tipo tipo);

	public boolean esLogico();

	public int numeroBytes();

	public char sufijo();
	
	public Campo get(String identificador);
}
