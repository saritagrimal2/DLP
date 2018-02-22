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
    0,    1,    1,    4,    4,    7,    7,    7,    7,    7,
    7,    7,    7,    7,    7,    7,    7,    7,    7,    7,
    7,    7,    7,    7,    7,    7,    7,    7,    7,    7,
    3,    3,    3,    3,    3,    3,    3,    3,    3,   15,
   10,   11,   12,    9,    9,   18,   18,   13,   14,   16,
   17,    2,    2,    6,   19,   19,   20,   21,   21,    8,
    8,    8,    8,    8,    8,    5,   23,   23,   22,   22,
   24,   24,
};
final static short yylen[] = {                            2,
    9,    2,    0,    1,    1,    1,    1,    1,    1,    3,
    3,    4,    2,    2,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    4,    4,
    2,    2,    2,    2,    2,    2,    1,    2,    0,    4,
    5,    4,    5,    1,    0,    1,    3,    5,    5,    3,
    5,    2,    0,    4,    1,    3,    3,    1,    2,    1,
    1,    1,    4,    4,    1,   10,    1,    0,    1,    0,
    2,    4,
};
final static short yydefred[] = {                         3,
    0,    0,    0,    0,    2,    4,    5,    0,    0,   55,
    0,    0,    0,   65,    0,   61,   60,   62,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   56,   54,    0,
   71,    0,    0,    0,    0,    0,    0,    0,    0,   67,
    0,    0,    0,   59,   64,   63,    6,    0,    8,    0,
    0,    0,    7,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   37,    0,
   52,    0,   72,   57,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   31,   32,   33,   34,   35,   36,   38,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   50,    0,   10,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   11,    0,
    0,    0,    0,    0,    0,    0,   42,    0,    0,    0,
   40,   12,   29,   66,   30,   41,   51,    0,   48,   43,
   49,
};
final static short yydgoto[] = {                          1,
    2,   38,   60,    5,    6,   39,   61,   62,  116,   63,
   64,   65,   66,   67,   68,   69,   70,  117,   34,   35,
   36,   24,   41,   25,
};
final static short yysindex[] = {                         0,
    0, -249, -253, -252,    0,    0,    0,  -25,  -12,    0,
   76,  -22, -224,    0,  -84,    0,    0,    0, -216, -220,
  -15,  -68,  -87,   16,   17, -252,  -24,    0,    0, -219,
    0,  -87, -191,   76, -252,  -53,  -87,  -13, -219,    0,
  -50,  -87,   15,    0,    0,    0,    0,  596,    0,   35,
  -47,   37,    0,  596,  596,   40,  596,  596,  596,  -44,
  498,   48,  -13,  -13,  -13,  -13,  -13,  -13,    0,  -13,
    0, -219,    0,    0,   50,  131,  596,  -13,  596,  270,
  360,  596,  -45,  -45,  392,    0,  596,  596,  596,  596,
  596,  596,  596,  596,  596,  596,  596,  596,  596,  596,
  596,  596,  596,    0,    0,    0,    0,    0,    0,    0,
  -13,  596,  596,  -13,  509,   41,   47,  -31,   54,  -13,
    0,   55,    0,  -21,  -21,  -21,  -21,  784,  784,  399,
  -21,  -21,  -29,  -29,  -45,  -45,  -45,  425,    0,  462,
  -27,   59,  509,  -20,   51,  596,    0,   52,  -19,   58,
    0,    0,    0,    0,    0,    0,    0,  509,    0,    0,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   66,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   68,    0,    0,    0,    0,  -33,
    0,   -2,    0,    0,   -6,    0,    0,   -3,  -33,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  535,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   -3,   -3,   -3,   -3,   -3,   -3,    0,   -3,
    0,  -33,    0,    0,    6,    0,   82,   -3,   82,    0,
    0,   82,   42,   71,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   -3,   82,    0,   -3,  -10,    0,   83,    0,    0,   -3,
    0,    0,    0,  650,  763,  819,  844,  102,  111,    0,
  884,  891,  656,  691,   97,  234,  325,    0,    0,    0,
    0,    0,  -30,    0,  570,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   -4,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,  -36,  924,    0,    0,  123, 1108,   22,  -77,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  122,    0,
   92,    0,    0,    0,
};
final static int YYTABLESIZE=1254;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         53,
  102,  119,   71,   19,  122,    8,   53,  100,    9,   10,
   28,   53,   98,   28,   12,  100,  102,   99,   22,   58,
   98,   96,    3,   97,  102,   99,   59,   13,   28,    4,
   46,   57,   21,   46,  142,  111,   47,   23,   26,   47,
   27,   28,    9,   29,   31,  101,    9,    9,    9,    9,
    9,    9,    9,   40,   30,   43,   32,   53,   46,    4,
   33,  101,   28,   73,    9,    9,    9,    9,   37,  101,
   42,   45,   72,   74,   77,   78,   79,   19,   13,   82,
   86,  145,   13,   13,   13,   13,   13,  103,   13,  112,
  146,   53,   28,  147,  148,  150,    9,  154,    9,  155,
   13,   13,   13,   13,  156,  160,   70,   14,   69,  157,
  159,   14,   14,   14,   14,   14,  161,   14,   58,   20,
   68,   39,   45,   44,    7,   11,   44,    0,    9,   14,
   14,   14,   14,   15,   13,    0,    0,   15,   15,   15,
   15,   15,   26,   15,    0,   26,    0,    0,    0,    0,
    0,   27,    0,    0,   27,   15,   15,   15,   15,    0,
   26,    0,   26,   14,   13,    0,   19,  100,    0,   27,
    0,   27,   98,   96,    0,   97,  102,   99,   14,    0,
    0,    0,   15,    0,    0,   16,   17,    0,   18,   15,
   95,  113,   94,   14,   26,    0,    0,    0,    0,    0,
    0,    0,    0,   27,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   15,
    0,  101,    0,   53,   26,    0,   53,   53,   53,    0,
   53,   53,   53,   27,    0,   53,   53,   53,    0,   53,
   53,   53,   53,   47,   53,    0,   48,   49,   50,    0,
   51,   52,   14,  114,    0,   53,   15,   54,    0,   16,
   17,   55,   18,    9,   56,    0,    0,    0,    9,    0,
   16,    0,    9,    9,   16,   16,   16,   16,   16,    0,
   16,    0,    9,    0,    0,    9,    0,    0,    0,    0,
    0,    0,   16,   16,   16,   16,    0,    0,    0,   13,
    0,    0,    0,    0,   13,    0,  100,    0,   13,   13,
    0,   98,   96,    0,   97,  102,   99,    0,   13,    0,
    0,   13,    0,    0,    0,    0,   16,    0,   14,   95,
  113,   94,    0,   14,    0,    0,    0,   14,   14,    0,
    0,   14,    0,    0,    0,   15,    0,   14,   16,   17,
   14,   18,    0,    0,   15,    0,   16,    0,    0,   15,
  101,   17,    0,   15,   15,   17,   17,   17,   17,   17,
    0,   17,    0,   15,    0,    0,   15,    0,   26,    0,
    0,   26,    0,   17,   17,   17,   17,   27,   87,    0,
   27,    0,  120,   88,    0,    0,  100,   89,   90,    0,
    0,   98,   96,    0,   97,  102,   99,   91,    0,    0,
   92,    0,    0,    0,    0,    0,    0,   17,  121,   95,
  113,   94,    0,    0,    0,    0,    0,    0,  100,    0,
    0,    0,  123,   98,   96,  100,   97,  102,   99,    0,
   98,   96,    0,   97,  102,   99,    0,   17,    0,    0,
  101,   95,  113,   94,    0,    0,    0,  151,   95,  113,
   94,  100,    0,    0,    0,    0,   98,   96,    0,   97,
  102,   99,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  101,    0,   95,  113,   94,    0,    0,  101,
    0,   16,    0,    0,    0,    0,   16,    0,  100,    0,
   16,   16,  153,   98,   96,    0,   97,  102,   99,    0,
   16,    0,    0,   16,    0,  101,    0,  152,    0,    0,
    0,   95,  113,   94,    0,    0,    0,   87,    0,    0,
    0,    0,   88,    0,  100,    0,   89,   90,    0,   98,
   96,    0,   97,  102,   99,  100,   91,    0,    0,   92,
   98,   96,  101,   97,  102,   99,    0,   95,   93,   94,
    0,    0,    0,    0,    0,    0,    0,    0,   95,  113,
   94,    9,    0,    0,    0,    0,    9,    9,    0,    9,
    9,    9,   17,    0,    0,    0,    0,   17,  101,    0,
    0,   17,   17,    0,    9,    9,    9,    0,    0,  101,
    0,   17,    0,    0,   17,    0,   30,    0,    0,    0,
    0,   30,   30,    0,   30,   30,   30,   87,    0,    0,
    0,    0,   88,    0,    0,    9,   89,   90,   58,   30,
   30,   30,    0,    0,    0,   59,   91,    0,    0,   92,
   57,    0,    0,    0,    0,    0,    0,    0,    0,   87,
    0,    0,    0,    0,   88,    0,   87,    0,   89,   90,
   30,   88,    0,    0,    0,   89,   90,    0,   91,    0,
    0,   92,    0,    0,    0,   91,    0,    0,   92,    0,
    0,    0,   87,    0,    0,    0,   19,   88,    0,    0,
   24,   89,   90,   24,    0,    0,   18,    0,   18,   18,
   18,   91,    0,    0,   92,    0,    0,    0,   24,   24,
   24,   24,    0,    0,   18,   18,   18,   18,    0,   87,
    0,    0,    0,    0,   88,    0,    0,    0,   89,   90,
    0,   19,    0,   19,   19,   19,    0,    0,   91,    0,
    0,   92,   24,    0,    0,    0,    0,    0,   18,   19,
   19,   19,   19,    0,    0,   87,    0,    0,    0,    0,
   88,    0,    0,    0,   89,   90,   87,    0,    0,    0,
    0,   88,   24,    0,   91,   89,   90,   92,   18,    0,
    0,    0,    0,   19,    0,   91,    0,    0,   92,    0,
    0,    0,    9,    0,    0,    0,    0,    9,    0,    0,
    0,    9,    9,   23,    0,    0,   23,    0,    0,    0,
    0,    9,    0,   19,    9,    0,    0,    0,    0,    0,
  100,   23,   23,   23,   23,   98,   96,   30,   97,  102,
   99,    0,   30,    0,    0,    0,   30,   30,    0,    0,
    0,    0,    0,   95,    0,   94,   30,    0,    0,   30,
    0,    0,   47,    0,    0,   23,   49,   75,    0,   21,
    0,   14,   21,    0,   53,   15,    0,    0,   16,   17,
    0,   18,    0,    0,  101,    0,    0,   21,   21,   21,
   21,    0,    0,    0,   25,   23,    0,   25,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   25,   25,   25,   25,    0,   24,    0,    0,
    0,   21,   24,   18,    0,    0,   24,   24,   18,    0,
    0,    0,   18,   18,   20,    0,   24,   20,    0,   24,
    0,   22,   18,    0,   22,   18,   25,    0,    0,    0,
    0,   21,   20,   20,   20,   20,    0,    0,   19,   22,
   22,   22,   22,   19,    0,    0,    0,   19,   19,    0,
    0,    0,    0,    0,    0,    0,   25,   19,    0,    0,
   19,    0,    0,    0,    0,    0,   20,    0,    0,    0,
    0,    0,    0,   22,    0,    0,  104,  105,  106,  107,
  108,  109,    0,  110,    0,    0,    0,    0,    0,    0,
    0,  118,    0,    0,    0,    0,   20,    0,    0,    0,
    0,    0,    0,   22,    0,    0,    0,    0,    0,    0,
   23,    0,    0,    0,    0,   23,    0,    0,    0,   23,
   23,    0,    0,    0,  141,    0,    0,  144,    0,   23,
    0,   87,   23,  149,    0,    0,   88,    0,    0,    0,
   89,   90,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   21,    0,    0,    0,
    0,   21,    0,    0,    0,   21,   21,    0,    0,    0,
    0,    0,    0,    0,    0,   21,    0,    0,   21,    0,
    0,   25,    0,    0,    0,    0,   25,    0,    0,    0,
   25,   25,    0,    0,    0,    0,    0,    0,    0,    0,
   25,    0,    0,   25,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   20,    0,    0,    0,    0,   20,    0,   22,    0,
   20,   20,    0,   22,    0,   76,    0,   22,   22,    0,
   20,   80,   81,   20,   83,   84,   85,   22,    0,    0,
   22,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  115,    0,  115,    0,    0,  115,
    0,    0,    0,    0,  124,  125,  126,  127,  128,  129,
  130,  131,  132,  133,  134,  135,  136,  137,  138,  139,
  140,    0,    0,    0,    0,    0,    0,    0,    0,  115,
  143,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  158,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   46,   79,   39,   91,   82,  259,   40,   37,  262,  262,
   41,   45,   42,   44,   40,   37,   46,   47,   41,   33,
   42,   43,  272,   45,   46,   47,   40,   40,   59,  279,
   41,   45,   11,   44,  112,   72,   41,  262,  123,   44,
  257,  262,   37,   59,   23,   91,   41,   42,   43,   44,
   45,   46,   47,   32,  123,   34,   41,   91,   37,  279,
   44,   91,   93,   42,   59,   60,   61,   62,   93,   91,
  262,  125,  123,   59,   40,  123,   40,   91,   37,   40,
  125,   41,   41,   42,   43,   44,   45,   40,   47,   40,
   44,  125,  123,  125,   41,   41,   91,  125,   93,   41,
   59,   60,   61,   62,  125,  125,   41,   37,   41,   59,
   59,   41,   42,   43,   44,   45,   59,   47,  125,   44,
  123,  125,   41,   41,    2,    4,   35,   -1,  123,   59,
   60,   61,   62,   37,   93,   -1,   -1,   41,   42,   43,
   44,   45,   41,   47,   -1,   44,   -1,   -1,   -1,   -1,
   -1,   41,   -1,   -1,   44,   59,   60,   61,   62,   -1,
   59,   -1,   61,   93,  123,   -1,   91,   37,   -1,   59,
   -1,   61,   42,   43,   -1,   45,   46,   47,  266,   -1,
   -1,   -1,  270,   -1,   -1,  273,  274,   -1,  276,   93,
   60,   61,   62,  123,   93,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,
   -1,   91,   -1,  257,  123,   -1,  260,  261,  262,   -1,
  264,  265,  266,  123,   -1,  269,  270,  271,   -1,  273,
  274,  275,  276,  257,  278,   -1,  260,  261,  262,   -1,
  264,  265,  266,  123,   -1,  269,  270,  271,   -1,  273,
  274,  275,  276,  258,  278,   -1,   -1,   -1,  263,   -1,
   37,   -1,  267,  268,   41,   42,   43,   44,   45,   -1,
   47,   -1,  277,   -1,   -1,  280,   -1,   -1,   -1,   -1,
   -1,   -1,   59,   60,   61,   62,   -1,   -1,   -1,  258,
   -1,   -1,   -1,   -1,  263,   -1,   37,   -1,  267,  268,
   -1,   42,   43,   -1,   45,   46,   47,   -1,  277,   -1,
   -1,  280,   -1,   -1,   -1,   -1,   93,   -1,  258,   60,
   61,   62,   -1,  263,   -1,   -1,   -1,  267,  268,   -1,
   -1,  266,   -1,   -1,   -1,  270,   -1,  277,  273,  274,
  280,  276,   -1,   -1,  258,   -1,  123,   -1,   -1,  263,
   91,   37,   -1,  267,  268,   41,   42,   43,   44,   45,
   -1,   47,   -1,  277,   -1,   -1,  280,   -1,  277,   -1,
   -1,  280,   -1,   59,   60,   61,   62,  277,  258,   -1,
  280,   -1,  123,  263,   -1,   -1,   37,  267,  268,   -1,
   -1,   42,   43,   -1,   45,   46,   47,  277,   -1,   -1,
  280,   -1,   -1,   -1,   -1,   -1,   -1,   93,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   37,   -1,
   -1,   -1,   41,   42,   43,   37,   45,   46,   47,   -1,
   42,   43,   -1,   45,   46,   47,   -1,  123,   -1,   -1,
   91,   60,   61,   62,   -1,   -1,   -1,   59,   60,   61,
   62,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   91,   -1,   60,   61,   62,   -1,   -1,   91,
   -1,  258,   -1,   -1,   -1,   -1,  263,   -1,   37,   -1,
  267,  268,   41,   42,   43,   -1,   45,   46,   47,   -1,
  277,   -1,   -1,  280,   -1,   91,   -1,   93,   -1,   -1,
   -1,   60,   61,   62,   -1,   -1,   -1,  258,   -1,   -1,
   -1,   -1,  263,   -1,   37,   -1,  267,  268,   -1,   42,
   43,   -1,   45,   46,   47,   37,  277,   -1,   -1,  280,
   42,   43,   91,   45,   46,   47,   -1,   60,   61,   62,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   60,   61,
   62,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,  258,   -1,   -1,   -1,   -1,  263,   91,   -1,
   -1,  267,  268,   -1,   60,   61,   62,   -1,   -1,   91,
   -1,  277,   -1,   -1,  280,   -1,   37,   -1,   -1,   -1,
   -1,   42,   43,   -1,   45,   46,   47,  258,   -1,   -1,
   -1,   -1,  263,   -1,   -1,   91,  267,  268,   33,   60,
   61,   62,   -1,   -1,   -1,   40,  277,   -1,   -1,  280,
   45,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  258,
   -1,   -1,   -1,   -1,  263,   -1,  258,   -1,  267,  268,
   91,  263,   -1,   -1,   -1,  267,  268,   -1,  277,   -1,
   -1,  280,   -1,   -1,   -1,  277,   -1,   -1,  280,   -1,
   -1,   -1,  258,   -1,   -1,   -1,   91,  263,   -1,   -1,
   41,  267,  268,   44,   -1,   -1,   41,   -1,   43,   44,
   45,  277,   -1,   -1,  280,   -1,   -1,   -1,   59,   60,
   61,   62,   -1,   -1,   59,   60,   61,   62,   -1,  258,
   -1,   -1,   -1,   -1,  263,   -1,   -1,   -1,  267,  268,
   -1,   41,   -1,   43,   44,   45,   -1,   -1,  277,   -1,
   -1,  280,   93,   -1,   -1,   -1,   -1,   -1,   93,   59,
   60,   61,   62,   -1,   -1,  258,   -1,   -1,   -1,   -1,
  263,   -1,   -1,   -1,  267,  268,  258,   -1,   -1,   -1,
   -1,  263,  123,   -1,  277,  267,  268,  280,  123,   -1,
   -1,   -1,   -1,   93,   -1,  277,   -1,   -1,  280,   -1,
   -1,   -1,  258,   -1,   -1,   -1,   -1,  263,   -1,   -1,
   -1,  267,  268,   41,   -1,   -1,   44,   -1,   -1,   -1,
   -1,  277,   -1,  123,  280,   -1,   -1,   -1,   -1,   -1,
   37,   59,   60,   61,   62,   42,   43,  258,   45,   46,
   47,   -1,  263,   -1,   -1,   -1,  267,  268,   -1,   -1,
   -1,   -1,   -1,   60,   -1,   62,  277,   -1,   -1,  280,
   -1,   -1,  257,   -1,   -1,   93,  261,  262,   -1,   41,
   -1,  266,   44,   -1,  269,  270,   -1,   -1,  273,  274,
   -1,  276,   -1,   -1,   91,   -1,   -1,   59,   60,   61,
   62,   -1,   -1,   -1,   41,  123,   -1,   44,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   59,   60,   61,   62,   -1,  258,   -1,   -1,
   -1,   93,  263,  258,   -1,   -1,  267,  268,  263,   -1,
   -1,   -1,  267,  268,   41,   -1,  277,   44,   -1,  280,
   -1,   41,  277,   -1,   44,  280,   93,   -1,   -1,   -1,
   -1,  123,   59,   60,   61,   62,   -1,   -1,  258,   59,
   60,   61,   62,  263,   -1,   -1,   -1,  267,  268,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  123,  277,   -1,   -1,
  280,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,
   -1,   -1,   -1,   93,   -1,   -1,   63,   64,   65,   66,
   67,   68,   -1,   70,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   78,   -1,   -1,   -1,   -1,  123,   -1,   -1,   -1,
   -1,   -1,   -1,  123,   -1,   -1,   -1,   -1,   -1,   -1,
  258,   -1,   -1,   -1,   -1,  263,   -1,   -1,   -1,  267,
  268,   -1,   -1,   -1,  111,   -1,   -1,  114,   -1,  277,
   -1,  258,  280,  120,   -1,   -1,  263,   -1,   -1,   -1,
  267,  268,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  258,   -1,   -1,   -1,
   -1,  263,   -1,   -1,   -1,  267,  268,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  277,   -1,   -1,  280,   -1,
   -1,  258,   -1,   -1,   -1,   -1,  263,   -1,   -1,   -1,
  267,  268,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  277,   -1,   -1,  280,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  258,   -1,   -1,   -1,   -1,  263,   -1,  258,   -1,
  267,  268,   -1,  263,   -1,   48,   -1,  267,  268,   -1,
  277,   54,   55,  280,   57,   58,   59,  277,   -1,   -1,
  280,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   77,   -1,   79,   -1,   -1,   82,
   -1,   -1,   -1,   -1,   87,   88,   89,   90,   91,   92,
   93,   94,   95,   96,   97,   98,   99,  100,  101,  102,
  103,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  112,
  113,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  146,
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
"programa : lista_definiciones FUNC MAIN '(' ')' '{' lista_variables lista_sentencias '}'",
"lista_definiciones : lista_definiciones list",
"lista_definiciones :",
"list : definicion_funcion",
"list : definicion_variable",
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
"expresion : expresion '=' expresion",
"expresion : tipo '(' expresion ')'",
"expresion : ID '(' lista_expresiones ')'",
"lista_sentencias : sentencia_if lista_sentencias",
"lista_sentencias : sentencia_else lista_sentencias",
"lista_sentencias : sentencia_while lista_sentencias",
"lista_sentencias : sentencia_write lista_sentencias",
"lista_sentencias : sentencia_read lista_sentencias",
"lista_sentencias : sentencia_asignacion lista_sentencias",
"lista_sentencias : sentencia_return",
"lista_sentencias : sentencia_invocacion lista_sentencias",
"lista_sentencias :",
"sentencia_asignacion : expresion '=' expresion ';'",
"sentencia_if : IF expresion '{' lista_sentencias '}'",
"sentencia_else : ELSE '{' lista_sentencias '}'",
"sentencia_while : WHILE expresion '{' lista_sentencias '}'",
"lista_expresiones : expresiones",
"lista_expresiones :",
"expresiones : expresion",
"expresiones : expresiones ',' expresion",
"sentencia_write : WRITE '(' lista_expresiones ')' ';'",
"sentencia_read : READ '(' lista_expresiones ')' ';'",
"sentencia_return : RETURN expresion ';'",
"sentencia_invocacion : ID '(' lista_expresiones ')' ';'",
"lista_variables : definicion_variable lista_variables",
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
"tipo : VOID",
"definicion_funcion : FUNC ID '(' lista_parametros ')' retorno '{' lista_variables lista_sentencias '}'",
"retorno : tipo",
"retorno :",
"lista_parametros : parametro",
"lista_parametros :",
"parametro : ID tipo",
"parametro : parametro ',' ID tipo",
};

//#line 189 "../../src/sintactico/sintactico.y"

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
//#line 608 "Parser.java"
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
