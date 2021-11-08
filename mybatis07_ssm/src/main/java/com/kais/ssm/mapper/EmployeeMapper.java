package com.kais.ssm.mapper;

import com.kais.ssm.pojo.Employee;

import java.util.List;

/**
 * @Author liuxiankai
 * @ClassName EmployeeMapper
 * @CreateTime 2021-10-18 16:03
 * @Description:Mapper接口
 */
public interface EmployeeMapper {

    List<Employee> getAll();
}
