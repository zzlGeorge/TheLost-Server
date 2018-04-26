package com.george.service.impl;

import com.george.dao.entity.GameProp;
import com.george.dao.entity.Player;
import com.george.dao.entity.PlayerProp;
import com.george.dao.mappers.PlayerPropMapper;
import com.george.service.PlayerPropService;
import com.george.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : George
 *         Description :
 *         Date : Created in 16:35 2018/4/4
 *         Modified By :
 */
@Service
@Transactional
public class PlayerPropServiceImpl implements PlayerPropService {

    @Autowired
    private PlayerPropMapper playerPropMapper;

    public List<GameProp> getProps(GameProp gameProp) {
        return playerPropMapper.findGameProp(gameProp);
    }

    public boolean saveOrUpdatePlayerProps(List<PlayerProp> playerProps) {

        List<GameProp> playerPropsData = playerPropMapper.getGamePropByPlayerId(playerProps.get(0).getPlayerId());
        for (PlayerProp prop : playerProps) {
//            prop.setPlayerId(ShiroUtils.getPlayerId());//当前玩家id设置
            if (hasTheProp(prop.getPropId(), playerPropsData)) {
                //找到对应关系
                PlayerProp param = new PlayerProp();
                param.setPlayerId(prop.getPlayerId());
                param.setPropId(prop.getPropId());
                List<PlayerProp> playerPropList = playerPropMapper.findByEntity(param, null);
                prop.setId(playerPropList.get(0).getId());
                playerPropMapper.update(prop);//更新
            } else {
                playerPropMapper.save(prop);
            }

        }
        return true;
    }

    public List<Player> getPropRank(Integer propId) {
        return playerPropMapper.getPropRank(propId);
    }

    private boolean hasTheProp(Integer propId, List<GameProp> gameProps) {
        if (gameProps != null) {
            for (GameProp gameProp : gameProps) {
                if (gameProp.getId() == propId) {
                    return true;
                }
            }
        }
        return false;
    }
}
