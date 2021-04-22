package com.upc.system.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class File {

  private long id;
  private String filename;
  private String url;
  private long patentId;
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


  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public long getPatentId() {
    return patentId;
  }

  public void setPatentId(long patentId) {
    this.patentId = patentId;
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
