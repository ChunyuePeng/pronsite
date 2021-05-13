package com.pcy.pronsite.shiro;

import com.pcy.pronsite.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ShiroExceptionHandler {

    private final Log logger = LogFactory.getLog(ShiroExceptionHandler.class);

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public Object unauthenticatedHandler(UnauthenticatedException e) {
        logger.warn(e.getMessage(), e);
        return ResponseUtil.unlogin();
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public Object unauthorizedHandler(AuthorizationException e) {
        logger.warn(e.getMessage(), e);
        return ResponseUtil.unauthz();
    }

}
