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
import ast.*;
import java.util.*;
import ast.tipo.*;

//#line 27 "Parser.java"




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
    0,    1,    1,    3,    3,    6,    6,    7,    7,    8,
    8,    8,    8,    8,    8,    8,    8,    8,    8,    8,
    8,    8,    8,    8,    8,    8,    8,    8,    8,    8,
    8,    8,    8,   10,   10,   11,   11,   11,   11,   11,
   11,   11,   16,   12,   12,   13,   14,   15,   17,   18,
   19,   19,    5,   20,   20,   21,   22,   22,    9,    9,
    9,    9,    9,    4,    2,   25,   25,   25,   24,   24,
   23,   23,   26,   26,
};
final static short yylen[] = {                            2,
    8,    0,    2,    1,    1,    0,    1,    1,    3,    1,
    1,    1,    1,    3,    3,    4,    2,    2,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    4,    4,    0,    2,    1,    1,    1,    1,    1,
    1,    1,    4,    5,    9,    5,    5,    5,    3,    5,
    0,    2,    4,    1,    3,    3,    1,    2,    1,    1,
    1,    4,    4,    9,    2,    1,    1,    1,    0,    1,
    0,    1,    2,    4,
};
final static short yydefred[] = {                         2,
    0,    0,    0,    0,    3,    4,    5,    0,    0,   54,
    0,    0,    0,    0,   60,   59,   61,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   55,   53,   51,   67,
   66,   68,   73,    0,    0,    0,   57,    0,    0,    0,
    0,    0,   70,    0,    0,   63,   58,   62,    1,   52,
    0,   51,   74,   56,   10,    0,   12,    0,    0,   11,
    0,    0,    0,    0,    0,    0,    0,    0,   35,   36,
   37,   38,   39,   40,   41,   42,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   64,    0,   34,    0,    0,    0,
    0,   34,   49,    0,   14,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   15,    0,    0,    0,    0,    0,    0,    0,    0,   43,
   16,   32,   33,    0,   50,    0,   47,   46,   48,    0,
   34,    0,   45,
};
final static short yydgoto[] = {                          1,
    2,   40,    5,    6,    7,  108,  109,   67,   68,   51,
   69,   70,   71,   72,   73,   74,   75,   76,   41,   36,
   37,   38,   23,   42,   33,   24,
};
final static short yysindex[] = {                         0,
    0, -270, -217, -254,    0,    0,    0,  -23,  -16,    0,
   76,  -12, -234,  -93,    0,    0,    0, -216, -212,   -3,
  -66, -237,   20,   19, -254,  -29,    0,    0,    0,    0,
    0,    0,    0, -237, -197,   76,    0, -121,  -90,  -59,
 -211,  -54,    0, -237,   12,    0,    0,    0,    0,    0,
   69,    0,    0,    0,    0,  679,    0,   33,   34,    0,
  679,  679,   35,  679,  679,  679,  420,   39,    0,    0,
    0,    0,    0,    0,    0,    0,  -49,   41,  446,  679,
  679,  453,  479,  679,  -43,  -43,  505,  679,  679,  679,
  679,  679,  679,  679,  679,  679,  679,  679,  679,  679,
  679,  679, -179,  679,    0,  679,    0,   43,   42,  648,
   44,    0,    0,   47,    0,  -32,  -32,  -32,  -32,  964,
  964,  541,  -32,  -32,  -21,  -21,  -43,  -43,  -43,  565,
    0,  601,   50,  -33,   31,  679,   37,  -13,   40,    0,
    0,    0,    0, -171,    0,  648,    0,    0,    0,  -22,
    0,    7,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   59,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   62,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -19,    0,    0,    0,    0,    0,    0,
   27,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -20,    0,    0,    0,    0,    0,    0,  715,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  102,    0,   65,
   65,    0,    0,   65,  128,  314,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   65,    0,    0,   66,    5,
    0,    0,    0,    0,    0,  838,  898,  906,  943,   -6,
   36,    0,  979,  987,  794,  831,  356,  382,  409,    0,
    0,    0,    0,    0,  750,    0,    0,    0,    0,    0,
    0,    0,    0,   49,    0,   10,    0,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   56,    0,    0,   70,  -62,    0, 1196,   -5,  -89,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  106,
   75,    0,    0,    0,   -1,    0,
};
final static int YYTABLESIZE=1332;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         65,
   18,    3,  103,   46,  101,   20,   66,   10,    4,   99,
   97,   64,   98,  103,  100,  101,   12,  134,  111,   65,
   99,  114,  138,   13,  103,  100,   66,   22,   21,   25,
   45,   64,   43,   48,   30,   30,   31,   30,   32,   65,
   26,    8,   53,  133,    9,    8,   66,  102,    8,   27,
    9,   64,   30,    9,   30,   28,   29,   18,  102,   34,
   34,  152,   35,   39,   44,   49,   34,    4,   52,  102,
   54,   34,   80,   81,   84,  105,   31,   18,  104,   31,
  106,   44,  131,  135,  137,  136,   30,  139,   44,  145,
  143,  144,  150,   44,   31,  147,   31,   18,  149,   71,
  151,   65,   72,   69,   65,    6,    7,   77,   66,   11,
   50,  148,   47,   64,    0,    0,   30,   34,    0,   19,
    0,    0,    0,    0,    0,    0,    0,    0,   31,    0,
    0,  153,    0,    0,    0,    0,    0,    0,   13,   44,
   10,    0,   13,   13,   13,   13,   13,   13,   13,    0,
    0,   34,    0,    0,    0,    0,    0,    0,   31,   18,
   13,   13,   13,   13,   17,    0,   18,    0,   17,   17,
   17,   17,   17,   44,   17,    0,    0,    0,    0,   14,
    0,    0,   15,   16,    0,   17,   17,   17,   17,   17,
    0,    0,   13,    0,   13,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   17,    0,    0,   55,   13,    0,   56,   57,   58,    0,
    0,   59,    0,    0,    0,   60,   14,   61,    0,   15,
   16,   62,   17,   55,   63,    0,   56,   57,   58,    0,
   17,   59,    0,    0,    0,   60,   14,   61,    0,   15,
   16,   62,   17,   55,   63,    0,   56,   57,   58,    0,
   30,   59,    0,   30,    0,   60,   14,   61,    0,   15,
   16,   62,   17,   34,   63,    0,   34,   34,   34,    0,
    0,   34,    0,    0,    0,   34,   34,   34,    0,   34,
   34,   34,   34,    0,   34,   44,    0,    0,   44,   44,
   44,    0,   31,   44,    0,   31,    0,   44,   44,   44,
    0,   44,   44,   44,   44,   55,   44,    0,   56,   57,
   58,    0,    0,   59,    0,    0,    0,   60,   14,   61,
    0,   15,   16,   62,   17,   14,   63,    0,   15,   16,
   18,   17,    0,    0,   18,   18,   18,   18,   18,   13,
   18,    0,    0,    0,   13,    0,    0,    0,   13,   13,
    0,    0,   18,   18,   18,   18,    0,    0,   13,    0,
    0,   13,    0,    0,    0,   17,    0,    0,    0,    0,
   17,    0,   19,    0,   17,   17,   19,   19,   19,   19,
   19,    0,   19,    0,   17,    0,   18,   17,    0,    0,
    0,    0,    0,    0,   19,   19,   19,   19,   20,    0,
    0,    0,   20,   20,   20,   20,   20,    0,   20,    0,
    0,    0,    0,    0,    0,    0,   18,    0,    0,    0,
   20,   20,   20,   20,    0,   21,    0,    0,   19,   21,
   21,   21,   21,   21,    0,   21,  101,    0,    0,    0,
    0,   99,   97,    0,   98,  103,  100,   21,   21,   21,
   21,    0,    0,    0,   20,    0,    0,    0,   19,   96,
   94,   95,  101,    0,    0,    0,    0,   99,   97,  101,
   98,  103,  100,    0,   99,   97,    0,   98,  103,  100,
    0,   21,    0,    0,   20,   96,    0,   95,    0,    0,
  102,    0,   96,    0,   95,  101,    0,    0,    0,    0,
   99,   97,    0,   98,  103,  100,    0,    0,    0,    0,
    0,   21,    0,    0,    0,    0,  102,  113,   96,    0,
   95,  101,    0,  102,    0,  115,   99,   97,    0,   98,
  103,  100,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   96,    0,   95,    0,  107,  102,
    0,   18,    0,    0,    0,  112,   18,  101,    0,    0,
   18,   18,   99,   97,    0,   98,  103,  100,    0,    0,
   18,    0,    0,   18,    0,  102,    0,    0,    0,  140,
   96,  101,   95,    0,    0,    0,   99,   97,    0,   98,
  103,  100,    0,   19,    0,    0,    0,    0,   19,    0,
    0,    0,   19,   19,   96,    0,   95,    0,    0,    0,
    0,  102,   19,    0,    0,   19,    0,  101,    0,   20,
    0,  142,   99,   97,   20,   98,  103,  100,   20,   20,
    0,    0,    0,    0,    0,  102,    0,  141,   20,    0,
   96,   20,   95,    0,    0,    0,   21,    0,    0,    0,
    0,   21,    0,    0,    0,   21,   21,   88,    0,    0,
    0,    0,   89,    0,  101,   21,   90,   91,   21,   99,
   97,  102,   98,  103,  100,    0,   92,    0,    0,   93,
    0,    0,    0,   88,    0,    0,    0,   96,   89,   95,
   88,   65,   90,   91,    0,   89,    0,    0,   66,   90,
   91,    0,   92,   64,    0,   93,    0,    0,    0,   92,
    0,    0,   93,    0,    0,    0,   88,    0,  102,    0,
    0,   89,    0,    0,    0,   90,   91,    0,    0,    0,
    0,   13,    0,    0,    0,   92,   13,   13,   93,   13,
   13,   13,   88,    0,    0,    0,    0,   89,    0,   18,
    0,   90,   91,    0,   13,   13,   13,    0,    0,    0,
    0,   92,    0,    0,   93,    0,   33,    0,    0,    0,
    0,   33,   33,    0,   33,   33,   33,    0,   88,    0,
    0,    0,    0,   89,    0,   13,    0,   90,   91,   33,
   33,   33,    0,    0,    0,    0,    0,   92,    0,    0,
   93,    0,   88,    0,    0,    0,    0,   89,    0,    0,
    0,   90,   91,    0,   22,    0,   22,   22,   22,    0,
   33,   92,    0,    0,   93,    0,    0,    0,    0,    0,
    0,    0,   22,   22,   22,   22,    0,    0,   88,    0,
    0,    0,    0,   89,    0,    0,    0,   90,   91,    0,
    0,   23,    0,   23,   23,   23,    0,   92,   28,    0,
   93,   28,    0,    0,    0,    0,   22,    0,    0,   23,
   23,   23,   23,    0,    0,    0,   28,   28,   28,   28,
    0,    0,    0,    0,    0,   88,    0,    0,    0,    0,
   89,    0,    0,    0,   90,   91,   22,    0,    0,    0,
    0,    0,    0,   23,   92,    0,    0,   93,    0,    0,
   28,    0,    0,    0,    0,   55,    0,    0,   27,   57,
   78,   27,    0,    0,    0,    0,   25,   60,   14,   25,
    0,   15,   16,   23,   17,    0,   27,   27,   27,   27,
   28,    0,    0,    0,   25,   25,   25,   25,    0,    0,
    0,    0,   13,    0,    0,    0,    0,   13,    0,    0,
    0,   13,   13,   29,    0,    0,   29,    0,    0,    0,
   27,   13,    0,    0,   13,    0,    0,    0,   25,    0,
  101,   29,   29,   29,   29,   99,   97,   33,   98,  103,
  100,    0,   33,    0,    0,    0,   33,   33,    0,   24,
   27,    0,   24,   96,    0,   95,   33,   26,   25,   33,
   26,    0,    0,    0,    0,   29,    0,   24,   24,   24,
   24,    0,    0,    0,    0,   26,   26,   26,   26,    0,
    0,   22,    0,    0,  102,    0,   22,    0,    0,    0,
   22,   22,    0,    0,    0,   29,    0,    0,    0,    0,
   22,   24,    0,   22,    0,    0,    0,    0,    0,   26,
    0,    0,    0,    0,    0,    0,    0,    0,   23,    0,
    0,    0,    0,   23,    0,   28,    0,   23,   23,    0,
   28,   24,    0,    0,   28,   28,    0,   23,    0,   26,
   23,    0,    0,    0,   28,    0,    0,   28,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   27,    0,    0,    0,    0,
   27,    0,    0,   25,   27,   27,    0,    0,   25,    0,
    0,    0,   25,   25,   27,    0,    0,   27,    0,    0,
    0,    0,   25,    0,    0,   25,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   29,    0,    0,    0,    0,   29,    0,    0,    0,   29,
   29,    0,    0,    0,    0,    0,    0,    0,    0,   29,
    0,   88,   29,    0,    0,    0,   89,    0,    0,    0,
   90,   91,    0,    0,    0,    0,   24,    0,    0,    0,
    0,   24,    0,    0,   26,   24,   24,    0,    0,   26,
    0,   79,    0,   26,   26,   24,   82,   83,   24,   85,
   86,   87,    0,   26,    0,    0,   26,    0,    0,    0,
    0,    0,    0,    0,    0,  110,  110,    0,    0,  110,
    0,    0,    0,  116,  117,  118,  119,  120,  121,  122,
  123,  124,  125,  126,  127,  128,  129,  130,    0,  132,
    0,  110,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  146,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   91,  272,   46,  125,   37,   11,   40,  262,  279,   42,
   43,   45,   45,   46,   47,   37,   40,  107,   81,   33,
   42,   84,  112,   40,   46,   47,   40,  262,   41,  123,
   36,   45,   34,   39,   41,  273,  274,   44,  276,   33,
  257,  259,   44,  106,  262,   41,   40,   91,   44,  262,
   41,   45,   59,   44,   61,   59,  123,   91,   91,   33,
   41,  151,   44,   93,  262,  125,   40,  279,  123,   91,
   59,   45,   40,   40,   40,  125,   41,   91,   40,   44,
   40,   33,  262,   41,   41,   44,   93,   41,   40,   59,
   41,  125,  264,   45,   59,   59,   61,   91,   59,   41,
  123,   33,   41,  123,  125,   41,   41,   52,   40,    4,
   41,  125,   38,   45,   -1,   -1,  123,   91,   -1,   44,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,
   -1,  125,   -1,   -1,   -1,   -1,   -1,   -1,   37,   91,
  262,   -1,   41,   42,   43,   44,   45,   46,   47,   -1,
   -1,  125,   -1,   -1,   -1,   -1,   -1,   -1,  123,   91,
   59,   60,   61,   62,   37,   -1,   91,   -1,   41,   42,
   43,   44,   45,  125,   47,   -1,   -1,   -1,   -1,  270,
   -1,   -1,  273,  274,   -1,  276,   59,   60,   61,   62,
   -1,   -1,   91,   -1,   93,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,  257,  123,   -1,  260,  261,  262,   -1,
   -1,  265,   -1,   -1,   -1,  269,  270,  271,   -1,  273,
  274,  275,  276,  257,  278,   -1,  260,  261,  262,   -1,
  123,  265,   -1,   -1,   -1,  269,  270,  271,   -1,  273,
  274,  275,  276,  257,  278,   -1,  260,  261,  262,   -1,
  277,  265,   -1,  280,   -1,  269,  270,  271,   -1,  273,
  274,  275,  276,  257,  278,   -1,  260,  261,  262,   -1,
   -1,  265,   -1,   -1,   -1,  269,  270,  271,   -1,  273,
  274,  275,  276,   -1,  278,  257,   -1,   -1,  260,  261,
  262,   -1,  277,  265,   -1,  280,   -1,  269,  270,  271,
   -1,  273,  274,  275,  276,  257,  278,   -1,  260,  261,
  262,   -1,   -1,  265,   -1,   -1,   -1,  269,  270,  271,
   -1,  273,  274,  275,  276,  270,  278,   -1,  273,  274,
   37,  276,   -1,   -1,   41,   42,   43,   44,   45,  258,
   47,   -1,   -1,   -1,  263,   -1,   -1,   -1,  267,  268,
   -1,   -1,   59,   60,   61,   62,   -1,   -1,  277,   -1,
   -1,  280,   -1,   -1,   -1,  258,   -1,   -1,   -1,   -1,
  263,   -1,   37,   -1,  267,  268,   41,   42,   43,   44,
   45,   -1,   47,   -1,  277,   -1,   93,  280,   -1,   -1,
   -1,   -1,   -1,   -1,   59,   60,   61,   62,   37,   -1,
   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,   -1,   -1,
   59,   60,   61,   62,   -1,   37,   -1,   -1,   93,   41,
   42,   43,   44,   45,   -1,   47,   37,   -1,   -1,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   59,   60,   61,
   62,   -1,   -1,   -1,   93,   -1,   -1,   -1,  123,   60,
   61,   62,   37,   -1,   -1,   -1,   -1,   42,   43,   37,
   45,   46,   47,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   93,   -1,   -1,  123,   60,   -1,   62,   -1,   -1,
   91,   -1,   60,   -1,   62,   37,   -1,   -1,   -1,   -1,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,
   -1,  123,   -1,   -1,   -1,   -1,   91,   59,   60,   -1,
   62,   37,   -1,   91,   -1,   41,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   60,   -1,   62,   -1,  123,   91,
   -1,  258,   -1,   -1,   -1,  123,  263,   37,   -1,   -1,
  267,  268,   42,   43,   -1,   45,   46,   47,   -1,   -1,
  277,   -1,   -1,  280,   -1,   91,   -1,   -1,   -1,   59,
   60,   37,   62,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,  258,   -1,   -1,   -1,   -1,  263,   -1,
   -1,   -1,  267,  268,   60,   -1,   62,   -1,   -1,   -1,
   -1,   91,  277,   -1,   -1,  280,   -1,   37,   -1,  258,
   -1,   41,   42,   43,  263,   45,   46,   47,  267,  268,
   -1,   -1,   -1,   -1,   -1,   91,   -1,   93,  277,   -1,
   60,  280,   62,   -1,   -1,   -1,  258,   -1,   -1,   -1,
   -1,  263,   -1,   -1,   -1,  267,  268,  258,   -1,   -1,
   -1,   -1,  263,   -1,   37,  277,  267,  268,  280,   42,
   43,   91,   45,   46,   47,   -1,  277,   -1,   -1,  280,
   -1,   -1,   -1,  258,   -1,   -1,   -1,   60,  263,   62,
  258,   33,  267,  268,   -1,  263,   -1,   -1,   40,  267,
  268,   -1,  277,   45,   -1,  280,   -1,   -1,   -1,  277,
   -1,   -1,  280,   -1,   -1,   -1,  258,   -1,   91,   -1,
   -1,  263,   -1,   -1,   -1,  267,  268,   -1,   -1,   -1,
   -1,   37,   -1,   -1,   -1,  277,   42,   43,  280,   45,
   46,   47,  258,   -1,   -1,   -1,   -1,  263,   -1,   91,
   -1,  267,  268,   -1,   60,   61,   62,   -1,   -1,   -1,
   -1,  277,   -1,   -1,  280,   -1,   37,   -1,   -1,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   -1,  258,   -1,
   -1,   -1,   -1,  263,   -1,   91,   -1,  267,  268,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,  277,   -1,   -1,
  280,   -1,  258,   -1,   -1,   -1,   -1,  263,   -1,   -1,
   -1,  267,  268,   -1,   41,   -1,   43,   44,   45,   -1,
   91,  277,   -1,   -1,  280,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   59,   60,   61,   62,   -1,   -1,  258,   -1,
   -1,   -1,   -1,  263,   -1,   -1,   -1,  267,  268,   -1,
   -1,   41,   -1,   43,   44,   45,   -1,  277,   41,   -1,
  280,   44,   -1,   -1,   -1,   -1,   93,   -1,   -1,   59,
   60,   61,   62,   -1,   -1,   -1,   59,   60,   61,   62,
   -1,   -1,   -1,   -1,   -1,  258,   -1,   -1,   -1,   -1,
  263,   -1,   -1,   -1,  267,  268,  123,   -1,   -1,   -1,
   -1,   -1,   -1,   93,  277,   -1,   -1,  280,   -1,   -1,
   93,   -1,   -1,   -1,   -1,  257,   -1,   -1,   41,  261,
  262,   44,   -1,   -1,   -1,   -1,   41,  269,  270,   44,
   -1,  273,  274,  123,  276,   -1,   59,   60,   61,   62,
  123,   -1,   -1,   -1,   59,   60,   61,   62,   -1,   -1,
   -1,   -1,  258,   -1,   -1,   -1,   -1,  263,   -1,   -1,
   -1,  267,  268,   41,   -1,   -1,   44,   -1,   -1,   -1,
   93,  277,   -1,   -1,  280,   -1,   -1,   -1,   93,   -1,
   37,   59,   60,   61,   62,   42,   43,  258,   45,   46,
   47,   -1,  263,   -1,   -1,   -1,  267,  268,   -1,   41,
  123,   -1,   44,   60,   -1,   62,  277,   41,  123,  280,
   44,   -1,   -1,   -1,   -1,   93,   -1,   59,   60,   61,
   62,   -1,   -1,   -1,   -1,   59,   60,   61,   62,   -1,
   -1,  258,   -1,   -1,   91,   -1,  263,   -1,   -1,   -1,
  267,  268,   -1,   -1,   -1,  123,   -1,   -1,   -1,   -1,
  277,   93,   -1,  280,   -1,   -1,   -1,   -1,   -1,   93,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  258,   -1,
   -1,   -1,   -1,  263,   -1,  258,   -1,  267,  268,   -1,
  263,  123,   -1,   -1,  267,  268,   -1,  277,   -1,  123,
  280,   -1,   -1,   -1,  277,   -1,   -1,  280,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  258,   -1,   -1,   -1,   -1,
  263,   -1,   -1,  258,  267,  268,   -1,   -1,  263,   -1,
   -1,   -1,  267,  268,  277,   -1,   -1,  280,   -1,   -1,
   -1,   -1,  277,   -1,   -1,  280,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  258,   -1,   -1,   -1,   -1,  263,   -1,   -1,   -1,  267,
  268,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,
   -1,  258,  280,   -1,   -1,   -1,  263,   -1,   -1,   -1,
  267,  268,   -1,   -1,   -1,   -1,  258,   -1,   -1,   -1,
   -1,  263,   -1,   -1,  258,  267,  268,   -1,   -1,  263,
   -1,   56,   -1,  267,  268,  277,   61,   62,  280,   64,
   65,   66,   -1,  277,   -1,   -1,  280,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   80,   81,   -1,   -1,   84,
   -1,   -1,   -1,   88,   89,   90,   91,   92,   93,   94,
   95,   96,   97,   98,   99,  100,  101,  102,   -1,  104,
   -1,  106,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  136,
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
"programa : lista_definiciones FUNC MAIN '(' ')' '{' cuerpo '}'",
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
"expresion : expresion '.' ID",
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
"sentencia : sentencia_while",
"sentencia : sentencia_write",
"sentencia : sentencia_read",
"sentencia : sentencia_asignacion",
"sentencia : sentencia_return",
"sentencia : sentencia_invocacion",
"sentencia_asignacion : expresion '=' expresion ';'",
"sentencia_if : IF expresion '{' lista_sentencias '}'",
"sentencia_if : IF expresion '{' lista_sentencias '}' ELSE '{' lista_sentencias '}'",
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
"campo : identificadores tipo ';'",
"lista_campos : campo",
"lista_campos : lista_campos campo",
"tipo : INT",
"tipo : FLOAT32",
"tipo : CHAR",
"tipo : '[' CTE_ENTERA ']' tipo",
"tipo : STRUCT '{' lista_campos '}'",
"definicion_funcion : FUNC ID '(' lista_parametros ')' retorno '{' cuerpo '}'",
"cuerpo : lista_variables lista_sentencias",
"tipoSimple : INT",
"tipoSimple : FLOAT32",
"tipoSimple : CHAR",
"retorno :",
"retorno : tipoSimple",
"lista_parametros :",
"lista_parametros : lista_parametrosP",
"lista_parametrosP : ID tipoSimple",
"lista_parametrosP : lista_parametrosP ',' ID tipoSimple",
};

