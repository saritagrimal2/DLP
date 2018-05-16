package generacioncodigo;


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
import visitor.Visitor;

public abstract class AbstractGC implements Visitor{
	
	@Override
	public Object visitar(Aritmetica a, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"Aritmetica\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(Comparacion c, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"Comparacion\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(Logica l, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"Logica\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(Negacion n, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"Negacion\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(MenosUnario m, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"MenosUnario\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(AccesoCampo c, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"AccesoCampo\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(Campo c, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"Campo\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(AccesoArray a, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"AccesoArray\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(Cast c, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"Cast\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(InvocacionFuncionExp f, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"InvocacionFuncionExp\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(LiteralEntero e, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"LiteralEntero\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(LiteralCaracter c, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"LiteralCaracter\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(LiteralReal r, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"LiteralReal\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(Identificador i, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"Identificador\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(sentenciaWhile w, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"sentenciaWhile\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(sentenciaIf i, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"sentenciaIf\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(Return r, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"Return\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(Lectura l, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"Lectura\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(Escritura e, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"Escritura\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(Asignacion a, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"Asignacion\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(InvocacionFuncionSent f, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"InvocacionFuncionSent\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(Programa p, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"Programa\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(DefFuncion f, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"DefFuncion\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(DefVariable v, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"DefVariable\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(TipoArray a, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"TipoArray\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(TipoCaracter c, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"TipoCaracter\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(TipoEntero e, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"TipoEntero\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(TipoError e, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"TipoError\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(TipoFloat f, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"TipoFloat\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(TipoFuncion f, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"TipoFuncion\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(TipoRegistro r, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"TipoRegistro\" no definida para la funcion de codigo " + this.getClass().getName());
	}

	@Override
	public Object visitar(TipoVoid v, Object param) {
		return new IllegalAccessError("Plantilla de generacion de codigo \"TipoVoid\" no definida para la funcion de codigo " + this.getClass().getName());
	}
}
