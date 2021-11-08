package com.kais.ssm.service;

import com.kais.ssm.mapper.EmployeeMapper;
import com.kais.ssm.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author liuxiankai
 * @ClassName EmployeeServiceImpl
 * @CreateTime 2021-10-21 23:02
 * @Description:
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAll() {
        // 使用 MyBatis-Spring 之后，你不再需要直接使用 SqlSessionFactory 了，
        // 因为你的 bean 可以被注入一个线程安全的 SqlSession，
        // 它能基于 Spring 的事务配置来自动提交、回滚、关闭 session。
        return employeeMapper.getAll();
    }
}