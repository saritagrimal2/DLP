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

programa : lista_definiciones FUNC MAIN '(' ')' '{' cuerpo '}'   { this.ast = new Programa(lexico.getLinea(), lexico.getColumna(), (List<Definicion>)$1, (List<Sentencia>)$7 ); }
		;
		
lista_definiciones: /* vacio */						{ $$ = new ArrayList<Definicion>(); }
				  | lista_definiciones definicion	{List<Definicion> defs = (List<Definicion>)$1; List<Definicion> def = (List<Definicion>)$2; 
				  										for(Definicion d:def){defs.add(d);} $$ = defs;} 
				  ;
				  
definicion: definicion_funcion		{ List<DefFuncion> df = new ArrayList<DefFuncion>(); df.add((DefFuncion) $1); $$=df;} 
	| definicion_variable			{ $$ = $1; }
	;
		
		
// * Expresiones

lista_expresiones: /* vacio */						{ $$ = new ArrayList<Expresion>();} 		
				| lista_expresionesP				{ $$ = $1;}
				;	   

lista_expresionesP: expresion						{ List<Expresion> expresiones = new ArrayList<Expresion>(); expresiones.add((Expresion)$1); $$ = expresiones;}
		   | lista_expresionesP ',' expresion		{ List<Expresion> expresiones = (List<Expresion>)$1; expresiones.add((Expresion)$3); $$ = expresiones;}
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

         
lista_sentencias: /* vacio */						{ $$ = new ArrayList<Sentencia>(); }
				| lista_sentencias sentencia		{List<Sentencia> sents = (List<Sentencia>)$1; List<Sentencia> sent = (List<Sentencia>)$2; 
				  										for(Sentencia s: sent){sents.add(s);} $$ = sents;} 
				;
				
				
sentencia: sentencia_if								{ List<Sentencia> sentencias = new ArrayList<Sentencia>(); sentencias.add((Sentencia)$1); $$ = sentencias;}
		 | sentencia_else							{ List<Sentencia> sentencias = new ArrayList<Sentencia>(); sentencias.add((Sentencia)$1); $$ = sentencias;}
		 | sentencia_while							{ List<Sentencia> sentencias = new ArrayList<Sentencia>(); sentencias.add((Sentencia)$1); $$ = sentencias;}
		 | sentencia_write							{ $$ = $1;}
		 | sentencia_read							{ $$ = $1;}
		 | sentencia_asignacion						{ List<Sentencia> sentencias = new ArrayList<Sentencia>(); sentencias.add((Sentencia)$1); $$ = sentencias;}
		 | sentencia_return							{ List<Sentencia> sentencias = new ArrayList<Sentencia>(); sentencias.add((Sentencia)$1); $$ = sentencias;}
		 | sentencia_invocacion						{ List<Sentencia> sentencias = new ArrayList<Sentencia>(); sentencias.add((Sentencia)$1); $$ = sentencias;}
		 ;
				
				
sentencia_asignacion: expresion '=' expresion ';'				{$$ = new Asignacion(lexico.getLinea(), lexico.getColumna(),(Expresion)$1,(Expresion)$3);}
					;
         
sentencia_if: IF expresion '{' lista_sentencias '}'				{$$ = new sentenciaIf(lexico.getLinea(), lexico.getColumna(),(Expresion)$2, (List<Sentencia>)$4 );}
			;
			
sentencia_else: ELSE '{' lista_sentencias '}'					{$$ = new sentenciaElse(lexico.getLinea(), lexico.getColumna(), (List<Sentencia>)$3 );}
			  ;
			
sentencia_while: WHILE expresion '{' lista_sentencias '}'		{$$ = new sentenciaWhile(lexico.getLinea(), lexico.getColumna(),(Expresion)$2, (List<Sentencia>)$4 );}
				;
							 				 			 			
sentencia_write: WRITE '(' lista_expresiones ')' ';'			{ List<Sentencia> sentencias = new ArrayList<Sentencia>(); List<Expresion> expresiones = (List<Expresion>) $3; 
																	for(Expresion expresion: expresiones) {sentencias.add(new Escritura(lexico.getLinea(), lexico.getColumna(), expresion));} $$=sentencias;}
				;
				
sentencia_read: READ '(' lista_expresiones ')' ';'				{ List<Sentencia> sentencias = new ArrayList<Sentencia>(); List<Expresion> expresiones = (List<Expresion>) $3; 
																	for(Expresion expresion: expresiones) {sentencias.add(new Lectura(lexico.getLinea(), lexico.getColumna(), expresion));} $$=sentencias;}
				;
		
