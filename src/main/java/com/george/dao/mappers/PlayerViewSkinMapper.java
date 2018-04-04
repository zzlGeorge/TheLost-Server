package com.george.dao.mappers;

import com.george.dao.entity.ViewSkin;
import com.george.utils.generators.mybatis.annotation.MyBatisMapper;

import com.george.utils.generators.mybatis.mapper.CRUDMapper;

import com.george.dao.entity.PlayerViewSkin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisMapper
public interface PlayerViewSkinMapper extends CRUDMapper<PlayerViewSkin, Long> {

    List<ViewSkin> findViewSkins(@Param("entity") ViewSkin viewSkin);
}
