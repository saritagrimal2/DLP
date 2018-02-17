
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import java.util.List;
import java.util.ArrayList;


public class IntrospectorDemo  {
	public static void main(String[] args) {
		IntrospectorModel model = new IntrospectorModel("Raíz", new NodoRaiz());
		new IntrospectorTree("Árbol", model);
	}
}

class NodoRaiz {
	private Nodo hijo = new Nodo("Hijo1");
	private List<String> hijos = new ArrayList<String>();
	private int hijoEntero;
	NodoRaiz()
	{
		int i;
		for(i=2; i<=10; i++)
			this.hijos.add("Hijo"+i);
			
		this.hijoEntero = i;
	}
}

class Nodo {
	private String nombre;
	
	Nodo(String nombre){
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
}