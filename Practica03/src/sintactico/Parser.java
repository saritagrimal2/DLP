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
public final static short MENOSUNARIO=281;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    2,    2,    2,    2,    2,    2,    2,
    9,    4,    5,    6,   10,   10,    7,    8,    1,    1,
   11,   12,   12,   14,   15,   15,   13,   13,   13,   13,
   13,
};
final static short yylen[] = {                            2,
    2,    1,    1,    1,    1,    3,    3,    4,    2,    2,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    2,    2,    2,    2,    2,    2,    0,
    4,    5,    4,    5,    1,    3,    5,    5,    2,    0,
    4,    1,    3,    3,    1,    2,    1,    1,    1,    4,
    4,
};
final static short yydefred[] = {                        40,
    0,    0,    2,    0,    4,    5,    0,    0,    3,    0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,
    0,    0,    0,   39,    0,    0,    0,    0,    0,   42,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   24,   25,   26,   27,   28,   29,    0,    0,    0,    0,
    0,    0,    0,   48,   47,   49,    0,    0,    0,    6,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    7,    0,   33,    0,    0,
    0,    0,    0,    0,   43,   41,   31,    8,   32,   37,
    0,   34,   38,    0,    0,    0,    0,    0,   46,   51,
   50,   44,
};
final static short yydgoto[] = {                          1,
    2,   16,   17,   18,   19,   20,   21,   22,   23,   60,
   24,  104,   69,  105,  106,
};
final static short yysindex[] = {                         0,
    0,  -33,    0,  382,    0,    0, -122,  -31,    0,  382,
  -23, -260,  382,  382,  382,    0,   94,  -13,  -13,  -13,
  -13,  -13,  -13,    0,  130,  -13,  382,  151,  382,    0,
  126,  -30,  -30,  248,  382,  382,  382,  382,  382,  382,
  382,  382,  382,  382,  382,  382,  382,  382,  382,  382,
    0,    0,    0,    0,    0,    0,  -13, -106,  387,  -38,
  -13,  -20,  -98,    0,    0,    0, -231, -221,  -28,    0,
  738,  -32,  590,  657,  448,  398,  304,  523,  173,  116,
  132,  -24,   -4,  -30,  330,    0,  -82,    0,  -10,  382,
  -69,    9, -260,  -16,    0,    0,    0,    0,    0,    0,
  387,    0,    0,  126, -260,  -56,  -87,   20,    0,    0,
    0,    0,
};
final static short yyrindex[] = {                         0,
    0,   80,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    8,    8,    8,
    8,    8,    8,    0,    0,  -44,    0,    0,    0,    0,
    0,   -8,    3,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -44,    0,   14,    0,
  -44,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  497,  493,  345,  140,  107,  260,    0,  486,  460,  421,
  410,   68,   57,   29,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   16,    0,    0,    0,  -43,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,  812,  778,    0,    0,    0,    0,    0,    0,   54,
    0,   72,  -29,    0,  -19,
};
final static int YYTABLESIZE=1005;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         14,
   26,   30,   89,   67,   46,   90,   15,   30,   27,   48,
   45,   13,   44,   50,   47,   50,   29,   48,   88,   14,
   92,   50,   47,   90,   93,   94,   15,   42,    9,   43,
   96,   13,    9,    9,    9,    9,    9,   48,    9,   10,
   95,   50,   99,   10,   10,   10,   10,   10,  100,   10,
    9,    9,    9,    9,   35,  102,   36,   35,   49,   36,
   49,   10,   10,   10,   10,   11,   49,  103,  110,   11,
   11,   11,   11,   11,  108,   11,  107,  111,  112,   30,
   30,   45,   62,   31,    9,  109,   49,   11,   11,   11,
   11,    0,    0,   12,    0,   10,    0,   12,    0,   12,
   12,   12,    0,   12,   13,    0,    0,    0,   13,    0,
   13,   13,   13,    0,    9,   12,   12,   12,   12,    0,
    0,   11,    0,    0,    0,   10,   13,   13,   13,   13,
   46,    0,   30,    0,    0,   48,   45,    0,   44,   50,
   47,    0,    0,    0,    0,    0,    0,   22,    0,   12,
   22,   11,   46,   42,   41,   43,    0,   48,   45,    0,
   13,   50,   47,    0,    0,   22,   46,   22,   46,   68,
    0,   48,   45,   48,   44,   50,   47,   50,   47,   12,
   21,    0,   63,   21,   49,   64,   65,   46,   66,   42,
   13,   43,   48,   45,    0,   44,   50,   47,   21,   22,
   21,    0,    0,    0,    0,    0,   49,    0,    0,   46,
   42,    0,   43,    0,   48,   45,   67,   44,   50,   47,
   49,    0,   49,    3,    0,    0,    4,    5,    6,   22,
    7,    8,   21,    0,   37,    9,    0,   10,    0,    0,
    0,   49,    0,    3,   11,   12,    4,    5,    6,    9,
    7,    8,   57,    0,    9,    9,    0,   10,    9,    9,
   10,    0,   21,   49,   11,   10,    0,    0,    9,   10,
   10,    9,    0,   61,    0,    0,    0,    0,    0,   10,
    0,    0,   10,    0,   46,    0,   11,    0,   70,   48,
   45,   11,   44,   50,   47,   11,   11,    0,    0,    0,
   23,    0,    0,   23,    0,   11,    0,   42,   11,   43,
    0,    0,    0,    0,   12,    0,    0,    0,   23,   12,
   23,    0,    0,   12,   12,   13,    0,    0,    0,    0,
   13,    0,    0,   12,   13,   13,   12,    0,   49,    0,
   46,    0,    0,    0,   13,   48,   45,   13,   44,   50,
   47,   35,   23,    0,    0,    0,   36,    0,    0,    0,
   37,   38,   97,   42,    0,   43,   46,    0,    0,    0,
   39,   48,   45,   40,   44,   50,   47,    0,    0,    0,
    0,    0,   23,   22,    0,   17,   22,   35,   17,   42,
    0,   43,   36,    0,   49,   63,   37,   38,   64,   65,
    0,   66,    0,   17,   17,   17,   39,   21,   35,   40,
    0,    0,    0,   36,   14,    0,   21,   37,   38,   21,
   49,   15,   98,   46,    0,    0,   13,   39,   48,   45,
   40,   44,   50,   47,   46,    0,    0,   17,    0,   48,
   45,    0,   44,   50,   47,    0,   42,    0,   43,    0,
   14,    0,   14,   14,   14,    0,    0,   42,    0,   43,
    0,   15,    0,    0,   15,   15,    0,   17,   14,   14,
   14,   14,    0,    0,    0,    0,    0,   49,    0,   15,
   15,   15,   15,    0,   46,    0,    0,    0,   49,   48,
   45,    0,   44,   50,   47,    0,    0,    0,    0,    0,
   16,    0,   14,   16,    0,   35,    0,   42,    0,   43,
   36,    0,    0,   15,   37,   38,    0,    0,   16,   16,
   16,   16,    0,    0,   39,    0,   18,   40,    0,   18,
    0,    0,   14,   19,    0,    0,   19,   20,   49,   23,
   20,    0,    0,   15,   18,   18,   18,    0,    0,    0,
    0,   19,   16,   19,    0,   20,    0,   20,    0,   46,
    0,   35,    0,    0,   48,   45,   36,   44,   50,   47,
   37,   38,    0,    0,    0,    0,    0,    0,   18,    0,
   39,    0,   16,   40,   43,   19,    0,   35,    0,   20,
    0,    0,   36,    0,    0,    0,   37,   38,    0,    0,
    0,    0,   17,    0,    0,    0,   39,   17,   18,   40,
    0,   17,   17,   49,    0,   19,    0,    0,    0,   20,
    0,   17,    0,    0,   17,    0,   46,    0,    0,    0,
    0,   48,   45,    0,   44,   50,   47,    0,    3,    0,
    0,    0,    5,    6,   35,    0,    0,    0,    0,   36,
    9,   43,    0,   37,   38,   35,    0,    0,    0,    0,
   36,    0,    0,   39,   37,   38,   40,   14,    0,    0,
    0,    0,   14,    0,   39,    0,   14,   14,   15,    0,
   49,    0,    0,   15,    0,    0,   14,   15,   15,   14,
    0,    0,    0,   46,    0,    0,    0,   15,   48,   45,
   15,   44,   50,   47,    0,   35,    0,    0,    0,    0,
   36,    0,    0,    0,   37,   38,   42,   16,   43,    0,
    0,    0,   16,    0,    0,    0,   16,   16,    0,    0,
    0,    0,    0,    0,    0,    0,   16,    0,    0,   16,
    0,    0,    0,   18,    0,    0,    0,   49,   18,    0,
   19,    0,    0,   18,   20,   19,    0,    0,    0,    0,
   19,    0,   18,    0,   20,   18,    0,    0,    0,   19,
    0,    0,   19,   20,   46,    0,   20,    0,    0,   48,
   45,   25,   44,   50,   47,    0,    0,   28,    0,   37,
   32,   33,   34,    0,    0,    0,    0,   42,    0,   43,
    0,    0,    0,    0,   59,    0,   59,    0,    0,    0,
    0,    0,   71,   72,   73,   74,   75,   76,   77,   78,
   79,   80,   81,   82,   83,   84,   85,   86,   49,   51,
   52,   53,   54,   55,   56,    0,    0,   58,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  101,   87,    0,
    0,    0,   91,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   35,    0,    0,    0,    0,   36,
    0,    0,    0,   37,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   36,    0,    0,    0,   37,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
  123,  262,   41,   91,   37,   44,   40,    0,   40,   42,
   43,   45,   45,   46,   47,   46,   40,   42,  125,   33,
   41,   46,   47,   44,  123,  257,   40,   60,   37,   62,
   59,   45,   41,   42,   43,   44,   45,   42,   47,   37,
  262,   46,  125,   41,   42,   43,   44,   45,   59,   47,
   59,   60,   61,   62,   41,  125,   41,   44,   91,   44,
   91,   59,   60,   61,   62,   37,   91,   59,  125,   41,
   42,   43,   44,   45,  104,   47,   93,  107,   59,    0,
  125,  125,   29,   12,   93,  105,   91,   59,   60,   61,
   62,   -1,   -1,   37,   -1,   93,   -1,   41,   -1,   43,
   44,   45,   -1,   47,   37,   -1,   -1,   -1,   41,   -1,
   43,   44,   45,   -1,  123,   59,   60,   61,   62,   -1,
   -1,   93,   -1,   -1,   -1,  123,   59,   60,   61,   62,
   37,   -1,  125,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   -1,   41,   -1,   93,
   44,  123,   37,   60,   61,   62,   -1,   42,   43,   -1,
   93,   46,   47,   -1,   -1,   59,   37,   61,   37,   44,
   -1,   42,   43,   42,   45,   46,   47,   46,   47,  123,
   41,   -1,  270,   44,   91,  273,  274,   37,  276,   60,
  123,   62,   42,   43,   -1,   45,   46,   47,   59,   93,
   61,   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,   37,
   60,   -1,   62,   -1,   42,   43,   91,   45,   46,   47,
   91,   -1,   91,  257,   -1,   -1,  260,  261,  262,  123,
  264,  265,   93,   -1,  267,  269,   -1,  271,   -1,   -1,
   -1,   91,   -1,  257,  278,  279,  260,  261,  262,  258,
  264,  265,  123,   -1,  263,  269,   -1,  271,  267,  268,
  258,   -1,  123,   91,  278,  263,   -1,   -1,  277,  267,
  268,  280,   -1,  123,   -1,   -1,   -1,   -1,   -1,  277,
   -1,   -1,  280,   -1,   37,   -1,  258,   -1,   41,   42,
   43,  263,   45,   46,   47,  267,  268,   -1,   -1,   -1,
   41,   -1,   -1,   44,   -1,  277,   -1,   60,  280,   62,
   -1,   -1,   -1,   -1,  258,   -1,   -1,   -1,   59,  263,
   61,   -1,   -1,  267,  268,  258,   -1,   -1,   -1,   -1,
  263,   -1,   -1,  277,  267,  268,  280,   -1,   91,   -1,
   37,   -1,   -1,   -1,  277,   42,   43,  280,   45,   46,
   47,  258,   93,   -1,   -1,   -1,  263,   -1,   -1,   -1,
  267,  268,   59,   60,   -1,   62,   37,   -1,   -1,   -1,
  277,   42,   43,  280,   45,   46,   47,   -1,   -1,   -1,
   -1,   -1,  123,  277,   -1,   41,  280,  258,   44,   60,
   -1,   62,  263,   -1,   91,  270,  267,  268,  273,  274,
   -1,  276,   -1,   59,   60,   61,  277,  268,  258,  280,
   -1,   -1,   -1,  263,   33,   -1,  277,  267,  268,  280,
   91,   40,   93,   37,   -1,   -1,   45,  277,   42,   43,
  280,   45,   46,   47,   37,   -1,   -1,   93,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   60,   -1,   62,   -1,
   41,   -1,   43,   44,   45,   -1,   -1,   60,   -1,   62,
   -1,   41,   -1,   -1,   44,   45,   -1,  123,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   91,   -1,   59,
   60,   61,   62,   -1,   37,   -1,   -1,   -1,   91,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,
   41,   -1,   93,   44,   -1,  258,   -1,   60,   -1,   62,
  263,   -1,   -1,   93,  267,  268,   -1,   -1,   59,   60,
   61,   62,   -1,   -1,  277,   -1,   41,  280,   -1,   44,
   -1,   -1,  123,   41,   -1,   -1,   44,   41,   91,  280,
   44,   -1,   -1,  123,   59,   60,   61,   -1,   -1,   -1,
   -1,   59,   93,   61,   -1,   59,   -1,   61,   -1,   37,
   -1,  258,   -1,   -1,   42,   43,  263,   45,   46,   47,
  267,  268,   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,
  277,   -1,  123,  280,   62,   93,   -1,  258,   -1,   93,
   -1,   -1,  263,   -1,   -1,   -1,  267,  268,   -1,   -1,
   -1,   -1,  258,   -1,   -1,   -1,  277,  263,  123,  280,
   -1,  267,  268,   91,   -1,  123,   -1,   -1,   -1,  123,
   -1,  277,   -1,   -1,  280,   -1,   37,   -1,   -1,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   -1,  257,   -1,
   -1,   -1,  261,  262,  258,   -1,   -1,   -1,   -1,  263,
  269,   62,   -1,  267,  268,  258,   -1,   -1,   -1,   -1,
  263,   -1,   -1,  277,  267,  268,  280,  258,   -1,   -1,
   -1,   -1,  263,   -1,  277,   -1,  267,  268,  258,   -1,
   91,   -1,   -1,  263,   -1,   -1,  277,  267,  268,  280,
   -1,   -1,   -1,   37,   -1,   -1,   -1,  277,   42,   43,
  280,   45,   46,   47,   -1,  258,   -1,   -1,   -1,   -1,
  263,   -1,   -1,   -1,  267,  268,   60,  258,   62,   -1,
   -1,   -1,  263,   -1,   -1,   -1,  267,  268,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  277,   -1,   -1,  280,
   -1,   -1,   -1,  258,   -1,   -1,   -1,   91,  263,   -1,
  258,   -1,   -1,  268,  258,  263,   -1,   -1,   -1,   -1,
  268,   -1,  277,   -1,  268,  280,   -1,   -1,   -1,  277,
   -1,   -1,  280,  277,   37,   -1,  280,   -1,   -1,   42,
   43,    4,   45,   46,   47,   -1,   -1,   10,   -1,  267,
   13,   14,   15,   -1,   -1,   -1,   -1,   60,   -1,   62,
   -1,   -1,   -1,   -1,   27,   -1,   29,   -1,   -1,   -1,
   -1,   -1,   35,   36,   37,   38,   39,   40,   41,   42,
   43,   44,   45,   46,   47,   48,   49,   50,   91,   18,
   19,   20,   21,   22,   23,   -1,   -1,   26,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   90,   57,   -1,
   -1,   -1,   61,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  258,   -1,   -1,   -1,   -1,  263,
   -1,   -1,   -1,  267,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  263,   -1,   -1,   -1,  267,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=281;
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
"CHAR","AND","READ","VAR","OR","MENOSUNARIO",
};
final static String yyrule[] = {
"$accept : programa",
"programa : lista_variables lista_sentencias",
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
"lista_sentencias : sentencia_if lista_sentencias",
"lista_sentencias : sentencia_else lista_sentencias",
"lista_sentencias : sentencia_while lista_sentencias",
"lista_sentencias : sentencia_write lista_sentencias",
"lista_sentencias : sentencia_read lista_sentencias",
"lista_sentencias : sentencia_asignacion lista_sentencias",
"lista_sentencias :",
"sentencia_asignacion : expresion '=' expresion ';'",
"sentencia_if : IF expresion '{' lista_sentencias '}'",
"sentencia_else : ELSE '{' lista_sentencias '}'",
"sentencia_while : WHILE expresion '{' lista_sentencias '}'",
"lista_expresiones : expresion",
"lista_expresiones : lista_expresiones ',' expresion",
"sentencia_write : WRITE '(' lista_expresiones ')' ';'",
"sentencia_read : READ '(' lista_expresiones ')' ';'",
"lista_variables : lista_variables definicion_variable",
"lista_variables :",
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

//#line 149 "../../src/sintactico/sintactico.y"

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
//#line 516 "Parser.java"
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
