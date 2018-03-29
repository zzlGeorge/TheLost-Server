package com.george.service.impl;

import com.george.dao.entity.Player;
import com.george.dao.mappers.PlayerMapper;
import com.george.service.PlayerService;
import com.george.web.exception.ex.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 2018/3/21.
 */
@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerMapper playerMapper;

    public List<Player> getPlayers(Player player) {
        return playerMapper.findByEntity(player, null);
    }

    public boolean savePlayer(Player player) {
        int operate = playerMapper.save(player);
        return operate > 0;
    }

    public boolean updatePlayer(Player player) {
        if (player.getId() == null) {
            try {
                throw new CustomException("id不可不传！");
            } catch (CustomException e) {
                e.printStackTrace();
            }
        }
        int operate = playerMapper.update(player);
        return operate > 0;
    }
}
