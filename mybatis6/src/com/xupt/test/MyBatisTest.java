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
	/*
	 * 两级缓存
	 *  一级缓存(本地缓存)  sqlSession级别的缓存，一级缓存一直是开启的
	 * 	 与数据库同一次会话期间查询到的数据会放在本地缓存中
	 * 	 一级缓存失效情况：
	 * 				1.sqlSession不同
	 * 				2.查询条件不同
	 * 				3.sqlSession相同，但是两次查询之间有过增删改操作
	 * 				4.sqlSession手动清除缓存（clearCache）
	 *  二级缓存
	 * 		一个namespace对应一个二级缓存
	 * 		使用：1.开启二级缓存		<setting name="cacheEnabled" value="true"/>
	 * 			  2.去mapper.xml中配置二级缓存 <cache></cache>
	 * 			  3.我们的pojo需要实现序列化接口	
	 * 
	 * 与缓存有关的设置属性   1）<setting name="cacheEnabled" value="true"/> 控制二级缓存的开闭（一级缓存不可关）
	 * 					  2）每个select标签的useCache='true'标签，false时关闭二级缓存，无法关闭一级缓存
	 * 			注重注意	  3）每个增删改标签都有flushCache='true'标签，增删改执行完成后就会清除一级缓存和二级缓存
	 * 					  4）openSession.clearCache()清除当前session的一级缓存，与二级缓存没关系
	 * 					  5）setting标签中localCacheScope本地缓存作用域：（一级缓存SESSION）：当前会话所有数据作用域
	 * 																	STATEMENT:可以禁用一级缓存
	 * */
	@Test
	public void testSecondLevelCache() throws IOException {
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		SqlSession openSession2=sqlSessionFactory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
			EmployeeMapperDynamicSQL mapper2 = openSession2.getMapper(EmployeeMapperDynamicSQL.class);
			Employee emp01=mapper.getEmpById(1);
			System.out.println(emp01);
			openSession.close();
			Employee emp02=mapper2.getEmpById(1);
			System.out.println(emp02);
			openSession2.close();
		}finally {


		}
	}
}
