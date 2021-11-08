package com.kais.resultMap.mapper;

import com.kais.resultMap.pojo.Employee;

import java.util.List;

/**
 * @Author liuxiankai
 * @ClassName EmployeeMapper
 * @CreateTime 2021-10-18 16:03
 * @Description:Mapper接口
 */
public interface EmployeeMapper {

    List<Employee> getAll();

    Employee getEmpAndDept(Integer id);

    /**
     * 方法描述:使用association进行分布查询
     * 时间:2021/10/20 15:24
    */
    Employee getEmpByIdStep(Integer id);

}
