package generacionCodigo;

import ast.Cast;
import ast.DefVariable;
import semantico.VisitorAbstracto;

public class visitorOffset extends VisitorAbstracto{
	
	//Offset de variables globales
	//Tamaño de nodos de ast que se visitan (Atributo)
	// Si cambiamos el orden, los offsets van a cambiar
	//Preguntamos a cada definicion de variable el numBytes de cada tipo
	private int tamGlobal = 0;
	
	@Override
	public Object visitar(DefVariable v, Object param) {
		v.getTipo().aceptar(this,param);
		
		//Variables globales
		if (v.getAmbito() == 0) {
			v.setOffset(tamGlobal);
			tamGlobal += v.getTipo().numeroBytes();
		} else {
			int tamLocal = 0;  //Hay que resetear el valor cuando visitas funcion
			//Variables locales 
			tamLocal -= v.getTipo().numeroBytes();
			v.setOffset(tamLocal);
		}
		
		
		
		
		return null;
	}
	
	@Override
	public Object visitar(Cast c, Object param) {
		c.getExpresion().aceptar(this, param);
		c.getTipo().aceptar(this, param);
		return null;
	}
	
	

}
