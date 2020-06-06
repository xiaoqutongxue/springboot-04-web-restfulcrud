package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.DepartmentDao;
import com.atguigu.springboot.dao.EmployeeDao;
import com.atguigu.springboot.entities.Department;
import com.atguigu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    // 查询所有员工列表页面
    @GetMapping("/emps")
    public String list(Model model){
       Collection<Employee> employees = employeeDao.getAll();

       // 放在请求域中
        model.addAttribute("emps",employees);

        return "/emp/list";

    }
    //来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        // 来到添加页面,查出所有的部门
        Collection<Department> departments = departmentDao.getDepartments();
        // 放在请求域中
        model.addAttribute("depts",departments);
        return "/emp/add";

    }

    // 员工添加功能
    // springMVC自动将参数列表和请求参数的属性进行一一对应：但是要求是请求参数的名字和参数列表的名字一样（也就是传入的参数Employee employee）
    @PostMapping("/emp")
    public String addEMP(Employee employee){

        // 来到员工页面
        // redirect重定向
        // forward请求转发
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    // 来到修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer integer,Model model){
        Employee employee = employeeDao.get(integer);
        model.addAttribute("emp",employee);
        // 来到添加页面,查出所有的部门
        Collection<Department> departments = departmentDao.getDepartments();
        // 放在请求域中
        model.addAttribute("depts",departments);
        // 回到修改页面（add.html相当于一个修改添加二合一的页面）
        return "/emp/add";
    }

    // 员工修改
    @PutMapping("/emp")
    public String updEMP(Employee employee){
        System.out.println("修改的员工数据:"+employee.toString());
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    // 员工删除
    @DeleteMapping("/del/{id}")
    public String delEmp(@PathVariable("id") Integer id){

        employeeDao.delete(id);
        return "redirect:/emps";

    }
}
