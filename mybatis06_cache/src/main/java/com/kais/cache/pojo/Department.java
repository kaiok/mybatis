package com.kais.cache.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author liuxiankai
 * @ClassName Department
 * @CreateTime 2021-10-18 16:56
 * @Description:
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String departmentName;
    /**
     * 方法描述:多对一查询
     * 时间:2021/10/20 16:06
    */
    private List<Employee> emps;

}
