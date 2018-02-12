// ************  Código a incluir ********************

package lexico;
import sintactico.Parser;

%%
// ************  Opciones ********************
// % debug // * Opción para depurar
%byaccj
%class Lexico
%public
%unicode
%line
%column

%{
// ************  Atributos y métodos ********************
// * Para acceder al número de línea (yyline es package)
public int getLinea() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al número de columna (yycolumn es package)
public int getColumna() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

// * Valor semantico del token
private Object yylval;
public Object getYylval() {
	return this.yylval;
}

%}

// ************  Patrones (macros) ********************
ConstanteEntera = [0-9]*
ConstanteReal = [0-9]+"."[0-9]*

%%
// ************  Acciones ********************

// * Constante Entera
{ConstanteEntera}	{ this.yylval = new Integer(yytext());
         			  return Parser.CTE_ENTERA;  }
         			  
// * Constante real
{ConstanteReal}	{ this.yylval = new Float(yytext());
         			  return Parser.CTE_REAL;  }

// * Palabra reservada read			  
"read" { this.yylval = yytext();
         			  return Parser.READ;	}
         			  
// * Palabra reservada write			  
"write" { this.yylval = yytext();
         			  return Parser.WRITE;	}
         			  
// * Palabra reservada while			  
"while" { this.yylval = yytext();
         			  return Parser.WHILE;	}
         			  
// * Palabra reservada if			  
"if" { this.yylval = yytext();
         			  return Parser.IF;	} 
         			  
// * Palabra reservada else			  
"else" { this.yylval = yytext();
         			  return Parser.ELSE;	} 

// * Palabra reservada int			  
"int" { this.yylval = yytext();
         			  return Parser.INT;	} 
         			  

// * Palabra reservada float32			  
"float32" { this.yylval = yytext();
         			  return Parser.FLOAT32;	} 

// * Palabra reservada char			  
"char" { this.yylval = yytext();
         			  return Parser.CHAR;	} 

// * Palabra reservada var			  
"var" { this.yylval = yytext();
         			  return Parser.VAR;	}

// * Palabra reservada struct			  
"struct" { this.yylval = yytext();
         			  return Parser.STRUCT;	} 

// * Palabra reservada return			  
"return" { this.yylval = yytext();
         			  return Parser.RETURN;	}  

// * Palabra reservada func			  
"func" { this.yylval = yytext();
         			  return Parser.FUNC;	}  
         			  
// * Palabra reservada main			  
"main" { this.yylval = yytext();
         			  return Parser.MAIN;	}  
         			  
// * Palabra reservada void			  
"void" { this.yylval = yytext();
         			  return Parser.VOID;	} 
        			  
"+" |
"-" |
">" |
"<" 	{this.yylval = yytext(); return yytext().charAt(0);	}
         			  
"=="	{this.yylval = yytext(); return Parser.IGUAL_IGUAL;	}

. {System.out.println("Error " + yytext()); }
"\n" {}  			  
         			  

