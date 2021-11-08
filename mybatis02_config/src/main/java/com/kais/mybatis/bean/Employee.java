package com.kais.mybatis.bean;

import org.apache.ibatis.type.Alias;

/**
 * @Author liuxiankai
 * @ClassName Employee
 * @CreateTime 2021-09-24 10:53
 * @Description: 实体类
 */
@Alias("emp")
public class Employee {

    private Integer id;
    private String lastName;
    private String email;
    private String gender;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", lastName=" + lastName + ", email="
                + email + ", gender=" + gender + "]";
    }
}
