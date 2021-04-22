package com.upc.system.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class Patent {

  private long id;
  private String patentName;
  private String category;
  private String applyNumber;
  private String authorizationNumber;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private java.sql.Date authorizationTime;
  private String access;
  private String remark;
  private long status;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private java.sql.Timestamp operateTime;
  private String operateIp;
  private String operator;
  private long delFlag;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getPatentName() {
    return patentName;
  }

  public void setPatentName(String patentName) {
    this.patentName = patentName;
  }


  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }


  public String getApplyNumber() {
    return applyNumber;
  }

  public void setApplyNumber(String applyNumber) {
    this.applyNumber = applyNumber;
  }


  public String getAuthorizationNumber() {
    return authorizationNumber;
  }

  public void setAuthorizationNumber(String authorizationNumber) {
    this.authorizationNumber = authorizationNumber;
  }


  public java.sql.Date getAuthorizationTime() {
    return authorizationTime;
  }

  public void setAuthorizationTime(java.sql.Date authorizationTime) {
    this.authorizationTime = authorizationTime;
  }


  public String getAccess() {
    return access;
  }

  public void setAccess(String access) {
    this.access = access;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public java.sql.Timestamp getOperateTime() {
    return operateTime;
  }

  public void setOperateTime(java.sql.Timestamp operateTime) {
    this.operateTime = operateTime;
  }


  public String getOperateIp() {
    return operateIp;
  }

  public void setOperateIp(String operateIp) {
    this.operateIp = operateIp;
  }


  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }


  public long getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(long delFlag) {
    this.delFlag = delFlag;
  }

}
