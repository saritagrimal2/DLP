package manejadorerrores;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import ast.tipo.TipoError;

public class ME{
	
	private List<TipoError> errores = new ArrayList<>();
	
	private static ME instance = new ME();

	private ME() {
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
