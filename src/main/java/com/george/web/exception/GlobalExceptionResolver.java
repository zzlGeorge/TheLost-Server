package com.george.web.exception;

import com.george.web.ParamObject;
import com.george.web.exception.ex.CustomException;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : George
 *         Description :
 *         Date : Created in 10:32 2018/4/2
 *         Modified By :
 */

@ControllerAdvice
public class GlobalExceptionResolver {

    @ExceptionHandler(value = CustomException.class)
    public @ResponseBody
    Object serviceCommonExceptionHandler(Exception e) {
        //对捕获的异常进行处理并打印日志等，之后返回json数据，方式与Controller相同
        if (e instanceof CustomException || e instanceof AuthenticationException) {
            ParamObject paramObject = new ParamObject();
            paramObject.setCode(0);
            paramObject.setMessage(e.getMessage());
            return paramObject;
        }
        return "未知错误！";
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exceptionHandler() {
        //当然也可以直接返回ModelAndView等类型，然后跳转相应的错误页面，这都根据实际的需要进行使用
        return new ModelAndView();
    }
}
