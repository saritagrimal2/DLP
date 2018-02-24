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
    0,    1,    1,    4,    4,    7,    7,    8,    8,    9,
    9,    9,    9,    9,    9,    9,    9,    9,    9,    9,
    9,    9,    9,    9,    9,    9,    9,    9,    9,    9,
    9,    9,    9,    3,    3,   11,   11,   11,   11,   11,
   11,   11,   11,   17,   12,   13,   14,   15,   16,   18,
   19,    2,    2,    6,   20,   20,   21,   22,   22,   10,
   10,   10,   10,   10,   10,    5,   24,   24,   23,   23,
   25,   25,
};
final static short yylen[] = {                            2,
    9,    0,    2,    1,    1,    0,    1,    1,    3,    1,
    1,    1,    1,    3,    3,    4,    2,    2,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    4,    4,    0,    2,    1,    1,    1,    1,    1,
    1,    1,    1,    4,    5,    4,    5,    5,    5,    3,
    5,    0,    2,    4,    1,    3,    3,    1,    2,    1,
    1,    1,    4,    4,    1,   10,    0,    1,    0,    1,
    2,    4,
};
final static short yydefred[] = {                         2,
    0,    0,    0,    0,    3,    4,    5,    0,    0,   55,
    0,    0,    0,   65,    0,   61,   60,   62,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   56,   54,   52,
   71,    0,    0,    0,   58,    0,    0,    0,   68,    0,
    0,    0,   64,   59,   63,    0,   53,   52,   72,   57,
   10,    0,   12,    0,    0,    0,   11,    0,    0,    0,
    0,    0,    0,    1,    0,    0,   35,   36,   37,   38,
   39,   40,   41,   42,   43,    0,    0,    0,    0,   34,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   34,    0,    0,    0,
    0,    0,   34,   50,    0,   14,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   15,    0,   66,    0,    0,    0,    0,   46,    0,
    0,    0,   44,   16,   32,   33,   45,   51,    0,   48,
   47,   49,
};
final static short yydgoto[] = {                          1,
    2,   38,   46,    5,    6,   47,  108,  109,   65,   66,
   67,   68,   69,   70,   71,   72,   73,   74,   75,   34,
   35,   36,   24,   40,   25,
};
final static short yysindex[] = {                         0,
    0, -255, -240, -254,    0,    0,    0,  -31,  -22,    0,
  179,  -12, -234,    0,  -93,    0,    0,    0, -221, -225,
  -16,  -73,  -90,   10,   11, -254,  -39,    0,    0,    0,
    0,  -90, -205,  179,    0, -121,  -90, -218,    0,  -61,
  -90,    4,    0,    0,    0,  -33,    0,    0,    0,    0,
    0,  662,    0,   24,  -58,   26,    0,  662,  662,   28,
  662,  662,  662,    0,  446,   29,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -218,   31,  474,  662,    0,
  662,  481,  502,  662,  -43,  -43,  540,  662,  662,  662,
  662,  662,  662,  662,  662,  662,  662,  662,  662,  662,
  662,  662,  662,  662,  -13,  662,    0,   32,   30,  823,
    7,   34,    0,    0,   35,    0,  -32,  -32,  -32,  -32,
 1014, 1014,  566,  -32,  -32,  -21,  -21,  -43,  -43,  -43,
  596,    0,  626,    0,   36,   27,   20,  662,    0,   21,
   49,   22,    0,    0,    0,    0,    0,    0,  823,    0,
    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   42,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   43,    0,    0,    0,    0,    0,
    0,  -38,    0,    0,    0,    0,    0,   69,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  730,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   69,  102,    0,   45,    0,
   45,    0,    0,   45,  312,  347,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   45,    0,    0,   46,   -3,
    0,    0,    0,    0,    0,    0,  885,  921,  961,  972,
  357,  925,    0, 1008, 1048,  856,  896,  382,  417,  453,
    0,    0,    0,    0,    0,    0,  795,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,   40,  -74,    0,    0,   88,  -50,    0, 1242,   12,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   87,
   57,    0,    0,    0,    0,
};
final static int YYTABLESIZE=1380;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         62,
   19,  105,  103,   43,  101,  111,   63,   10,   12,   99,
   97,   61,   98,  103,  100,  101,    3,   13,    8,   62,
   99,    9,   21,    4,  103,  100,   63,   23,   22,   26,
  112,   61,  136,  115,   31,   27,   28,    8,  141,   62,
    8,    9,   29,   39,    9,   42,   63,  102,   45,   30,
   32,   61,   49,   37,   33,  135,   41,   19,  102,   62,
    4,   48,   50,   79,   80,   81,   63,   84,  104,  102,
  106,   61,  137,  138,  140,  142,  146,   19,  148,  150,
  152,   62,   69,   70,   67,    6,    7,   76,   63,    7,
   11,   64,   44,   61,    0,    0,    0,   19,    0,    0,
    0,   34,    0,    0,    0,    0,    0,    0,   34,    0,
    0,  134,    0,   34,    0,    0,    0,   19,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  139,    0,    0,    0,    0,    0,    0,   13,   19,
   10,    0,   13,   13,   13,   13,   13,   13,   13,    0,
    0,  147,    0,    0,    0,    0,    0,    0,    0,   34,
   13,   13,   13,   13,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  151,    0,   14,    0,    0,    0,   15,
    0,    0,   16,   17,    0,   18,    0,    0,    0,    0,
    0,    0,   13,   34,   13,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   20,   51,   13,    0,   52,   53,   54,    0,
   55,   56,   14,    0,    0,   57,   15,   58,    0,   16,
   17,   59,   18,   51,   60,    0,   52,   53,   54,    0,
   55,   56,   14,    0,    0,   57,   15,   58,    0,   16,
   17,   59,   18,   51,   60,    0,   52,   53,   54,   19,
   55,   56,   14,    0,    0,   57,   15,   58,    0,   16,
   17,   59,   18,   51,   60,    0,   52,   53,   54,    0,
   55,   56,   14,    0,    0,   57,   15,   58,    0,   16,
   17,   59,   18,    0,   60,   51,    0,    0,   52,   53,
   54,    0,   55,   56,   14,    0,    0,   57,   15,   58,
    0,   16,   17,   59,   18,   34,   60,    0,   34,   34,
   34,    0,   34,   34,   34,    0,    0,   34,   34,   34,
    0,   34,   34,   34,   34,    0,   34,    0,   17,    0,
    0,    0,   17,   17,   17,   17,   17,    0,   17,   13,
    0,    0,    0,    0,   13,    0,    0,    0,   13,   13,
   17,   17,   17,   17,    0,    0,    0,    0,   13,    0,
    0,   13,    0,   18,    0,    0,    0,   18,   18,   18,
   18,   18,    0,   18,    0,    0,    0,   30,    0,    0,
   30,    0,    0,    0,   17,   18,   18,   18,   18,    0,
    0,    0,    0,    0,    0,   30,    0,   30,   19,    0,
    0,    0,   19,   19,   19,   19,   19,    0,   19,    0,
    0,    0,    0,    0,   17,    0,    0,    0,    0,   18,
   19,   19,   19,   19,   14,    0,    0,    0,   15,   30,
    0,   16,   17,   20,   18,    0,    0,   20,   20,   20,
   20,   20,    0,   20,    0,    0,    0,    0,    0,   18,
    0,    0,    0,    0,   19,   20,   20,   20,   20,   30,
    0,    0,  101,    0,    0,    0,    0,   99,   97,   21,
   98,  103,  100,   21,   21,   21,   21,   21,    0,   21,
    0,    0,    0,    0,   19,   96,   94,   95,    0,   20,
  101,   21,   21,   21,   21,   99,   97,  101,   98,  103,
  100,    0,   99,   97,    0,   98,  103,  100,    0,    0,
    0,    0,    0,   96,    0,   95,  102,    0,  101,   20,
   96,    0,   95,   99,   97,   21,   98,  103,  100,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  114,   96,    0,   95,  102,    0,    0,    0,    0,   17,
    0,  102,    0,    0,   17,   21,  101,    0,   17,   17,
  116,   99,   97,    0,   98,  103,  100,    0,   17,    0,
    0,   17,  102,    0,    0,    0,  107,    0,    0,   96,
    0,   95,  101,  113,   18,    0,    0,   99,   97,   18,
   98,  103,  100,   18,   18,    0,    0,    0,    0,    0,
    0,    0,    0,   18,  143,   96,   18,   95,    0,    0,
  102,    0,  101,   30,    0,    0,   30,   99,   97,   19,
   98,  103,  100,    0,   19,    0,    0,    0,   19,   19,
    0,    0,    0,    0,    0,   96,  102,   95,   19,    0,
    0,   19,  101,    0,    0,    0,  145,   99,   97,    0,
   98,  103,  100,    0,   20,    0,    0,    0,    0,   20,
    0,    0,    0,   20,   20,   96,  102,   95,  144,    0,
    0,    0,    0,   20,   62,    0,   20,    0,    0,    0,
    0,   63,    0,   88,    0,    0,   61,    0,   89,    0,
   21,    0,   90,   91,    0,   21,  102,    0,    0,   21,
   21,    0,   92,    0,    0,   93,    0,    0,    0,   21,
    0,   88,   21,    0,    0,    0,   89,    0,   88,    0,
   90,   91,    0,   89,    0,    0,    0,   90,   91,    0,
   92,    0,   19,   93,    0,    0,    0,   92,    0,   88,
   93,    0,    0,    0,   89,    0,   13,    0,   90,   91,
    0,   13,   13,    0,   13,   13,   13,    0,   92,    0,
    0,   93,    0,    0,    0,    0,    0,    0,    0,   13,
   13,   13,    0,    0,    0,    0,    0,   88,    0,    0,
    0,    0,   89,    0,    0,    0,   90,   91,    0,    0,
    0,    0,    0,    0,    0,    0,   92,    0,    0,   93,
   13,    0,    0,   88,    0,    0,    0,    0,   89,    0,
    0,   33,   90,   91,    0,    0,   33,   33,    0,   33,
   33,   33,   92,    0,    0,   93,    0,    0,    0,    0,
    0,    0,    0,   88,   33,   33,   33,    0,   89,  101,
    0,    0,   90,   91,   99,   97,    0,   98,  103,  100,
    0,    0,   92,    0,    0,   93,    0,    0,    0,    0,
    0,    0,   96,   88,   95,   33,    0,    0,   89,    0,
    0,    0,   90,   91,    0,    0,   22,    0,   22,   22,
   22,    0,   92,    0,    0,   93,    0,    0,    0,    0,
    0,    0,    0,  102,   22,   22,   22,   22,   51,    0,
    0,    0,   53,   77,    0,   28,    0,   14,   28,    0,
   57,   15,    0,    0,   16,   17,   23,   18,   23,   23,
   23,    0,    0,   28,   28,   28,   28,    0,   22,    0,
    0,    0,    0,    0,   23,   23,   23,   23,    0,    0,
    0,   27,    0,    0,   27,   31,    0,    0,   31,    0,
    0,    0,    0,    0,    0,    0,    0,   28,   22,   27,
   27,   27,   27,   31,    0,   31,    0,   13,   23,    0,
    0,    0,   13,    0,    0,    0,   13,   13,    0,    0,
    0,   25,    0,    0,   25,    0,   13,   28,    0,   13,
    0,    0,   29,   27,    0,   29,    0,   31,   23,   25,
   25,   25,   25,    0,    0,    0,    0,    0,    0,    0,
   29,   29,   29,   29,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   27,    0,    0,    0,   31,   24,    0,
  101,   24,   33,   25,    0,   99,   97,   33,   98,  103,
  100,   33,   33,    0,   29,    0,   24,   24,   24,   24,
    0,   33,    0,   96,   33,   95,    0,    0,    0,    0,
   88,    0,    0,   25,    0,   89,    0,    0,   26,   90,
   91,   26,    0,    0,   29,    0,    0,    0,    0,   92,
   24,    0,   93,    0,  102,    0,   26,   26,   26,   26,
    0,    0,    0,   22,    0,    0,    0,    0,   22,    0,
    0,    0,   22,   22,    0,    0,    0,    0,    0,    0,
   24,    0,   22,    0,    0,   22,    0,    0,    0,    0,
   26,    0,   28,    0,    0,    0,    0,   28,    0,    0,
    0,   28,   28,   23,    0,    0,    0,    0,   23,    0,
    0,   28,   23,   23,   28,    0,    0,    0,    0,    0,
   26,    0,   23,    0,    0,   23,    0,    0,   27,    0,
    0,    0,    0,   27,    0,    0,    0,   27,   27,    0,
    0,    0,    0,    0,    0,    0,    0,   27,    0,    0,
   27,   31,    0,    0,   31,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   25,    0,
    0,    0,    0,   25,    0,    0,    0,   25,   25,   29,
    0,    0,    0,    0,   29,    0,    0,   25,   29,   29,
   25,    0,    0,    0,    0,    0,    0,    0,   29,    0,
    0,   29,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   24,    0,    0,    0,    0,
   24,   88,    0,    0,   24,   24,   89,    0,    0,    0,
   90,   91,    0,    0,   24,    0,    0,   24,    0,    0,
    0,    0,    0,   78,    0,    0,    0,    0,    0,   82,
   83,    0,   85,   86,   87,   26,    0,    0,    0,    0,
   26,    0,    0,    0,   26,   26,    0,    0,    0,    0,
  110,    0,  110,    0,   26,  110,    0,   26,    0,  117,
  118,  119,  120,  121,  122,  123,  124,  125,  126,  127,
  128,  129,  130,  131,  132,  133,    0,  110,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  149,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   91,   76,   46,  125,   37,   80,   40,  262,   40,   42,
   43,   45,   45,   46,   47,   37,  272,   40,  259,   33,
   42,  262,   11,  279,   46,   47,   40,  262,   41,  123,
   81,   45,  107,   84,   23,  257,  262,   41,  113,   33,
   44,   41,   59,   32,   44,   34,   40,   91,   37,  123,
   41,   45,   41,   93,   44,  106,  262,   91,   91,   33,
  279,  123,   59,   40,  123,   40,   40,   40,   40,   91,
   40,   45,   41,   44,   41,   41,   41,   91,   59,   59,
   59,   33,   41,   41,  123,   41,   41,   48,   40,    2,
    4,  125,   36,   45,   -1,   -1,   -1,   91,   -1,   -1,
   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,
   -1,  125,   -1,   45,   -1,   -1,   -1,   91,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  125,   -1,   -1,   -1,   -1,   -1,   -1,   37,   91,
  262,   -1,   41,   42,   43,   44,   45,   46,   47,   -1,
   -1,  125,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  125,   -1,  266,   -1,   -1,   -1,  270,
   -1,   -1,  273,  274,   -1,  276,   -1,   -1,   -1,   -1,
   -1,   -1,   91,  125,   93,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   44,  257,  123,   -1,  260,  261,  262,   -1,
  264,  265,  266,   -1,   -1,  269,  270,  271,   -1,  273,
  274,  275,  276,  257,  278,   -1,  260,  261,  262,   -1,
  264,  265,  266,   -1,   -1,  269,  270,  271,   -1,  273,
  274,  275,  276,  257,  278,   -1,  260,  261,  262,   91,
  264,  265,  266,   -1,   -1,  269,  270,  271,   -1,  273,
  274,  275,  276,  257,  278,   -1,  260,  261,  262,   -1,
  264,  265,  266,   -1,   -1,  269,  270,  271,   -1,  273,
  274,  275,  276,   -1,  278,  257,   -1,   -1,  260,  261,
  262,   -1,  264,  265,  266,   -1,   -1,  269,  270,  271,
   -1,  273,  274,  275,  276,  257,  278,   -1,  260,  261,
  262,   -1,  264,  265,  266,   -1,   -1,  269,  270,  271,
   -1,  273,  274,  275,  276,   -1,  278,   -1,   37,   -1,
   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,  258,
   -1,   -1,   -1,   -1,  263,   -1,   -1,   -1,  267,  268,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,  277,   -1,
   -1,  280,   -1,   37,   -1,   -1,   -1,   41,   42,   43,
   44,   45,   -1,   47,   -1,   -1,   -1,   41,   -1,   -1,
   44,   -1,   -1,   -1,   93,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,   -1,   -1,   59,   -1,   61,   37,   -1,
   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,   -1,   -1,  123,   -1,   -1,   -1,   -1,   93,
   59,   60,   61,   62,  266,   -1,   -1,   -1,  270,   93,
   -1,  273,  274,   37,  276,   -1,   -1,   41,   42,   43,
   44,   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,  123,
   -1,   -1,   -1,   -1,   93,   59,   60,   61,   62,  123,
   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,   37,
   45,   46,   47,   41,   42,   43,   44,   45,   -1,   47,
   -1,   -1,   -1,   -1,  123,   60,   61,   62,   -1,   93,
   37,   59,   60,   61,   62,   42,   43,   37,   45,   46,
   47,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   60,   -1,   62,   91,   -1,   37,  123,
   60,   -1,   62,   42,   43,   93,   45,   46,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   59,   60,   -1,   62,   91,   -1,   -1,   -1,   -1,  258,
   -1,   91,   -1,   -1,  263,  123,   37,   -1,  267,  268,
   41,   42,   43,   -1,   45,   46,   47,   -1,  277,   -1,
   -1,  280,   91,   -1,   -1,   -1,  123,   -1,   -1,   60,
   -1,   62,   37,  123,  258,   -1,   -1,   42,   43,  263,
   45,   46,   47,  267,  268,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  277,   59,   60,  280,   62,   -1,   -1,
   91,   -1,   37,  277,   -1,   -1,  280,   42,   43,  258,
   45,   46,   47,   -1,  263,   -1,   -1,   -1,  267,  268,
   -1,   -1,   -1,   -1,   -1,   60,   91,   62,  277,   -1,
   -1,  280,   37,   -1,   -1,   -1,   41,   42,   43,   -1,
   45,   46,   47,   -1,  258,   -1,   -1,   -1,   -1,  263,
   -1,   -1,   -1,  267,  268,   60,   91,   62,   93,   -1,
   -1,   -1,   -1,  277,   33,   -1,  280,   -1,   -1,   -1,
   -1,   40,   -1,  258,   -1,   -1,   45,   -1,  263,   -1,
  258,   -1,  267,  268,   -1,  263,   91,   -1,   -1,  267,
  268,   -1,  277,   -1,   -1,  280,   -1,   -1,   -1,  277,
   -1,  258,  280,   -1,   -1,   -1,  263,   -1,  258,   -1,
  267,  268,   -1,  263,   -1,   -1,   -1,  267,  268,   -1,
  277,   -1,   91,  280,   -1,   -1,   -1,  277,   -1,  258,
  280,   -1,   -1,   -1,  263,   -1,   37,   -1,  267,  268,
   -1,   42,   43,   -1,   45,   46,   47,   -1,  277,   -1,
   -1,  280,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,  258,   -1,   -1,
   -1,   -1,  263,   -1,   -1,   -1,  267,  268,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  277,   -1,   -1,  280,
   91,   -1,   -1,  258,   -1,   -1,   -1,   -1,  263,   -1,
   -1,   37,  267,  268,   -1,   -1,   42,   43,   -1,   45,
   46,   47,  277,   -1,   -1,  280,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  258,   60,   61,   62,   -1,  263,   37,
   -1,   -1,  267,  268,   42,   43,   -1,   45,   46,   47,
   -1,   -1,  277,   -1,   -1,  280,   -1,   -1,   -1,   -1,
   -1,   -1,   60,  258,   62,   91,   -1,   -1,  263,   -1,
   -1,   -1,  267,  268,   -1,   -1,   41,   -1,   43,   44,
   45,   -1,  277,   -1,   -1,  280,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   91,   59,   60,   61,   62,  257,   -1,
   -1,   -1,  261,  262,   -1,   41,   -1,  266,   44,   -1,
  269,  270,   -1,   -1,  273,  274,   41,  276,   43,   44,
   45,   -1,   -1,   59,   60,   61,   62,   -1,   93,   -1,
   -1,   -1,   -1,   -1,   59,   60,   61,   62,   -1,   -1,
   -1,   41,   -1,   -1,   44,   41,   -1,   -1,   44,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,  123,   59,
   60,   61,   62,   59,   -1,   61,   -1,  258,   93,   -1,
   -1,   -1,  263,   -1,   -1,   -1,  267,  268,   -1,   -1,
   -1,   41,   -1,   -1,   44,   -1,  277,  123,   -1,  280,
   -1,   -1,   41,   93,   -1,   44,   -1,   93,  123,   59,
   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  123,   -1,   -1,   -1,  123,   41,   -1,
   37,   44,  258,   93,   -1,   42,   43,  263,   45,   46,
   47,  267,  268,   -1,   93,   -1,   59,   60,   61,   62,
   -1,  277,   -1,   60,  280,   62,   -1,   -1,   -1,   -1,
  258,   -1,   -1,  123,   -1,  263,   -1,   -1,   41,  267,
  268,   44,   -1,   -1,  123,   -1,   -1,   -1,   -1,  277,
   93,   -1,  280,   -1,   91,   -1,   59,   60,   61,   62,
   -1,   -1,   -1,  258,   -1,   -1,   -1,   -1,  263,   -1,
   -1,   -1,  267,  268,   -1,   -1,   -1,   -1,   -1,   -1,
  123,   -1,  277,   -1,   -1,  280,   -1,   -1,   -1,   -1,
   93,   -1,  258,   -1,   -1,   -1,   -1,  263,   -1,   -1,
   -1,  267,  268,  258,   -1,   -1,   -1,   -1,  263,   -1,
   -1,  277,  267,  268,  280,   -1,   -1,   -1,   -1,   -1,
  123,   -1,  277,   -1,   -1,  280,   -1,   -1,  258,   -1,
   -1,   -1,   -1,  263,   -1,   -1,   -1,  267,  268,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,   -1,   -1,
  280,  277,   -1,   -1,  280,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  258,   -1,
   -1,   -1,   -1,  263,   -1,   -1,   -1,  267,  268,  258,
   -1,   -1,   -1,   -1,  263,   -1,   -1,  277,  267,  268,
  280,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,   -1,
   -1,  280,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  258,   -1,   -1,   -1,   -1,
  263,  258,   -1,   -1,  267,  268,  263,   -1,   -1,   -1,
  267,  268,   -1,   -1,  277,   -1,   -1,  280,   -1,   -1,
   -1,   -1,   -1,   52,   -1,   -1,   -1,   -1,   -1,   58,
   59,   -1,   61,   62,   63,  258,   -1,   -1,   -1,   -1,
  263,   -1,   -1,   -1,  267,  268,   -1,   -1,   -1,   -1,
   79,   -1,   81,   -1,  277,   84,   -1,  280,   -1,   88,
   89,   90,   91,   92,   93,   94,   95,   96,   97,   98,
   99,  100,  101,  102,  103,  104,   -1,  106,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  138,
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
"lista_definiciones :",
"lista_definiciones : lista_definiciones definicion",
"definicion : definicion_funcion",
"definicion : definicion_variable",
"lista_expresiones :",
"lista_expresiones : lista_expresionesP",
"lista_expresionesP : expresion",
"lista_expresionesP : lista_expresionesP ',' expresion",
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
"expresion : tipo '(' expresion ')'",
"expresion : ID '(' lista_expresiones ')'",
"lista_sentencias :",
"lista_sentencias : lista_sentencias sentencia",
"sentencia : sentencia_if",
"sentencia : sentencia_else",
"sentencia : sentencia_while",
"sentencia : sentencia_write",
"sentencia : sentencia_read",
"sentencia : sentencia_asignacion",
"sentencia : sentencia_return",
"sentencia : sentencia_invocacion",
"sentencia_asignacion : expresion '=' expresion ';'",
"sentencia_if : IF expresion '{' lista_sentencias '}'",
"sentencia_else : ELSE '{' lista_sentencias '}'",
"sentencia_while : WHILE expresion '{' lista_sentencias '}'",
"sentencia_write : WRITE '(' lista_expresiones ')' ';'",
"sentencia_read : READ '(' lista_expresiones ')' ';'",
"sentencia_return : RETURN expresion ';'",
"sentencia_invocacion : ID '(' lista_expresiones ')' ';'",
"lista_variables :",
"lista_variables : lista_variables definicion_variable",
"definicion_variable : VAR identificadores tipo ';'",
"identificadores : ID",
"identificadores : identificadores ',' ID",
"variable_struct : identificadores tipo ';'",
"variables_struct : variable_struct",
"variables_struct : variables_struct variable_struct",
"tipo : INT",
"tipo : FLOAT32",
"tipo : CHAR",
"tipo : '[' CTE_ENTERA ']' tipo",
"tipo : STRUCT '{' variables_struct '}'",
"tipo : VOID",
"definicion_funcion : FUNC ID '(' lista_parametros ')' retorno '{' lista_variables lista_sentencias '}'",
"retorno :",
"retorno : tipo",
"lista_parametros :",
"lista_parametros : lista_parametrosP",
"lista_parametrosP : ID tipo",
"lista_parametrosP : lista_parametrosP ',' ID tipo",
};

//#line 192 "../../src/sintactico/sintactico.y"

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
//#line 629 "Parser.java"
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
