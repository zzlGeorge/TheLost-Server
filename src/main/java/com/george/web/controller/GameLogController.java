package com.george.web.controller;

import com.george.dao.entity.EndGameLog;
import com.george.dao.entity.LikedLog;
import com.george.dao.entity.Player;
import com.george.dao.entity.SendCoinsLog;
import com.george.service.GameLogService;
import com.george.web.ParamObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : George
 *         Description :
 *         Date : Created in 9:54 2018/4/4
 *         Modified By :
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/gameLog")
public class GameLogController extends BaseController {
    @Autowired
    private GameLogService gameLogService;

    /**
     * 查前30条记录
     * @param endGameLog
     * @return
     */
    @RequestMapping(value = "/getEndGameData", method = {RequestMethod.GET})
    @ResponseBody
    public Object getEndGameData(EndGameLog endGameLog) {
        ParamObject paramObject = new ParamObject();
        List<EndGameLog> endGameLogs = gameLogService.getEndGameData(endGameLog);
        putDataInParam(endGameLogs, paramObject);
        return paramObject;
    }


    @RequestMapping(value = "/getScoreRank", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object getScoreRank() {
        ParamObject paramObject = new ParamObject();
        List<Player> scoreRank = gameLogService.getScoreRank();
        putDataInParam(scoreRank, paramObject);
        return paramObject;
    }



    @RequestMapping(value = "/saveEndGameData", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object saveEndGameData(EndGameLog endGameLog) {
        ParamObject paramObject = new ParamObject();
        boolean res = gameLogService.saveEndGameData(endGameLog);
        operateResult(res, paramObject);
        return paramObject;
    }

    @RequestMapping(value = "/saveLikedLog", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object saveLikedLog(LikedLog likedLog) {
        ParamObject paramObject = new ParamObject();
        boolean res = gameLogService.saveLikedLog(likedLog);
        operateResult(res, paramObject);
        return paramObject;
    }

    @RequestMapping(value = "/saveSendCoinsLog", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object saveSendCoinsLog(SendCoinsLog sendCoinsLog) {
        ParamObject paramObject = new ParamObject();
        boolean res = gameLogService.saveSendCoinsLog(sendCoinsLog);
        operateResult(res, paramObject);
        return paramObject;
    }
}
