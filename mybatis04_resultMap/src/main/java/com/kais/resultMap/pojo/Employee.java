package com.kais.resultMap.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	/**
	 * 方法描述:演示实体类属性和数据库列名不一致如何映射
	 * 时间:2021/10/18 16:02
	*/
	private Integer userId;
	private String lastName;
	private String userEmail;
	private String gender;
	private Department dept;

}
