package com.ttzf.aop;

import com.ttzf.utils.CommonUtils;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {

    private static Logger logger = LoggerFactory.getLogger(ExceptionAspect.class.getName());

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)" +
                 "||@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void myPointCut() {}

    @AfterThrowing(pointcut = "myPointCut()", throwing = "e")
    public void handleException(Exception e) {
        e.printStackTrace();
        logger.error(e.getMessage());
        CommonUtils.writeContent("出现异常:"+e.getMessage());
    }
}
