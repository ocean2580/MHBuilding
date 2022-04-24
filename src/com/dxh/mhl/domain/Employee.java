package com.dxh.mhl.domain;

public class Employee {

    private Integer id;
    private String empId;
    private String name;
    private String job;
    private String pwd;

    public Employee() {
    }

    public Employee(Integer id, String empId, String name, String job, String pwd) {
        this.id = id;
        this.empId = empId;
        this.name = name;
        this.job = job;
        this.pwd = pwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