//#line 214 "../../src/sintactico/sintactico.y"

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
//#line 633 "Parser.java"
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
case 1:
//#line 53 "../../src/sintactico/sintactico.y"
{ this.ast = new Programa(lexico.getLinea(), lexico.getColumna(), (List<Definicion>)val_peek(7), (List<Sentencia>)val_peek(1) ); }
break;
case 2:
//#line 56 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Definicion>(); }
break;
case 3:
//#line 57 "../../src/sintactico/sintactico.y"
{List<Definicion> defs = (List<Definicion>)val_peek(1); List<Definicion> def = (List<Definicion>)val_peek(0); 
				  										for(Definicion d:def){defs.add(d);} yyval = defs;}
break;
case 4:
//#line 61 "../../src/sintactico/sintactico.y"
{ List<DefFuncion> df = new ArrayList<DefFuncion>(); df.add((DefFuncion) val_peek(0)); yyval=df;}
break;
case 5:
//#line 62 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 6:
//#line 68 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Expresion>();}
break;
case 7:
//#line 69 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 8:
//#line 72 "../../src/sintactico/sintactico.y"
{ List<Expresion> expresiones = new ArrayList<Expresion>(); expresiones.add((Expresion)val_peek(0)); yyval = expresiones;}
break;
case 9:
//#line 73 "../../src/sintactico/sintactico.y"
{ List<Expresion> expresiones = (List<Expresion>)val_peek(2); expresiones.add((Expresion)val_peek(0)); yyval = expresiones;}
break;
case 10:
//#line 76 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralEntero(lexico.getLinea(), lexico.getColumna(),(int)val_peek(0));}
break;
case 11:
//#line 77 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralReal(lexico.getLinea(), lexico.getColumna(),(double)val_peek(0));}
break;
case 12:
//#line 78 "../../src/sintactico/sintactico.y"
{ yyval = new LiteralCaracter(lexico.getLinea(), lexico.getColumna(),(char)val_peek(0));}
break;
case 13:
//#line 79 "../../src/sintactico/sintactico.y"
{ yyval = new Identificador(lexico.getLinea(), lexico.getColumna(),(String)val_peek(0));}
break;
case 14:
//#line 80 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 15:
//#line 81 "../../src/sintactico/sintactico.y"
{ yyval = new AccesoCampo (lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (String)val_peek(0));}
break;
case 16:
//#line 82 "../../src/sintactico/sintactico.y"
{ yyval = new AccesoArray (lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(3), (Expresion)val_peek(1));}
break;
case 17:
//#line 83 "../../src/sintactico/sintactico.y"
{ yyval = new MenosUnario(lexico.getLinea(), lexico.getColumna(),(Expresion) val_peek(0));}
break;
case 18:
//#line 84 "../../src/sintactico/sintactico.y"
{ yyval = new Negacion(lexico.getLinea(), lexico.getColumna(),(Expresion) val_peek(0));}
break;
case 19:
//#line 85 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion) val_peek(2),(String)val_peek(1),(Expresion)val_peek(0));}
break;
case 20:
//#line 86 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion) val_peek(2),(String)val_peek(1),(Expresion)val_peek(0));}
break;
case 21:
//#line 87 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion) val_peek(2),(String)val_peek(1),(Expresion)val_peek(0));}
break;
case 22:
//#line 88 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion) val_peek(2),(String)val_peek(1),(Expresion)val_peek(0));}
break;
case 23:
//#line 89 "../../src/sintactico/sintactico.y"
{ yyval = new Aritmetica(lexico.getLinea(), lexico.getColumna(), (Expresion) val_peek(2),(String)val_peek(1),(Expresion)val_peek(0));}
break;
case 24:
//#line 90 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion) val_peek(2),(String)val_peek(1),(Expresion)val_peek(0));}
break;
case 25:
//#line 91 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion) val_peek(2),(String)val_peek(1),(Expresion)val_peek(0));}
break;
case 26:
//#line 92 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion) val_peek(2),(String)val_peek(1),(Expresion)val_peek(0));}
break;
case 27:
//#line 93 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion) val_peek(2),(String)val_peek(1),(Expresion)val_peek(0));}
break;
case 28:
//#line 94 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion) val_peek(2),(String)val_peek(1),(Expresion)val_peek(0));}
break;
case 29:
//#line 95 "../../src/sintactico/sintactico.y"
{ yyval = new Comparacion(lexico.getLinea(), lexico.getColumna(), (Expresion) val_peek(2),(String)val_peek(1),(Expresion)val_peek(0));}
break;
case 30:
//#line 96 "../../src/sintactico/sintactico.y"
{ yyval = new Logica(lexico.getLinea(), lexico.getColumna(), (Expresion) val_peek(2),(String)val_peek(1),(Expresion)val_peek(0));}
break;
case 31:
//#line 97 "../../src/sintactico/sintactico.y"
{ yyval = new Logica(lexico.getLinea(), lexico.getColumna(), (Expresion) val_peek(2),(String)val_peek(1),(Expresion)val_peek(0));}
break;
case 32:
//#line 98 "../../src/sintactico/sintactico.y"
{ yyval = new Cast(lexico.getLinea(), lexico.getColumna(), (Tipo) val_peek(3),(Expresion)val_peek(1));}
break;
case 33:
//#line 99 "../../src/sintactico/sintactico.y"
{ yyval = new InvocacionFuncionExp(lexico.getLinea(), lexico.getColumna(), new Identificador(lexico.getLinea(), lexico.getColumna(),(String)val_peek(3)), (List<Expresion>)val_peek(1));}
break;
case 34:
//#line 106 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Sentencia>(); }
break;
case 35:
//#line 107 "../../src/sintactico/sintactico.y"
{List<Sentencia> sents = (List<Sentencia>)val_peek(1); List<Sentencia> sent = (List<Sentencia>)val_peek(0); 
				  										for(Sentencia s: sent){sents.add(s);} yyval = sents;}
