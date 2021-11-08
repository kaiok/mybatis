package com.kais.mapper;

import com.kais.pojo.Blog;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author liuxiankai
 * @ClassName BlogMapper
 * @CreateTime 2021-10-20 17:41
 * @Description:
 */
public interface BlogMapper {

    @Select("select * from blog")
    List<Blog> getByPage();

}
