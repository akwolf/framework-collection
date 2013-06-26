package com.akwolf.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;

import com.akwolf.bean.Person;
import com.akwolf.dao.IPersonDao;
import com.akwolf.util.DBManager;

public class PersonDaoImpl implements IPersonDao {

	private QueryRunner runner;
	private Logger logger = Logger.getLogger(PersonDaoImpl.class);

	// connection从外部传入更加合适,可以将不同daoImpl的执行放到一个事务中
	// 而对于conn的关闭放在对数据库操作的调用处

	public PersonDaoImpl() {
		//this.connection = connection;
		this.runner = new QueryRunner(DBManager.getDataSource());
	}

	@Override
	public Person getPersonById(int personId) throws SQLException {
		Person person = null;
		String sql = "select * from person p where p.personId = ?";
		try {
			person = runner.query(sql, new BeanHandler<Person>(Person.class),
					personId);
			logger.debug("Execute Query Row where personId=" + personId);
		} catch (SQLException e) {
			logger.error("Execute Query Error!!");
			throw e;
		}
		person = runner.query(sql, new BeanHandler<Person>(Person.class),
				personId);
		return person;
	}

	@Override
	public List<Person> limitPersonList(int begin, int rows)
			throws SQLException {
		List<Person> list = null;
		String sql = "select * from person p limit ?,?";
		try {
			list = runner.query(sql, new BeanListHandler<Person>(Person.class),
					begin, rows);
		} catch (SQLException e) {
			logger.error("Execute Query Error!!");
			throw e;
		}

		return list;
	}

	@Override
	public boolean updatePerson(Connection connection,Person person) throws SQLException {
		boolean flag = false;
		// connection的粒度放到放到方法这一级，如果设为全局变量在一个daoImpl执行完一个更新后
		// 在执行一个更新就会报connection null的错误
		// Connection connection = DBManager.getDataSource().getConnection();
		String sql = "update Person p set p.name=?,p.age=? where p.personId=?";
		try {
			flag = runner.update(connection, sql, person.getName(),
					person.getAge(), person.getPersonId()) == 1 ? true : false;
		} catch (SQLException e) {
			logger.error("更新时出错!!");
			throw e;
		}
		return flag;
	}

	@Override
	public boolean deletePerson(Connection connection,int personId) throws SQLException {
		boolean flag = false;
		String sql = "delete from Person where personId=?";
		try {
			flag = runner.update(connection, sql, personId) == 1 ? true : false;
		} catch (SQLException e) {
			logger.error("更新时出错!!");
			throw e;
		}
		return flag;
	}
	
	@Override
	public boolean insertPerson(Connection connection,Person person) throws SQLException{
		boolean flag = false;
		String sql = "insert into person values(null,?,?) " ;
		try {
			flag = runner.update(connection, sql, person.getName(),person.getAge()) == 1 ? true : false;
		} catch (SQLException e) {
			logger.error("插入数据时出错!!");
		}
		return flag ;
	}

	@Override
	public long resultCount() throws SQLException {
		long results=0;
		String sql="select count(personId) count from person ";
		try {
			List<Map<String,Object>> lst=runner.query(sql, new MapListHandler());
			if(lst!=null){
				Map<String,Object> map=lst.get(0);
				results=(Long)map.get("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
}
