This app will allow you to log input and output of every method just through configuration
============




In order to make this jar to work **org.aop.logger.AbstractWeaver** must be implemented with following two methods:

#### first

    public Printer getLogger 
where **org.aop.logger.decorator.Printer** should be the result of a chain of decorators that implement

    public abstract String print(Object o) 

(see sample below)

#### second

    public pointcut named genericPointCut()

class implementing **org.aop.logger.AbstractWeaver** can be as following:



    package some.package.name;
    
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
    		!execution(* org.aop.logger.WeaverImpl.*(..)) 
    		&& execution(* test.org.aop.logger..*.*(..));
    	
    
    }
    		



*BasicPrinter*, *FileDecorator* and *HttpDecorator* are already in the jar itself.

In case you want to create your own printer (i.e. *MyPrinter*) and chain it to the others do it like this, inside *WeaverImpl* class:


    public Printer getPrinter(){
    		if(printer == null){ printer = new MyPrinter(new HttpDecorator(new FileDecorator(new BasicPrinter())));}
    		return  printer;
    	}

while pointcut is https://eclipse.org/aspectj/doc/next/progguide/semantics-pointcuts.html


Everything is logged when log4j.properties is set in DEBUG mode. To apply other types of log level different annotation have been provided:

*LoggingTrace*,*LoggingDebug*,*LoggingInfo*,*LoggingWarn*,*LoggingError*,*LoggingOff*

Just attach the desired annotation over the method and Voila!

Hope this helps!
