package com.george.service;

import com.george.dao.entity.EndGameLog;
import com.george.dao.entity.LikedLog;
import com.george.dao.entity.Player;
import com.george.dao.entity.SendCoinsLog;

import java.util.List;

/**
 * @author : George
 *         Description :
 *         Date : Created in 11:19 2018/4/4
 *         Modified By :
 */
public interface GameLogService {
    boolean saveEndGameData(EndGameLog endGameLog);

    List<EndGameLog> getEndGameData(EndGameLog endGameLog);

    boolean saveLikedLog(LikedLog likedLog);

    boolean saveSendCoinsLog(SendCoinsLog sendCoinsLog);

    List<Player> getScoreRank();
}
