package com.george.dao.mappers;

import com.george.dao.entity.GameCharacter;
import com.george.utils.generators.mybatis.annotation.MyBatisMapper;

import com.george.utils.generators.mybatis.mapper.CRUDMapper;

import com.george.dao.entity.PlayerCharacter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisMapper
public interface PlayerCharacterMapper extends CRUDMapper<PlayerCharacter, Long> {

    List<GameCharacter> findGameCharacters(@Param("entity") GameCharacter character);

    List<GameCharacter> getGameCharacterByPlayerId(@Param("playerId") Integer playerId);
}
