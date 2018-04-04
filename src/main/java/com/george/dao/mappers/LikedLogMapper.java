package com.george.dao.mappers;

import com.george.utils.generators.mybatis.annotation.MyBatisMapper;

import com.george.utils.generators.mybatis.mapper.CRUDMapper;

import com.george.dao.entity.LikedLog;

@MyBatisMapper
public interface LikedLogMapper extends CRUDMapper<LikedLog, Long> {

}
