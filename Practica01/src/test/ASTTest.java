package test;

import introspector.view.IntrospectorTree;
import introspector.model.IntrospectorModel;

import java.util.*;

import ast.Aritmetica;
import ast.Asignacion;
import ast.Escritura;
import ast.Expresion;
import ast.Lectura;
import ast.LiteralEntero;
import ast.MenosUnario;
import ast.NodoAST;
import ast.Programa;
import ast.Sentencia;
import ast.Variable;

/**
 * Pequeño programa de prueba.<br/>
 * Diseño de Lenguajes de Programación<br/>
 * Escuela de Ingeniería Informática<br/>
 * Universidad de Oviedo<br/>
 * 
 * @author Francisco Ortin
 */
public class ASTTest {

	/**
	 * Prueba de creación de un AST.
	 * El programa de entrada es: 
	 *   read a,b; 
	 *   a = (-b+3)*c/2; 
	 *   write a,c*2;
	 */
	private static NodoAST crearArbol() {
		List<Sentencia> sentencias = new ArrayList<Sentencia>();
		
		// * Primera linea
		Expresion expresion = new Variable(1, 6, "a");
		sentencias.add(new Lectura(1, 6, expresion));
		
		expresion = new Variable(1, 8, "b");
		sentencias.add(new Lectura(1, 8, expresion));
		// * Segunda Línea
		Sentencia sentencia = new Asignacion(2, 3, 
				new Variable(2, 1, "a"),
				new Aritmetica(2, 11,
						new Aritmetica(2, 8, 
								new MenosUnario(2, 6, 
										new Variable(2, 7, "b")
										),
								"+",
								new LiteralEntero(2, 9, 3)
								),
						"*",
						new Aritmetica(2, 13,
								new Variable(2, 12, "c"),
								"/",
								new LiteralEntero(2, 14, 2)
								)
						)
				);
		sentencias.add(sentencia);
		
		// * Tercera Línea
		expresion = new Variable(3, 7, "a");
		sentencias.add(new Escritura(3, 7, expresion));
		
		expresion = new Aritmetica(3, 10, 
				new Variable(3, 9, "c"),
				"*",
				new LiteralEntero(3, 11, 2)
				);
		sentencias.add(new Escritura(3, 9, expresion));
		
		// * Construimos y devolvemos el árbol
		return new Programa(1, 1, sentencias);		
	}

	public static void main(String[] args) {
		IntrospectorModel modelo = new IntrospectorModel("Programa",
				crearArbol());
		new IntrospectorTree("Introspector", modelo);
	}
}
