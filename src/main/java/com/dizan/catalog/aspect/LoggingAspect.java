package com.dizan.catalog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.dizan.catalog.dto.BookDetailResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class LoggingAspect {

	@Pointcut("execution (* com.dizan.catalog.web.*.*(com.dizan.catalog.dto.PublisherCreateRequestDTO))")
	private void restApi() {
	}

	@Pointcut("within(com.dizan.catalog.web.*)")
	private void withinPointcutExample() {
	}

	@Pointcut("args(com.dizan.catalog.dto.PublisherCreateRequestDTO)")
	private void argsPointcutExample() {
	}

	@Pointcut("@args(com.dizan.catalog.annotation.LogThisArg)")
	private void argsAnnotationPointcutExample() {
	}

	@Pointcut("@annotation(com.dizan.catalog.annotation.LogThisMethod)")
	private void annotationPointcutExample() {
	}

	@Before("annotationPointcutExample()")
	public void beforeExecutionLogging() {
		log.info("this is log from aspect before method executed");
	}

	@After("annotationPointcutExample()")
	public void afterExecutionLogging() {
		log.info("this is log from aspect after method executed");
	}

	@AfterReturning("annotationPointcutExample()")
	public void afterReturnExecutionLogging() {
		log.info("this is log from aspect after returning method executed");
	}

	@AfterThrowing("annotationPointcutExample()")
	public void afterThrowingExecutionLogging() {
		log.info("this is log from aspect after throwing method executed");
	}

	@Around("restApi()")
	public Object processingTimeLogging(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		
		try {	
			log.info("start {}.{} ", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
			stopWatch.start();
			return joinPoint.proceed();
		}finally {
			stopWatch.stop();
			log.info("finish {}.{} execution time = {}", joinPoint.getTarget().getClass().getName(),
					joinPoint.getSignature().getName(), stopWatch.getTotalTimeMillis());
			
		}
	}
}
