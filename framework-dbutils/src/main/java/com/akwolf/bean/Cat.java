package com.akwolf.bean;

public class Cat {

	private int catId ;
	
	private int personId ;
	
	private String name ;

	

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Cat [catId=" + catId + ", personId=" + personId + ", name="
				+ name + "]";
	}
	
	
}
