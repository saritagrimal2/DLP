package generacioncodigo;

import ast.DefFuncion;
import ast.DefVariable;
import ast.Sentencia;
import ast.tipo.Campo;
import ast.tipo.TipoFuncion;
import ast.tipo.TipoRegistro;
import semantico.VisitorAbstracto;

public class visitorOffset extends VisitorAbstracto {

	private int offsetGlobal = 0;
	private int offsetLocal = 0;
	private int offsetParam = 4; 

	@Override
	public Object visitar(DefVariable v, Object param) {
		v.getTipo().aceptar(this, param);

		// Variables globales
		if (v.getAmbito() == 0) {
			v.setOffset(offsetGlobal);
			offsetGlobal += v.getTipo().numeroBytes();
		} else {
			
			if ((boolean)param) {
				//Parametros
				v.setOffset(offsetParam);
				offsetParam += v.getTipo().numeroBytes();
			} else {
				// Variables locales
				offsetLocal -= v.getTipo().numeroBytes();
				v.setOffset(offsetLocal);
			}
		}
		return null;
	}

	@Override
	public Object visitar(DefFuncion f, Object param) {
		offsetLocal = 0; 
		offsetParam = 4;
		for (Sentencia s : f.getSentencias())
			s.aceptar(this, false);
		f.getTipo().aceptar(this, param);
		return null;
	}
	
	@Override
	public Object visitar(TipoFuncion f, Object param) {
		f.getTipoRetorno().aceptar(this, param);
		
		for (int i = f.getArgumentos().size()-1; i>=0; i--) {
			f.getArgumentos().get(i).aceptar(this, true);
		}
		
		return null;
	}
	

	@Override
	public Object visitar(TipoRegistro r, Object param) {
		int offsetCampo = 0;
		for (Campo c : r.getCampos()) {
			c.aceptar(this, param);
			c.setOffset(offsetCampo);
			offsetCampo+= c.getTipo().numeroBytes();
		}
		return null;
	}


}
