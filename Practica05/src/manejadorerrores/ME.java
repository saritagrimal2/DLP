package manejadorerrores;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import ast.AbstractNodoAST;
import ast.tipo.TipoError;

public class ME extends AbstractNodoAST{
	
	private List<TipoError> errores = new ArrayList<>();
	
	private static ME instance = new ME();

	private ME() {
		super(0,0);
	}

	public static ME getME() {
		if (instance == null)
			instance = new ME();
		return instance;
	}
	
	public boolean huboErrores() {
		return !errores.isEmpty();
	}


	public void mostrarErrores(PrintStream pS) {
		for (TipoError error: errores) {
			pS.println(error.toString());
		}
	}
	
	public void añadirErrores(TipoError error) {
		errores.add(error);
	}
	
}
