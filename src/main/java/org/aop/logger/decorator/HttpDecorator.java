package org.aop.logger.decorator;

import org.springframework.http.ResponseEntity;

public class HttpDecorator extends Decorator {

	public HttpDecorator(Printer p) {
		super(p);
	}

	@Override
	public String print(Object object) {
		StringBuilder result = new StringBuilder(super.print(object));
		if (object instanceof ResponseEntity<?>)
			result.append(((ResponseEntity<String>) object).toString());
		return result.toString();
	}

}
