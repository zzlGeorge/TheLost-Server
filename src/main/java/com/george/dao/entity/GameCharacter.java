package com.george.dao.entity;

import com.george.utils.generators.mybatis.generator.annotation.Table;

import java.util.Date;

@Table(name = "character")
public class GameCharacter {
    private Integer id;
    private String roleCode;    //角色代码
    private String roleName;    //角色名
    private String roleDesc;    //角色描述
    private Date createTime;    //创建时间
    private String remark;    //备注
    private Integer isValid;    //记录有效标记（0：无效；1：有效）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
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
