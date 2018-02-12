
package sintactico;

import lexico.Lexico;

/**
 * Clase Analizador Sintáctico (Parser).<br/>
 * Diseño de Lenguajes de Programación.<br/>
 * Escuela de Ingeniería Informática.<br/>
 * Universidad de Oviedo.<br/>
 * @author Francisco Ortin
 */

public class Parser {

    // * Tokens
    public final static int CTE_ENTERA = 257;
    public final static int CTE_REAL = 258;
    public final static int READ = 259;
    public final static int WRITE = 260;
    public final static int WHILE = 261;
    public final static int IF = 262;
    public final static int ELSE = 263;
    public final static int INT = 264;
    public final static int FLOAT32 = 265;
    public final static int CHAR = 266;
    public final static int VAR = 267;
    public final static int STRUCT = 268;
    public final static int RETURN = 269;
    public final static int FUNC = 270;
    public final static int MAIN = 271;
    public final static int VOIF = 272;
    public final static int IGUAL_IGUAL = 273;
    
   
    /**
    * Referencia al analizador léxico
    */
    private Lexico lexico;
    
    // * Constructor del Sintáctico
    public Parser(Lexico lexico) {
        // * El sintático conoce al léxico
        this.lexico = lexico;
    }
    
}