package semantico;

import ast.DefFuncion;
import ast.DefVariable;
import ast.Sentencia;
import ast.tipo.TipoError;
import tablasimbolos.TablaSimbolos;

public class VisitorIdentificador extends VisitorAbstracto{
	
	private TablaSimbolos tablaSimbolos;
	
	public VisitorIdentificador() {
		tablaSimbolos = new TablaSimbolos();
	}
	

	@Override
	public Object visitar(DefFuncion f, Object param) {
		
		if(!tablaSimbolos.insertar(f)) {
			new TipoError(0,0, "Funcion ya declarada");
		}
			
		tablaSimbolos.set();
		for (Sentencia s : f.getSentencias())
			s.aceptar(this, param);
		f.getTipo().aceptar(this, param);
		tablaSimbolos.reset();
		
		return null;
	}

	@Override
	public Object visitar(DefVariable v, Object param) {
		if(!tablaSimbolos.insertar(v)) {
			new TipoError(0,0, "Variable ya declarada");
		}
		v.getTipo().aceptar(this, param);
		
		return null;
	}


}
