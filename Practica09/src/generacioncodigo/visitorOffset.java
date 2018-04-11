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

	// Offset de variables globales
	// Tamaño de nodos de ast que se visitan (Atributo)
	// Si cambiamos el orden, los offsets van a cambiar
	// Preguntamos a cada definicion de variable el numBytes de cada tipo
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
			//Maaaaaaaaaaaaaaaaaaaaal 
			
			TipoFuncion f = (TipoFuncion) param;
			
			for (DefVariable d: f.getArgumentos()) {
				if (d.getIdentificador().equals(v.getIdentificador())) {
					// Parametros
					v.setOffset(offsetParam);
					System.out.println("Nombre: " + v.getIdentificador() + " offset: " + v.getOffset());
				}
			}
			// Variables locales
			offsetLocal -= v.getTipo().numeroBytes();
			v.setOffset(offsetLocal);
			System.out.println("Nombre: " + v.getIdentificador() + " offset: " + v.getOffset());
		}
		return null;
	}

	@Override
	public Object visitar(DefFuncion f, Object param) {
		for (Sentencia s : f.getSentencias())
			s.aceptar(this, f.getTipo());
		f.getTipo().aceptar(this, param);

		offsetLocal = 0; // Hay que resetear el valor cuando visitas funcion

		return null;
	}

	@Override
	public Object visitar(InvocacionFuncionSent f, Object param) {
		f.getIdentificador().aceptar(this, param);
		for (Expresion e : f.getArgumentos()) {
			e.aceptar(this, param);
		}
		return null;
	}

	@Override
	public Object visitar(InvocacionFuncionExp f, Object param) {
		f.getIdentificador().aceptar(this, param);
		for (Expresion e : f.getArgumentos()) {
			e.aceptar(this, param);
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
