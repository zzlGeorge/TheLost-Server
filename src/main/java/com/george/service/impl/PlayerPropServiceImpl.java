package com.george.service.impl;

import com.george.dao.entity.GameProp;
import com.george.dao.mappers.PlayerPropMapper;
import com.george.service.PlayerPropService;
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
}
