package ast.tipo;

import ast.NodoASTAbstracto;
import manejadorerrores.ME;
import visitor.Visitor;

public class TipoError extends NodoASTAbstracto implements Tipo{
	
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

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}
	
}
