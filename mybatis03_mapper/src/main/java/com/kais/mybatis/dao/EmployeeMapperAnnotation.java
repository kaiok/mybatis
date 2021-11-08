package com.kais.mybatis.dao;

import com.kais.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author liuxiankai
 * @ClassName EmployeeMapperAnnotation
 * @CreateTime 2021-10-18 9:41
 * @Description: 基于注解实现mybatis CRUD
 */
public interface EmployeeMapperAnnotation {

    @Select("select * from employee where id = #{id}")
    Employee getEmpById(Integer id);

    @Insert("insert into employee(last_name,email,gender) values(#{lastName},#{email},#{gender})")
    int saveEmp(Employee employee);

    @Update("update employee set last_name = #{lastName} , email = #{email} , gender = #{gender} where id = #{id}")
    int updateEmp(Employee employee);

    @Delete("delete from employee where id = #{id}")
    int deleteEmp(Integer id);


}
