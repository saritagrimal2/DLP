package generacioncodigo;

import ast.Cast;
import ast.DefFuncion;
import ast.DefVariable;
import ast.Expresion;
import ast.InvocacionFuncionExp;
import ast.InvocacionFuncionSent;
import ast.Sentencia;
import ast.tipo.TipoFuncion;
import semantico.VisitorAbstracto;

public class visitorOffset extends VisitorAbstracto {

	private int offsetGlobal = 0;
	private int offsetLocal = 0;
	private int offsetParam = 4; // Reserva memoria para unas cosas de la funcion

	@Override
	public Object visitar(DefVariable v, Object param) {
		v.getTipo().aceptar(this, param);

		// Variables globales
		if (v.getAmbito() == 0) {
			v.setOffset(offsetGlobal);
			offsetGlobal += v.getTipo().numeroBytes();

			System.out.println("Nombre: " + v.getIdentificador() + " offset: " + v.getOffset());

		} else {
			
			if ((boolean)param == false) {
				//Parametros
				v.setOffset(offsetParam);
				offsetParam += v.getTipo().numeroBytes();
				System.out.println("Nombre: " + v.getIdentificador() + " offset: " + v.getOffset());
			} else {
				// Variables locales
				offsetLocal -= v.getTipo().numeroBytes();
				v.setOffset(offsetLocal);
				System.out.println("Nombre: " + v.getIdentificador() + " offset: " + v.getOffset());
			}
		}
		return null;
	}

	@Override
	public Object visitar(DefFuncion f, Object param) {
		for (Sentencia s : f.getSentencias())
			s.aceptar(this, true);
		f.getTipo().aceptar(this, param);

		offsetLocal = 0; // Hay que resetear el valor cuando visitas funcion

		return null;
	}
	
	@Override
	public Object visitar(TipoFuncion f, Object param) {
		f.getTipoRetorno().aceptar(this, param);
		
		for (int i = f.getArgumentos().size()-1; i>=0; i--) {
			f.getArgumentos().get(i).aceptar(this, false);
		}
		
		offsetParam = 0;
		return null;
	}

	@Override
	public Object visitar(InvocacionFuncionSent f, Object param) {
		f.getIdentificador().aceptar(this, param);
		for (Expresion e : f.getArgumentos()) {
			e.aceptar(this, param);
		}
		offsetParam = 0;
		return null;
	}

	@Override
	public Object visitar(InvocacionFuncionExp f, Object param) {
		f.getIdentificador().aceptar(this, param);
		for (Expresion e : f.getArgumentos()) {
			e.aceptar(this, param);
		}
		offsetParam = 0;
		return null;
	}

	@Override
	public Object visitar(Cast c, Object param) {
		c.getExpresion().aceptar(this, param);
		c.getTipoCast().aceptar(this, param);
		return null;
	}

}
