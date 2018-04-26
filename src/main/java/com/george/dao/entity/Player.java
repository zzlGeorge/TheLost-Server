package com.george.dao.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.swing.text.View;
import java.util.Date;
import java.util.List;

public class Player {
    private Integer id;
    private String userName;    //用户名
    private String password;    //密码
    private String sex;    //性别
    private String area;    //地区
    private Integer loginStatus;    //登陆状态（0：不在线，1：在线）
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;    //创建时间
    private Integer status;    //启用状态（0：禁用，1：启用）
    private Integer isDelete;    //是否删除（1：是，0：否）

    private Integer rankNum;//排名编号
    private Integer totalScore;
    private Integer propQuan;


    private List<GameProp> gameProps; //玩家的道具数据
    private List<Achievement> achievements; //玩家成就
    private List<GameCharacter> gameCharacters;//玩家拥有角色的
    private List<ViewSkin> viewSkins;  //玩家场景皮肤

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public List<GameProp> getGameProps() {
        return gameProps;
    }

    public void setGameProps(List<GameProp> gameProps) {
        this.gameProps = gameProps;
    }

    public List<Achievement> getAchievement() {
        return achievements;
    }

    public void setAchievement(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public List<GameCharacter> getGameCharacters() {
        return gameCharacters;
    }

    public void setGameCharacters(List<GameCharacter> gameCharacters) {
        this.gameCharacters = gameCharacters;
    }

    public List<ViewSkin> getViewSkins() {
        return viewSkins;
    }

    public void setViewSkins(List<ViewSkin> viewSkins) {
        this.viewSkins = viewSkins;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getPropQuan() {
        return propQuan;
    }

    public void setPropQuan(Integer propQuan) {
        this.propQuan = propQuan;
    }

    public Integer getRankNum() {
        return rankNum;
    }

    public void setRankNum(Integer rankNum) {
        this.rankNum = rankNum;
    }
}
