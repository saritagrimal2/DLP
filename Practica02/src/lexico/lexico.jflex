// ************  C�digo a incluir ********************

package lexico;
import sintactico.Parser;

%%
// ************  Opciones ********************
// % debug // * Opci�n para depurar
%byaccj
%class Lexico
%public
%unicode
%line
%column

%{
// ************  Atributos y m�todos ********************
// * Para acceder al n�mero de l�nea (yyline es package)
public int getLinea() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al n�mero de columna (yycolumn es package)
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

%%
// ************  Acciones ********************

// * Constante Entera
{ConstanteEntera}	{ this.yylval = new Integer(yytext());
         			  return Parser.CTE_ENTERA;  }

