package ast.tipo;

import java.util.List;

import ast.DefVariable;
import visitor.Visitor;

public class TipoFuncion extends TipoAbstracto {
	
	private Tipo tipoRetorno; 
	private List<DefVariable> argumentos;
	
	public TipoFuncion(int linea, int columna, Tipo tipoRetorno, List<DefVariable> argumentos) {
		super(linea, columna);
		this.tipoRetorno = tipoRetorno;
		this.argumentos = argumentos;
	}
	
	public Tipo getTipoRetorno() {
		return tipoRetorno;
	}

	public List<DefVariable> getArgumentos() {
		return argumentos;
	}

	@Override
	public String toString() {
		return "TipoFuncion [tipoRetorno=" + tipoRetorno + ", argumentos=" + argumentos + "]";
	}
	
	@Override
	public Object aceptar(Visitor visitor, Object param) {
		return visitor.visitar(this, param);
	}
	
	@Override
	public Tipo parentises(List<Tipo> tipos) {
		
		for (int i=0; i< tipos.size(); i++) {
			if (tipos.get(i)!= argumentos.get(i).getTipo()) {
				return new TipoError(0, 0, "El tipo del parametro no es compatible con el tipo del argumento");
			}
		}
		return tipoRetorno;
	}
}
