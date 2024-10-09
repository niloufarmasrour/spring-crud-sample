package com.isc.masrour.crudSample.sample.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Slf4j
@Component
public class CustomerAspectImplementation {

    @Before(value = " execution (* com.isc.masrour.crudSample.sample.controller.*(..)) ")
    public void beforeCallController(JoinPoint joinPoint){
        log.info("Request to " + joinPoint.getSignature() + " started at " + new Date());
    }

    @After(value = " execution (* com.isc.masrour.crudSample.sample.controller.*(..)) ")
    public void afterCallController(JoinPoint joinPoint){
        log.info("Request to " + joinPoint.getSignature() + " finished at " + new Date());
    }

    @AfterReturning(pointcut = "execution(* com.isc.masrour.crudSample.sample.service.CustomerService.*(..))" , returning="customer")
    public void afterReturningAdvice(Object customer) {
        log.info("After returning advice: Processing the result - " + customer);
    }
}
