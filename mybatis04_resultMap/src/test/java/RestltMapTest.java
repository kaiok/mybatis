import com.kais.resultMap.mapper.DepartmentMapper;
import com.kais.resultMap.mapper.EmployeeMapper;
import com.kais.resultMap.pojo.Department;
import com.kais.resultMap.pojo.Employee;
import com.kais.resultMap.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @Author liuxiankai
 * @ClassName RestltMapTest
 * @CreateTime 2021-10-18 16:15
 * @Description:
 */
public class RestltMapTest {

    /**
     * 方法描述:修改实体类属性名称，配置resultMap映射关系，查询操作实现
     * 时间:2021/10/18 16:46
    */
    @Test
    public void testResultMap() throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        List<Employee> employeeList = mapper.getAll();
        employeeList.forEach(System.out :: println);

        sqlSession.commit();
        sqlSession.close();
    }

    /*resultMap进阶用法-----一对多*/
    /**
     * 方法描述:级联模式，联合查询,通过employee.id == department.id进行联合查询
     *
     * @返回值 : void
     * @作者 : lxk
     * 时间:2021/10/20 14:02
    */
    @Test
    public void testGetEmpAndDept(){

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        Employee empAndDept = mapper.getEmpAndDept(8);
        System.out.println(empAndDept);
        System.out.println("==============");
        System.out.println(empAndDept.getDept());

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDept(){

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);

        Department deptById = mapper.getDeptById(1);
        System.out.println(deptById);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 方法描述:使用association进行分步查询
     * 时间:2021/10/20 15:41
    */
    @Test
    public void testEmpByIdStep(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

//        Employee employee = mapper.getEmpByIdStep(1);
//        System.out.println(employee);
//        System.out.println("==============");
//        System.out.println(employee.getDept());

        //更改MyEmpByStep为MyEmpDis，测试鉴别器
        //    <select id="getEmpByIdStep" resultMap="MyEmpByStep">
        //        select * from employee where id = #{id}
        //    </select>
        //开启延迟加载模式，实现按需加载，只会输出dept
        Employee employee = mapper.getEmpByIdStep(1);
        System.out.println(employee.getDept());
        System.out.println(employee.getLastName());

        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 方法描述:多对一 ，通过department，查询多个employee
     * 时间:2021/10/20 15:41
     */
    @Test
    public void testgetDeptByIdPlus(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);

//        Employee employee = mapper.getEmpByIdStep(1);
//        System.out.println(employee);
//        System.out.println("==============");
//        System.out.println(employee.getDept());


        //开启延迟加载模式，实现按需加载，只会输出dept
        List<Department> deptByIdPlus = departmentMapper.getDeptByIdPlus(1);
        for (Department department : deptByIdPlus
                ) {
            System.out.println(department);
        }

        sqlSession.commit();
        sqlSession.close();
    }

}
