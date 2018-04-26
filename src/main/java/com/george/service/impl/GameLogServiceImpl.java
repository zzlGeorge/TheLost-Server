package com.george.service.impl;

import com.george.dao.entity.EndGameLog;
import com.george.dao.entity.LikedLog;
import com.george.dao.entity.Player;
import com.george.dao.entity.SendCoinsLog;
import com.george.dao.mappers.EndGameLogMapper;
import com.george.dao.mappers.LikedLogMapper;
import com.george.dao.mappers.SendCoinsLogMapper;
import com.george.service.GameLogService;
import com.george.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : George
 *         Description :
 *         Date : Created in 11:20 2018/4/4
 *         Modified By :
 */

@Service
@Transactional
public class GameLogServiceImpl implements GameLogService {

    @Autowired
    private EndGameLogMapper endGameLogMapper;
    @Autowired
    private LikedLogMapper likedLogMapper;
    @Autowired
    private SendCoinsLogMapper sendCoinsLogMapper;

    public boolean saveEndGameData(EndGameLog endGameLog) {
//        endGameLog.setPlayerId(ShiroUtils.getPlayerId());//当前玩家id设置
        return endGameLogMapper.save(endGameLog) > 0;
    }

    public List<EndGameLog> getEndGameData(EndGameLog endGameLog) {
        return endGameLogMapper.findByEntity(endGameLog, null);
    }

    public boolean saveLikedLog(LikedLog likedLog) {
//        likedLog.setFromWhoId(ShiroUtils.getPlayerId());//当前玩家id设置
        return likedLogMapper.save(likedLog) > 0;
    }

    public boolean saveSendCoinsLog(SendCoinsLog sendCoinsLog) {
        // TODO 送多少金币？
//        sendCoinsLog.setFromWhoId(ShiroUtils.getPlayerId());//当前玩家id设置
        return sendCoinsLogMapper.save(sendCoinsLog) > 0;
    }

    public List<Player> getScoreRank() {
        return endGameLogMapper.getScoreRank();
    }

}
