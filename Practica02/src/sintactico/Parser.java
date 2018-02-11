
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
    public final static int CTE_ENTERA = 257;
   
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