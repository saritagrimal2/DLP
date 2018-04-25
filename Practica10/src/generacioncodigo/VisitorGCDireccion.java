package generacioncodigo;

import ast.DefVariable;
import ast.Identificador;

public class VisitorGCDireccion extends AbstractGC{
	
	private GC gc;

	public VisitorGCDireccion(GC gc) {
		this.gc = gc;
	}

	@Override
	public Object visitar(Identificador i, Object param) {
		
		DefVariable dv = (DefVariable) i.getDefinicion();
		if(dv.getOffset() == 0) {
			gc.pusha(dv.getOffset());
		}else {
			gc.pushabp();
			gc.push(i.getTipo(), dv.getOffset());
			gc.add(i.getTipo());
		}

		return null;
	}

}
