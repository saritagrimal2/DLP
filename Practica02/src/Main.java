
import sintactico.Parser;
import java.io.FileReader;
import java.io.IOException;

import lexico.Lexico;

/**
 * Prueba del analizador l�xico.<br/>
 * Dise�o de Lenguajes de Programaci�n.<br/>
 * Escuela de Ingenier�a Inform�tica.<br/>
 * Universidad de Oviedo <br/>
 * 
 * @author Francisco Ortin
 */

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
		
		Lexico lexico = new Lexico(fr);
		Parser parser = new Parser(lexico);
		int token;
		while ((token=lexico.yylex())!=0) {
		    System.out.println("Linea: "+lexico.getLinea()+
		            ", columna: "+lexico.getColumna()+
		            ", token: "+token+
		            ", valor sem�ntico: "+lexico.getYylval()+".");
		}
	}
}