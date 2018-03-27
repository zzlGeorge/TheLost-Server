package com.george.web.controller;

import com.george.dao.entity.Player;
import com.george.service.PlayerService;
import com.george.utils.CommonUtils;
import com.george.web.ParamObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by admin on 2018/3/24.
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public Object getPlayers() {
        ParamObject paramObject = new ParamObject();
        paramObject.setDataList(playerService.getAPlayer());
        return paramObject;
    }

    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object savePlayers(Player player, String str) {
        Player player1 = (Player) CommonUtils.decodePojo(player);
        try {//文件页面为utf-8
            CommonUtils.getEncoding(str);
            String s = new String(str.getBytes("ISO-8859-1"), "UTF-8");
            URLDecoder.decode(str, "utf-8");
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ParamObject paramObject = new ParamObject();
//        boolean res = playerService.savePlayer(player);
//        if (res) {
//            paramObject.setCode(1);
//            paramObject.setMessage("success!");
//        } else {
//            paramObject.setCode(0);
//            paramObject.setMessage("failure!!");
//        }
        return paramObject;
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public Object updatePlayers() {
        ParamObject paramObject = new ParamObject();
        return paramObject;
    }
}
