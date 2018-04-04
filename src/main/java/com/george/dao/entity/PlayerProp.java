package com.george.dao.entity;
import com.george.utils.generators.mybatis.generator.annotation.Table;

import java.util.Date;

@Table(name = "player_prop")
public class PlayerProp {
	private Integer id;
	private Integer playerId;	//玩家id 
	private Integer propId;	//道具id 
	private Integer ownQuan;	//拥有量 
	private Date createTime;	//创建时间 
	private String remark;	//备注 
	private Integer isValid;	//记录有效标记（0：无效；1：有效） 

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

	public Integer getPropId() {
		return propId;
	}

	public void setPropId(Integer propId) {
		 this.propId = propId;
	}

	public Integer getOwnQuan() {
		return ownQuan;
	}

	public void setOwnQuan(Integer ownQuan) {
		 this.ownQuan = ownQuan;
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
