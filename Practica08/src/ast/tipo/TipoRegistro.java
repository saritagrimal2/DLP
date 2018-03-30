package ast.tipo;

import java.util.List;

import visitor.Visitor;

public class TipoRegistro extends TipoAbstracto {
	
	List<Campo> campos;

	public TipoRegistro(int linea, int columna, List<Campo> campos) {
		super(linea, columna);
		this.campos = campos;
	}
	
	public List<Campo> getCampos() {
		return campos;
	}

	@Override
	public String toString() {
		return "TipoRegistro [campos=" + campos + "]";
	}

	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}
	
	@Override
	public Tipo punto(String campo) {
		for (Campo c: campos) {
			if (c.getIdentificador().equals(campo)) {
				return c.getTipo();
			}
		}
		return new TipoError(0,0,"El identificador del campo no existe");
	}
}
