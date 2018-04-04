package com.george.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.george.dao.entity.GameCharacter;
import com.george.dao.entity.PlayerCharacter;
import com.george.service.CharacterService;
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
@RequestMapping(value = "/character")
public class CharacterController extends BaseController {
    @Autowired
    private CharacterService characterService;

    @RequestMapping(value = "/getCharacter", method = {RequestMethod.GET})
    @ResponseBody
    public Object getCharacter(GameCharacter character) {
        ParamObject paramObject = new ParamObject();
        List<GameCharacter> gameCharacters = characterService.getCharacter(character);
        putDataInParam(gameCharacters, paramObject);
        return paramObject;
    }

    @RequestMapping(value = "/savePlayerCharacter", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object savePlayerCharacter(String playerCharacters) {
        ParamObject paramObject = new ParamObject();

        if (CommonUtils.strIsEmpty(playerCharacters)) {
            throw new CustomException("没有任何角色数据传来！");
        }
        List<PlayerCharacter> playerCharacterList = JSONArray.parseArray(playerCharacters, PlayerCharacter.class);

        boolean res = characterService.savePlayerCharacter(playerCharacterList);
        operateResult(res, paramObject);
        return paramObject;
    }
}
