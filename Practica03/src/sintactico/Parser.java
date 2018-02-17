
package sintactico;

import lexico.Lexico;

/**
 * Clase Analizador Sint�ctico (Parser).<br/>
 * Dise�o de Lenguajes de Programaci�n.<br/>
 * Escuela de Ingenier�a Inform�tica.<br/>
 * Universidad de Oviedo.<br/>
 * @author Francisco Ortin
 */

public class Parser {

    // * Tokens
	
	//Constantes
    public final static int CTE_ENTERA = 257;
    public final static int CTE_REAL = 279;
    public final static int CTE_CARACTER = 280;
    
    //Palabras reservadas
    public final static int READ = 258;
    public final static int WRITE = 259;
    public final static int WHILE = 260;
    public final static int IF = 261;
    public final static int ELSE = 262;
    public final static int INT = 263;
    public final static int FLOAT32 = 264;
    public final static int CHAR = 265;
    public final static int VAR = 266;
    public final static int STRUCT = 267;
    public final static int RETURN = 268;
    public final static int FUNC = 269;
    public final static int MAIN = 270;
    public final static int VOID = 271;
    
    //Operadores
    public final static int IGUAL_IGUAL = 272;
    public final static int MENOR_IGUAL = 273;
    public final static int MAYOR_IGUAL = 274;
    public final static int DISTINTO = 275;
    public final static int AND = 276;
    public final static int OR = 277;
    
    //Identificador
    public final static int ID = 278;
   
    /**
    * Referencia al analizador l�xico
    */
    private Lexico lexico;
    
    // * Constructor del Sint�ctico
    public Parser(Lexico lexico) {
        // * El sint�tico conoce al l�xico
        this.lexico = lexico;
    }
    
}