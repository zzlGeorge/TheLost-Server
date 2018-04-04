package com.george.dao.entity;

import java.util.Date;

public class SendCoinsLog {
    private Integer id;
    private Integer fromWhoId;    //赠送金币者id
    private Integer toWhoId;    //被赠送金币者
    private Date createTime;    //创建时间
    private String remark;    //备注
    private Integer isValid;    //该行记录是否有效

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromWhoId() {
        return fromWhoId;
    }

    public void setFromWhoId(Integer fromWhoId) {
        this.fromWhoId = fromWhoId;
    }

    public Integer getToWhoId() {
        return toWhoId;
    }

    public void setToWhoId(Integer toWhoId) {
        this.toWhoId = toWhoId;
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
