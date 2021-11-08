package com.kais.mybatis.dao;

import com.kais.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author liuxiankai
 * @ClassName EmployeeMapper
 * @CreateTime 2021-09-30 13:47
 * @Description:
 */
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void addEmp(Employee employee);

    public boolean updateEmp(Employee employee);

    public boolean deleteEmpById(Integer id);

    /**
     * 方法描述:mybatis处理多个参数
     * 时间:2021/9/30 16:11
    */
    public Employee getEmpByIdAndLastName(@Param("id")Integer id, @Param("lastName")String lastName);


    /**
     * 方法描述:参数类型为map
     * 时间:2021/9/30 16:24
    */
    public Employee getEmpByMap(Map<String, Object> map);

    /**
     * 方法描述:返回类型为List集合的查询方法
     * 时间:2021/10/9 16:37
    */
    public List<Employee> getEmpsByListNameLike(String lastName);

    /**
     * 方法描述:返回一条记录的map，将所有数据以key-value的形式存入map中，key就是列名，值就是对应的值
     * 时间:2021/10/9 16:47
    */
    public Map<String,Object> getEmpByIdReturnMap(Integer id);


    //多条记录封装一个map：Map<Integer,Employee>:键是这条记录的主键，值是记录封装后的javaBean
    //@MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key
    @MapKey("id")
    public Map<Integer, Employee> getEmpByLastNameLikeReturnMap(String lastName);


}
