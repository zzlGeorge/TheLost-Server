package com.george.service;

import com.george.dao.entity.Achievement;
import com.george.dao.entity.PlayerAch;

import java.util.List;

/**
 * Created by admin on 2018/4/4.
 */
public interface AchievementService {
    List<Achievement> getAch(Achievement achievement);

    boolean savePlayerAchs(List<PlayerAch> props);
}
