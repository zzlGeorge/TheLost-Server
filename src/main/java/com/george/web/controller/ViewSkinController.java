package com.george.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.george.dao.entity.PlayerViewSkin;
import com.george.dao.entity.ViewSkin;
import com.george.service.ViewSkinService;
import com.george.utils.CommonUtils;
import com.george.web.ParamObject;
import com.george.web.exception.ex.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2018/4/4.
 */

@CrossOrigin
@Controller
@RequestMapping(value = "/viewSkin")
public class ViewSkinController extends BaseController {
    @Autowired
    private ViewSkinService viewSkinService;

    @RequestMapping(value = "/getViewSkins", method = {RequestMethod.GET})
    @ResponseBody
    public Object getViewSkins(ViewSkin viewSkin) {
        ParamObject paramObject = new ParamObject();
        List<ViewSkin> viewSkins = viewSkinService.getViewSkins(viewSkin);
        putDataInParam(viewSkins, paramObject);
        return paramObject;
    }

    @RequestMapping(value = "/savePlayerViewSkins", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object savePlayerViewSkins(String playerViewSkins) {
        ParamObject paramObject = new ParamObject();

        if (CommonUtils.strIsEmpty(playerViewSkins)) {
            throw new CustomException("没有任何场景皮肤对应传来！");
        }
        List<PlayerViewSkin> playerViewSkinList = JSONArray.parseArray(playerViewSkins, PlayerViewSkin.class);

        boolean res = viewSkinService.savePlayerViewSkins(playerViewSkinList);
        operateResult(res, paramObject);
        return paramObject;
    }
}
