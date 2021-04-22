package com.upc.system.mapper;

import com.upc.system.entity.Patent;
import com.upc.system.entity.PatentRelation;
import com.upc.system.vo.PatentVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatentMapper {
    //根据用户id查询专利
    @Select("SELECT * FROM patent WHERE del_flag = 0 and id in (select patent_id from patent_relation where user_id = #{id} and del_flag = 0)")
    List<Patent> selectPatentByUserId(long id);

    //根据公司id查询专利
    @Select("SELECT * FROM patent WHERE del_flag = 0 and id in" +
            "(select patent_id from patent_relation where company_id = #{companyId} and product_id = 0 and project_id = 0 and del_flag = 0)")
    List<Patent> getPatentsByCompany(long companyId);

    //根据公司名查询专利名称列表
    @Select("SELECT patent_name FROM patent WHERE del_flag = 0 and id in" +
            "(select patent_id from patent_relation where company_id = #{companyId} and product_id = 0 and project_id = 0 and del_flag = 0)")
    List<String> selectPatentNameByCompanyName(long companyId);

    //根据产品名查询专利名
    @Select("SELECT patent_name FROM patent WHERE del_flag = 0 and id in" +
            "(select patent_id from patent_relation where company_id = #{companyId} and product_id = #{productId} and project_id = 0 and del_flag = 0)")
    List<String> getProjectNameByProductNameCompanyName(long companyId, long productId);

    //根据产品名查询专利
    @Select("SELECT * FROM patent WHERE del_flag = 0 and id in" +
            "(select patent_id from patent_relation where company_id = #{companyId} and product_id = #{productId} and project_id = 0 and del_flag = 0)")
    List<Patent> getPatentsByProduct(long companyId, long productId);

    //根据项目名查询专利
    @Select("SELECT * FROM patent WHERE del_flag = 0 and id in" +
            "(select patent_id from patent_relation where project_id =#{projectId} and del_flag = 0)")
    List<Patent> getPatentsByProject(long projectId);

    //根据项目查询专利名
    @Select("SELECT patent_name FROM patent WHERE del_flag = 0 and id in" +
            "(select patent_id from patent_relation where project_id =#{projectId} and del_flag = 0)")
    List<String> getPatentNameByProject(long projectId);
    //查询专利id
    @Select("SELECT id FROM patent WHERE del_flag = 0 and patent_name =#{patentName}")
    long getPatentIdByPatentName(String patentName);
    //通过id查询专利
    @Select("SELECT * FROM patent WHERE del_flag = 0 and id =#{patentId}")
    Patent getPatent(long patentId);

    //通过名字查询专利
    @Select("SELECT * FROM patent WHERE del_flag = 0 and patent_name =#{patentName}")
    Patent selectPatentByName(String patentName);

    @Insert("INSERT INTO patent ( patent_name, category, apply_number, authorization_number, authorization_time, access, remark, status, operate_time, operate_ip, operator, del_flag)" +
            " VALUES ( #{patentName}, #{category}, #{applyNumber}, #{authorizationNumber}, #{authorizationTime}, #{access}, #{remark}, #{status}, #{operateTime}, #{operateIp}, #{operator}, #{delFlag})")
    int insertPatent(PatentVo patentVo);

    @Insert("INSERT INTO patent_relation ( patent_id, project_id, product_id, company_id, operate_time, operate_ip, operator,del_flag)" +
            " VALUES ( #{patentId},#{projectId}, #{productId}, #{companyId}, #{operateTime}, #{operateIp}, #{operator},#{delFlag})")
    void insertPatentRelation(PatentRelation patentRelation);
}
