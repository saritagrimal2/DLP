package generacioncodigo;

import java.io.FileWriter;
import java.io.IOException;

import ast.tipo.Tipo;

public class GC {
	
	private FileWriter fsalida;

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
		String op = "";
		if (operador.equals("+")) {
			op = "add";
		} else if (operador.equals("-")) {
			op = "sub";
		} else if (operador.equals("*")) {
			op = "mul";
		}else if (operador.equals("/")) {
			op = "div";
		}else if (operador.equals("%")) {
			op = "mod";
		}
		
		String salida = "\t" + op + tipo.sufijo();
		writeFile(salida);
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
		String op = "";
		if (operador == ">") {
			op = "gt";
		} else if (operador.equals("<")) {
			op = "lt";
		} else if (operador.equals(">=")) {
			op = "ge";
		}else if (operador.equals("<=")) {
			op = "le";
		}else if (operador.equals("==")) {
			op = "eq";
		}else if (operador.equals("!=")) {
			op = "ne";
		}
		
		String salida = "\t" + op + tipo.sufijo();
		writeFile(salida);
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
		String op = "";
		if (operador.equals("&&")) {
			op = "and";
		} else if (operador.equals("||")) {
			op = "or";
		} else if (operador.equals("!")) {
			op = "not";
		}
		
		String salida = "\t" + op;
		writeFile(salida);
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
	
	public void convertir (Tipo tipo) {
		if (tipo.sufijo() == 'b') {
			String salida ="\tb2i";
			writeFile(salida);
		}
	}
	
//	Instruciones de salto
	public void etiqueta(String valor)  {
		String salida =" " + valor + ":";
		writeFile(salida);
	}
	
	public void etiqueta(int num)  {
		String salida =" etiqueta" + num + ":";
		writeFile(salida);
	}
	
	
	public int getEtiquetas(int n) {
		int aux = 0;
		aux += n;
		return aux;
	}
	
	public void jmp(String label)  {
		String salida ="\tjmp " + label + ":";
		writeFile(salida);
	}
	
	public void jz(String label)  {
		String salida ="\tjz " + label + ":";
		writeFile(salida);
	}
	
	public void jnz(String label)  {
		String salida ="\tjnz " + label + ":";
		writeFile(salida);
	}
	
//	Funciones
	public void call(String id)  {
		String salida ="call " + id;
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
		String salida ="halt";
		writeFile(salida);
	}
	
//	Informacion de debug
	public void source(String constante)  {
		String salida ="#source \""+ constante +"\"";
		writeFile(salida);
	}
	
	public void line(int constante)  {
		String salida ="#line\t"+ constante;
		writeFile(salida);
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
