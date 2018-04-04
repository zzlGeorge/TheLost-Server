package com.george.web.controller;

import com.george.web.ParamObject;

/**
 * @author : George
 *         Description :
 *         Date : Created in 11:21 2018/4/4
 *         Modified By :
 */
public class BaseController {

    protected void operateResult(boolean res, ParamObject paramObject) {
        if (res) {
            paramObject.setCode(1);
            paramObject.setMessage("success!");
        } else {
            paramObject.setCode(0);
            paramObject.setMessage("failure!!");
        }
    }

    protected void putDataInParam(Object data, ParamObject paramObject) {
        paramObject.setCode(1);
        paramObject.setDataList(data);
        paramObject.setMessage("success!");
    }

}
