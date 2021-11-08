package com.kais.ssm.controller;

import com.kais.ssm.pojo.Employee;
import com.kais.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author liuxiankai
 * @ClassName EmployeeController
 * @CreateTime 2021-10-21 23:02
 * @Description:
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public String getAll(Model model){
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "list";
    }
}