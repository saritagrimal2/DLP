package tablasimbolos;

import java.util.*;
import ast.Definicion;

public class TablaSimbolos {
	
	private int ambito=0;
	private List<Map<String,Definicion>> tabla;
	
	public TablaSimbolos()  {
		tabla = new ArrayList<Map<String,Definicion>>();
		tabla.add(new HashMap<String, Definicion>());
	}

	/**
	 * Se crea nuevo ambito con esa nueva variable
	 * Cuando se visita definicion de funcion
	 */
	public void set() {
		tabla.add(new HashMap<String, Definicion>());
		this.ambito++;
	}
	
	public void reset() {
		tabla.remove(this.ambito);
		this.ambito--;
	}
	
	
	public boolean insertar(Definicion simbolo) {
		if (simbolo != null && buscarAmbitoActual(simbolo.getIdentificador()) == null) {
			this.tabla.get(this.ambito).put(simbolo.getIdentificador(), simbolo);
			simbolo.setAmbito(ambito);
			return true;
		}
		return false;
	}
	
	/**
	 * Busca en las varibles locales y luego en las locales
	 * Busca la variable con su definicion
	 * sino error, uso de variable no definida.
	 * 
	 * @param id
	 * @return
	 */
	public Definicion buscar(String id) {
		for (int i= this.ambito; i>=0; i--) {
			if (this.tabla.get(i).containsKey(id)) {
				return this.tabla.get(i).get(id);
			}
		}
		return null;
	}

	/**
	 * Busca el ambito actual
	 * @param id
	 * @return Definicion
	 */
	public Definicion buscarAmbitoActual(String id) {
		return this.tabla.get(this.ambito).get(id);
	}
}
