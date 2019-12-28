package com.xupt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xupt.bean.Employee;

public interface EmployeeMapperDynamicSQL {
	public List<Employee> getEmpsByConditionIf(Employee employee);
	public List<Employee> getEmpsByConditionTrim(Employee employee);
	public List<Employee> getEmpsByConditionChoose(Employee employee);
	public List<Employee> getEmpsByConditionForeach(@Param("ids")List<Integer> ids);
	public void updateEmp(Employee employee);
	public void addEmps(@Param("emps")List<Employee> emps);
}
