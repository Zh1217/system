package com.upc.system.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class Product {

  private long id;
  private String productName;
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


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
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
