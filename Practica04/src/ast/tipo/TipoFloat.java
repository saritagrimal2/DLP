package ast.tipo;


public class TipoFloat implements Tipo {

	private static TipoFloat instance = new TipoFloat();

	private TipoFloat() {
	}

	public static TipoFloat getInstance() {
		if (instance == null)
			instance = new TipoFloat();
		return instance;
	}


	@Override
	public String toString() {
		return "TipoFloat []";
	}
}
