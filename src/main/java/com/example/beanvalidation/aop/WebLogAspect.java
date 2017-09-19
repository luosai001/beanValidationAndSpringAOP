package com.example.beanvalidation.aop;

import com.example.beanvalidation.exception.WrapException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by sai.luo on 2017-9-19.
 */
@Component
@Aspect
public class WebLogAspect {

    private static final Logger log = LoggerFactory.getLogger(WebLogAspect.class);
    private static  final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 定义切点
     */
    @Pointcut("execution(* com.example.beanvalidation.controller..*.*(..))")
    public void webLog(){}
    /**
     * 定义前置通知
     */
    @Before("webLog()")
    public void before(JoinPoint joinPoint){
        log.info("前置通知执行");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //记录日志
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("请求方法："+className+"."+methodName);
        log.info("请求参数："+ Arrays.asList(joinPoint.getArgs()[0]));
        log.info("IP:"+request.getRemoteAddr());

    }
    /**
     * 返回通知 不管是抛异常还是正常都是会执行
     */

    @After("webLog()")
    public void after(){
        log.info("后置通知执行");
    }
    /**
     * 定义环绕通知 方法签名有返回类型
     */
    @Around("webLog()")
    public Object  around(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        log.info("环绕通知开始");
        // 实际开发中需要定义一个返回类，包装返回结果。
        /**
         * public class ResponseResult{
         *   int code ;
         *   String message ;
         *   T result ;
         *
         *   // get set methods
         *   // constructor
         *   }
         */
        try {
            Object proceed = proceedingJoinPoint.proceed();
            log.info("返回正常结果："+objectMapper.writeValueAsString(proceed));
            return proceed;
        }catch (WrapException ex){
           log.error("出现异常："+ex.getMessage());
        }

        log.info("环绕通知结束");
        return null;
    }
    /**
     * 异常通知 不建议使用异常通知，因为会使用系统输出打印日志，消耗系统资源，建议在环绕通知中 捕获不同异常，进行处理
     */
    @AfterThrowing(pointcut = "webLog()",throwing = "ex")
    public void afterThrowing(Throwable ex){
        log.info("异常通知");
        log.error("error: "+ex.getMessage());
    }

    /**
     * 正常返回通知
     */
    @AfterReturning(pointcut = "webLog()",returning = "result")
    public void afterReturning(Object result)throws Exception{
        log.info("正常返回通知");
        log.info(objectMapper.writeValueAsString(result));
    }
}
