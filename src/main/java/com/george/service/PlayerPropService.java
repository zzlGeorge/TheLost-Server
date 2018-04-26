package com.george.service;

import com.george.dao.entity.GameProp;
import com.george.dao.entity.Player;
import com.george.dao.entity.PlayerProp;

import java.util.List;

/**
 * @author : George
 *         Description :
 *         Date : Created in 16:35 2018/4/4
 *         Modified By :
 */
public interface PlayerPropService {
    List<GameProp> getProps(GameProp gameProp);

    boolean saveOrUpdatePlayerProps(List<PlayerProp> playerProps);

    List<Player> getPropRank(Integer propId);
}
