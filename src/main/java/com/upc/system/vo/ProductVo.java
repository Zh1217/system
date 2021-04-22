package com.upc.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ProductVo {

    private long companyId;
    private String companyName;
    private long id;
    private String productName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.sql.Timestamp operateTime;
    private String operateIp;
    private String operator;
    private long delFlag;
}
