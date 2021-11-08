import com.kais.mapper.BlogMapper;
import com.kais.pojo.Blog;
import com.kais.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 * @Author liuxiankai
 * @ClassName DynamicSQLTest
 * @CreateTime 2021-10-21 9:27
 * @Description:
 */
public class DynamicSQLTest {

    /**
     * 动态SQL---if语句分析
     *      1.blog.setTitle("%MyBatis%");
     *        blog.setAuthor("%ais%");
     *        对应的sql==select * from blog where title like ? and author like ?
     *      2.blog.setTitle("%MyBatis%");
     *        blog.setAuthor("");
     *        对应的sql==select * from blog where title like ?
     *      3.如果不给Title赋值，那么查询语句会报错
     *        blog.setTitle("");
     *        blog.setAuthor("%ais%");
     *        对应的sql==select * from user where and author like #{author}，这是非法的，解决：使用where标签
     * @return void
     * @Author LiuXianKai
     * @create 2021/10/21 9:45
     */
    @Test
    public void testIF(){

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setTitle("");
        blog.setAuthor("%ais%");
        List<Blog> list = mapper.getByBlogIf(blog);
        list.forEach(System.out :: println);

        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 改造if语句第三种情况
     *  1. MyBatis 使用 where 标签来将所有的查询条件包括在内，MyBatis 会自动的忽略 where 后第一个不合法的 and 或 or,
     *  并且在有条件的情况下自动拼接上 where
     *  2.if语句第三种情况对应的
     *  sql语句 == Preparing: select * from blog WHERE author like ?
     *  3.xml
     * @return void 
     * @Author LiuXianKai
     * @create 2021/10/21 9:57
     */
    @Test
    public void testWhere(){

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setTitle("");
        blog.setAuthor("%ais%");
        List<Blog> list = mapper.getByBlogWhere(blog);
        list.forEach(System.out :: println);

        sqlSession.commit();
        sqlSession.close();
    }

    
    /**
     * bind
     * 我们进行模糊查询时，每次给属性赋值都加上了 %%，显示的加上通配符，这样并不是很好，
     * 应该让 MyBatis 为我们加上通配符，想要完成这个功能需要使用 bind 元素
     * 元素允许你在 OGNL 表达式以外创建一个变量，并将其绑定到当前的上下文。通常用来拼接模糊查询
     *  
     * @return void 
     * @Author LiuXianKai
     * @create 2021/10/21 10:09
     */
    @Test
    public void testBind(){

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        // 直接赋值了一个y, 没有使用通配符
        blog.setTitle("y");
        blog.setAuthor("o");
        List<Blog> list = mapper.getByBlogBind(blog);
        list.forEach(System.out :: println);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * set
     * set元素会动态地在行首插入 SET关键字，并会删掉额外的逗号（这些逗号是在使用条件语句给列赋值时引入的）
     * mybatis帮我们加上了set关键字, 并且删除了set后面第一个不合法的逗号
     * 修改, 但是只修改属性值不为null的属性
     * 1.该sql语句为 == update blog SET create_time = ? , views = ? where id = ?
     * @return void
     * @Author LiuXianKai
     * @create 2021/10/21 10:17
     */
    @Test
    public void testSet(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setId("0001");
        blog.setCreateTime(Date.valueOf("2020-1-1"));
        blog.setViews(999);
        int update = mapper.update(blog);

        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 有时候，我们不想使用所有的条件，而只是想从多个条件中选择一个使用
     * 针对这种情况，MyBatis 提供了 choose 元素，它有点像 Java 中的 switch 语句
     * （choose --> switch，when --> case，otherwise --> default）
     *
     * @return void
     * @Author LiuXianKai
     * @create 2021/10/21 10:24
     */
    @Test
    public void testChoose(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        // 赋值 id 和 title
        // 给 id 和 title 属性赋值, 因为choose只会进入一个when, 所以查询条件只有id, 没有title
        Blog blog1 = new Blog();
        blog1.setId("0001");
        blog1.setTitle("my");
        List<Blog> list1 = mapper.getByBlogChoose(blog1);
        System.out.println(list1);

        // 只赋值 title
        // 只给 title 属性赋值, 查询条件加上了title
        Blog blog2 = new Blog();
        blog2.setTitle("my");
        List<Blog> list2 = mapper.getByBlogChoose(blog2);
        System.out.println(list2);

        // 什么都不赋值
        // 什么都没给, 传了一个null, 进入了otherwise中
        List<Blog> list3 = mapper.getByBlogChoose(null);
        System.out.println(list3);

        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * foreach
     * 通过id的List集合查询多条数据
     * sql == select * from blog WHERE id in( ? , ? , ? )
     * @return void
     * @Author LiuXianKai
     * @create 2021/10/21 10:39
     */
    @Test
    public void testForeach(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        String []ids = new String[] {"0001", "0002", "0003"};
        List<Blog> list = mapper.getByIds(Arrays.asList(ids));
        list.forEach(System.out :: println);

        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 抽取可重用sql
     * 场景：在真实开发中我们不能写这样的 SQL 语句 select * from xxx，是不能写 * 的，
     * 但是每写一个查询语句都写上全部的列名，就造成了代码的冗余，而且也不易于维护。
     * 还好 MyBatis 提供了解决方案。如果表中字段发生了更改，我们只需要修改 sql 片段就 OK 了
     *
     * @return void
     * @Author LiuXianKai
     * @create 2021/10/21 10:45
     */
    @Test
    public void testSql(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        List<Blog> list = mapper.getAll();
        list.forEach(System.out :: println);

        sqlSession.commit();
        sqlSession.close();
    }


}
