package com.kais.dao;

import com.kais.bean.Employee;

/**
 * @Author liuxiankai
 * @ClassName EmployeeMapper
 * @CreateTime 2021-09-24 11:00
 * @Description: 接口Mapper
 */
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

}
