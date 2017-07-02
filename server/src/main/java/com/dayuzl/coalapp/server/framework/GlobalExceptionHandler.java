package com.dayuzl.coalapp.server.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

//    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public String processValidationException(IllegalArgumentException e){
//        logger.error("error");
//        return e.getMessage();
//    }

//    public static final String DEFAULT_ERROR_VIEW = "error";
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName(DEFAULT_ERROR_VIEW);
//        return mav;
//    }


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String processException(HttpServletRequest req, Exception e) throws Exception {

        logger.error(e.getMessage(), e);
        if (e instanceof BusinessException){
            return e.getMessage();
        } else {
            return "系统错误，请联系开发人员！";
        }
    }
}
