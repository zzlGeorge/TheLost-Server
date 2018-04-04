package com.george.dao.mappers;

import com.george.dao.entity.Mission;
import com.george.utils.generators.mybatis.annotation.MyBatisMapper;

import com.george.utils.generators.mybatis.mapper.CRUDMapper;

import com.george.dao.entity.PlayerMission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisMapper
public interface PlayerMissionMapper extends CRUDMapper<PlayerMission, Long> {

    List<Mission> findMissions(@Param("entity") Mission mission);
}
