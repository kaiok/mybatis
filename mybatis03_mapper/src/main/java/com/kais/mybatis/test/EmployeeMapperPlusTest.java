package com.kais.mybatis.test;

import com.kais.mybatis.bean.Employee;
import com.kais.mybatis.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author liuxiankai
 * @ClassName EmployeeMapperPlusTest
 * @CreateTime 2021-10-20 11:18
 * @Description:
 */
public class EmployeeMapperPlusTest {

    SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testById() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
        Employee empById = mapper.getEmpById(8);
        System.out.println(empById);
        sqlSession.commit();
        sqlSession.close();
    }

}
