package semantico;

import ast.AccesoArray;
import ast.AccesoCampo;
import ast.Aritmetica;
import ast.Asignacion;
import ast.Cast;
import ast.Comparacion;
import ast.DefFuncion;
import ast.DefVariable;
import ast.Definicion;
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
import ast.Variable;
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

public abstract class VisitorAbstracto implements Visitor{

	@Override
	public Object visitar(Aritmetica a, Object param) {
		a.getExp1().aceptar(this, param);
		//a.getOperador(); no lo hay que tener en cuenta
		a.getExp2().aceptar(this, param);
		
		
		return null;
	}

	@Override
	public Object visitar(Comparacion c, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(Logica l, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(Negacion n, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(MenosUnario m, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(AccesoCampo c, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(Campo c, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(AccesoArray a, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(Cast c, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(InvocacionFuncionExp f, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(LiteralEntero e, Object param) {
		e.setLValue(false);
		return null;
	}

	@Override
	public Object visitar(LiteralCaracter c, Object param) {
		c.setLValue(false);
		return null;
	}

	@Override
	public Object visitar(LiteralReal r, Object param) {
		r.setLValue(false);
		return null;
	}

	@Override
	public Object visitar(Identificador i, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(sentenciaWhile w, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(sentenciaIf i, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(Return r, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(Lectura l, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(Escritura e, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(Asignacion a, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(InvocacionFuncionSent f, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(Programa p, Object param) {
		for (Definicion d: p.getDefinicones()) {
			d.aceptar(this, param);
		}
		return null;
	}

	@Override
	public Object visitar(DefFuncion f, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(DefVariable v, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(Variable v, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(TipoArray a, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(TipoCaracter c, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(TipoEntero e, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(TipoError e, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(TipoFloat f, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(TipoFuncion f, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(TipoRegistro r, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitar(TipoVoid v, Object param) {
		// TODO Auto-generated method stub
		return null;
	}
	

	

}
