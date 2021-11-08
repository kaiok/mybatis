package com.kais.mybatis.test;

import com.kais.mybatis.bean.Department;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;


/**
 * @Author liuxiankai
 * @ClassName DepartmentMapperTest
 * @CreateTime 2021-09-30 10:30
 * @Description:
 */
class DepartmentMapperTest {


    SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 方法描述:根据id查询公寓
     *
     * @返回值 : void
     * @作者 : lxk
     * 时间:2021/9/30 13:44
    */
    @Test
    void mapperTest01() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        Department department = openSession.selectOne("com.kais.mybatis.dao.DepartmentMapper.getDeptById",1);
        System.out.println(department);
    }

}
