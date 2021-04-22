package com.upc.system.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class Project {

  private long id;
  private String projectCode;
  private String projectName;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private java.sql.Date projectStartTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private java.sql.Date projectEndTime;
  private String evidence;
  private long expense;
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


  public String getProjectCode() {
    return projectCode;
  }

  public void setProjectCode(String projectCode) {
    this.projectCode = projectCode;
  }


  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }


  public java.sql.Date getProjectStartTime() {
    return projectStartTime;
  }

  public void setProjectStartTime(java.sql.Date projectStartTime) {
    this.projectStartTime = projectStartTime;
  }


  public java.sql.Date getProjectEndTime() {
    return projectEndTime;
  }

  public void setProjectEndTime(java.sql.Date projectEndTime) {
    this.projectEndTime = projectEndTime;
  }


  public String getEvidence() {
    return evidence;
  }

  public void setEvidence(String evidence) {
    this.evidence = evidence;
  }


  public long getExpense() {
    return expense;
  }

  public void setExpense(long expense) {
    this.expense = expense;
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
