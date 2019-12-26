package com.xupt.dao;

import com.xupt.bean.Employee;

public interface EmployeeMapper {
	public Employee getEmployee(Integer id);
	public void addEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public void deleteEmployee(Integer id); 
}