break;
case 36:
//#line 112 "../../src/sintactico/sintactico.y"
{ List<Sentencia> sentencias = new ArrayList<Sentencia>(); sentencias.add((Sentencia)val_peek(0)); yyval = sentencias;}
break;
case 37:
//#line 113 "../../src/sintactico/sintactico.y"
{ List<Sentencia> sentencias = new ArrayList<Sentencia>(); sentencias.add((Sentencia)val_peek(0)); yyval = sentencias;}
break;
case 38:
//#line 114 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 39:
//#line 115 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 40:
//#line 116 "../../src/sintactico/sintactico.y"
{ List<Sentencia> sentencias = new ArrayList<Sentencia>(); sentencias.add((Sentencia)val_peek(0)); yyval = sentencias;}
break;
case 41:
//#line 117 "../../src/sintactico/sintactico.y"
{ List<Sentencia> sentencias = new ArrayList<Sentencia>(); sentencias.add((Sentencia)val_peek(0)); yyval = sentencias;}
break;
case 42:
//#line 118 "../../src/sintactico/sintactico.y"
{ List<Sentencia> sentencias = new ArrayList<Sentencia>(); sentencias.add((Sentencia)val_peek(0)); yyval = sentencias;}
break;
case 43:
//#line 122 "../../src/sintactico/sintactico.y"
{yyval = new Asignacion(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(3),(Expresion)val_peek(1));}
break;
case 44:
//#line 125 "../../src/sintactico/sintactico.y"
{yyval = new sentenciaIf(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(3), (List<Sentencia>)val_peek(1), new ArrayList<Sentencia>());}
break;
case 45:
//#line 126 "../../src/sintactico/sintactico.y"
{yyval = new sentenciaIf(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(7), (List<Sentencia>)val_peek(5), (List<Sentencia>)val_peek(1));}
break;
case 46:
//#line 130 "../../src/sintactico/sintactico.y"
{yyval = new sentenciaWhile(lexico.getLinea(), lexico.getColumna(),(Expresion)val_peek(3), (List<Sentencia>)val_peek(1) );}
break;
case 47:
//#line 133 "../../src/sintactico/sintactico.y"
{ List<Sentencia> sentencias = new ArrayList<Sentencia>(); List<Expresion> expresiones = (List<Expresion>) val_peek(2); 
																	for(Expresion expresion: expresiones) {sentencias.add(new Escritura(lexico.getLinea(), lexico.getColumna(), expresion));} yyval=sentencias;}
