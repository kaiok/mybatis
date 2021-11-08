package com.kais.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author liuxiankai
 * @ClassName Blog
 * @CreateTime 2021-10-20 17:38
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private String id;
    private String title;
    private String author;
    private Date createTime;
    private Integer views;
}