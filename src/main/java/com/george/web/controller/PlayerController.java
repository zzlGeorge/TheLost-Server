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
    public Object getPlayers(Player player) {
        ParamObject paramObject = new ParamObject();
        paramObject.setDataList(playerService.getPlayers(player));
        return paramObject;
    }

    /**
     * 终端传来的数据中文会乱码，待解决【2018年3月29日 15:57:17】
     *
     * @param player
     * @param str
     * @return
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
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
//        operateResult(res, paramObject);
        return paramObject;
    }

    @RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object updatePlayers(Player player) {
        ParamObject paramObject = new ParamObject();
        boolean res = playerService.updatePlayer(player);
        operateResult(res, paramObject);
        return paramObject;
    }

    private void operateResult(boolean res, ParamObject paramObject) {
        if (res) {
            paramObject.setCode(1);
            paramObject.setMessage("success!");
        } else {
            paramObject.setCode(0);
            paramObject.setMessage("failure!!");
        }
    }
}
