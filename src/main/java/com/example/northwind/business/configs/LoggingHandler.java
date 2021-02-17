package com.example.northwind.business.configs;


import java.util.Arrays;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingHandler {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Pointcut("within(@org.springframework.stereotype.Service *)")
  public void service() {
  }

  @Pointcut("execution(* *.*(..))")
  protected void allMethod() {
  }


  @AfterReturning(pointcut = "service() && allMethod()", returning = "result")
  public void logAfter(JoinPoint joinPoint, Object result) {
    String returnValue = this.getValue(result);
    log.debug("Method Return value : {} ", returnValue);
  }

  @AfterThrowing(pointcut = "service() && allMethod()", throwing = "exception")
  public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
    log.error("An exception has been thrown in {}", joinPoint.getSignature().getName() + " ()");
    log.error("Cause : {0}", exception.getCause());
  }

  @Around("service() && allMethod()")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

    long start = System.currentTimeMillis();
    try {
      String className = joinPoint.getSignature().getDeclaringTypeName();
      String methodName = joinPoint.getSignature().getName();
      Object result = joinPoint.proceed();
      long elapsedTime = System.currentTimeMillis() - start;
      log.debug("Method " + className + "." + methodName + " ()" + " execution time : "
          + elapsedTime + " ms");

      return result;
    } catch (IllegalArgumentException e) {
      log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
          + joinPoint.getSignature().getName() + "()");
      throw e;
    }
  }

  private String getValue(Object result) {
    String returnValue = null;
    if (null != result) {
      if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
        returnValue = ReflectionToStringBuilder.toString(result);
      } else {
        returnValue = result.toString();
      }
    }
    return returnValue;
  }
}