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
    0,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    1,    1,    1,    1,    1,    1,
    3,    4,    5,    8,    8,    6,    7,    9,    9,   10,
   11,   11,   13,   14,   14,   12,   12,   12,   12,   12,
};
final static short yylen[] = {                            2,
    1,    1,    1,    1,    1,    3,    3,    4,    2,    2,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    4,    2,    2,    2,    2,    2,    0,
    5,    4,    5,    1,    3,    5,    5,    2,    0,    4,
    1,    3,    3,    1,    2,    1,    1,    1,    4,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,
    0,    0,    2,    4,    5,    3,    0,    0,    0,    0,
    0,    0,    0,    0,   25,   26,   27,   28,   29,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    6,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    7,    0,   32,    0,    0,    0,    0,   24,    8,   31,
   36,    0,   33,   37,
};
final static short yydgoto[] = {                          6,
    7,   51,    8,    9,   10,   11,   12,   52,    0,    0,
    0,    0,    0,    0,
};
final static short yysindex[] = {                        86,
  -13, -122,  -38,  -13,  -27,    0,    0,   86,   86,   86,
   86,   86,    0,    0,    0,    0,  -13,  -13,  -13,   12,
   86,  -13,   38,  -13,    0,    0,    0,    0,    0,  556,
  -20,   65,  -13,  -13,  -13,  -13,  -13,  -13,  -13,  -13,
  -13,  -13,  -13,  -13,  -13,  -13,  -13,  -13,   86, -111,
  156,  -32,   86,   -4,    0,  534,  589,  709,  506,  495,
  167,   72,  279,   45,  556,  456,    5,   22,  -20,  104,
    0,  -97,    0,  -30,  -13,  -95,  -28,    0,    0,    0,
    0,  156,    0,    0,
};
final static short yyrindex[] = {                        39,
    0,    0,    0,    0,    0,    0,    0,    3,    3,    3,
    3,    3,    0,    0,    0,    0,    0,    0,    0,    0,
  -84,    0,    0,    0,    0,    0,    0,    0,    0,  315,
  -37,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -84,    0,
    9,    0,  -84,    0,    0,  411,  350,  361,  422,  433,
  438,    0,  243,  398,  387,  250,  130,    1,  -26,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   25,    0,    0,
};
final static short yygindex[] = {                         0,
  175,  697,    0,    0,    0,    0,    0,   19,    0,    0,
    0,    0,    0,    0,
};
final static int YYTABLESIZE=856;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         10,
   21,   22,   30,   10,   10,   10,   10,   10,   74,   10,
   11,   75,   24,   73,   11,   11,   11,   11,   11,   18,
   11,   10,   10,   10,   10,   48,   19,   80,   81,   83,
   84,   17,   11,   11,   11,   11,   77,   12,   30,   75,
   30,   12,   54,   12,   12,   12,   46,   12,   44,   34,
   48,   45,   34,   46,   43,   10,   42,   48,   45,   12,
   12,   12,   12,   46,    0,   35,   11,   48,   35,    0,
   47,   40,   39,   41,   44,    0,    0,    0,    0,   46,
   43,   44,   42,   48,   45,   10,   46,   43,    0,   42,
   48,   45,    0,   12,    0,   47,   11,   40,   39,   41,
    0,   44,   47,    0,    0,   55,   46,   43,   44,   42,
   48,   45,   47,   46,   43,    0,   42,   48,   45,    0,
    0,    0,    0,   12,   40,   39,   41,   30,   47,    0,
   78,   40,   39,   41,   49,   47,    0,    0,    0,    0,
   44,    0,    0,    0,    0,   46,   43,    0,   42,   48,
   45,    0,    0,    0,    0,   47,    0,    0,    0,    0,
   53,    0,   47,   40,   39,   41,   13,    0,    0,    0,
   13,    0,   13,   13,   13,    0,    0,    0,    0,    0,
    0,    0,   25,   26,   27,   28,   29,    0,   13,   13,
   13,   13,   44,    0,   47,   50,   79,   46,   43,    0,
   42,   48,   45,   44,    0,    0,    0,    0,   46,   43,
    0,   42,   48,   45,    0,   40,   39,   41,    0,    0,
   10,    0,   13,   72,    0,   10,   40,   76,   41,   10,
   10,   11,    0,    0,    0,    0,   11,    0,    0,   10,
   11,   11,   10,   13,    0,    0,   47,   14,   15,    0,
   11,    0,   13,   11,    0,   16,    0,   47,   12,    0,
    0,    0,    0,   12,    0,    0,    0,   12,   12,   33,
    0,    0,    0,    0,   34,    0,    0,   12,   35,   36,
   12,    0,    0,   18,    0,    0,   18,    0,   37,    0,
   14,   38,   14,   14,   14,   33,    0,    0,    0,    0,
   34,   18,   18,   18,   35,   36,    0,    0,   14,   14,
   14,   14,    0,    0,   37,   44,    0,   38,    0,    0,
   46,   43,   33,   42,   48,   45,    0,   34,    0,   33,
    0,   35,   36,    0,   34,   18,    0,    0,   35,   36,
   41,   37,   14,    0,   38,    1,    0,    0,   37,    2,
    3,   38,    0,    0,    0,    9,    4,    0,    9,    9,
    0,   33,    0,    5,    0,   18,   34,    0,    0,   47,
   35,   36,   14,    9,    9,    9,    9,    0,    0,    0,
   37,    0,    0,   38,    0,    0,    0,   13,    0,    0,
   19,    0,   13,   19,    0,    0,   13,   13,    0,    0,
    0,   17,    0,    0,   17,    0,   13,    9,   19,   13,
   19,    0,    0,   33,    0,    0,    0,    0,   34,   17,
   17,   17,   35,   36,   33,    0,    0,   15,    0,   34,
   15,   15,   37,   35,   36,   38,    0,    9,   16,    0,
    0,   16,   19,   37,    0,   15,   15,   15,   15,    0,
    0,   20,    0,   17,   20,    0,   16,   16,   16,   16,
    0,    0,   21,    0,    0,   21,    0,    0,    0,   20,
    0,   20,   19,   22,    0,    0,   22,    0,   23,   15,
   21,   23,   21,   17,    0,    0,    0,    0,    0,    0,
   16,   22,   44,   22,    0,    0,   23,   46,   23,    0,
   18,   48,   45,   20,    0,   18,    0,   14,    0,   15,
   18,    0,   14,    0,   21,    0,   14,   14,    0,   18,
   16,    0,   18,    0,    0,   22,   14,    0,    0,   14,
   23,   44,    0,   20,    0,    0,   46,   43,    0,   42,
   48,   45,   44,    0,   21,   35,   47,   46,   43,    0,
   42,   48,   45,    0,   40,   22,   41,    0,    0,    0,
   23,    0,    0,    0,    0,   40,    0,   41,    0,    0,
   44,    0,    9,    0,    0,   46,   43,    9,   42,   48,
   45,    9,    9,    0,    0,   47,    0,    0,    0,    0,
    0,    9,   44,   40,    9,   41,   47,   46,   43,    0,
    0,   48,   45,    0,    0,    0,    0,   19,    0,    0,
    0,    0,   19,    0,    0,    0,    0,   19,   17,    0,
    0,    0,    0,   17,   47,   44,   19,   17,   17,   19,
   46,   43,    0,   42,   48,   45,    0,   17,    0,    0,
   17,    0,    0,    0,   15,    0,   47,    0,   40,   15,
   41,    0,    0,   15,   15,   16,    0,    0,    0,    0,
   16,    0,    0,   15,   16,   16,   15,    0,   20,    0,
    0,    0,    0,    0,   16,    0,    0,   16,   20,   47,
    0,    0,    0,    0,    0,    0,    0,   20,    0,   21,
   20,    0,    0,    0,    0,    0,    0,   20,   21,    0,
   23,   21,    0,    0,    0,    0,    0,    0,    0,   22,
    0,    0,   22,   30,   31,   32,    0,   23,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   56,
   57,   58,   59,   60,   61,   62,   63,   64,   65,   66,
   67,   68,   69,   70,   71,   44,    0,    0,    0,    0,
   46,   43,   33,   42,   48,   45,    0,   34,    0,    0,
    0,   35,   36,   33,    0,    0,    0,    0,   34,    0,
   41,   82,   35,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   34,    0,    0,   47,
   35,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   35,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
  123,   40,    0,   41,   42,   43,   44,   45,   41,   47,
   37,   44,   40,  125,   41,   42,   43,   44,   45,   33,
   47,   59,   60,   61,   62,   46,   40,  125,   59,  125,
   59,   45,   59,   60,   61,   62,   41,   37,    0,   44,
  125,   41,   24,   43,   44,   45,   42,   47,   37,   41,
   46,   47,   44,   42,   43,   93,   45,   46,   47,   59,
   60,   61,   62,   42,   -1,   41,   93,   46,   44,   -1,
   91,   60,   61,   62,   37,   -1,   -1,   -1,   -1,   42,
   43,   37,   45,   46,   47,  123,   42,   43,   -1,   45,
   46,   47,   -1,   93,   -1,   91,  123,   60,   61,   62,
   -1,   37,   91,   -1,   -1,   41,   42,   43,   37,   45,
   46,   47,   91,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   -1,  123,   60,   61,   62,  125,   91,   -1,
   59,   60,   61,   62,  123,   91,   -1,   -1,   -1,   -1,
   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,
  123,   -1,   91,   60,   61,   62,   37,   -1,   -1,   -1,
   41,   -1,   43,   44,   45,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,    8,    9,   10,   11,   12,   -1,   59,   60,
   61,   62,   37,   -1,   91,   21,   93,   42,   43,   -1,
   45,   46,   47,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   60,   61,   62,   -1,   -1,
  258,   -1,   93,   49,   -1,  263,   60,   53,   62,  267,
  268,  258,   -1,   -1,   -1,   -1,  263,   -1,   -1,  277,
  267,  268,  280,  257,   -1,   -1,   91,  261,  262,   -1,
  277,   -1,  123,  280,   -1,  269,   -1,   91,  258,   -1,
   -1,   -1,   -1,  263,   -1,   -1,   -1,  267,  268,  258,
   -1,   -1,   -1,   -1,  263,   -1,   -1,  277,  267,  268,
  280,   -1,   -1,   41,   -1,   -1,   44,   -1,  277,   -1,
   41,  280,   43,   44,   45,  258,   -1,   -1,   -1,   -1,
  263,   59,   60,   61,  267,  268,   -1,   -1,   59,   60,
   61,   62,   -1,   -1,  277,   37,   -1,  280,   -1,   -1,
   42,   43,  258,   45,   46,   47,   -1,  263,   -1,  258,
   -1,  267,  268,   -1,  263,   93,   -1,   -1,  267,  268,
   62,  277,   93,   -1,  280,  260,   -1,   -1,  277,  264,
  265,  280,   -1,   -1,   -1,   41,  271,   -1,   44,   45,
   -1,  258,   -1,  278,   -1,  123,  263,   -1,   -1,   91,
  267,  268,  123,   59,   60,   61,   62,   -1,   -1,   -1,
  277,   -1,   -1,  280,   -1,   -1,   -1,  258,   -1,   -1,
   41,   -1,  263,   44,   -1,   -1,  267,  268,   -1,   -1,
   -1,   41,   -1,   -1,   44,   -1,  277,   93,   59,  280,
   61,   -1,   -1,  258,   -1,   -1,   -1,   -1,  263,   59,
   60,   61,  267,  268,  258,   -1,   -1,   41,   -1,  263,
   44,   45,  277,  267,  268,  280,   -1,  123,   41,   -1,
   -1,   44,   93,  277,   -1,   59,   60,   61,   62,   -1,
   -1,   41,   -1,   93,   44,   -1,   59,   60,   61,   62,
   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,   59,
   -1,   61,  123,   41,   -1,   -1,   44,   -1,   41,   93,
   59,   44,   61,  123,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   59,   37,   61,   -1,   -1,   59,   42,   61,   -1,
  258,   46,   47,   93,   -1,  263,   -1,  258,   -1,  123,
  268,   -1,  263,   -1,   93,   -1,  267,  268,   -1,  277,
  123,   -1,  280,   -1,   -1,   93,  277,   -1,   -1,  280,
   93,   37,   -1,  123,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   37,   -1,  123,  267,   91,   42,   43,   -1,
   45,   46,   47,   -1,   60,  123,   62,   -1,   -1,   -1,
  123,   -1,   -1,   -1,   -1,   60,   -1,   62,   -1,   -1,
   37,   -1,  258,   -1,   -1,   42,   43,  263,   45,   46,
   47,  267,  268,   -1,   -1,   91,   -1,   -1,   -1,   -1,
   -1,  277,   37,   60,  280,   62,   91,   42,   43,   -1,
   -1,   46,   47,   -1,   -1,   -1,   -1,  258,   -1,   -1,
   -1,   -1,  263,   -1,   -1,   -1,   -1,  268,  258,   -1,
   -1,   -1,   -1,  263,   91,   37,  277,  267,  268,  280,
   42,   43,   -1,   45,   46,   47,   -1,  277,   -1,   -1,
  280,   -1,   -1,   -1,  258,   -1,   91,   -1,   60,  263,
   62,   -1,   -1,  267,  268,  258,   -1,   -1,   -1,   -1,
  263,   -1,   -1,  277,  267,  268,  280,   -1,  258,   -1,
   -1,   -1,   -1,   -1,  277,   -1,   -1,  280,  268,   91,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,   -1,  268,
  280,   -1,   -1,   -1,   -1,   -1,   -1,    1,  277,   -1,
    4,  280,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,
   -1,   -1,  280,   17,   18,   19,   -1,  280,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   33,
   34,   35,   36,   37,   38,   39,   40,   41,   42,   43,
   44,   45,   46,   47,   48,   37,   -1,   -1,   -1,   -1,
   42,   43,  258,   45,   46,   47,   -1,  263,   -1,   -1,
   -1,  267,  268,  258,   -1,   -1,   -1,   -1,  263,   -1,
   62,   75,  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  263,   -1,   -1,   91,
  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  267,
};
}
final static short YYFINAL=6;
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
"programa : lista_sentencias",
"expresion : CTE_ENTERA",
"expresion : CTE_REAL",
"expresion : CTE_CARACTER",
"expresion : ID",
"expresion : '(' expresion ')'",
"expresion : expresion '.' expresion",
"expresion : expresion '[' expresion ']'",
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
"expresion : expresion '=' expresion ';'",
"lista_sentencias : sentencia_if lista_sentencias",
"lista_sentencias : sentencia_else lista_sentencias",
"lista_sentencias : sentencia_while lista_sentencias",
"lista_sentencias : sentencia_write lista_sentencias",
"lista_sentencias : sentencia_read lista_sentencias",
"lista_sentencias :",
"sentencia_if : IF expresion '{' lista_sentencias '}'",
"sentencia_else : ELSE '{' lista_sentencias '}'",
"sentencia_while : WHILE expresion '{' lista_sentencias '}'",
"lista_expresiones : expresion",
"lista_expresiones : lista_expresiones ',' expresion",
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

//#line 146 "../../src/sintactico/sintactico.y"

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
//#line 473 "Parser.java"
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
