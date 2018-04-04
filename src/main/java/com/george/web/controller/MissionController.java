package com.george.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.george.dao.entity.Mission;
import com.george.dao.entity.PlayerMission;
import com.george.service.MissionService;
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
@RequestMapping(value = "/mission")
public class MissionController extends BaseController {

    @Autowired
    private MissionService missionService;

    @RequestMapping(value = "/getMissions", method = {RequestMethod.GET})
    @ResponseBody
    public Object getMissions(Mission mission) {
        ParamObject paramObject = new ParamObject();
        List<Mission> missionList = missionService.getMissions(mission);
        putDataInParam(missionList, paramObject);
        return paramObject;
    }

    @RequestMapping(value = "/saveOrUpdatePlayerMissions", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object saveOrUpdatePlayerMissions(String playerViewSkins) {
        ParamObject paramObject = new ParamObject();

        if (CommonUtils.strIsEmpty(playerViewSkins)) {
            throw new CustomException("没有任何任务对应传来！");
        }
        List<PlayerMission> missionList = JSONArray.parseArray(playerViewSkins, PlayerMission.class);

        boolean res = missionService.saveOrUpdatePlayerMissions(missionList);
        operateResult(res, paramObject);
        return paramObject;
    }
}
