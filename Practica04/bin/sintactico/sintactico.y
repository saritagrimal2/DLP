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

%right '='
%left AND OR
%left '>' MAYOR_IGUAL '<' MENOR_IGUAL DISTINTO IGUAL_IGUAL
%left '+' '-'
%left '*' '/' '%'
%right '!'
%right MENOSUNARIO
%nonassoc '[' ']'
%left '.'
%nonassoc '(' ')'

%%
// * Gramática y acciones Yacc

programa : lista_definiciones FUNC MAIN '(' ')' '{' lista_variables lista_sentencias '}'
		;
		
lista_definiciones: /* vacio */
				  | lista_definiciones definicion
				  ;
				  
definicion: definicion_funcion
	| definicion_variable
	;
		
		
// * Expresiones

lista_expresiones: /* vacio */
				| lista_expresionesP
				;	   

lista_expresionesP: expresion
		   | lista_expresionesP ',' expresion
		   ;   

expresion: CTE_ENTERA	
         | CTE_REAL
         | CTE_CARACTER
         | ID
         | '(' expresion ')'
         | expresion '.' expresion
         | expresion '[' expresion ']'
         | '-' expresion %prec MENOSUNARIO
         | '!' expresion
         | expresion '*' expresion		
         | expresion '/' expresion
         | expresion '%' expresion		
         | expresion '+' expresion
         | expresion '-' expresion		
         | expresion '>' expresion
         | expresion MAYOR_IGUAL expresion		
         | expresion '<' expresion
         | expresion MENOR_IGUAL expresion		
         | expresion DISTINTO expresion
         | expresion IGUAL_IGUAL expresion		
         | expresion AND expresion
         | expresion OR expresion
         | tipo '(' expresion ')'
         | ID '('  lista_expresiones ')'
         ;
               
   
// * Sentencias       

         
lista_sentencias: /* vacio */
				| lista_sentencias sentencia
				;
				
				
sentencia: sentencia_if
		 | sentencia_else
		 | sentencia_while
		 | sentencia_write
		 | sentencia_read
		 | sentencia_asignacion
		 | sentencia_return
		 | sentencia_invocacion
		 ;
				
				
sentencia_asignacion: expresion '=' expresion ';'
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
		
sentencia_return: RETURN expresion ';'
				;

sentencia_invocacion: ID '('  lista_expresiones ')' ';'
					;
		

// * Definicion varible

lista_variables: /* vacio */
				|lista_variables definicion_variable 
				;
				

definicion_variable: VAR identificadores tipo ';'
					;
					
identificadores: ID
				| identificadores ',' ID
				;
					
variable_struct: identificadores tipo ';'
				;
				
variables_struct: variable_struct 
				| variables_struct variable_struct 
				;

tipo: INT
	| FLOAT32
	| CHAR
	| '[' CTE_ENTERA ']' tipo
	| STRUCT '{' variables_struct '}' /*Cambiar a lista_campos*/
	| VOID /*quitar, ahora ek void seria como no poner nada, quitar tambien del lexico */
	;


// * Definicion funcion

definicion_funcion: FUNC ID '(' lista_parametros ')' retorno '{' lista_variables lista_sentencias '}'
				  ;
				  

retorno: /* vacio */ 
	   | tipo
	   ;
		
lista_parametros: /* vacio */    /*Cambiar a definiciones de variables, se considera tmabien sin var*/
				| lista_parametrosP
				;
				
lista_parametrosP: ID tipo
		| lista_parametrosP ',' ID tipo
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
