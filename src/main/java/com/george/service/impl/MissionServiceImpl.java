package com.george.service.impl;

import com.george.dao.entity.Mission;
import com.george.dao.entity.PlayerMission;
import com.george.dao.mappers.PlayerMissionMapper;
import com.george.service.MissionService;
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
public class MissionServiceImpl implements MissionService{
    @Autowired
    private PlayerMissionMapper missionMapper;


    public List<Mission> getMissions(Mission mission) {
        return missionMapper.findMissions(mission);
    }

    public boolean saveOrUpdatePlayerMissions(List<PlayerMission> missionList) {
        for (PlayerMission playerMission : missionList) {
            playerMission.setPlayerId(ShiroUtils.getPlayerId());//当前玩家id设置
            //TODO 有任务，更新；否者，写入表数据
            missionMapper.save(playerMission);
        }
        return true;
    }
}
