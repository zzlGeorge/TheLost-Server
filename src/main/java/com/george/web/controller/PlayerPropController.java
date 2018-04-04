package com.george.web.controller;

import com.george.dao.entity.GameProp;
import com.george.service.PlayerPropService;
import com.george.web.ParamObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : George
 *         Description :
 *         Date : Created in 16:33 2018/4/4
 *         Modified By :
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/playerProp")
public class PlayerPropController extends BaseController {
    @Autowired
    private PlayerPropService playerPropService;

    @RequestMapping(value = "/getProps", method = {RequestMethod.GET})
    @ResponseBody
    public Object getProps(GameProp gameProp) {
        ParamObject paramObject = new ParamObject();
        List<GameProp> endGameLogs = playerPropService.getProps(gameProp);
        putDataInParam(endGameLogs, paramObject);
        return paramObject;
    }
}
