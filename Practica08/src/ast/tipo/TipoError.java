package ast.tipo;

import manejadorerrores.ME;
import visitor.Visitor;

public class TipoError extends TipoAbstracto{
	
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
