package com.george.service.impl;

import com.george.dao.entity.GameCharacter;
import com.george.dao.entity.PlayerCharacter;
import com.george.dao.mappers.PlayerCharacterMapper;
import com.george.service.CharacterService;
import com.george.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 2018/4/4.
 */

@Service
@Transactional
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private PlayerCharacterMapper characterMapper;

    public List<GameCharacter> getCharacter(GameCharacter character) {
        return characterMapper.findGameCharacters(character);
    }

    public boolean savePlayerCharacter(List<PlayerCharacter> characters) {
        for (PlayerCharacter playerCharacter : characters) {
            playerCharacter.setPlayerId(ShiroUtils.getPlayerId());//当前玩家id设置
            //TODO 有成就，跳过写入表
            characterMapper.save(playerCharacter);
        }
        return true;
    }
}
