package com.upc.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
@Data
public class PatentVo {
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
    private long companyId;
    private String companyName;
    private long productId;
    private String productName;
    private long projectId;
    private String projectName;

}