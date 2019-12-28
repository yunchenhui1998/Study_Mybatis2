package com.xupt.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.xupt.bean.Department;
import com.xupt.bean.Employee;
import com.xupt.dao.EmployeeMapperDynamicSQL;

public class MyBatisTest {
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String xmlPath="mybatis-config.xml";
		InputStream inputStream=Resources.getResourceAsStream(xmlPath);
		SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
//	测试if语句
	@Test
	public void test01() throws IOException {
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession=sqlSessionFactory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
			Employee employee=new Employee(null,"%e%","jerry@email.com",null);
			List<Employee> employees = mapper.getEmpsByConditionIf(employee);
			for(Employee e:employees) {
				System.out.println(e);
			}
		}finally {
			openSession.close();
		}
	}
//	测试trim语句
	@Test
	public void test02() throws IOException {
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession=sqlSessionFactory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
			Employee employee=new Employee(null,"%e%","jerry@email.com",null);
			List<Employee> employees = mapper.getEmpsByConditionTrim(employee);
			for(Employee e:employees) {
				System.out.println(e);
			}
		}finally {
			openSession.close();
		}
	}
//	测试choose语句
	@Test
	public void test03() throws IOException {
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession=sqlSessionFactory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
			Employee employee=new Employee(null,null,null,null);
			List<Employee> employees = mapper.getEmpsByConditionChoose(employee);
			for(Employee e:employees) {
				System.out.println(e);
			}
		}finally {
			openSession.close();
		}
	}
//	测试set语句
	@Test
	public void test04() throws IOException {
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession=sqlSessionFactory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
			Employee employee=new Employee(1,"jerry",null,null);
			mapper.updateEmp(employee);
			
		}finally {
			openSession.commit();
			openSession.close();
		}
	}
//	测试foreach语句
	@Test
	public void test05() throws IOException {
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession=sqlSessionFactory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
			List<Employee> employees = mapper.getEmpsByConditionForeach(Arrays.asList(1,2,3));
			for(Employee e:employees) {
				System.out.println(e);
			}
			
		}finally {
			openSession.commit();
			openSession.close();
		}
	}
//	测试foreach语句批量插入
	@Test
	public void test06() throws IOException {
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession=sqlSessionFactory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
			List<Employee> emps =new ArrayList<>();
			emps.add(new Employee(null,"smith","smith@email.com","1",new Department(1)));
			emps.add(new Employee(null,"jack","jack@email.com","1",new Department(1)));
			mapper.addEmps(emps);
			openSession.commit();
		}finally {

			openSession.close();
		}
	}
}
