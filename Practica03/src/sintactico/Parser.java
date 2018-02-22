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
    0,    1,    1,    1,    1,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    7,    7,    7,    7,    7,    7,    7,    7,    7,   13,
    8,    9,   10,    6,    6,   16,   16,   11,   12,   14,
   15,   17,   17,    3,   18,   18,   19,   20,   20,    5,
    5,    5,    5,    5,    5,    2,   22,   22,   21,   21,
   23,   23,
};
final static short yylen[] = {                            2,
    1,    1,    1,    2,    2,    1,    1,    1,    1,    3,
    3,    4,    2,    2,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    4,    4,
    2,    2,    2,    2,    2,    2,    1,    2,    0,    4,
    5,    4,    5,    1,    0,    1,    3,    5,    5,    3,
    5,    2,    0,    4,    1,    3,    3,    1,    2,    1,
    1,    1,    4,    4,    1,   10,    1,    0,    1,    0,
    2,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    1,    0,    0,    0,   55,    0,    4,
    5,    0,   65,    0,   61,   60,   62,    0,    0,    0,
    0,    0,    0,    0,    0,   56,   54,   71,    0,    0,
    0,    0,    0,    0,   67,    0,    0,    0,   59,   64,
   63,    0,   72,   57,    0,    0,   52,    6,    0,    8,
    0,    0,    0,    7,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   37,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   66,
   31,   32,   33,   34,   35,   36,   38,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   50,    0,   10,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   11,    0,    0,    0,    0,    0,
    0,   42,    0,    0,    0,   40,   12,   29,   30,   41,
   51,    0,   48,   43,   49,
};
final static short yydgoto[] = {                          3,
    4,    5,    6,   61,   62,  112,   63,   64,   65,   66,
   67,   68,   69,   70,   71,  113,   46,   31,   32,   33,
   22,   36,   23,
};
final static short yysindex[] = {                      -269,
 -247, -243,    0,    0, -269, -269,  -17,    0,   76,    0,
    0, -225,    0,  -85,    0,    0,    0, -218, -222,  -18,
  -87,    1,   10, -243,  -49,    0,    0,    0,  -87, -217,
   76, -243,  -70,  -87,    0,  -67,  -87,   -2,    0,    0,
    0, -220,    0,    0, -220,  -13,    0,    0,  596,    0,
   20,  -62,   24,    0,  596,  596,   29,  596,  596,  596,
  498,   31,  -53,  -13,  -13,  -13,  -13,  -13,  -13,    0,
  -13,   33,  131,  596,  -13,  596,  270,  360,  596,  -45,
  -45,  392,  596,  596,  596,  596,  596,  596,  596,  596,
  596,  596,  596,  596,  596,  596,  596,  596,  596,    0,
    0,    0,    0,    0,    0,    0,    0,  596,  596,  -13,
  509,   34,   32,  -51,   36,  -13,    0,   39,    0,  -21,
  -21,  -21,  -21,  784,  784,  399,  -21,  -21,  -29,  -29,
  -45,  -45,  -45,  425,    0,  462,   40,  509,  -43,   35,
  596,    0,   37,  -37,   41,    0,    0,    0,    0,    0,
    0,  509,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,   90,   91,    0,    0,    0,    0,
    0,   54,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   57,    0,    0,    0,    0,    0,  -16,    0,
    0,  -20,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -33,    0,    0,  -33,  -19,    0,    0,    0,    0,
  535,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -19,  -19,  -19,  -19,  -19,  -19,    0,
  -19,    6,    0,   68,  -19,   68,    0,    0,   68,   42,
   71,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   68,    0,  -19,
  -35,    0,   69,    0,    0,  -19,    0,    0,    0,  650,
  763,  819,  844,  102,  111,    0,  884,  891,  656,  691,
   97,  234,  325,    0,    0,    0,    0,  -30,    0,  570,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -11,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   30,    0,  -14, 1107,  116,  -74,  409,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   66,  115,    0,   87,
    0,    0,    0,
};
final static int YYTABLESIZE=1248;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         53,
   98,  115,    1,   18,  118,   46,   53,   96,   46,    2,
   28,   53,   94,   28,    7,   96,   98,   95,    8,   59,
   94,   92,   12,   93,   98,   95,   60,   45,   28,   47,
   45,   58,   47,  137,   10,   11,   21,   24,   25,   26,
   27,   29,    9,   34,   37,   97,    9,    9,    9,    9,
    9,    9,    9,   30,   40,   42,   44,   53,    2,   74,
   75,   97,   28,   76,    9,    9,    9,    9,   79,   97,
   99,  100,  108,  142,  140,  141,  143,   18,   13,  145,
  149,  150,   13,   13,   13,   13,   13,  154,   13,    2,
    3,   53,   28,  151,   70,  153,    9,   69,    9,  155,
   13,   13,   13,   13,   58,   39,   68,   14,   45,   44,
   47,   14,   14,   14,   14,   14,    9,   14,   39,   19,
    0,    0,    0,    0,   20,    0,    0,    0,    9,   14,
   14,   14,   14,   15,   13,    0,   28,   15,   15,   15,
   15,   15,   26,   15,   35,   26,   38,    0,    0,   41,
    0,   27,   43,    0,   27,   15,   15,   15,   15,    0,
   26,    0,   26,   14,   13,    0,   18,   96,    0,   27,
    0,   27,   94,   92,    0,   93,   98,   95,   13,    0,
    0,    0,   14,    0,    0,   15,   16,    0,   17,   15,
   91,  109,   90,   14,   26,    0,    0,    0,    0,    0,
    0,    0,    0,   27,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   15,
    0,   97,    0,   53,   26,    0,   53,   53,   53,    0,
   53,   53,   53,   27,    0,   53,   53,   53,    0,   53,
   53,   53,   53,   48,   53,    0,   49,   50,   51,    0,
   52,   53,   13,  110,    0,   54,   14,   55,    0,   15,
   16,   56,   17,    9,   57,    0,    0,    0,    9,    0,
   16,    0,    9,    9,   16,   16,   16,   16,   16,    0,
   16,    0,    9,    0,    0,    9,    0,    0,    0,    0,
    0,    0,   16,   16,   16,   16,    0,    0,    0,   13,
    0,    0,    0,    0,   13,    0,   96,    0,   13,   13,
    0,   94,   92,    0,   93,   98,   95,    0,   13,    0,
    0,   13,    0,    0,    0,    0,   16,    0,   14,   91,
  109,   90,    0,   14,    0,    0,    0,   14,   14,    0,
    0,   13,    0,    0,    0,   14,    0,   14,   15,   16,
   14,   17,    0,    0,   15,    0,   16,    0,    0,   15,
   97,   17,    0,   15,   15,   17,   17,   17,   17,   17,
    0,   17,    0,   15,    0,    0,   15,    0,   26,    0,
    0,   26,    0,   17,   17,   17,   17,   27,   83,    0,
   27,    0,  116,   84,    0,    0,   96,   85,   86,    0,
    0,   94,   92,    0,   93,   98,   95,   87,    0,    0,
   88,    0,    0,    0,    0,    0,    0,   17,  117,   91,
  109,   90,    0,    0,    0,    0,    0,    0,   96,    0,
    0,    0,  119,   94,   92,   96,   93,   98,   95,    0,
   94,   92,    0,   93,   98,   95,    0,   17,    0,    0,
   97,   91,  109,   90,    0,    0,    0,  146,   91,  109,
   90,   96,    0,    0,    0,    0,   94,   92,    0,   93,
   98,   95,  101,  102,  103,  104,  105,  106,    0,  107,
    0,    0,   97,  114,   91,  109,   90,    0,    0,   97,
    0,   16,    0,    0,    0,    0,   16,    0,   96,    0,
   16,   16,  148,   94,   92,    0,   93,   98,   95,    0,
   16,    0,    0,   16,    0,   97,    0,  147,  139,    0,
    0,   91,  109,   90,  144,    0,    0,   83,    0,    0,
    0,    0,   84,    0,   96,    0,   85,   86,    0,   94,
   92,    0,   93,   98,   95,   96,   87,    0,    0,   88,
   94,   92,   97,   93,   98,   95,    0,   91,   89,   90,
    0,    0,    0,    0,    0,    0,    0,    0,   91,  109,
   90,    9,    0,    0,    0,    0,    9,    9,    0,    9,
    9,    9,   17,    0,    0,    0,    0,   17,   97,    0,
    0,   17,   17,    0,    9,    9,    9,    0,    0,   97,
    0,   17,    0,    0,   17,    0,   30,    0,    0,    0,
    0,   30,   30,    0,   30,   30,   30,   83,    0,    0,
    0,    0,   84,    0,    0,    9,   85,   86,   59,   30,
   30,   30,    0,    0,    0,   60,   87,    0,    0,   88,
   58,    0,    0,    0,    0,    0,    0,    0,    0,   83,
    0,    0,    0,    0,   84,    0,   83,    0,   85,   86,
   30,   84,    0,    0,    0,   85,   86,    0,   87,    0,
    0,   88,    0,    0,    0,   87,    0,    0,   88,    0,
    0,    0,   83,    0,    0,    0,   18,   84,    0,    0,
   24,   85,   86,   24,    0,    0,   18,    0,   18,   18,
   18,   87,    0,    0,   88,    0,    0,    0,   24,   24,
   24,   24,    0,    0,   18,   18,   18,   18,    0,   83,
    0,    0,    0,    0,   84,    0,    0,    0,   85,   86,
    0,   19,    0,   19,   19,   19,    0,    0,   87,    0,
    0,   88,   24,    0,    0,    0,    0,    0,   18,   19,
   19,   19,   19,    0,    0,   83,    0,    0,    0,    0,
   84,    0,    0,    0,   85,   86,   83,    0,    0,    0,
    0,   84,   24,    0,   87,   85,   86,   88,   18,    0,
    0,    0,    0,   19,    0,   87,    0,    0,   88,    0,
    0,    0,    9,    0,    0,    0,    0,    9,    0,    0,
    0,    9,    9,   23,    0,    0,   23,    0,    0,    0,
    0,    9,    0,   19,    9,    0,    0,    0,    0,    0,
   96,   23,   23,   23,   23,   94,   92,   30,   93,   98,
   95,    0,   30,    0,    0,    0,   30,   30,    0,    0,
    0,    0,    0,   91,    0,   90,   30,    0,    0,   30,
    0,    0,   48,    0,    0,   23,   50,   72,    0,   21,
    0,   13,   21,    0,   54,   14,    0,    0,   15,   16,
    0,   17,    0,    0,   97,    0,    0,   21,   21,   21,
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
    0,    0,    0,   22,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   20,    0,    0,    0,
    0,    0,    0,   22,    0,    0,    0,    0,    0,    0,
   23,    0,    0,    0,    0,   23,    0,    0,    0,   23,
   23,    0,    0,    0,    0,    0,    0,    0,    0,   23,
    0,   83,   23,    0,    0,    0,   84,    0,    0,    0,
   85,   86,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   21,    0,    0,    0,
    0,   21,    0,    0,    0,   21,   21,    0,    0,    0,
    0,    0,    0,    0,    0,   21,    0,    0,   21,    0,
    0,   25,    0,    0,    0,    0,   25,    0,    0,    0,
   25,   25,    0,    0,    0,    0,    0,    0,    0,    0,
   25,    0,    0,   25,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   20,    0,    0,    0,    0,   20,    0,   22,    0,
   20,   20,    0,   22,    0,   73,    0,   22,   22,    0,
   20,   77,   78,   20,   80,   81,   82,   22,    0,    0,
   22,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  111,    0,  111,    0,    0,  111,    0,    0,    0,  120,
  121,  122,  123,  124,  125,  126,  127,  128,  129,  130,
  131,  132,  133,  134,  135,  136,    0,    0,    0,    0,
    0,    0,    0,    0,  111,  138,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  152,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   46,   76,  272,   91,   79,   41,   40,   37,   44,  279,
   41,   45,   42,   44,  262,   37,   46,   47,  262,   33,
   42,   43,   40,   45,   46,   47,   40,   42,   59,   41,
   45,   45,   44,  108,    5,    6,  262,  123,  257,  262,
   59,   41,   37,   93,  262,   91,   41,   42,   43,   44,
   45,   46,   47,   44,  125,  123,   59,   91,  279,   40,
  123,   91,   93,   40,   59,   60,   61,   62,   40,   91,
   40,  125,   40,  125,   41,   44,   41,   91,   37,   41,
   41,  125,   41,   42,   43,   44,   45,  125,   47,    0,
    0,  125,  123,   59,   41,   59,   91,   41,   93,   59,
   59,   60,   61,   62,  125,  125,  123,   37,   41,   41,
   45,   41,   42,   43,   44,   45,    2,   47,   32,   44,
   -1,   -1,   -1,   -1,    9,   -1,   -1,   -1,  123,   59,
   60,   61,   62,   37,   93,   -1,   21,   41,   42,   43,
   44,   45,   41,   47,   29,   44,   31,   -1,   -1,   34,
   -1,   41,   37,   -1,   44,   59,   60,   61,   62,   -1,
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
   46,   47,   64,   65,   66,   67,   68,   69,   -1,   71,
   -1,   -1,   91,   75,   60,   61,   62,   -1,   -1,   91,
   -1,  258,   -1,   -1,   -1,   -1,  263,   -1,   37,   -1,
  267,  268,   41,   42,   43,   -1,   45,   46,   47,   -1,
  277,   -1,   -1,  280,   -1,   91,   -1,   93,  110,   -1,
   -1,   60,   61,   62,  116,   -1,   -1,  258,   -1,   -1,
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
   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,   -1,   -1,
   -1,   -1,   -1,  123,   -1,   -1,   -1,   -1,   -1,   -1,
  258,   -1,   -1,   -1,   -1,  263,   -1,   -1,   -1,  267,
  268,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,
   -1,  258,  280,   -1,   -1,   -1,  263,   -1,   -1,   -1,
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
  267,  268,   -1,  263,   -1,   49,   -1,  267,  268,   -1,
  277,   55,   56,  280,   58,   59,   60,  277,   -1,   -1,
  280,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   74,   -1,   76,   -1,   -1,   79,   -1,   -1,   -1,   83,
   84,   85,   86,   87,   88,   89,   90,   91,   92,   93,
   94,   95,   96,   97,   98,   99,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  108,  109,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  141,
};
}
final static short YYFINAL=3;
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
"programa : lista_definiciones",
"lista_definiciones : definicion_funcion",
"lista_definiciones : definicion_variable",
"lista_definiciones : definicion_funcion lista_definiciones",
"lista_definiciones : definicion_variable lista_definiciones",
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

//#line 182 "../../src/sintactico/sintactico.y"

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
//#line 603 "Parser.java"
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
