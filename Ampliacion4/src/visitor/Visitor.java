package visitor;

import ast.AccesoArray;
import ast.AccesoCampo;
import ast.Aritmetica;
import ast.Asignacion;
import ast.Cast;
import ast.Comparacion;
import ast.DefFuncion;
import ast.DefVariable;
import ast.Escritura;
import ast.Identificador;
import ast.InvocacionFuncionExp;
import ast.InvocacionFuncionSent;
import ast.Lectura;
import ast.LiteralCaracter;
import ast.LiteralEntero;
import ast.LiteralReal;
import ast.Logica;
import ast.MenosUnario;
import ast.Negacion;
import ast.Programa;
import ast.Return;
import ast.sentenciaIf;
import ast.sentenciaWhile;
import ast.tipo.Campo;
import ast.tipo.TipoArray;
import ast.tipo.TipoCaracter;
import ast.tipo.TipoEntero;
import ast.tipo.TipoError;
import ast.tipo.TipoFloat;
import ast.tipo.TipoFuncion;
import ast.tipo.TipoRegistro;
import ast.tipo.TipoVoid;

public interface Visitor {

	//Expresiones
	public Object visitar(Aritmetica a, Object param);
	public Object visitar(Comparacion c, Object param);
	public Object visitar(Logica l, Object param);
	public Object visitar(Negacion n, Object param);
	public Object visitar(MenosUnario m, Object param);
	public Object visitar(AccesoCampo c, Object param);
	public Object visitar(Campo c, Object param);
	public Object visitar(AccesoArray a, Object param);
	public Object visitar(Cast c, Object param);
	public Object visitar(InvocacionFuncionExp f, Object param);
	public Object visitar(LiteralEntero e, Object param);
	public Object visitar(LiteralCaracter c, Object param);
	public Object visitar(LiteralReal r, Object param);
	public Object visitar(Identificador i, Object param);
	
	//Sentencias
	public Object visitar(sentenciaWhile w, Object param);
	public Object visitar(sentenciaIf i, Object param);
	public Object visitar(Return r, Object param);
	public Object visitar(Lectura l, Object param);
	public Object visitar(Escritura e, Object param);
	public Object visitar(Asignacion a, Object param);
	public Object visitar(InvocacionFuncionSent f, Object param);
	
	//Otros nodos AST
	public Object visitar(Programa p, Object param);
	public Object visitar(DefFuncion f, Object param);
	public Object visitar(DefVariable v, Object param);
	
	//Tipos
	public Object visitar(TipoArray a, Object param);
	public Object visitar(TipoCaracter c, Object param);
	public Object visitar(TipoEntero e, Object param);
	public Object visitar(TipoError e, Object param);
	public Object visitar(TipoFloat f, Object param);
	public Object visitar(TipoFuncion f, Object param);
	public Object visitar(TipoRegistro r, Object param);
	public Object visitar(TipoVoid v, Object param);
	
}
