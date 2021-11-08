import com.kais.cache.mapper.EmployeeMapper;
import com.kais.cache.pojo.Employee;
import com.kais.cache.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @Author liuxiankai
 * @ClassName CacheTest
 * @CreateTime 2021-10-21 11:28
 * @Description:
 */
public class CacheTest {

    /**
     * 一级缓存测试
     * 一级缓存失效的情况
     * 1.一级缓存又称为sqlSession，也就是说两次查询的sqlSession不同，那么一级缓存就没有起作用了
     * 2.sqlSession相同，但是查询条件不同，也需要重新向数据库发送请求
     * 3.sqlSession相同，执行了增删改操作（这次操对数据库有影响）
     * 4.sqlSession相同，用户清除了缓存：sqlSession.clearCache();
     *
     *
     * 即，每一个sqlSession为一个sql会话，对应一个缓存区
     *
     * @return void
     * @Author LiuXianKai
     * @create 2021/10/21 13:18
     */
    @Test
    public void testFirstLevelCache(){

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        //清除mybatis缓存
//        sqlSession.clearCache();

        Employee employee = mapper.geEmployeeById(1);
        System.out.println(employee);
        //一级缓存是默认开启的，第二次查询并没有重新发送sql请求
        Employee employee1 = employee;
        System.out.println(employee1);
        System.out.println(employee == employee1);

        //此时一级缓存就没有起作用
//        SqlSession sqlSession1 = MyBatisUtil.getSqlSession();
//        EmployeeMapper mapper1 = sqlSession1.getMapper(EmployeeMapper.class);
//        Employee employee2 = mapper.geEmployeeById(1);
    }

    
    /**
     * 二级缓存测试
     *  1.开启二级缓存总开关
     *      <setting name="cacheEnabled" value="true"/>
     *  2.开启Mapper.xml的二级缓存，所有参数都是可选的
     *      <cache eviction="FIFO" flushInterval="60000" size="512" readOnly="true"/>
     *      eviction：缓存策略
     *      flushInterval：缓存刷新间隔，默认为不清空，单位毫秒
     *      readOnly：是否只读
     *          （true：【只读】mybatis认为所有从缓存中获取数据的操作都是只读的，也就是查询，不会修改数据，查询速度加快）
     *          （false：【非只读】mybatis觉得获取的数据可能会被修改，会利用序列化&反序列化的技术克隆一份新的数据给你，速度稍慢）
     *      size：缓存存放多少元素
     *  3.我们的POJO需要实现序列化接口
     *  4.缓存相关的配置
     *      (1)<setting name="cacheEnabled" value="false"/>关闭的是二级缓存,一级缓存一直可用
     *      (2)每个select标签都有userCache="true";值为false的情况:一级缓存依然使用,二级缓存不适用
     *      (3)每个增删改标签的flushCache = "true",会清除缓存(一级和二级都会被清空)
     *      (4)sqlSession.clearCache();只是清除当前sqlSession的一级缓存
     *      (5)localCacheScope:本地缓存作用域：（一级缓存sqlSession）：当前会话的所有数据保存在会话缓存中
     *          会禁用一级缓存
     *
     * @return void 
     * @Author LiuXianKai
     * @create 2021/10/21 13:38
     */
    @Test
    public void testSecondLevelCache(){

        //两个sqlSession，都进行查询Id为1的Employee，当xml中二级缓存的配置注释掉后，会发送两次sql请求，
        //开启配置，第二次sql请求将在缓存中取出第一次查询出来的数据（id=1的employee）
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        SqlSession sqlSession2 = MyBatisUtil.getSqlSession();

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);

        Employee employee = mapper.geEmployeeById(1);
        System.out.println(employee);
        //必须要先将第一次sqlSession关闭，该数据才会被放入二级缓存中，第二次请求才会从二级缓存中尝试获取数据
        sqlSession.close();

        Employee employee1 = mapper2.geEmployeeById(1);
        System.out.println(employee1);
//        sqlSession.close();
        sqlSession2.close();


    }

}
