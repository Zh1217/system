package com.upc.system.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class User {

  private long id;
  private String username;
  private String phone;
  private String password;
  private String area;
  private long identity;
  private long status;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private java.sql.Timestamp registerTime;
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


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }


  public long getIdentity() {
    return identity;
  }

  public void setIdentity(long identity) {
    this.identity = identity;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public java.sql.Timestamp getRegisterTime() {
    return registerTime;
  }

  public void setRegisterTime(java.sql.Timestamp registerTime) {
    this.registerTime = registerTime;
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
