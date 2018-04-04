package com.george.dao.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EndGameLog {
    private Integer id;
    private Integer playerId;    //玩家id
    private Integer getFruitsQuan;    //水果获取数
    private Integer getCoinsQuan;    //金币获取数
    private Integer getDiamondsQuan;    //钻石获取数
    private Integer getBloodVialQuan;    //血瓶获取量
    private Integer getRewardsQuan;    //奖励道具数
    private Integer getInvinciblePotionsQuan;    //无敌药水获取数
    private Integer getRevivesQuan;    //还魂香获取数
    private Integer getPnvuQuan;    //血瓶获取数
    private Integer totalScore;    //总得分
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    public Integer getGetFruitsQuan() {
        return getFruitsQuan;
    }

    public void setGetFruitsQuan(Integer getFruitsQuan) {
        this.getFruitsQuan = getFruitsQuan;
    }

    public Integer getGetCoinsQuan() {
        return getCoinsQuan;
    }

    public void setGetCoinsQuan(Integer getCoinsQuan) {
        this.getCoinsQuan = getCoinsQuan;
    }

    public Integer getGetDiamondsQuan() {
        return getDiamondsQuan;
    }

    public void setGetDiamondsQuan(Integer getDiamondsQuan) {
        this.getDiamondsQuan = getDiamondsQuan;
    }

    public Integer getGetBloodVialQuan() {
        return getBloodVialQuan;
    }

    public void setGetBloodVialQuan(Integer getBloodVialQuan) {
        this.getBloodVialQuan = getBloodVialQuan;
    }

    public Integer getGetRewardsQuan() {
        return getRewardsQuan;
    }

    public void setGetRewardsQuan(Integer getRewardsQuan) {
        this.getRewardsQuan = getRewardsQuan;
    }

    public Integer getGetInvinciblePotionsQuan() {
        return getInvinciblePotionsQuan;
    }

    public void setGetInvinciblePotionsQuan(Integer getInvinciblePotionsQuan) {
        this.getInvinciblePotionsQuan = getInvinciblePotionsQuan;
    }

    public Integer getGetRevivesQuan() {
        return getRevivesQuan;
    }

    public void setGetRevivesQuan(Integer getRevivesQuan) {
        this.getRevivesQuan = getRevivesQuan;
    }

    public Integer getGetPnvuQuan() {
        return getPnvuQuan;
    }

    public void setGetPnvuQuan(Integer getPnvuQuan) {
        this.getPnvuQuan = getPnvuQuan;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
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
