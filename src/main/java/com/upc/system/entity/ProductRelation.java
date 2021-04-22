package com.upc.system.entity;


public class ProductRelation {

  private long id;
  private long companyId;
  private long productId;
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


  public long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(long companyId) {
    this.companyId = companyId;
  }


  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
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