sentencia_return: RETURN expresion ';'							{$$ = new Return(lexico.getLinea(), lexico.getColumna(), (Expresion)$2 );}
				;

sentencia_invocacion: ID '('  lista_expresiones ')' ';'			{$$ = new InvocacionFuncionSent(lexico.getLinea(), lexico.getColumna(), new Identificador(lexico.getLinea(), lexico.getColumna(),(String)$1), (List<Expresion>)$3);}
					;
		

// * Definicion variable				

lista_variables: /* vacio */								{ $$ = new ArrayList<DefVariable>(); }
				|lista_variables definicion_variable 		{ List<DefVariable> defs = (List<DefVariable>)$1; List<DefVariable> def = (List<DefVariable>) $2; 
																for(DefVariable var: def){defs.add(var);} $$ = defs;}
				;

definicion_variable: VAR identificadores tipo ';'			{ List<String> identificadores = (List<String>)$2; List<DefVariable> variables = new ArrayList<DefVariable>(); 
															  	for (String id: identificadores) {variables.add(new DefVariable(lexico.getLinea(), lexico.getColumna(),id, (Tipo)$3 ));} $$ = variables;}
					;
					
identificadores: ID											{ List<String> identificadores = new ArrayList<String>(); identificadores.add((String)$1); $$ = identificadores;}
				| identificadores ',' ID					{ List<String> identificadores = (List<String>)$1; identificadores.add((String)$3); $$ = identificadores;}
				;
					
campo: identificadores tipo ';'								{ List<String> identificadores = (List<String>)$1; List<Campo> campos = new ArrayList<Campo>(); 
															  	for (String id: identificadores) {campos.add(new Campo(lexico.getLinea(), lexico.getColumna(),id, (Tipo)$2 ));} $$ = campos;}
				;
				
lista_campos: campo											{ $$ = $1;}   
			| lista_campos campo 							{ List<Campo> camps = (List<Campo>)$1; List<Campo> camp = (List<Campo>) $2; 
																for(Campo c: camp){camps.add(c);} $$ = camps;}
			;
			

tipo: INT								{ $$ = TipoEntero.getInstance();}
	| FLOAT32							{ $$ = TipoFloat.getInstance();}
	| CHAR								{ $$ = TipoCaracter.getInstance();}
	| '[' CTE_ENTERA ']' tipo			{ $$ = new TipoArray(lexico.getLinea(), lexico.getColumna(),(int) $2, (Tipo) $4);} 
	| STRUCT '{' lista_campos '}'		{ $$ = new TipoRegistro(lexico.getLinea(), lexico.getColumna(),(List<Campo>) $3);} 
	;


// * Definicion funcion

definicion_funcion: FUNC ID '(' lista_parametros ')' retorno '{' cuerpo '}'   { $$ = new DefFuncion(lexico.getLinea(), lexico.getColumna(), 
																				(String)$2, new TipoFuncion(lexico.getLinea(), lexico.getColumna(), (Tipo)$6, (List<DefVariable>)$4 ),(List<Sentencia>)$8); }
				  ;
				  
cuerpo: lista_variables lista_sentencias	{ List<Sentencia> st = (List<Sentencia>) $1; List<Sentencia> sent = (List<Sentencia>) $2;
												for(Sentencia s: sent){st.add(s);} $$ = st;}
		;				  
				  
tipoSimple: INT				{ $$ = TipoEntero.getInstance();}
		  | FLOAT32			{ $$ = TipoFloat.getInstance();}
	      | CHAR			{ $$ = TipoCaracter.getInstance();}
	      ;


retorno: /* vacio */ 		{ $$ = TipoVoid.getInstance();}
	   | tipoSimple			{ $$ = $1;}
	   ;
		
lista_parametros: /* vacio */								{ $$ = new ArrayList<DefVariable>();}
				| lista_parametrosP							{ $$ = $1;}
				;
				
lista_parametrosP: ID tipoSimple							{ List<DefVariable> variables = new ArrayList<DefVariable>(); variables.add(new DefVariable(lexico.getLinea(), lexico.getColumna(), (String)$1, (Tipo)$2)); $$ = variables;}
		        | lista_parametrosP ',' ID tipoSimple		{ List<DefVariable> variables = (List<DefVariable>)$1; variables.add(new DefVariable(lexico.getLinea(), lexico.getColumna(), (String)$3, (Tipo)$4)); $$ = variables;}
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
