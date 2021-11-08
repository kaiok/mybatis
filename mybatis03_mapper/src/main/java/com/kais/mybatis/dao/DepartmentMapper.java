package com.kais.mybatis.dao;

import com.kais.mybatis.bean.Department;

/**
 * @Author liuxiankai
 * @ClassName DepartmentMapper
 * @CreateTime 2021-09-30 10:00
 * @Description: 公寓类接口
 */
public interface DepartmentMapper {

    public Department getDeptById(Integer id);

//    public Department getDeptByIdPlus(Integer id);

//    public Department getDeptByIdStep(Integer id);


}
