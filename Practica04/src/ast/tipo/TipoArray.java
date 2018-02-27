package ast.tipo;

public class TipoArray implements Tipo {
	
	private int tama�o;
	private Tipo tipo;

	public TipoArray(int tama�o, Tipo tipo) {
		this.tama�o = tama�o;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoArray [tama�o=" + tama�o + ", tipo=" + tipo + "]";
	}

}
