package com.george.service.impl;

import com.george.dao.entity.PlayerViewSkin;
import com.george.dao.entity.ViewSkin;
import com.george.dao.mappers.PlayerViewSkinMapper;
import com.george.service.ViewSkinService;
import com.george.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 2018/4/4.
 */

@Service
@Transactional
public class ViewSkinServiceImpl implements ViewSkinService {
    @Autowired
    private PlayerViewSkinMapper viewSkinMapper;


    public List<ViewSkin> getViewSkins(ViewSkin viewSkin) {
        return viewSkinMapper.findViewSkins(viewSkin);
    }

    public boolean savePlayerViewSkins(List<PlayerViewSkin> playerViewSkinList) {
        for (PlayerViewSkin playerViewSkin : playerViewSkinList) {
            playerViewSkin.setPlayerId(ShiroUtils.getPlayerId());//当前玩家id设置
            //TODO 有皮肤，跳过写入表
            viewSkinMapper.save(playerViewSkin);
        }
        return true;
    }
}
