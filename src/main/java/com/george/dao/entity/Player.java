package com.george.dao.entity;
import java.util.Date;

public class Player{
	private Integer id;
	private String userName;	//用户名 
	private String password;	//密码 
	private String sex;	//性别 
	private String area;	//地区 
	private Integer loginStatus;	//登陆状态（0：不在线，1：在线） 
	private Date createTime;	//创建时间 
	private Integer status;	//启用状态（0：禁用，1：启用） 
	private Integer isDelete;	//是否删除（1：是，0：否） 

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

}
