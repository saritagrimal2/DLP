//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package sintactico;



//#line 2 "../../src/sintactico/sintactico.y"
/* * Declaraciones de código Java*/
/* * Se sitúan al comienzo del archivo generado*/
/* * El package lo añade yacc si utilizamos la opción -Jpackage*/
import lexico.Lexico;
import java.io.Reader;
//#line 23 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short CTE_ENTERA=257;
public final static short DISTINTO=258;
public final static short MAIN=259;
public final static short IF=260;
public final static short CTE_CARACTER=261;
public final static short ID=262;
public final static short MENOR_IGUAL=263;
public final static short ELSE=264;
public final static short WRITE=265;
public final static short VOID=266;
public final static short MAYOR_IGUAL=267;
public final static short IGUAL_IGUAL=268;
public final static short CTE_REAL=269;
public final static short STRUCT=270;
public final static short WHILE=271;
public final static short FUNC=272;
public final static short FLOAT32=273;
public final static short INT=274;
public final static short RETURN=275;
public final static short CHAR=276;
public final static short AND=277;
public final static short READ=278;
public final static short VAR=279;
public final static short OR=280;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    3,    3,    3,    3,
    3,    3,    4,    5,    6,    7,    8,    9,    9,   10,
   11,   11,   13,   14,   14,   12,   12,   12,   12,   12,
};
final static short yylen[] = {                            2,
    1,    1,    3,    1,    1,    1,    1,    3,    3,    3,
    2,    2,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    2,    2,    2,    2,
    2,    0,    5,    4,    5,    5,    5,    2,    0,    4,
    1,    3,    3,    1,    2,    1,    1,    1,    4,    4,
};
final static short yydefred[] = {                         0,
    4,    6,    7,    5,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   10,    8,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    9,
};
final static short yydgoto[] = {                          9,
   10,   11,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yysindex[] = {                       -23,
    0,    0,    0,    0,  -23,  -23,  -23,  -23,    0,  -37,
  304,  100,  -44,  183,  411,  -23,  -23,  -23,  -23,  -23,
  -23,  -23,  -23,  -23,  -23,  -23,  -23,  -23,  -23,  -23,
  -23,    0,    0,  304,  450,  471,  124,  443,  422,  342,
  304,  -12,  -19,  100,  -31,  -42,  -33,  -44,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    8,
    3,  115,    1,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   14,  163,  148,   37,   75,  149,   51,
   52,  113,  155,  140,   90,   65,   39,   12,    0,
};
final static short yygindex[] = {                         0,
    0,  518,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static int YYTABLESIZE=738;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         30,
   12,   31,    2,   31,   29,   28,   16,    1,   30,    6,
   30,   13,   31,    3,   31,   29,    8,   28,    0,    0,
    0,    5,   30,   27,   28,   26,   31,   29,    0,   30,
   27,    0,   26,   31,   29,    0,   19,   12,   14,    0,
    0,   12,   12,   12,   12,   12,    2,   12,   13,   25,
   25,   26,   13,   13,   13,   13,   13,    3,   13,    0,
   12,   12,   12,    0,   15,    0,    0,    7,    0,    0,
    0,   13,   13,   13,   23,   14,    0,   19,    0,   14,
   19,   14,   14,   14,    0,   14,    0,    0,    0,   16,
    0,   25,   26,   12,   25,   26,   19,   19,   14,   14,
   14,   15,    0,    0,   13,   15,    0,   15,   15,   15,
    0,   25,   20,    0,   11,   23,    0,    0,   23,    0,
    0,    0,    0,    0,   15,   15,   15,    0,    0,   19,
   16,   14,   16,   16,   16,   23,   28,    0,    0,   17,
    0,   30,   27,   25,   26,   31,   29,   21,   24,   16,
   16,   16,    0,   20,   18,   11,   20,   15,   11,   11,
   28,    0,   22,    0,    0,   30,   27,   23,   26,   31,
   29,    0,   20,   20,   11,   11,   11,    0,    0,    0,
   17,    0,   16,   17,   17,   25,    0,    0,   21,   24,
    0,   21,   24,    0,    0,   18,    0,    0,   18,   17,
   17,   17,    0,   22,    0,   20,   22,   11,   21,   24,
    0,    0,    0,    0,   18,   18,   18,    0,    0,   28,
    0,    0,    0,   22,   30,   27,    0,   26,   31,   29,
    0,    0,   17,    1,    0,    0,    0,    2,    3,    0,
   21,   24,   24,   23,   25,    4,    0,   18,    0,    0,
    0,    0,    0,    0,   19,   22,    0,    0,   12,    0,
    0,    0,    0,   12,    0,    0,    0,   12,   12,   13,
    0,    0,    0,    0,   13,   32,    0,   12,   13,   13,
   12,    0,    0,    0,    0,    0,    0,    0,   13,    0,
    0,   13,    0,    0,   19,    0,   14,    0,    0,   19,
    0,   14,    0,   19,   19,   14,   14,    0,    0,    0,
    0,    0,    0,   19,    0,   14,   19,    0,   14,    0,
    0,    0,   15,    0,    0,    0,    0,   15,    0,    0,
   25,   15,   15,    0,    0,    0,    0,    0,    0,    0,
   28,   15,   23,    0,   15,   30,   27,   16,   26,   31,
   29,   23,   16,    0,   23,    0,   16,   16,    0,    0,
    0,    0,    0,   24,   23,   25,   16,    0,    0,   16,
   20,    0,   11,    0,    0,   20,    0,   11,   28,    0,
   20,   11,   11,   30,   27,    0,   26,   31,   29,   20,
    0,   11,   20,    0,   11,    0,    0,   17,    0,    0,
    0,   24,   17,   25,    0,   21,   17,   17,    0,    0,
   21,    0,   18,    0,    0,   21,   17,   18,    0,   17,
   22,   18,   18,    0,   21,   24,    0,   21,   24,    0,
   22,   18,    0,    0,   18,    0,    0,    0,    0,   22,
   17,    0,   22,    0,    0,   18,    0,   28,    0,   19,
   20,   33,   30,   27,    0,   26,   31,   29,   28,   21,
    0,    0,   22,   30,   27,    0,   26,   31,   29,    0,
   24,   23,   25,    0,    0,    0,    0,    0,    0,   28,
    0,   24,    0,   25,   30,   27,   28,   26,   31,   29,
    0,   30,   27,    0,   26,   31,   29,    0,    0,    0,
    0,    0,   24,    0,   25,    0,    0,   28,    0,   24,
    0,   25,   30,   27,    0,   26,   31,   29,    0,    0,
    0,    0,   12,   13,   14,   15,    0,    0,    0,    0,
   24,    0,   25,   34,   35,   36,   37,   38,   39,   40,
   41,   42,   43,   44,   45,   46,   47,   48,   49,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   17,    0,    0,    0,    0,   18,    0,    0,    0,
   19,   20,    0,    0,    0,    0,    0,    0,    0,    0,
   21,    0,    0,   22,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   17,
    0,    0,    0,    0,   18,    0,    0,    0,   19,   20,
    0,    0,    0,    0,    0,    0,    0,    0,   21,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   17,    0,
    0,    0,    0,   18,    0,    0,    0,   19,   20,   17,
    0,    0,    0,    0,   18,    0,    0,   21,   19,   20,
   22,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   17,    0,    0,    0,    0,   18,    0,    0,    0,   19,
    0,    0,   18,    0,    0,    0,   19,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   19,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         42,
    0,   46,    0,   46,   47,   37,   44,    0,   42,   33,
   42,    0,   46,    0,   46,   47,   40,   37,   -1,   -1,
   -1,   45,   42,   43,   37,   45,   46,   47,   -1,   42,
   43,   -1,   45,   46,   47,   -1,    0,   37,    0,   -1,
   -1,   41,   42,   43,   44,   45,   44,   47,   37,   62,
    0,    0,   41,   42,   43,   44,   45,   44,   47,   -1,
   60,   61,   62,   -1,    0,   -1,   -1,   91,   -1,   -1,
   -1,   60,   61,   62,    0,   37,   -1,   41,   -1,   41,
   44,   43,   44,   45,   -1,   47,   -1,   -1,   -1,    0,
   -1,   41,   41,   93,   44,   44,   60,   61,   60,   61,
   62,   37,   -1,   -1,   93,   41,   -1,   43,   44,   45,
   -1,   61,    0,   -1,    0,   41,   -1,   -1,   44,   -1,
   -1,   -1,   -1,   -1,   60,   61,   62,   -1,   -1,   93,
   41,   93,   43,   44,   45,   61,   37,   -1,   -1,    0,
   -1,   42,   43,   93,   93,   46,   47,    0,    0,   60,
   61,   62,   -1,   41,    0,   41,   44,   93,   44,   45,
   37,   -1,    0,   -1,   -1,   42,   43,   93,   45,   46,
   47,   -1,   60,   61,   60,   61,   62,   -1,   -1,   -1,
   41,   -1,   93,   44,   45,   62,   -1,   -1,   41,   41,
   -1,   44,   44,   -1,   -1,   41,   -1,   -1,   44,   60,
   61,   62,   -1,   41,   -1,   93,   44,   93,   61,   61,
   -1,   -1,   -1,   -1,   60,   61,   62,   -1,   -1,   37,
   -1,   -1,   -1,   61,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   93,  257,   -1,   -1,   -1,  261,  262,   -1,
   93,   93,   60,   61,   62,  269,   -1,   93,   -1,   -1,
   -1,   -1,   -1,   -1,  267,   93,   -1,   -1,  258,   -1,
   -1,   -1,   -1,  263,   -1,   -1,   -1,  267,  268,  258,
   -1,   -1,   -1,   -1,  263,   93,   -1,  277,  267,  268,
  280,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,   -1,
   -1,  280,   -1,   -1,  258,   -1,  258,   -1,   -1,  263,
   -1,  263,   -1,  267,  268,  267,  268,   -1,   -1,   -1,
   -1,   -1,   -1,  277,   -1,  277,  280,   -1,  280,   -1,
   -1,   -1,  258,   -1,   -1,   -1,   -1,  263,   -1,   -1,
  280,  267,  268,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   37,  277,  268,   -1,  280,   42,   43,  258,   45,   46,
   47,  277,  263,   -1,  280,   -1,  267,  268,   -1,   -1,
   -1,   -1,   -1,   60,   61,   62,  277,   -1,   -1,  280,
  258,   -1,  258,   -1,   -1,  263,   -1,  263,   37,   -1,
  268,  267,  268,   42,   43,   -1,   45,   46,   47,  277,
   -1,  277,  280,   -1,  280,   -1,   -1,  258,   -1,   -1,
   -1,   60,  263,   62,   -1,  258,  267,  268,   -1,   -1,
  263,   -1,  258,   -1,   -1,  268,  277,  263,   -1,  280,
  258,  267,  268,   -1,  277,  277,   -1,  280,  280,   -1,
  268,  277,   -1,   -1,  280,   -1,   -1,   -1,   -1,  277,
  258,   -1,  280,   -1,   -1,  263,   -1,   37,   -1,  267,
  268,   41,   42,   43,   -1,   45,   46,   47,   37,  277,
   -1,   -1,  280,   42,   43,   -1,   45,   46,   47,   -1,
   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   37,
   -1,   60,   -1,   62,   42,   43,   37,   45,   46,   47,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   -1,   60,   -1,   62,   -1,   -1,   37,   -1,   60,
   -1,   62,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,    5,    6,    7,    8,   -1,   -1,   -1,   -1,
   60,   -1,   62,   16,   17,   18,   19,   20,   21,   22,
   23,   24,   25,   26,   27,   28,   29,   30,   31,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  258,   -1,   -1,   -1,   -1,  263,   -1,   -1,   -1,
  267,  268,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  277,   -1,   -1,  280,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  258,
   -1,   -1,   -1,   -1,  263,   -1,   -1,   -1,  267,  268,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  258,   -1,
   -1,   -1,   -1,  263,   -1,   -1,   -1,  267,  268,  258,
   -1,   -1,   -1,   -1,  263,   -1,   -1,  277,  267,  268,
  280,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  258,   -1,   -1,   -1,   -1,  263,   -1,   -1,   -1,  267,
   -1,   -1,  263,   -1,   -1,   -1,  267,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  267,
};
}
final static short YYFINAL=9;
final static short YYMAXTOKEN=280;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"CTE_ENTERA","DISTINTO","MAIN",
"IF","CTE_CARACTER","ID","MENOR_IGUAL","ELSE","WRITE","VOID","MAYOR_IGUAL",
"IGUAL_IGUAL","CTE_REAL","STRUCT","WHILE","FUNC","FLOAT32","INT","RETURN",
"CHAR","AND","READ","VAR","OR",
};
final static String yyrule[] = {
"$accept : programa",
"programa : lista_expresiones",
"lista_expresiones : expresion",
"lista_expresiones : lista_expresiones ',' expresion",
"expresion : CTE_ENTERA",
"expresion : CTE_REAL",
"expresion : CTE_CARACTER",
"expresion : ID",
"expresion : '(' expresion ')'",
"expresion : expresion '.' expresion",
"expresion : '[' expresion ']'",
"expresion : '-' expresion",
"expresion : '!' expresion",
"expresion : expresion '*' expresion",
"expresion : expresion '/' expresion",
"expresion : expresion '%' expresion",
"expresion : expresion '+' expresion",
"expresion : expresion '-' expresion",
"expresion : expresion '>' expresion",
"expresion : expresion MAYOR_IGUAL expresion",
"expresion : expresion '<' expresion",
"expresion : expresion MENOR_IGUAL expresion",
"expresion : expresion DISTINTO expresion",
"expresion : expresion IGUAL_IGUAL expresion",
"expresion : expresion AND expresion",
"expresion : expresion OR expresion",
"expresion : expresion '=' expresion",
"lista_sentencias : sentencia_if lista_sentencias",
"lista_sentencias : sentencia_else lista_sentencias",
"lista_sentencias : sentencia_while lista_sentencias",
"lista_sentencias : sentencia_write lista_sentencias",
"lista_sentencias : sentencia_read lista_sentencias",
"lista_sentencias :",
"sentencia_if : IF expresion '{' lista_sentencias '}'",
"sentencia_else : ELSE '{' lista_sentencias '}'",
"sentencia_while : WHILE expresion '{' lista_sentencias '}'",
"sentencia_write : WRITE '(' lista_expresiones ')' ';'",
"sentencia_read : READ '(' lista_expresiones ')' ';'",
"lista_definiciones : lista_definiciones definicion_variable",
"lista_definiciones :",
"definicion_variable : VAR identificadores tipo ';'",
"identificadores : ID",
"identificadores : identificadores ',' ID",
"variable_struct : identificadores tipo ';'",
"variables_struct : variable_struct",
"variables_struct : variable_struct variables_struct",
"tipo : INT",
"tipo : FLOAT32",
"tipo : CHAR",
"tipo : '[' CTE_ENTERA ']' tipo",
"tipo : STRUCT '{' variables_struct '}'",
};

//#line 145 "../../src/sintactico/sintactico.y"

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
//#line 437 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
