package com.george.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.george.dao.entity.Achievement;
import com.george.dao.entity.PlayerAch;
import com.george.service.AchievementService;
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
@RequestMapping(value = "/achievement")
public class AchievementController extends BaseController{
    @Autowired
    private AchievementService achievementService;

    @RequestMapping(value = "/getAch", method = {RequestMethod.GET})
    @ResponseBody
    public Object getAch(Achievement achievement) {
        ParamObject paramObject = new ParamObject();
        List<Achievement> endGameLogs = achievementService.getAch(achievement);
        putDataInParam(endGameLogs, paramObject);
        return paramObject;
    }

    @RequestMapping(value = "/savePlayerAchs", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object savePlayerAchs(String playerAchs) {
        ParamObject paramObject = new ParamObject();

        if (CommonUtils.strIsEmpty(playerAchs)) {
            throw new CustomException("没有任何成就数据传来！");
        }
        List<PlayerAch> playerAches = JSONArray.parseArray(playerAchs, PlayerAch.class);

        boolean res = achievementService.savePlayerAchs(playerAches);
        operateResult(res, paramObject);
        return paramObject;
    }

}
