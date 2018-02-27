package ast.tipo;


public class TipoCaracter implements Tipo {
	
	private static TipoCaracter instance = new TipoCaracter();

	private TipoCaracter() {
	}

	public static TipoCaracter getInstance() {
		if (instance == null)
			instance = new TipoCaracter();
		return instance;
	}

	@Override
	public String toString() {
		return "TipoCaracter []";
	}

}
