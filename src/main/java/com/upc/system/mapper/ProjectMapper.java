package com.upc.system.mapper;

import com.upc.system.entity.Patent;
import com.upc.system.entity.Project;
import com.upc.system.entity.ProjectRelation;
import com.upc.system.vo.ProjectVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMapper {

    //查询某公司名下的项目（不包括公司产品名下的项目）
    @Select("SELECT * FROM project WHERE del_flag = 0 and id in" +
            "(select project_id from project_relation where company_id = #{companyId} and product_id = 0 and del_flag = 0)")
    List<Project> getProjectsByCompany(@Param("companyId") long companyId);
    //查询某产品名下的项目（不包括公司产品名下的项目）
    @Select("SELECT * FROM project WHERE del_flag = 0 and id in" +
            "(select project_id from project_relation where company_id = #{companyId} and product_id = #{productId} and del_flag = 0)")
    List<Project> getProjectsByProduct(@Param("companyId") long companyId, @Param("productId") long productId);

    //根据项目名获取项目id
    @Select("SELECT id FROM project WHERE project_name = #{projectName} AND del_flag = 0")
    long getProjectIdByProjectName(@Param("projectName") String projectName);
    //查询某个项目的信息
    @Select("SELECT * FROM project WHERE del_flag = 0 and id =#{projectId}")
    Project getProject(@Param("projectId") long projectId);
    //查询某个项目是否已经存在
    @Select("SELECT * FROM project WHERE del_flag = 0 and project_code =#{projectCode}")
    Project selectProjectByCode(@Param("projectCode") String projectCode);
    //插入项目
    @Insert("INSERT INTO project ( project_code, project_name, project_start_time, project_end_time , evidence, expense, operate_time, operate_ip, operator, del_flag)" +
            " VALUES ( #{projectCode}, #{projectName}, #{projectStartTime}, #{projectEndTime}, #{evidence}, #{expense},  #{operateTime}, #{operateIp}, #{operator}, #{delFlag})")
    int insertProject(ProjectVo projectVo);
    //根据项目编号查询项目id
    @Select("SELECT id FROM project WHERE del_flag = 0 and project_code =#{projectCode}")
    long getIdByCode(@Param("projectCode") String projectCode);
    //建立关系
    @Insert("INSERT INTO project_relation ( project_id, product_id, company_id, operate_time, operate_ip, operator,del_flag)" +
            " VALUES ( #{projectId}, #{productId}, #{companyId}, #{operateTime}, #{operateIp}, #{operator},#{delFlag})")
    void insertProjectRelation(ProjectRelation projectRelation);
    //根据用户id获取该用户绑定的所有项目
    @Select("SELECT * FROM project WHERE del_flag = 0 and id in (select project_id from project_relation where company_id in( SELECT company_id FROM user_company WHERE del_flag = 0 AND user_id =#{userId}) and product_id = 0 and del_flag = 0)")
    List<Project> selectProjectByUserId(@Param("userId") long userId);
    //根据公司名查询项目名称列表
    @Select("SELECT project_name FROM project WHERE del_flag = 0 and id in" +
            "(select project_id from project_relation where company_id = #{companyId} and product_id = 0 and del_flag = 0)")
    List<String> selectProjectNameByCompanyName(@Param("companyId") long companyId);
    //查询某产品名下的项目（不包括公司产品名下的项目）
    @Select("SELECT project_name FROM project WHERE del_flag = 0 and id in" +
            "(select project_id from project_relation where company_id = #{companyId} and product_id = #{productId} and del_flag = 0)")
    List<String> getProjectNameByProductNameCompanyName(@Param("companyId") long companyId, @Param("productId") long productId);
    //修改项目信息
    @Update("UPDATE project SET project_code = #{projectCode}, project_name = #{projectName}, project_start_time = #{projectStartTime}," +
            " project_end_time = #{projectEndTime}, evidence = #{evidence}, expense = #{expense}, " +
            "operate_time = #{operateTime}, operate_ip = #{operateIp}, operator = #{operator} where id = #{id} and del_flag = 0")
    int updateProject(Project project);
    //删除项目
    @Update("UPDATE project SET operate_time = #{operateTime}, operate_ip = #{operateIp}, operator = #{operator}, del_flag = #{id} where id = #{id} AND del_flag =0 ")
    int deleteProject(Project project);
    @Update("UPDATE project_relation SET operate_time = #{operateTime}, operate_ip = #{operateIp}, operator = #{operator}, del_flag = #{id} where project_id = #{id} AND del_flag =0")
    int deleteProjectRelation(Project project);
    //通过项目名字查询项目id
    @Select("SELECT id FROM project WHERE del_flag = 0 and project_name =#{projectName}")
    long selectProjectIdByProjectame(String projectName);

}
