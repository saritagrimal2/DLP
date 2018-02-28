%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import lexico.Lexico;
import java.io.Reader;
import ast.*;
import java.util.*;
import ast.tipo.*;

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

lista_expresiones: /* vacio */						{ $$ = new ArrayList<Expresion>();}				
				| lista_expresionesP				{ $$ = $1;}
				;	   

lista_expresionesP: expresion						{ List<Expresion> expresiones = new ArrayList<Expresion>(); expresiones.add((Expresion)$1); $$=expresiones;}					
		   | lista_expresionesP ',' expresion		{ $$ = $1; ((List<Expresion>)$$).add((Expresion)$3);}
		   ;   

expresion: CTE_ENTERA								{ $$ = new LiteralEntero(lexico.getLinea(), lexico.getColumna(),(int)$1);}
         | CTE_REAL									{ $$ = new LiteralReal(lexico.getLinea(), lexico.getColumna(),(double)$1);}
         | CTE_CARACTER 	  						{ $$ = new LiteralCaracter(lexico.getLinea(), lexico.getColumna(),(char)$1);}
         | ID				 						{ $$ = new Identificador(lexico.getLinea(), lexico.getColumna(),(String)$1);}
         | '(' expresion ')'  						{ $$ = $2; }
         | expresion '.' ID   						{ $$ = new AccesoCampo (lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (String)$3);}
         | expresion '[' expresion ']'  			{ $$ = new AccesoArray (lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3);} 
         | '-' expresion %prec MENOSUNARIO  		{ $$ = new MenosUnario(lexico.getLinea(), lexico.getColumna(),(Expresion) $2);}
         | '!' expresion							{ $$ = new Negacion(lexico.getLinea(), lexico.getColumna(),(Expresion) $2);}
         | expresion '*' expresion					{ $$ = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion) $1,(String)$2,(Expresion)$3);}
         | expresion '/' expresion					{ $$ = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion) $1,(String)$2,(Expresion)$3);}
         | expresion '%' expresion					{ $$ = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion) $1,(String)$2,(Expresion)$3);}		
         | expresion '+' expresion					{ $$ = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion) $1,(String)$2,(Expresion)$3);}
         | expresion '-' expresion					{ $$ = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion) $1,(String)$2,(Expresion)$3);}		
         | expresion '>' expresion					{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion) $1,(String)$2,(Expresion)$3);}
         | expresion MAYOR_IGUAL expresion		    { $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion) $1,(String)$2,(Expresion)$3);}
         | expresion '<' expresion					{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion) $1,(String)$2,(Expresion)$3);}
         | expresion MENOR_IGUAL expresion			{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion) $1,(String)$2,(Expresion)$3);}
         | expresion DISTINTO expresion				{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion) $1,(String)$2,(Expresion)$3);}
         | expresion IGUAL_IGUAL expresion			{ $$ = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion) $1,(String)$2,(Expresion)$3);}
         | expresion AND expresion					{ $$ = new Logica(lexico.getLinea(), lexico.getColumna(), (Expresion) $1,(String)$2,(Expresion)$3);}
         | expresion OR expresion					{ $$ = new Logica(lexico.getLinea(), lexico.getColumna(), (Expresion) $1,(String)$2,(Expresion)$3);}
         | tipo '(' expresion ')'					{ $$ = new Cast(lexico.getLinea(), lexico.getColumna(), (Tipo) $1,(Expresion)$3);} 
         | ID '('  lista_expresiones ')'			{ $$ = new InvocacionFuncionExp(lexico.getLinea(), lexico.getColumna(), new Identificador(lexico.getLinea(), lexico.getColumna(),(String)$1), (List<Expresion>)$3);} 
         ;
               
   
// * Sentencias       

         
lista_sentencias: /* vacio */						{ $$ = new ArrayList<Sentencia>();} 
				| lista_sentencias sentencia		{ $$ = $1; ((List<Sentencia>)$$).add((Sentencia)$2);}
				;
				
				
sentencia: sentencia_if								{ $$ = $1;}
		 | sentencia_else							{ $$ = $1;}
		 | sentencia_while							{ $$ = $1;}
		 | sentencia_write							{ $$ = $1;}
		 | sentencia_read							{ $$ = $1;}
		 | sentencia_asignacion						{ $$ = $1;}
		 | sentencia_return							{ $$ = $1;}
		 | sentencia_invocacion						{ $$ = $1;}
		 ;
				
				
sentencia_asignacion: expresion '=' expresion ';'				{$$ = new Asignacion(lexico.getLinea(), lexico.getColumna(),(Expresion)$1,(Expresion)$3);}
					;
         
sentencia_if: IF expresion '{' lista_sentencias '}'				{$$ = new sentenciaIf(lexico.getLinea(), lexico.getColumna(),(Expresion)$2, (List<Sentencia>)$4 );}
			;
			
sentencia_else: ELSE '{' lista_sentencias '}'					{$$ = new sentenciaElse(lexico.getLinea(), lexico.getColumna(), (List<Sentencia>)$3 );}
			  ;
			
sentencia_while: WHILE expresion '{' lista_sentencias '}'		{$$ = new sentenciaWhile(lexico.getLinea(), lexico.getColumna(),(Expresion)$2, (List<Sentencia>)$4 );}
				;
							 				 			 			
sentencia_write: WRITE '(' lista_expresiones ')' ';'			
				;
				
sentencia_read: READ '(' lista_expresiones ')' ';'				
				;
		
sentencia_return: RETURN expresion ';'							{$$ = new Return(lexico.getLinea(), lexico.getColumna(), (Expresion)$2 );}
				;

sentencia_invocacion: ID '('  lista_expresiones ')' ';'			{$$ = new InvocacionFuncionSent(lexico.getLinea(), lexico.getColumna(), new Identificador(lexico.getLinea(), lexico.getColumna(),(String)$1), (List<Expresion>)$3);}
					;
		

// * Definicion variable				

lista_variables: /* vacio */								{ $$ = new ArrayList<DefVariable>();} 
				|lista_variables definicion_variable 		{ $$ = $1; ((List<DefVariable>)$$).add((DefVariable)$2);}
				;

definicion_variable: VAR identificadores tipo ';'			
					;
					
identificadores: ID											{ List<String> identificadores = new ArrayList<String>(); identificadores.add((String)$1); $$=identificadores;}
				| identificadores ',' ID					{ $$ = $1; ((List<String>)$$).add((String)$3);}
				;
					
campo: identificadores tipo ';'				
				;
				
lista_campos: campo
			| lista_campos campo 
			;
			

tipo: INT
	| FLOAT32
	| CHAR
	| '[' CTE_ENTERA ']' tipo
	| STRUCT '{' lista_campos '}'
	;


// * Definicion funcion

definicion_funcion: FUNC ID '(' lista_parametros ')' retorno '{' lista_variables lista_sentencias '}'
				  ;
				  
tipoSimple: INT
		  | FLOAT32
	      | CHAR
	      ;


retorno: /* vacio */ 
	   | tipoSimple
	   ;
		
lista_parametros: /* vacio */
				| lista_parametrosP
				;
				
lista_parametrosP: ID tipoSimple
		        | lista_parametrosP ',' ID tipoSimple
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
private NodoAST ast;

public NodoAST getAST(){
    return ast;
}

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
