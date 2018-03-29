package com.george.web.exception;

import com.george.web.exception.ex.CustomException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : George
 *         Description :
 *         Date : Created in 16:35 2018/3/29
 *         Modified By :
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    /**
     * 异常处理管理类
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @return
     */
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse, Object o, Exception e) {
        e.printStackTrace();
        CustomException customException = null;

        //如果抛出的是系统自定义的异常则直接转换
        if (e instanceof CustomException) {
            customException = (CustomException) e;
        } else {
            //如果抛出的不是系统自定义的异常则重新构造一个未知错误异常
        }

        //向前台返回错误信息
        ModelAndView modelAndView = new ModelAndView();
        assert customException != null;
        modelAndView.addObject("message", customException.getMessage());
        modelAndView.setViewName("/WEB-INF/jsp/error.jsp");

        return modelAndView;
    }

}
