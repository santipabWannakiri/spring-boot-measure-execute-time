package com.measure.execute.time.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.measure.execute.time.model.LogInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.measure.execute.time.serviceImpl.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] request = joinPoint.getArgs();
        log.info(mapper.writeValueAsString(new LogInfo(className, methodName, 0.0 ,request)));
    }

    @Around("execution(* com.measure.execute.time.serviceImpl.*.*(..))")
    public Object logMethodExitAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        long startTime = System.currentTimeMillis();
        Object response = joinPoint.proceed();
        long executionTimeMillis = (System.currentTimeMillis() - startTime);
        double executionTimeSeconds = (double) executionTimeMillis / 1000.0;

        // Check the type of the response and handle accordingly
        if (response instanceof Optional<?>) {
            Optional<?> optionalResponse = (Optional<?>) response;
            if (optionalResponse.isPresent()) {
                // Response is present, handle it
                Object actualResponse = optionalResponse.get();
                log.info(mapper.writeValueAsString(new LogInfo(className, methodName,executionTimeSeconds, actualResponse)));
            } else {
                // Response is empty (Optional is empty)
                log.info("{}.{} : Response is empty", className, methodName);
            }
        } else if (response instanceof List<?>) {
            // Response is a List, handle it
            List<?> listResponse = (List<?>) response;
            log.info(mapper.writeValueAsString(new LogInfo(className, methodName, executionTimeSeconds, listResponse)));
        } else {
            // Handle other types if needed
            log.info(mapper.writeValueAsString(new LogInfo(className, methodName, executionTimeSeconds, response)));
        }
        return response;
    }
}
