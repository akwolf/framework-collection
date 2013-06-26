package com.akwolf.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

import com.akwolf.bean.Person;
import com.akwolf.dao.impl.PersonDaoImpl;
import com.akwolf.util.DBManager;

public class Test {

	public static void main(String[] args) throws SQLException {
		//System.out.println(DBManager.getDataSource());
		
		PersonDaoImpl daoImpl = new PersonDaoImpl() ;
		//System.out.println(daoImpl.getPersonById(1));
		//System.out.println(daoImpl.limitPersonList(1, 10));
		Connection connection = DBManager.getDataSource().getConnection();
		Person p = new Person() ;
		//p.setPersonId(1) ;
		p.setName("akwolf") ;
		p.setAge(22) ;
		//System.out.println(daoImpl.updatePerson(connection, p));
		//System.out.println(daoImpl.deletePerson(connection, 3));
		System.out.println(daoImpl.insertPerson(connection, p));
		DbUtils.close(connection);
	}

}
