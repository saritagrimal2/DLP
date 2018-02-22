%{
// * Declaraciones de c�digo Java
// * Se sit�an al comienzo del archivo generado
// * El package lo a�ade yacc si utilizamos la opci�n -Jpackage
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
// * Gram�tica y acciones Yacc

programa : lista_definiciones
		;
		
lista_definiciones: definicion_funcion
				 | definicion_variable
				 | definicion_funcion lista_definiciones
				 | definicion_variable lista_definiciones
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
         | expresion '=' expresion
         | tipo '(' expresion ')'
         | ID '('  lista_expresiones ')'
         ;
   
         
// * Definicion sentencias
         
lista_sentencias: sentencia_if lista_sentencias
				| sentencia_else lista_sentencias
				| sentencia_while lista_sentencias
				| sentencia_write lista_sentencias
				| sentencia_read lista_sentencias
				| sentencia_asignacion lista_sentencias
				| sentencia_return
				| sentencia_invocacion lista_sentencias
				|
				;
				
sentencia_asignacion: expresion '=' expresion ';'
         
sentencia_if: IF expresion '{' lista_sentencias '}'
			;
			
sentencia_else: ELSE '{' lista_sentencias '}'
			  ;
			
sentencia_while: WHILE expresion '{' lista_sentencias '}'
				;
							 
lista_expresiones: expresiones
				| 
				;	   

expresiones: expresion
		| expresiones ',' expresion
				 
				 			 			
				
sentencia_write: WRITE '(' lista_expresiones ')' ';'
				;
				
sentencia_read: READ '(' lista_expresiones ')' ';'
				;
		
sentencia_return: RETURN expresion ';'
				;

sentencia_invocacion: ID '('  lista_expresiones ')' ';'
					;
		

// * Definicion varible

lista_variables: definicion_variable lista_variables
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
	| VOID
	;


// * Definicion funcion

definicion_funcion: FUNC ID '(' lista_parametros ')' retorno '{' lista_variables lista_sentencias '}'
				  ;

retorno: tipo 
	   |
	   ;
	   
	   
lista_parametros: parametro
				| 
				;	   

parametro: ID tipo
		| parametro ',' ID tipo
	 

%%

// * C�digo Java
// * Se crea una clase "Parser", lo que aqu� ubiquemos ser�:
//	- Atributos, si son variables
//	- M�todos, si son funciones
//   de la clase "Parser"

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador l�xico
private Lexico lexico;

// * Llamada al analizador l�xico
private int yylex () {
    int token=0;
    try { 
		token=lexico.yylex(); 	
		this.yylval = lexico.getYylval();
    } catch(Throwable e) {
	    System.err.println ("Error L�xico en l�nea " + lexico.getLinea()+
		" y columna "+lexico.getColumna()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sint�cticos
public void yyerror (String error) {
    System.err.println ("Error Sint�ctico en l�nea " + lexico.getLinea()+
		" y columna "+lexico.getColumna()+":\n\t"+error);
}

// * Constructor del Sint�ctico
public Parser(Lexico lexico) {
	this.lexico = lexico;
}
