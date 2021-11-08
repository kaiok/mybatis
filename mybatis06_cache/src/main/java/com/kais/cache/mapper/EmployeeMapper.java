package com.kais.cache.mapper;

import com.kais.cache.pojo.Employee;

/**
 * @Author liuxiankai
 * @ClassName EmployeeMapper
 * @CreateTime 2021-10-18 16:03
 * @Description:Mapper接口
 */
public interface EmployeeMapper {

    Employee geEmployeeById(Integer id);

}
