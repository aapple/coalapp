package com.skyblue.coalapp.server.example.controller;

import com.young.wuxia.server.domain.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yaobin on 2017/4/28.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

//    @ExceptionHandler(IllegalArgumentException.class)
////    @ResponseStatus(HttpStatus.BAD_REQUEST)
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
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

}