break;
case 48:
//#line 137 "../../src/sintactico/sintactico.y"
{ List<Sentencia> sentencias = new ArrayList<Sentencia>(); List<Expresion> expresiones = (List<Expresion>) val_peek(2); 
																	for(Expresion expresion: expresiones) {sentencias.add(new Lectura(lexico.getLinea(), lexico.getColumna(), expresion));} yyval=sentencias;}
break;
case 49:
//#line 141 "../../src/sintactico/sintactico.y"
{yyval = new Return(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(1) );}
break;
case 50:
//#line 144 "../../src/sintactico/sintactico.y"
{yyval = new InvocacionFuncionSent(lexico.getLinea(), lexico.getColumna(), new Identificador(lexico.getLinea(), lexico.getColumna(),(String)val_peek(4)), (List<Expresion>)val_peek(2));}
break;
case 51:
//#line 150 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefVariable>(); }
break;
case 52:
//#line 151 "../../src/sintactico/sintactico.y"
{ List<DefVariable> defs = (List<DefVariable>)val_peek(1); List<DefVariable> def = (List<DefVariable>) val_peek(0); 
																for(DefVariable var: def){defs.add(var);} yyval = defs;}
break;
case 53:
//#line 155 "../../src/sintactico/sintactico.y"
{ List<String> identificadores = (List<String>)val_peek(2); List<DefVariable> variables = new ArrayList<DefVariable>(); 
															  	for (String id: identificadores) {variables.add(new DefVariable(lexico.getLinea(), lexico.getColumna(),id, (Tipo)val_peek(1) ));} yyval = variables;}
