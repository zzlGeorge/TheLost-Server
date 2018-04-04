package com.george.service.impl;

import com.george.dao.entity.Achievement;
import com.george.dao.entity.PlayerAch;
import com.george.dao.mappers.PlayerAchMapper;
import com.george.service.AchievementService;
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
public class AchievementServiceImpl implements AchievementService {
    @Autowired
    private PlayerAchMapper playerAchMapper;

    public List<Achievement> getAch(Achievement achievement) {
        return playerAchMapper.findGameAchs(achievement);
    }

    public boolean savePlayerAchs(List<PlayerAch> playerAchs) {
        for (PlayerAch ach : playerAchs) {
            ach.setPlayerId(ShiroUtils.getPlayerId());//当前玩家id设置
            //TODO 有成就，跳过写入表
            playerAchMapper.save(ach);
        }
        return true;
    }
}
