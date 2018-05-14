package generacioncodigo;

import java.io.FileWriter;
import java.io.IOException;

import ast.DefVariable;
import ast.Definicion;
import ast.tipo.Tipo;
import ast.tipo.TipoCaracter;
import ast.tipo.TipoEntero;
import ast.tipo.TipoFloat;

public class GC {
	
	private FileWriter fsalida;
	private int etiqueta = 0;

	public GC(String salida) {
		try {
			fsalida = new FileWriter(salida);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	Instruciones Push
	public void push(Tipo tipo, int valor)  {
		String salida ="\tpush" + tipo.sufijo() + "\t" + valor;
		writeFile(salida);
	}
	
	public void pushf(Tipo tipo, double valor)  {
		String salida ="\tpush" + tipo.sufijo() + "\t" + valor;
		writeFile(salida);
	}
	
	public void pusha(int direccion)  {
		String salida ="\tpusha\t" + direccion;
		writeFile(salida);
	}
	
	public void pushabp() {
		String salida ="\tpush\tbp";
		writeFile(salida);
	}
	
//	Instrucciones load y store
	public void load(Tipo tipo) {
		String salida ="\tload" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void store(Tipo tipo) {
		String salida ="\tstore" + tipo.sufijo();
		writeFile(salida);
	}
	
//	Instrucciones pop y dup
	public void pop(Tipo tipo) {
		String salida ="\tpop" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void dup(Tipo tipo) {
		String salida ="\tdup" + tipo.sufijo();
		writeFile(salida);
	}

//	Instrucciones aritmeticas
	public void add(Tipo tipo)  {
		String salida ="\tadd" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void sub(Tipo tipo){
		String salida ="\tsub" + tipo.sufijo();
		writeFile(salida);
	}

	public void mul(Tipo tipo){
		String salida ="\tmul" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void div(Tipo tipo){
		String salida ="\tdiv" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void mod(Tipo tipo) {
		String salida ="\tmod" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void aritmetica(String operador, Tipo tipo) {
		if (operador.equals("+")) {
			add(tipo);
		} else if (operador.equals("-")) {
			sub(tipo);
		} else if (operador.equals("*")) {
			mul(tipo);
		}else if (operador.equals("/")) {
			div(tipo);
		}else if (operador.equals("%")) {
			mod(tipo);
		}
	}

	
//	Instrucciones de comparacion
	public void gt(Tipo tipo) {
		String salida ="\tgt" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void lt(Tipo tipo) {
		String salida ="\tlt" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void ge(Tipo tipo) {
		String salida ="\tge" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void le(Tipo tipo) {
		String salida ="\tle" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void eq(Tipo tipo) {
		String salida ="\teq" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void ne(Tipo tipo) {
		String salida ="\tne" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void comparacion(String operador, Tipo tipo) {
		if (operador == ">") {
			gt(tipo);
		} else if (operador.equals("<")) {
			lt(tipo);
		} else if (operador.equals(">=")) {
			ge(tipo);
		}else if (operador.equals("<=")) {
			le(tipo);
		}else if (operador.equals("==")) {
			eq(tipo);
		}else if (operador.equals("!=")) {
			ne(tipo);
		}
	}
	
//	Instrucciones logicas
	public void and() {
		String salida ="\tand";
		writeFile(salida);
	}
	
	public void or() {
		String salida ="\tor";
		writeFile(salida);
	}
	
	public void not() {
		String salida ="\tnot";
		writeFile(salida);
	}
	
	public void logica(String operador) {
		if (operador.equals("&&")) {
			and();
		} else if (operador.equals("||")) {
			or();
		} 
	}
	
//	Instrucciones input y output 
	public void out(Tipo tipo)  {
		String salida ="\tout" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void in(Tipo tipo)  {
		String salida ="\tin" + tipo.sufijo();
		writeFile(salida);
	}
	
//	Instrucciones de conversion
	public void b2i()  {
		String salida ="\tb2i";
		writeFile(salida);
	}
	
	public void i2f()  {
		String salida ="\ti2f";
		writeFile(salida);
	}
	
	public void f2i()  {
		String salida ="\tf2i";
		writeFile(salida);
	}
	
	public void i2b()  {
		String salida ="\ti2b";
		writeFile(salida);
	}
	
	public void cast(Tipo tipoExp, Tipo tipoCast ) {
		if (tipoExp.equals(TipoCaracter.getInstance()) && tipoCast.equals(TipoEntero.getInstance()) ) {
			b2i();
		}else if(tipoExp.equals(TipoEntero.getInstance()) && tipoCast.equals(TipoFloat.getInstance())) {
			i2f();
		}else if(tipoExp.equals(TipoFloat.getInstance()) && tipoCast.equals(TipoEntero.getInstance())) {
			f2i();
		}else if(tipoExp.equals(TipoEntero.getInstance()) && tipoCast.equals(TipoCaracter.getInstance())) {
			i2b();
		}else if(tipoExp.equals(TipoCaracter.getInstance()) && tipoCast.equals(TipoFloat.getInstance())) {
			b2i();
			i2f();
		}else if(tipoExp.equals(TipoFloat.getInstance()) && tipoCast.equals(TipoCaracter.getInstance())) {
			f2i();
			i2b();
		}
	}
	
	public void convertir(Tipo tipoE1, Tipo tipoE) {
		switch (tipoE1.sufijo()) {
		case 'i':
			if (tipoE.sufijo() == 'b') {
				i2b();
			} else if (tipoE.sufijo() == 'f') {
				i2f();
			}
			break;
		case 'b':
			if (tipoE.sufijo() == 'f') {
				b2i();
				i2f();
			} else if (tipoE.sufijo() == 'i') {
				b2i();
			}
			break;
		}
	}
	
//	Instruciones de salto
	public void etiqueta(String valor)  {
		String salida =" " + valor + ":";
		writeFile(salida);
	}
	
	public void etiqueta(int num)  {
		String salida ="label_" + num + ":";
		writeFile(salida);
	}
	
	
	public int getEtiquetas(int n) {
		int aux = this.etiqueta;
		this.etiqueta += n;
		return aux;
	}
	
	public void jmp(int label)  {
		String salida ="\tjmp " + "label_" + label;
		writeFile(salida);
	}
	
	public void jz(int label)  {
		String salida ="\tjz " +  "label_" +label;
		writeFile(salida);
	}
	
	public void jnz(String label)  {
		String salida ="\tjnz " +  "label_" +label;
		writeFile(salida);
	}
	
//	Funciones
	public void call(String id)  {
		String salida ="\tcall\t" + id;
		writeFile(salida);
	}
	
	public void enter(int constante)  {
		String salida ="\tenter\t" + constante;
		writeFile(salida);
	}
	
	public void ret(int consReturn, int consLocal, int consParam)  {
		String salida ="\tret " + consReturn + ", " + consLocal + ", " + consParam;
		writeFile(salida);
	}
	
//	Finalizacion del programa
	public void halt()  {
		String salida ="\thalt";
		writeFile(salida);
	}
	
//	Informacion de debug
	public void source(String constante)  {
		String salida ="\n#source \""+ constante +"\""+ "\n";
		writeFile(salida);
	}
	
	public void line(int constante)  {
		String salida ="#line\t"+ constante;
		writeFile(salida);
	}
	
	public void comentarioVariables(Definicion d) {
		String salida ="\t' * var "+ d.getIdentificador() + " " + d.getTipo() 
						+ " (offset " + ((DefVariable) d).getOffset()+")";
		writeFile(salida);
	}
	
	
	public void comentarioParametros() {
		writeFile("\t' * Parameters");
	}
	
	public void comentarioLocales() {
		writeFile("\t' * Local variables");
	}
	
	public void comentarioCuerpoFuncion() {
		writeFile("\t' * Function body");
	}
	
	public void comentarioReturnVoid() {
		writeFile("\t' Void Return");
	}
	
	public void comentarioReturn() {
		writeFile("\t' Return");
	}
	
	public void comentarioMain() {
		writeFile("\n" + "' Invocation to the main function");
	}
	
	public void comentarioLinea(int linea) {
		writeFile("#line\t"+ linea);
	}
	
//	 Metodo que escribe
	 public void writeFile(String value) {
	  try {
		 fsalida.write(value + "\n");
		 fsalida.flush();
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	 }


	
	
}
