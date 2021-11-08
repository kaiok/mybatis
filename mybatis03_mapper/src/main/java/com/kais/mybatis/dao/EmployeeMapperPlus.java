package com.kais.mybatis.dao;

import com.kais.mybatis.bean.Employee;

/**
 * @Author liuxiankai
 * @ClassName EmployeeMapperPlus
 * @CreateTime 2021-10-20 11:14
 * @Description: resultMap自定义映射规则
 */
public interface EmployeeMapperPlus {

    Employee getEmpById(Integer var1);

}
