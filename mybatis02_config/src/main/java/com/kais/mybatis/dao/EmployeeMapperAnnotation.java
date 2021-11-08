package com.kais.mybatis.dao;

import com.kais.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * @Author liuxiankai
 * @ClassName EmployeeMapperAnnotation
 * @CreateTime 2021-09-24 17:52
 * @Description: 使用注解开发
 */
public interface EmployeeMapperAnnotation {

    @Select("select * from employee where id=#{id}")
    public Employee getEmpById(Integer id);
}
