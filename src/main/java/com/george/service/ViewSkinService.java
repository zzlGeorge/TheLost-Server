package com.george.service;

import com.george.dao.entity.PlayerViewSkin;
import com.george.dao.entity.ViewSkin;

import java.util.List;

/**
 * Created by admin on 2018/4/4.
 */
public interface ViewSkinService {
    List<ViewSkin> getViewSkins(ViewSkin viewSkin);

    boolean savePlayerViewSkins(List<PlayerViewSkin> playerViewSkinList);
}
