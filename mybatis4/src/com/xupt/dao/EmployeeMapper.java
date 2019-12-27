package com.xupt.dao;

import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import com.xupt.bean.Employee;

public interface EmployeeMapper {
	//多条记录封装map，key是主键，值就是封装后的javaBean
	@MapKey("lastName")
	public 	Map<Integer,Employee> getEmpByLastNameLikeReturnMap(String lastName);
	//返回一条记录的map，key就是列名，值就是对应值
	public Map<String,Object> getEmpByIdReturnMap(Integer id);
	public Employee getEmployee(Integer id);
	public void addEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public void deleteEmployee(Integer id); 
}
