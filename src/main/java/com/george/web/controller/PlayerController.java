package com.george.web.controller;

import com.george.dao.entity.Player;
import com.george.service.PlayerService;
import com.george.shiro.ShiroUtils;
import com.george.web.ParamObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by admin on 2018/3/24.
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/player")
public class PlayerController extends BaseController {
    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public Object getPlayers(Player player) {
        ParamObject paramObject = new ParamObject();
        paramObject.setDataList(playerService.getPlayers(player));
        paramObject.setCode(1);
        return paramObject;
    }

    @RequestMapping(value = "/remindLogin", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object remindLogin() {
        ParamObject paramObject = new ParamObject();
        paramObject.setMessage("请登陆！");
        paramObject.setCode(1);
        return paramObject;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object register(Player player) {
        ParamObject paramObject = new ParamObject();
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(player.getUserName(), player.getPassword());
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            paramObject.setMessage(e.getMessage());
            paramObject.setCode(0);
            return paramObject;
        } catch (UnknownAccountException e) {
            paramObject.setMessage(e.getMessage());
            paramObject.setCode(0);
            return paramObject;
        }
        operateResult(true, paramObject);
        paramObject.setDataList(((ParamObject) getCtPlayer()).getDataList());
        return paramObject;
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Object logout() {
        Player playerEntity = ShiroUtils.getPlayerEntity();
        playerEntity.setLoginStatus(0);
        playerService.updatePlayer(playerEntity);//更新登录状态

        ShiroUtils.logout();
        ParamObject paramObject = new ParamObject();
        operateResult(true, paramObject);
        return paramObject;
    }

    /**
     * 终端传来的数据中文会乱码，待解决【2018年3月29日 15:57:17】【已解决】
     *
     * @param player
     * @return
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object savePlayers(Player player) {
//        Player player1 = (Player) CommonUtils.decodePojo(player);
        ParamObject paramObject = new ParamObject();
        boolean res = playerService.savePlayer(player);
        operateResult(res, paramObject);
        return paramObject;
    }


    @RequestMapping(value = "/getCtPlayer", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object getCtPlayer() {
        ParamObject paramObject = new ParamObject();
        paramObject.setDataList(ShiroUtils.getPlayerEntity());
        paramObject.setCode(1);
        return paramObject;
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public Object updatePlayers(Player player) {
        ParamObject paramObject = new ParamObject();
        boolean res = playerService.updatePlayer(player);
        operateResult(res, paramObject);
        return paramObject;
    }
}
