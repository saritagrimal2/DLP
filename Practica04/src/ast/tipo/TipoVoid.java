package ast.tipo;


public class TipoVoid implements Tipo {

	private static TipoVoid instance = new TipoVoid();

	private TipoVoid() {
	}

	public static TipoVoid getInstance() {
		if (instance == null)
			instance = new TipoVoid();
		return instance;
	}


	@Override
	public String toString() {
		return "TipoVoid []";
	}
}
