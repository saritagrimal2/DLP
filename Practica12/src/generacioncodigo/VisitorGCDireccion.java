package generacioncodigo;

import ast.AccesoArray;
import ast.AccesoCampo;
import ast.DefVariable;
import ast.Identificador;
import ast.tipo.TipoEntero;

public class VisitorGCDireccion extends AbstractGC{
	
	private VisitorGCValor valor;
	private GC gc;

	public VisitorGCDireccion(VisitorGCValor valor, GC gc) {
		this.valor = valor;
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
	
	@Override
	public Object visitar(AccesoArray a, Object param) {
		a.getExp1().aceptar(this, param);
		a.getExp2().aceptar(valor, param);
		gc.push(TipoEntero.getInstance(), a.getTipo().numeroBytes());
		gc.mul(TipoEntero.getInstance());
		gc.add(TipoEntero.getInstance()); 
		
		return null;
	}
	
	@Override
	public Object visitar(AccesoCampo c, Object param) {
		c.getExpresion().aceptar(this, param);
		int offsetCampo=0;
		gc.push(TipoEntero.getInstance(),offsetCampo);
		gc.add(TipoEntero.getInstance());
		return null;
	}

}
