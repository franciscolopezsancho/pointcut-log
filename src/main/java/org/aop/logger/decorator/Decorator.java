package org.aop.logger.decorator;

public class Decorator extends Printer {
	private Printer printer;
	
	public Decorator(Printer p){
		this.printer = p;
	}
	
	public String print(Object o){
		return printer.print(o);
	}
	
}