break;
case 54:
//#line 159 "../../src/sintactico/sintactico.y"
{ List<String> identificadores = new ArrayList<String>(); identificadores.add((String)val_peek(0)); yyval = identificadores;}
break;
case 55:
//#line 160 "../../src/sintactico/sintactico.y"
{ List<String> identificadores = (List<String>)val_peek(2); if (!identificadores.contains((String)val_peek(0))) {identificadores.add((String)val_peek(0));}
																else{ new TipoError (lexico.getLinea(), lexico.getColumna(), "variable duplicada");}; yyval = identificadores;}
break;
case 56:
//#line 166 "../../src/sintactico/sintactico.y"
{ List<String> identificadores = (List<String>)val_peek(2); List<Campo> campos = new ArrayList<Campo>(); 
															  	for (String id: identificadores) {campos.add(new Campo(lexico.getLinea(), lexico.getColumna(),id, (Tipo)val_peek(1) ));} yyval = campos;}
break;
case 57:
//#line 170 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 58:
//#line 171 "../../src/sintactico/sintactico.y"
{ List<Campo> camps = (List<Campo>)val_peek(1); List<Campo> camp = (List<Campo>) val_peek(0); 
																for(Campo c: camp){if (!camps.contains(c)){camps.add(c);}else {new TipoError (lexico.getLinea(), lexico.getColumna(), "[AccesoCampo] Campo duplicado.");}} yyval = camps;}
