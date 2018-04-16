package ast.tipo;

import java.util.List;

import ast.NodoAST;

public interface Tipo extends NodoAST {

	public Tipo aritmetica(Tipo expresion);
	public Tipo aritmetica(); // Si es real devulvo real y asi con todos
	public Tipo comparacion(Tipo expresion);
	public Tipo logica();
	public Tipo logica(Tipo expresion);

	public Tipo cast(Tipo expresion);
	public Tipo corchetes(Tipo indice); //Solo admitimos enteros. Es accesoArray
	public Tipo punto(String campo); //Es AccesoCampo
	public Tipo parentises(List<Tipo> tipos); //InvocacionFuncionExpr Devolvemos el tipo de retorno del tipoFuncin
	
	public Tipo equivalente(Tipo tipo); //Asignacion y tambien en la lectura y return se puede llamar tambien
	//promocionaA y se puede usar en mas sitios, eso seria para la implicita
	
	public boolean esLogico(); //Solo interviene el objeto impificito, en sentencia if y while.
	//Por defecto vamos a retornar falso, en tipo entero y tipo caracter devolvemos true en el resto false.
	
	public int numeroBytes();
}
