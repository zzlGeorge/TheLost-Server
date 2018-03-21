package com.george.service.impl;

import com.george.dao.entity.Player;
import com.george.dao.mappers.PlayerMapper;
import com.george.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2018/3/21.
 */
@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    private PlayerMapper playerMapper;

    public Player getAPlayer() {
        return playerMapper.get(1L);
    }
}
