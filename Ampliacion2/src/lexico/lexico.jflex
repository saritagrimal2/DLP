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

Identificador = [a-zA-Zñá-úÁ-Ú][a-zA-Zñá-úÁ-Ú0-9]*
ConstanteEntera = [0-9]*
Real = [.][0-9]+|[0-9]+[.][0-9]*
ConstanteReal = {Real}|{Real}E-[0-9]+|[0-9]+e[+][0-9]+|[0-9]+e-[0-9]+
CodigoASCII = [']\\[0-9]*[']


%%
// ************  Acciones ********************
         			  
// * Palabras Reservadas
		  
"read" { this.yylval = yytext();
         return Parser.READ;	}
         			  			  
"write" { this.yylval = yytext();
          return Parser.WRITE;	}
         			  			  
"while" { this.yylval = yytext();
          return Parser.WHILE;	}
         			  		  
"if" { this.yylval = yytext();
       return Parser.IF;	} 
         			  	  
"else" { this.yylval = yytext();
         return Parser.ELSE;	} 
		  
"int" { this.yylval = yytext();
        return Parser.INT;	} 
         			  			  
"float32" { this.yylval = yytext();
           	return Parser.FLOAT32;	} 
		  
"char" { this.yylval = yytext();
         return Parser.CHAR;	} 
		  
"var" { this.yylval = yytext();
        return Parser.VAR;	}
			  
"struct" { this.yylval = yytext();
           return Parser.STRUCT;	} 
		  
"return" { this.yylval = yytext();
           return Parser.RETURN;	}  
	  
"func" { this.yylval = yytext();
         return Parser.FUNC;	}  
         			  	  
"main" { this.yylval = yytext();
         return Parser.MAIN;	}  
         
         
// * Identificador

{Identificador}		{ this.yylval = yytext();
         			  return Parser.ID;  }

// * Constantes

{ConstanteEntera}	{ this.yylval = new Integer(yytext());
         			  return Parser.CTE_ENTERA;  }
         			  
{ConstanteReal}		{ this.yylval = new Double(yytext());
         			  return Parser.CTE_REAL;  }
         			  
{CodigoASCII}	{ this.yylval = (char) Integer.parseInt(yytext().replace("'","").replace("\\",""));
         			  return Parser.CTE_CARACTER;  }
         			  
"'"."'"	{ this.yylval = yytext().charAt(1);
         			  return Parser.CTE_CARACTER;  }
         			  
'\\n' { this.yylval = new Character('\n');
         			  return Parser.CTE_CARACTER;  }
         			  
'\\t' { this.yylval = new Character('\t');
         			  return Parser.CTE_CARACTER;  }
         
// * Operadores

"+" |
"-" |
"/" |
"*"	|
"%" |
">" |
"<" |
"="	|
"(" |
")" |
"[" |
"]" |
"!" |
"." {this.yylval = yytext(); 
	 return yycharat(0);	}
	 
"==" {this.yylval = yytext(); 
	  return Parser.IGUAL_IGUAL;	}
	  
"<=" {this.yylval = yytext(); 
	  return Parser.MENOR_IGUAL;	}
	  
">=" {this.yylval = yytext(); 
	  return Parser.MAYOR_IGUAL;	}
	  
"!=" {this.yylval = yytext(); 
	  return Parser.DISTINTO;	}
	  
"&&" {this.yylval = yytext(); 
	  return Parser.AND;	}
	  
"||" {this.yylval = yytext(); 
	  return Parser.OR;	}

"++" {this.yylval = yytext(); 
	  return Parser.MAS_MAS;	}	  
	  
"--" {this.yylval = yytext(); 
	  return Parser.MENOS_MENOS;	}
	  
// * Delimitadores

"{" |
"}" |
";" |
"," {this.yylval = yytext(); 
	 return yycharat(0);	}
	 
// * Cosas a ignorar

" " |
"\t" |
"\n" |
"\r" |
"//".* |
"/*"~"*/" {}


// * Caracteres erroneos
.	{System.err.println ("Lexical error at line " + this.yyline + " and column " + this.yycolumn +":\n\tUnknow character \'"+ yycharat(0) + "\'."); }