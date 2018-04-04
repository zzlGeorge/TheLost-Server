package com.george.dao.entity;

import com.george.utils.generators.mybatis.generator.annotation.Table;

import java.util.Date;

@Table(name = "player_viewSkin")
public class PlayerViewSkin {
    private Integer id;
    private Integer playerId;    //玩家id
    private Integer skinId;    //场景皮肤id
    private Date createTime;    //创建时间
    private String remark;    //备注
    private Integer isValid;    //记录有效标记（0：无效；1：有效）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getSkinId() {
        return skinId;
    }

    public void setSkinId(Integer skinId) {
        this.skinId = skinId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

}
