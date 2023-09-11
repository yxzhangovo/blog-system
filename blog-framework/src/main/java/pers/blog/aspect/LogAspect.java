package pers.blog.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pers.blog.annotation.SystemLog;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zyx
 * @create: 2023/8/31
 * @description: 配置切面
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    // 切点
    @Pointcut("@annotation(pers.blog.annotation.SystemLog)")
    public void pt() {

    }

    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);
        } finally {
            log.info("=======End=======" + System.lineSeparator());
        }
        return ret;

    }

    private void handleAfter(Object ret) {
        log.info("Response       : {}", JSON.toJSONString(ret));    // 打印出参
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // 获取被增强方法上的注解对象
        SystemLog systemLog = getSystemLog(joinPoint);

        log.info("=======Start=======");

        log.info("URL            : {}", request.getRequestURL());   // 打印请求 URL

        log.info("BusinessName   : {}", systemLog.businessName());  // 打印描述信息

        log.info("HTTP Method    : {}", request.getMethod());   // 打印请求方式

        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());    // 打印调用Controller的全路径以及执行方法

        log.info("IP             : {}", request.getRemoteHost());   // 打印请求的IP

        log.info("Request Args   : {}", JSON.toJSONString(joinPoint.getArgs()));    // 打印请求入参
    }

    /**
     * 获取注解对象
     * @param joinPoint
     * @return
     */
    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        SystemLog systemLog = methodSignature.getMethod().getAnnotation(SystemLog.class);
        return systemLog;
    }
}