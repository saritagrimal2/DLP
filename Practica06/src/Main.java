import java.io.FileReader;
import java.io.IOException;

import ast.NodoAST;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import lexico.Lexico;
import sintactico.Parser;
import visitor.Visitor;
import manejadorerrores.ME;
import semantico.VisitorSemantico;

public class Main {
	public static void main(String args[]) throws IOException {
	    if (args.length<1) {
	        System.err.println("Necesito el archivo de entrada.");
	        return;
	    }
	        
		FileReader fr=null;
		try {
			fr=new FileReader(args[0]);
		} catch(IOException io) {
			System.err.println("El archivo "+args[0]+" no se ha podido abrir.");
			return;
		}
		
		// * Creamos l�xico y sint�ctico
		Lexico lexico = new Lexico(fr);
		Parser parser = new Parser(lexico);
		// * "Parseamos"
		parser.run();	
		
		
		Visitor v = new VisitorSemantico();
		NodoAST nodoRaiz = parser.getAST();
		nodoRaiz.aceptar(v, null);
		
				
		// * Comprobamos si hubo errores
		if(ME.getME().huboErrores()){
			// * Mostramos los errores
			ME.getME().mostrarErrores(System.err);
		}
		else{			
			// * Mostramos el AST
			IntrospectorModel modelo=new IntrospectorModel("Programa",parser.getAST());
			new IntrospectorTree("Introspector", modelo);
		}
	}

}