package generacioncodigo;

import ast.DefVariable;
import ast.Identificador;
import ast.tipo.TipoEntero;

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
			gc.push(TipoEntero.getInstance(), dv.getOffset());
			gc.add(TipoEntero.getInstance());
		}

		return null;
	}

}
