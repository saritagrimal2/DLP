%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import lexico.Lexico;
import java.io.Reader;
%}

// * Declaraciones Yacc
%token CTE_ENTERA
%token DISTINTO
%token MAIN
%token IF
%token CTE_CARACTER
%token ID
%token MENOR_IGUAL
%token ELSE
%token WRITE
%token VOID
%token MAYOR_IGUAL
%token IGUAL_IGUAL
%token CTE_REAL
%token STRUCT
%token WHILE
%token FUNC
%token FLOAT32
%token INT
%token RETURN
%token CHAR
%token AND
%token READ
%token VAR
%token OR

%left '+'
%left '*'

%%
// * Gramática y acciones Yacc
programa : lista_definiciones
		;
		
lista_expresiones: expresion
				 | lista_expresiones ',' expresion
				 ;

expresion: expresion '+' expresion		
         | expresion '*' expresion
         | CTE_ENTERA	
         | CTE_REAL
         | CTE_CARACTER
         ;
                  
         
lista_sentencias: sentencia_if lista_sentencias
				| sentencia_else lista_sentencias
				| sentencia_while lista_sentencias
				| sentencia_write lista_sentencias
				| sentencia_read lista_sentencias
				|
				;
         
sentencia_if: IF expresion '{' lista_sentencias '}'
			;
			
sentencia_else: ELSE '{' lista_sentencias '}'
			  ;
			
sentencia_while: WHILE expresion '{' lista_sentencias '}'
				;
				
sentencia_write: WRITE '(' lista_expresiones ')' ';'
				;
				
sentencia_read: READ '(' lista_expresiones ')' ';'
				;
				



lista_definiciones: lista_definiciones definicion_variable 
				  | 
				  ;

				
				
definicion_variable: VAR identificadores tipo ';'
					;
					
identificadores: ID
				| identificadores ',' ID
				;
					
variable_struct: identificadores tipo ';'
				;
				
variables_struct: variable_struct 
				| variable_struct variables_struct
				;

					
tipo: INT
	| FLOAT32
	| CHAR
	| '[' CTE_ENTERA ']' tipo
	| STRUCT '{' variables_struct '}'
	;


%%

// * Código Java
// * Se crea una clase "Parser", lo que aquí ubiquemos será:
//	- Atributos, si son variables
//	- Métodos, si son funciones
//   de la clase "Parser"

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador léxico
private Lexico lexico;

// * Llamada al analizador léxico
private int yylex () {
    int token=0;
    try { 
		token=lexico.yylex(); 	
		this.yylval = lexico.getYylval();
    } catch(Throwable e) {
	    System.err.println ("Error Léxico en línea " + lexico.getLinea()+
		" y columna "+lexico.getColumna()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sintácticos
public void yyerror (String error) {
    System.err.println ("Error Sintáctico en línea " + lexico.getLinea()+
		" y columna "+lexico.getColumna()+":\n\t"+error);
}

// * Constructor del Sintáctico
public Parser(Lexico lexico) {
	this.lexico = lexico;
}
