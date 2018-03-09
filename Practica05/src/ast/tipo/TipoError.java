package ast.tipo;

import ast.AbstractNodoAST;
import manejadorerrores.ME;

public class TipoError extends AbstractNodoAST implements Tipo{
	
	private String mensaje;

	public TipoError(int linea, int columna, String mensaje) {
		super(linea, columna);
		this.mensaje = mensaje;
		ME.getME().añadirErrores(this);
	}

	@Override
	public String toString() {
		return "Error: " + mensaje;
	}

}
