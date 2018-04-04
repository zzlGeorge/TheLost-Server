package com.george.service;

import com.george.dao.entity.Mission;
import com.george.dao.entity.PlayerMission;

import java.util.List;

/**
 * Created by admin on 2018/4/4.
 */
public interface MissionService {
    List<Mission> getMissions(Mission mission);

    boolean saveOrUpdatePlayerMissions(List<PlayerMission> missionList);
}
