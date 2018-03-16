package ast.tipo;

import ast.NodoASTAbstracto;
import visitor.Visitor;

public class Campo extends NodoASTAbstracto{
	
	private String identificador;
	private Tipo tipo;
	
	public Campo(int linea, int columna, String identificador, Tipo tipo) {
		super(linea, columna);
		this.identificador = identificador;
		this.tipo = tipo;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campo other = (Campo) obj;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Campo [identificador=" + identificador + ", tipo=" + tipo + "]";
	}
	
	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}

}
