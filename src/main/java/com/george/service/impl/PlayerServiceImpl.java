package com.george.service.impl;

import com.george.dao.entity.Player;
import com.george.dao.mappers.PlayerMapper;
import com.george.service.PlayerService;
import com.george.utils.CommonUtils;
import com.george.utils.Pagine;
import com.george.web.exception.ex.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        Pagine<Player> pagine = new Pagine<Player>(1, 1, Sort.Direction.DESC, "createTime");
        return playerMapper.findByEntity(player, pagine);
    }

    public boolean savePlayer(Player player) {
        player.setPassword(CommonUtils.md5Password(player.getPassword()));//加密

        Player player1 = new Player();
        player1.setUserName(player.getUserName());
        //去重
        List<Player> players = playerMapper.findByEntity(player1, null);
        if (players != null && players.size() != 0) {
            throw new CustomException("注册用户重名，请重新注册！");
        }

        int operate = playerMapper.save(player);
        return operate > 0;
    }

    public boolean updatePlayer(Player player) {
        if (player.getId() == null) {
            throw new CustomException("id不可不传！");
        }
        int operate = playerMapper.update(player);
        return operate > 0;
    }
}
