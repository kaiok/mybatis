package com.kais.resultMap.mapper;

import com.kais.resultMap.pojo.Department;

import java.util.List;

/**
 * @Author liuxiankai
 * @ClassName DepartmentMapper
 * @CreateTime 2021-10-18 17:08
 * @Description:
 */
public interface DepartmentMapper {

    Department getDeptById(Integer id);

    /**
     * 方法描述:查询department的同时，将该公寓的所有employee也查出来，多对一
     * 时间:2021/10/20 15:58
    */
    public List<Department> getDeptByIdPlus(Integer id);

}
