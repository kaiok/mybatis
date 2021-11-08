import com.kais.mapper.BlogMapper;
import com.kais.pojo.Blog;
import com.kais.pojo.BlogExample;
import com.kais.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author liuxiankai
 * @ClassName MyBatisTest
 * @CreateTime 2021-10-21 23:53
 * @Description:
 */
public class MyBatisTest {
    // 运行这个单元测试, 自动生成
    @Test
    public void testMbg() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("src\\main\\resources\\generationConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    @Test
    public void testMyBatis3(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        try {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            // 1.查询所有
            List<Blog> blogs = mapper.selectByExample(null);
            // 2.查询博客标题中带字母s的, 作者名字中带大字的
            // blogExample对象封装查询条件
            BlogExample blogExample = new BlogExample();
            // 3.创建Criteria, 这个Criteria就是拼装查询条件
            BlogExample.Criteria criteria = blogExample.createCriteria();
            // andXXXYYY表示添加and条件, XXX代表字段名, YYY代表条件(like,is...)
            criteria.andTitleLike("%s%");
            // 4.添加另外一组添加, 再次创建创建Criteria
            BlogExample.Criteria criteria2 = blogExample.createCriteria();
            // 设置Criteria的条件
            criteria2.andAuthorLike("%大%");
            // 5.调用or()表示这组添加与其他Criteria的关系
            blogExample.or(criteria2);
            blogs = mapper.selectByExample(blogExample);
            for (Blog blog : blogs) {
                System.out.println(blog);
            }
        } finally {
            sqlSession.close();
        }
    }
}