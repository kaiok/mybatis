package com.kais.mybatis.test;

import com.kais.mybatis.bean.Employee;
import com.kais.mybatis.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author liuxiankai
 * @ClassName EmployeeMapperAnnotationTest
 * @CreateTime 2021-10-18 9:44
 * @Description: 基于注解实现测试
 */
public class EmployeeMapperAnnotationTest {

    SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 方法描述:基于注解实现根据id查询数据
     * 时间:2021/10/18 11:12
    */
    @Test
    public void test01() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
        Employee empById = mapper.getEmpById(1);
        System.out.println(empById);
        sqlSession.close();
    }

    /**
     * 方法描述:基于注解实现保存功能
     * 时间:2021/10/18 15:15
    */
    @Test
    public void saveEmpTest() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);

        Employee employee = new Employee(null, "lxk", "lxk@qq.com", "1");
        int i = mapper.saveEmp(employee);
        System.out.println("i = " + i);
        //不提交session，便没有数据
        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 方法描述:基于注解实现更新功能
     * 时间:2021/10/18 15:16
    */
    @Test
    public void updateEmpTest() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);

        Employee employee = mapper.getEmpById(6);
        employee.setLastName("lizhiqiang");
        employee.setEmail("lizhiqiang.com");
        employee.setGender("man");

        int update = mapper.updateEmp(employee);
        System.out.println(update);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteEmpTest() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);

        Integer result = mapper.deleteEmp(9);
        System.out.println(result);
        sqlSession.commit();
        sqlSession.close();
    }

}
