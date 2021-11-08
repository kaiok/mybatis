package com.kais.mybatis.test;

import com.kais.mybatis.bean.Employee;
import com.kais.mybatis.dao.EmployeeMapper;
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
 * @ClassName MybatisConfigTest
 * @CreateTime 2021-09-24 11:04
 * @Description: 测试
 */
class MybatisConfigTest {


    /**
     * 方法描述:
     * 1、接口式编程
     * 原生：		Dao		====>  DaoImpl
     * mybatis：	Mapper	====>  xxMapper.xml
     *
     * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
     *
     * 3、SqlSession和connection一样都是非线程安全。每次使用都应该去获取新的对象
     *
     * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象
     * （将接口和xml进行绑定）
     * EmployeeMapper empMapper = sqlSession.getMapper(EmployeeMapper.class);
     *
     * 5、两个重要的配置文件：
     * mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
     * sql映射文件：保存了每一个sql语句的映射信息：将sql抽取出来
     *
     * @返回值 : org.apache.ibatis.session.SqlSessionFactory
     * @作者 : lxk
     * 时间:2021/9/24 11:13
    */
    SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * 1、根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象 有数据源一些运行环境信息
     *
     * 2、sql映射文件；配置了每一个sql，以及sql的封装规则等
     *
     * 3、将sql映射文件注册在全局配置文件中
     * 4、写代码：
     * 		1）、根据全局配置文件得到SqlSessionFactory；
     * 		2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查
     * 			一个sqlSession就是代表和数据库的一次会话，用完关闭
     * 		3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的
     */
    @Test
    void test() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2、获取sqlSession实例，能直接执行已经映射的sql语句
        // sql的唯一标识(xml文件中id名)：statement Unique identifier matching the statement to use
        // 执行sql要用的参数：parameter A parameter object to pass to the statement
        //3.确保 SqlSession 关闭的标准模式:try (SqlSession openSession = sqlSessionFactory.openSession())
        try (SqlSession openSession = sqlSessionFactory.openSession()) {
            Employee employee = openSession.selectOne(
                    "com.kais.mybatis.dao.EmployeeMapper.getEmpById", 1);
            System.out.println(employee);
        }
    }


    /**
     * 方法描述:接口式编程
     *
     * @返回值 : void
     * @作者 : lxk
     * 时间:2021/9/24 16:03
    */
    @Test
    void test01() throws IOException {
        //1.获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2.获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        //3.获取接口的实现类对象
        //<mapper namespace="com.kais.mybatis.dao.EmployeeMapper">接口类与xml进行了绑定那么mybatis会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        Employee empById = mapper.getEmpById(1);
        System.out.println(mapper.getClass());
        System.out.println(empById);
        openSession.close();
    }


    @Test
    void test02() throws IOException{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (SqlSession openSession = sqlSessionFactory.openSession()) {
            EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
            Employee empById = mapper.getEmpById(1);
            System.out.println(empById);
        }
    }


}
