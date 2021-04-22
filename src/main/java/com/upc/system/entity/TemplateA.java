package com.upc.system.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class TemplateA {

  private long id;
  private String companyName;
  private String content;
  private long countPatent;
  private String patentName;
  private long countProduct;
  private String productName;
  private String field;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private java.sql.Date time;
  private String operateIp;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private java.sql.Timestamp operateTime;
  private String operator;
  private long delFlag;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public long getCountPatent() {
    return countPatent;
  }

  public void setCountPatent(long countPatent) {
    this.countPatent = countPatent;
  }


  public String getPatentName() {
    return patentName;
  }

  public void setPatentName(String patentName) {
    this.patentName = patentName;
  }


  public long getCountProduct() {
    return countProduct;
  }

  public void setCountProduct(long countProduct) {
    this.countProduct = countProduct;
  }


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }


  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }


  public java.sql.Date getTime() {
    return time;
  }

  public void setTime(java.sql.Date time) {
    this.time = time;
  }


  public String getOperateIp() {
    return operateIp;
  }

  public void setOperateIp(String operateIp) {
    this.operateIp = operateIp;
  }


  public java.sql.Timestamp getOperateTime() {
    return operateTime;
  }

  public void setOperateTime(java.sql.Timestamp operateTime) {
    this.operateTime = operateTime;
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
