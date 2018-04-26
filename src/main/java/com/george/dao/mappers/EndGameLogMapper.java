package com.george.dao.mappers;

import com.george.dao.entity.Player;
import com.george.utils.generators.mybatis.annotation.MyBatisMapper;

import com.george.utils.generators.mybatis.mapper.CRUDMapper;

import com.george.dao.entity.EndGameLog;

import java.util.List;

@MyBatisMapper
public interface EndGameLogMapper extends CRUDMapper<EndGameLog, Long> {

    List<Player> getScoreRank();
}
