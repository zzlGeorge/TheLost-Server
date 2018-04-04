package com.george.service;

import com.george.dao.entity.GameCharacter;
import com.george.dao.entity.PlayerCharacter;

import java.util.List;

/**
 * Created by admin on 2018/4/4.
 */
public interface CharacterService {
    List<GameCharacter> getCharacter(GameCharacter character);

    boolean savePlayerCharacter(List<PlayerCharacter> characters);
}
