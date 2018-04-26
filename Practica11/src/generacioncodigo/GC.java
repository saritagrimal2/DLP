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
		String salida ="push" + tipo.sufijo() + " " + valor;
		writeFile(salida);
	}
	
	public void pushf(Tipo tipo, double valor)  {
		String salida ="push" + tipo.sufijo() + " " + valor;
		writeFile(salida);
	}
	
	public void pusha(int direccion)  {
		String salida ="pusha " + direccion;
		writeFile(salida);
	}
	
	public void pushabp() {
		String salida ="pushabp";
		writeFile(salida);
	}
	
//	Instrucciones load y store
	public void load(Tipo tipo) {
		String salida ="load" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void store(Tipo tipo) {
		String salida ="store" + tipo.sufijo();
		writeFile(salida);
	}
	
//	Instrucciones pop y dup
	public void pop(Tipo tipo) {
		String salida ="pop" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void dup(Tipo tipo) {
		String salida ="dup" + tipo.sufijo();
		writeFile(salida);
	}

//	Instrucciones aritmeticas
	public void add(Tipo tipo)  {
		String salida ="add" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void sub(Tipo tipo){
		String salida ="sub" + tipo.sufijo();
		writeFile(salida);
	}

	public void mul(Tipo tipo){
		String salida ="mul" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void div(Tipo tipo){
		String salida ="div" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void mod(Tipo tipo) {
		String salida ="mod" + tipo.sufijo();
		writeFile(salida);
	}
	
//	Instrucciones de comparacion
	public void gt(Tipo tipo) {
		String salida ="gt" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void lt(Tipo tipo) {
		String salida ="lt" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void ge(Tipo tipo) {
		String salida ="ge" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void le(Tipo tipo) {
		String salida ="le" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void eq(Tipo tipo) {
		String salida ="eq" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void ne(Tipo tipo) {
		String salida ="ne" + tipo.sufijo();
		writeFile(salida);
	}
	
//	Instrucciones logicas
	public void and() {
		String salida ="and";
		writeFile(salida);
	}
	
	public void or() {
		String salida ="or";
		writeFile(salida);
	}
	
	public void not() {
		String salida ="not";
		writeFile(salida);
	}
	
//	Instrucciones input y output 
	public void out(Tipo tipo)  {
		String salida ="out" + tipo.sufijo();
		writeFile(salida);
	}
	
	public void in(Tipo tipo)  {
		String salida ="in" + tipo.sufijo();
		writeFile(salida);
	}
	
//	Instrucciones de conversion
	public void b2i()  {
		String salida ="b2i";
		writeFile(salida);
	}
	
	public void i2f()  {
		String salida ="i2f";
		writeFile(salida);
	}
	
	public void f2i()  {
		String salida ="f2i";
		writeFile(salida);
	}
	
	public void i2b()  {
		String salida ="i2b";
		writeFile(salida);
	}
	
//	Instruciones de salto
	public void etiqueta(String id)  {
		String salida ="<" + id + ">";
		writeFile(salida);
	}
	
	public void jmp(String label)  {
		String salida ="jmp <" + label + ">";
		writeFile(salida);
	}
	
	public void jz(String label)  {
		String salida ="jz <" + label + ">";
		writeFile(salida);
	}
	
	public void jnz(String label)  {
		String salida ="jnz <" + label + ">";
		writeFile(salida);
	}
	
//	Funciones
	public void call(String id)  {
		String salida ="call <" + id + ">";
		writeFile(salida);
	}
	
	public void enter(int constante)  {
		String salida ="enter <" + constante + ">";
		writeFile(salida);
	}
	
	public void ret(int consReturn, int consLocal, int consParam)  {
		String salida ="ret <" + consReturn + ">" + "<" + consLocal + ">" + "<" + consParam + ">";
		writeFile(salida);
	}
	
//	Finalizacion del programa
	public void halt()  {
		String salida ="halt";
		writeFile(salida);
	}
	
//	Informacion de debug
	public void source(String constante)  {
		String salida ="#source <"+ constante +">";
		writeFile(salida);
	}
	
	public void line(int constante)  {
		String salida ="#line	"+ constante;
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
