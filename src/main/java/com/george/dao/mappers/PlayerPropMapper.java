package com.george.dao.mappers;

import com.george.dao.entity.GameProp;
import com.george.utils.generators.mybatis.annotation.MyBatisMapper;

import com.george.utils.generators.mybatis.mapper.CRUDMapper;

import com.george.dao.entity.PlayerProp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisMapper
public interface PlayerPropMapper extends CRUDMapper<PlayerProp, Long> {

    List<GameProp> findGameProp(@Param("entity") GameProp gameProp);
}