break;
case 59:
//#line 176 "../../src/sintactico/sintactico.y"
{ yyval = TipoEntero.getInstance();}
break;
case 60:
//#line 177 "../../src/sintactico/sintactico.y"
{ yyval = TipoFloat.getInstance();}
break;
case 61:
//#line 178 "../../src/sintactico/sintactico.y"
{ yyval = TipoCaracter.getInstance();}
break;
case 62:
//#line 179 "../../src/sintactico/sintactico.y"
{ yyval = new TipoArray(lexico.getLinea(), lexico.getColumna(),(int) val_peek(2), (Tipo) val_peek(0));}
break;
case 63:
//#line 180 "../../src/sintactico/sintactico.y"
{ yyval = new TipoRegistro(lexico.getLinea(), lexico.getColumna(),(List<Campo>) val_peek(1));}
break;
case 64:
//#line 186 "../../src/sintactico/sintactico.y"
{ yyval = new DefFuncion(lexico.getLinea(), lexico.getColumna(), 
																				(String)val_peek(7), new TipoFuncion(lexico.getLinea(), lexico.getColumna(), (Tipo)val_peek(3), (List<DefVariable>)val_peek(5) ),(List<Sentencia>)val_peek(1)); }
break;
case 65:
//#line 190 "../../src/sintactico/sintactico.y"
{ List<Sentencia> st = (List<Sentencia>) val_peek(1); List<Sentencia> sent = (List<Sentencia>) val_peek(0);
												for(Sentencia s: sent){st.add(s);} yyval = st;}
