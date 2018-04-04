package com.george.dao.entity;

import java.util.Date;

public class ViewSkin {
    private Integer id;
    private String skinCode;    //场景代码
    private String skinName;    //场景皮肤名
    private String skinDesc;    //场景描述
    private Date createTime;    //创建时间
    private String remark;    //备注
    private Integer isValid;    //记录有效标记（0：无效；1：有效）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkinCode() {
        return skinCode;
    }

    public void setSkinCode(String skinCode) {
        this.skinCode = skinCode;
    }

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public String getSkinDesc() {
        return skinDesc;
    }

    public void setSkinDesc(String skinDesc) {
        this.skinDesc = skinDesc;
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
