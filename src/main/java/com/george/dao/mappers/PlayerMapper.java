package com.george.dao.mappers;

import com.george.utils.generators.mybatis.annotation.MyBatisMapper;

import com.george.utils.generators.mybatis.mapper.CRUDMapper;

import com.george.dao.entity.Player;

@MyBatisMapper
public interface PlayerMapper extends CRUDMapper<Player, Long> {

}
