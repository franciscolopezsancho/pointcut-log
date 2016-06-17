package org.aop.logger;

import org.aop.logger.decorator.BasicPrinter;
import org.aop.logger.decorator.FileDecorator;
import org.aop.logger.decorator.HttpDecorator;
import org.aop.logger.decorator.Printer;

public aspect WeaverImpl extends AbstractWeaver   {

	public Printer getPrinter(){
		if(printer == null){ printer = new HttpDecorator(new FileDecorator(new BasicPrinter()));}
		return  printer;
	}

	public pointcut genericPointCut() : 
		!execution(* org.aop.logger..*.*(..)) 
		&& execution(* test.org.aop.logger..*.*(..));
	

}

