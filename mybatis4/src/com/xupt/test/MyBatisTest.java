package com.xupt.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.xupt.bean.Department;
import com.xupt.bean.Employee;
import com.xupt.dao.DepartmentMapper;
import com.xupt.dao.EmployeeMapper;
import com.xupt.dao.EmployeeMapperPlus;

class MyBatisTest {
	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	@Test
	public void test() throws IOException {
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession=sqlSessionFactory.openSession();
		try {
			EmployeeMapperPlus mapper=openSession.getMapper(EmployeeMapperPlus.class);
			Employee empById = mapper.getEmpById(1);
			System.out.println(empById);
			openSession.commit();
		}finally {
			openSession.close();
		}
	}
	@Test
	public void test2() throws IOException {
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession=sqlSessionFactory.openSession();
		try {
			EmployeeMapperPlus mapper=openSession.getMapper(EmployeeMapperPlus.class);
			Employee employee=mapper.getEmpByIdStep(1);
//			System.out.println(employee);
			System.out.println(employee.getLastName());
			openSession.commit();
			
		}finally {
			openSession.close();
		}
	}
	@Test
	public void test3() throws IOException {
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession=sqlSessionFactory.openSession();
		try {
			EmployeeMapperPlus mapper=openSession.getMapper(EmployeeMapperPlus.class);
			Employee empAndDept=mapper.getEmpAndDept(1);
			System.out.println(empAndDept);
			System.out.println(empAndDept.getDept());
			openSession.commit();
			
		}finally {
			openSession.close();
		}
	}
	@Test
	public void test4() throws IOException {
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession=sqlSessionFactory.openSession();
		try {
			DepartmentMapper mapper=openSession.getMapper(DepartmentMapper.class);
			Department department=mapper.getDeptByIdPlus(1);
			System.out.println(department);
			System.out.println(department.getEmployees());
			openSession.commit();
			
		}finally {
			openSession.close();
		}
	}	
	@Test
	public void test5() throws IOException {
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession=sqlSessionFactory.openSession();
		try {
			DepartmentMapper mapper=openSession.getMapper(DepartmentMapper.class);
			Department department=mapper.getDeptByIdStep(1);
			System.out.println(department);
			System.out.println(department.getEmployees());
			openSession.commit();
			
		}finally {
			openSession.close();
		}
	}
}
