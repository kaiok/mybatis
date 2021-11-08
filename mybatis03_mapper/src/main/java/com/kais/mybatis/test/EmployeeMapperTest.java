package com.kais.mybatis.test;

import com.kais.mybatis.bean.Employee;
import com.kais.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @Author liuxiankai
 * @ClassName EmployeeMapperTest
 * @CreateTime 2021-09-30 14:11
 * @Description:
 */
public class EmployeeMapperTest {

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
        Employee employee = openSession.selectOne("com.kais.mybatis.dao.EmployeeMapper.getEmpById",1);
        System.out.println(employee);
    }

    /**
     * 方法描述:测试增删改
     *
     * @返回值 : void
     * @作者 : lxk
     * 时间:2021/9/30 14:38
     * 1、mybatis允许增删改直接定义以下类型返回值
     *		Integer、Long、Boolean、void
     * 2、我们需要手动提交数据
     *		sqlSessionFactory.openSession();===》手动提交
     *		sqlSessionFactory.openSession(true);===》自动提交
    */
    @Test
    void mapperTest02() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //1、获取到的SqlSession不会自动提交数据
        try (SqlSession openSession = sqlSessionFactory.openSession()) {

            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            //测试添加
//            Employee employee = new Employee(null, "wangwa", null, "1");
//            mapper.addEmp(employee);
//            //mybatis没有开启逐渐获取，当新对象id为null时，getId方法获取不到id
//            System.out.println(employee.getId());

            //测试添加：开启主键获取
            Employee employee = new Employee(null, "wangwa", null, "1");
            mapper.addEmp(employee);
            //mybatis没有开启逐渐获取，当新对象id为null时，getId方法获取不到id
            System.out.println(employee.getId());

            //测试修改
//            Employee employee1 = new Employee(5, "wangwa", "kais@qq.com", "1");
//            boolean blag = mapper.updateEmp(employee1);
//            System.out.println(blag);

            //测试删除
//            boolean tag = mapper.deleteEmpById(2);
//            System.out.println(tag);

            openSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 方法描述:mybatis处理多个参数
     *
     * @返回值 : void
     * @作者 : lxk
     * 时间:2021/9/30 16:13
    */
    @Test
    void mapperTest03() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (SqlSession openSession = sqlSessionFactory.openSession()) {

            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            //mybatis处理多个参数
//            Employee employee = mapper.getEmpByIdAndLastName(1,"kais");
//            System.out.println(employee);

            //当参数为map类型数据时，查询数据
//            Map<String, Object> hashMap = new HashMap<>();
//            hashMap.put("id",1);
//            hashMap.put("lastName","kais");
//            Employee empByMap = mapper.getEmpByMap(hashMap);
//            System.out.println(empByMap);

            //返回类型为List集合的查询方法
//            List<Employee> empsByListNameLike = mapper.getEmpsByListNameLike("%e%");
//            for(Employee employee : empsByListNameLike){
//                System.out.println(employee);
//            }

            //返回一个map类型数据，封装单条数据
//            Map<String, Object> empByIdReturnMap = mapper.getEmpByIdReturnMap(1);
//            System.out.println(empByIdReturnMap);


            //多条记录封装一个map，并返回这个map
            Map<Integer, Employee> empByLastNameLikeReturnMap = mapper.getEmpByLastNameLikeReturnMap("%a%");
            System.out.println(empByLastNameLikeReturnMap);

            openSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
