package com.akwolf.service;

import java.util.List;

import com.akwolf.bean.Person;

public interface IPersonService {
	public List<Person> listPerson(int begin,int rows) ;
	
	public boolean deletePerson(int personId) ;
	
	public boolean updatePerson(Person person) ;
	
	public boolean addPerson(Person person) ;
	
	public long resultCount() ;
}	
