package com.kais.mapper;

import com.kais.pojo.Blog;

import java.util.List;

/**
 * @Author liuxiankai
 * @ClassName BlogMapper
 * @CreateTime 2021-10-20 17:41
 * @Description:
 */
public interface BlogMapper {

    // 查询博客, 携带了哪个字段查询条件就带上这个字段的值
    List<Blog> getByBlogIf(Blog blog);

    List<Blog> getByBlogWhere(Blog blog);

    List<Blog> getByBlogBind(Blog blog);

    // 修改, 但是只修改属性值不为null的属性
    int update(Blog blog);

    // 有id, 根据id精准匹配; 有title就根据title进行模糊查询; 如果都没有就查询author为苞米豆的blog
    List<Blog> getByBlogChoose(Blog blog);

    // 通过id的List集合查询多条数据
    List<Blog> getByIds(List<String> ids);

    List<Blog> getAll();


}
