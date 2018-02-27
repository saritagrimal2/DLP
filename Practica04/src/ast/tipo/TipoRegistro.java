package ast.tipo;

import java.util.List;

public class TipoRegistro implements Tipo {
	
	List<Campo> campos;

	public TipoRegistro(List<Campo> campos) {
		this.campos = campos;
	}

	@Override
	public String toString() {
		return "TipoRegistro [campos=" + campos + "]";
	}

}
