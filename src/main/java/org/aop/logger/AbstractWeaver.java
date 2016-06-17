package org.aop.logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.aop.logger.decorator.Printer;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;

public abstract aspect AbstractWeaver  {
	
	public static Printer printer;
	
	public abstract Printer getPrinter();
	
	public abstract pointcut genericPointCut();
	
	before() : genericPointCut() {
		if (!thisJoinPointStaticPart.getSignature().getName().contains("lambda")) {
			Object[] paramValues = thisJoinPoint.getArgs();
			String[] paramNames = ((CodeSignature) thisJoinPointStaticPart.getSignature()).getParameterNames();
			StringBuilder logLine = new StringBuilder("I : ").append(thisJoinPointStaticPart.getSignature().getName())
					.append("(");
			if (paramNames.length != 0)
				getPrinter().logParamValues(logLine, paramNames, paramValues);
			logLine.append(")");
			// getPrinter().getLogger(thisJoinPoint).info(logLine.toString());
			log((MethodSignature) thisJoinPointStaticPart.getSignature(), getPrinter().getLogger(thisJoinPoint),
					logLine.toString());
		}
	}

	@SuppressWarnings("rawtypes")
	after() returning(Object r): genericPointCut() {
		if (!thisJoinPointStaticPart.getSignature().getName().contains("lambda")) {
			String result = null;
			if (r != null && (!(r instanceof List) || ((List) r).size() != 0)) {
				StringBuilder rv = new StringBuilder("O : ");
				rv.append(getPrinter().print(r));
				result = rv.toString();
			}
			Object[] paramValues = thisJoinPoint.getArgs();

			String[] paramNames = ((CodeSignature) thisJoinPointStaticPart.getSignature()).getParameterNames();
			StringBuilder logLine = new StringBuilder(thisJoinPointStaticPart.getSignature().getName()).append("(");
			if (paramNames.length != 0)
				getPrinter().logParamValues(logLine, paramNames, paramValues);
			logLine.append(")");
			if (result == null)
				result = "O : void";
			log((MethodSignature) thisJoinPointStaticPart.getSignature(), getPrinter().getLogger(thisJoinPoint),
					result + " -- from I : " + logLine.toString());
		}
	}

	private void log(MethodSignature ms, Logger logger, String toLog) {
		Method targetMethod = ms.getMethod();
		List<Annotation> anns = Arrays.asList(targetMethod.getAnnotations());
		List<String> annType = anns.stream().map(e -> e.annotationType().getName()).collect(Collectors.toList());
		// by default logs debug
		if (annType.isEmpty()) {
			logger.debug(toLog);
		} else if (annType.contains(org.aop.logger.annotation.LoggingOff.class.getName())) {
			// then no log
		} else if (annType.contains(org.aop.logger.annotation.LoggingTrace.class.getName())) {
			logger.trace(toLog);
		} else if (annType.contains(org.aop.logger.annotation.LoggingDebug.class.getName())) {
			logger.debug(toLog);
		} else if (annType.contains(org.aop.logger.annotation.LoggingInfo.class.getName())) {
			logger.info(toLog);
		} else if (annType.contains(org.aop.logger.annotation.LoggingWarn.class.getName())) {
			logger.warn(toLog);
		} else if (annType.contains(org.aop.logger.annotation.LoggingError.class.getName())) {
			logger.error(toLog);
		} else {
			logger.debug(toLog);
		}
	}
}
