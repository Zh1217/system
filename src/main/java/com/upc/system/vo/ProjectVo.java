package com.upc.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ProjectVo {
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
    private long delFlag;
    private long companyId;
    private String companyName;
    private long productId;
    private String productName;
    private java.sql.Timestamp operateTime;
    private String operateIp;
    private String operator;
}
