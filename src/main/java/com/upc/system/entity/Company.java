package com.upc.system.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class Company {

  private long id;
  private String companyName;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private java.sql.Date registerTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private java.sql.Date declareTime;
  private String introduction;
  private String field;
  private long member;
  private long researcher;
  private long income;
  private long allIncome;
  private long countProject;
  private long countPatent;
  private String registerArea;
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


  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }


  public java.sql.Date getRegisterTime() {
    return registerTime;
  }

  public void setRegisterTime(java.sql.Date registerTime) {
    this.registerTime = registerTime;
  }


  public java.sql.Date getDeclareTime() {
    return declareTime;
  }

  public void setDeclareTime(java.sql.Date declareTime) {
    this.declareTime = declareTime;
  }


  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }


  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }


  public long getMember() {
    return member;
  }

  public void setMember(long member) {
    this.member = member;
  }


  public long getResearcher() {
    return researcher;
  }

  public void setResearcher(long researcher) {
    this.researcher = researcher;
  }


  public long getIncome() {
    return income;
  }

  public void setIncome(long income) {
    this.income = income;
  }


  public long getAllIncome() {
    return allIncome;
  }

  public void setAllIncome(long allIncome) {
    this.allIncome = allIncome;
  }


  public long getCountProject() {
    return countProject;
  }

  public void setCountProject(long countProject) {
    this.countProject = countProject;
  }


  public long getCountPatent() {
    return countPatent;
  }

  public void setCountPatent(long countPatent) {
    this.countPatent = countPatent;
  }


  public String getRegisterArea() {
    return registerArea;
  }

  public void setRegisterArea(String registerArea) {
    this.registerArea = registerArea;
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
