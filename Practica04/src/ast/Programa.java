package ast;

import java.util.ArrayList;
import java.util.List;

public class Programa extends AbstractNodoAST{
	
	private List<Sentencia> definiciones;

	public Programa(int linea, int columna, List<Definicion> definiciones, 
			List<DefVarible> variblesLocales, List<Sentencia> cuerpoMain) {
		super(linea, columna);
		this.definiciones = definiciones;
		DefFuncion mi_main = DefFuncion(linea, columna "main", new TipoFuncion(TipoVoid.getInstance(), new ArrayList<DefVariable>()));
		this.definiciones.add(mi_main);
	}

	public List<Sentencia> getDefiniciones() {
		return definiciones;
	}

	public void setDefiniciones(List<Sentencia> sentencias) {
		this.definiciones = sentencias;
	}

}
