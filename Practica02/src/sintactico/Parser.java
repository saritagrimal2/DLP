
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