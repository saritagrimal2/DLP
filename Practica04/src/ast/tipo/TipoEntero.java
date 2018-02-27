package ast.tipo;


public class TipoEntero implements Tipo {

	private static TipoEntero instance = new TipoEntero();

	private TipoEntero() {
	}

	public static TipoEntero getInstance() {
		if (instance == null)
			instance = new TipoEntero();
		return instance;
	}

	@Override
	public String toString() {
		return "TipoEntero []";
	}
}
