package com.akwolf.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import com.akwolf.bean.Person;
import com.akwolf.dao.IPersonDao;
import com.akwolf.dao.impl.PersonDaoImpl;
import com.akwolf.service.IPersonService;
import com.akwolf.util.DBManager;

/**
 * 一个service方法中可以用来完成一个transaction的操作
 * 
 * @author zhangh
 *
 */
public class PersonServiceImpl implements IPersonService {
	
	private IPersonDao personDao ;
	
	private static final Logger logger = Logger.getLogger(PersonServiceImpl.class) ;
	
	public PersonServiceImpl(){
		personDao = new PersonDaoImpl() ;
	}

	@Override
	public List<Person> listPerson(int begin, int rows) {
		List<Person> list = null;
		try {
			list = personDao.limitPersonList(begin, rows);
		} catch (SQLException e) {
			logger.error("查询人员列表时出错!!") ;
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public long resultCount() {
		long count = 0 ;
		try {
			count = personDao.resultCount() ;
		} catch (SQLException e) {
			logger.error("统计person总记录时出错!!");
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public boolean deletePerson(int personId) {
		boolean flag = false ;
		Connection conn = null ;
		try {
			conn = DBManager.getDataSource().getConnection() ;
			flag = personDao.deletePerson(conn, personId) ;
			DbUtils.close(conn) ;
		} catch (SQLException e) {
			logger.error("删除"+personId+"Person出错") ;
			DbUtils.rollbackAndCloseQuietly(conn) ;
		}
		
		return flag;
	}

	@Override
	public boolean updatePerson(Person person) {
		boolean flag = false ;
		Connection conn = null ;
		try {
			conn = DBManager.getDataSource().getConnection() ;
			flag = personDao.updatePerson(conn, person) ;
			DbUtils.close(conn) ;
		} catch (SQLException e) {
			logger.error("更新"+person.getPersonId()+"Person出错") ;
			DbUtils.rollbackAndCloseQuietly(conn) ;
		}
		
		return flag;
	}

	@Override
	public boolean addPerson(Person person) {
		boolean flag = false ;
		Connection conn = null ;
		try {
			conn = DBManager.getDataSource().getConnection() ;
			flag = personDao.insertPerson(conn, person) ;
			DbUtils.close(conn) ;
		} catch (SQLException e) {
			logger.error("更新"+person.getPersonId()+"Person出错") ;
			DbUtils.rollbackAndCloseQuietly(conn) ;
		}
		
		return flag;
	}
}
