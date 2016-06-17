package org.aop.logger.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Printer {

	public abstract String print(Object o);


	public Logger getLogger(org.aspectj.lang.JoinPoint joinPoint) {
			@SuppressWarnings("rawtypes")
			Class declaringType = joinPoint.getSignature().getDeclaringType();
			return LoggerFactory.getLogger(declaringType);
	}

	public void logParamValues(StringBuilder logLine, String[] paramNames, Object[] paramValues) {
		for (int i = 0; i < paramValues.length; i++) {
			logLine.append(paramNames[i]).append("=").append(print(paramValues[i]));
			if (i < paramValues.length - 1)
				logLine.append(", ");
		}
	}
}
