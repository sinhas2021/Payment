package com.secretesc.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private final static Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

	@Around("execution(* com.secretesc.demo.*.*.*(..))")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		LOGGER.info("Entering class " + pjp.getSignature().getDeclaringTypeName() + ", method " + pjp.getSignature().getName());
		Object retVal = pjp.proceed();
		LOGGER.info("Exiting class " + pjp.getSignature().getDeclaringTypeName() + ", method " + pjp.getSignature().getName());
		return retVal;
	}

}