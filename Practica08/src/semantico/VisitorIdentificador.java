package semantico;

import ast.DefFuncion;
import ast.DefVariable;
import ast.Identificador;
import ast.Sentencia;
import ast.tipo.TipoError;
import tablasimbolos.TablaSimbolos;

public class VisitorIdentificador extends VisitorAbstracto{
	
	private TablaSimbolos tablaSimbolos;
	
	public VisitorIdentificador() {
		tablaSimbolos = new TablaSimbolos();
	}
	
	
	@Override
	public Object visitar(Identificador i, Object param) {
		if (tablaSimbolos.buscar(i.getNombre()) == null) {
			new TipoError(i.getLinea(), i.getColumna(), "[Identificador] Variable o función no definida.");
		}
		i.setDefinicion(tablaSimbolos.buscar(i.getNombre()));
		return null;
	}
	
	@Override
	public Object visitar(DefFuncion f, Object param) {
		
		if(!tablaSimbolos.insertar(f)) {
			new TipoError(f.getLinea(), f.getColumna(), "[DefFuncion] Función ya declarada.");
		}
			
		tablaSimbolos.set();
		f.getTipo().aceptar(this, param);
		for (Sentencia s : f.getSentencias())
			s.aceptar(this, param);
		tablaSimbolos.reset();
		
		return null;
	}

	@Override
	public Object visitar(DefVariable v, Object param) {
		if(!tablaSimbolos.insertar(v)) {
			new TipoError(v.getLinea(),v.getColumna(), "[DefVarible] Variable ya declarada.");
		}
		v.getTipo().aceptar(this, param);
		
		return null;
	}


}
