package com.akwolf.key;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.akwolf.util.DBManager;

public class PrimaryKey {

	public boolean insertPerson() throws SQLException {
		boolean flag = false;
		String sql = "insert into person values(null,?,?) ";

		Connection conn = DBManager.getDataSource().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);

		pstmt.setString(1, "akwolf");
		pstmt.setInt(2, 22);

		flag = pstmt.executeUpdate() == 1 ? true : false;
		ResultSet rs = pstmt.getGeneratedKeys();
		int pk = 0;
		if (rs.next()) {
			pk = rs.getInt(1);
		}
		System.out.println(pk);

		return flag;
	}

	public static void main(String[] args) throws SQLException {
		new PrimaryKey().insertPerson();
	}
}
