package com.akwolf.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.akwolf.bean.Person;

public interface IPersonDao {
	public Person getPersonById(int personId) throws SQLException;
	
	public List<Person> limitPersonList(int begin,int rows) throws SQLException;
	
	public boolean updatePerson(Connection connection,Person person) throws SQLException;
	
	public boolean deletePerson(Connection connection,int personId) throws SQLException;
	
	public boolean insertPerson(Connection connection,Person person) throws SQLException ;
	
	public long resultCount() throws SQLException ;
	
}
