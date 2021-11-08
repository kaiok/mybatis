import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kais.mapper.BlogMapper;
import com.kais.pojo.Blog;
import com.kais.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @Author liuxiankai
 * @ClassName PageHelperTest
 * @CreateTime 2021-10-22 1:35
 * @Description:
 */
public class PageHelperTest {
    @Test
    public void testHello(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        //
        /**
         * 原理:
         *      调用PageHelper类的startPage方法指定查询页数和每页的数量, 会返回一个page对象
         *
         *      调用startPage方法后, PageHelper会在当前线程中绑定一个page对象, 跟返回的对象是同一个对象,
         *      然后在MyBatis执行我们的查询时, 会被PageHelper拦截, 并拿出与当前线程绑定的page对象,
         *      并修改MyBatis即将执行的sql语句, 为sql语句添加上分页的功能。
         *      为什么PageHelper会拦截MyBatis的执行, 这是MyBatis的插件机制的问题, 想要了解自己去百度。
         */
        Page<Object> page = PageHelper.startPage(2, 2);

        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        List<Blog> blogs = mapper.getByPage();
        blogs.forEach(System.out :: println);

        // page对象封装了查询信息
        System.out.println("当前页码:" + page.getPageNum());
        System.out.println("总记录数:" + page.getTotal());
        System.out.println("每页的记录数:" + page.getPageSize());
        System.out.println("总页码:" + page.getPages());

        sqlSession.close();
    }


    @Test
    public void testPlugins(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        // offsetPage(起始索引, 查询条数)
        Page<Object> page = PageHelper.offsetPage(1, 3);
        List<Blog> blogs = mapper.getByPage();
        blogs.forEach(System.out :: println);

        sqlSession.close();
    }

}