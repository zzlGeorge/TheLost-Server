package com.george.dao.mappers;

import com.george.dao.entity.Achievement;
import com.george.utils.generators.mybatis.annotation.MyBatisMapper;

import com.george.utils.generators.mybatis.mapper.CRUDMapper;

import com.george.dao.entity.PlayerAch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisMapper
public interface PlayerAchMapper extends CRUDMapper<PlayerAch, Long> {

    List<Achievement> findGameAchs(@Param("entity") Achievement achievement);
}
