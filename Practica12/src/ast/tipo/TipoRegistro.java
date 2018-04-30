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
		return null;
	}
	
	@Override
	public int numeroBytes() {
		int acumulado = 0;
		for (Campo c: campos) {
			acumulado += c.getTipo().numeroBytes();
		}
		return acumulado;
	}
	
	@Override
	public Campo get(String nombre) {
		for (Campo c: campos) {
			if (nombre.equals(c.getIdentificador())) {
				return c;
			}
		}
		return null;
	}
}
