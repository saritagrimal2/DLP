package ast.tipo;

public class TipoArray implements Tipo {
	
	private int tamaño;
	private Tipo tipo;

	public TipoArray(int tamaño, Tipo tipo) {
		this.tamaño = tamaño;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoArray [tamaño=" + tamaño + ", tipo=" + tipo + "]";
	}

}