break;
case 66:
//#line 194 "../../src/sintactico/sintactico.y"
{ yyval = TipoEntero.getInstance();}
break;
case 67:
//#line 195 "../../src/sintactico/sintactico.y"
{ yyval = TipoFloat.getInstance();}
break;
case 68:
//#line 196 "../../src/sintactico/sintactico.y"
{ yyval = TipoCaracter.getInstance();}
break;
case 69:
//#line 200 "../../src/sintactico/sintactico.y"
{ yyval = TipoVoid.getInstance();}
break;
case 70:
//#line 201 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 71:
//#line 204 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefVariable>();}
break;
case 72:
//#line 205 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0);}
break;
case 73:
//#line 208 "../../src/sintactico/sintactico.y"
{ List<DefVariable> variables = new ArrayList<DefVariable>(); variables.add(new DefVariable(lexico.getLinea(), lexico.getColumna(), (String)val_peek(1), (Tipo)val_peek(0))); yyval = variables;}
break;
case 74:
//#line 209 "../../src/sintactico/sintactico.y"
{ List<DefVariable> variables = (List<DefVariable>)val_peek(3); variables.add(new DefVariable(lexico.getLinea(), lexico.getColumna(), (String)val_peek(1), (Tipo)val_peek(0))); yyval = variables;}
break;
//#line 1089 "Parser.java"
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
