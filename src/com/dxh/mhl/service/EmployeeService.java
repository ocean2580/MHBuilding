package com.dxh.mhl.service;

import com.dxh.mhl.dao.EmployeeDAO;
import com.dxh.mhl.domain.Employee;

public class EmployeeService {

    private EmployeeDAO employeeDAO = new EmployeeDAO(); // sql


    public Employee getEmployeeByIdAndPwd(String empId, String pwd) {
       return employeeDAO.querySingle("select * from employee where empId = ? and pwd = md5(?)",
                Employee.class,empId,pwd);
    }



}
