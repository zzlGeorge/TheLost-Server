package com.george.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.george.service.PlayerService;
import com.george.web.ParamObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by George on 2017/12/11.
 */

@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object testPage() {
        ParamObject paramObject = new ParamObject();
        paramObject.setDataList(playerService.getAPlayer());
        return paramObject;
    }

}
