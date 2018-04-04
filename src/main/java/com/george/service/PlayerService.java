package com.george.service;

import com.george.dao.entity.GameCharacter;
import com.george.dao.entity.Player;

import java.util.List;

/**
 * Created by admin on 2018/3/21.
 */
public interface PlayerService {
    List<Player> getPlayers(Player player);

    boolean savePlayer(Player player);

    boolean updatePlayer(Player player);
}
