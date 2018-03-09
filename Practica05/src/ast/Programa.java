package ast;

import java.util.ArrayList;
import java.util.List;

import ast.tipo.TipoFuncion;
import ast.tipo.TipoVoid;

public class Programa extends AbstractNodoAST{
	
	private List<Definicion> definiciones;

	public Programa(int linea, int columna, List<Definicion> definiciones,
			List<Sentencia> sentencias) {
		super(linea, columna);
		this.definiciones = definiciones;
		
		DefFuncion mi_main = new DefFuncion (linea, columna, "main", 
				new TipoFuncion(linea, columna, TipoVoid.getInstance(), new ArrayList<DefVariable>()), sentencias);
		
		this.definiciones.add(mi_main);
	}

	@Override
	public String toString() {
		return "Programa [definiciones=" + definiciones + "]";
	}

}
