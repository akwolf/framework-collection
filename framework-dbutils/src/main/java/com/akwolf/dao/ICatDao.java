package com.akwolf.dao;

import java.util.List;

import com.akwolf.bean.Cat;

public interface ICatDao {

	public Cat getCatById(int catId);

	public List<Cat> limitCatList(int begin, int rows);

	public int updateCat(Cat cat);

	public int deleteCat(int catId);
}
