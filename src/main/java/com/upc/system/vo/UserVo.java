package com.upc.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.upc.system.entity.Company;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.List;


@Data
public class UserVo {
    private long id;
    private String username;
    private String phone;
    private String password;
    private String area;
    private long identity;
    private long status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp registerTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp operateTime;
    private String operateIp;
    private String operator;
    private long delFlag;
    private List<Company> companys;
}
