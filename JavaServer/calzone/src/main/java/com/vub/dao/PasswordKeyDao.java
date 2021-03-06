package com.vub.dao;


import com.vub.db.DbTranslate;
import com.vub.model.PasswordKey;

public class PasswordKeyDao {
	
	DbTranslate db = new DbTranslate();
	
	public void insert(PasswordKey passwordKey) {
		db.insertPasswordKey(passwordKey);
	}
	
	public void delete(PasswordKey passwordKey) {
		db.deletePasswordKey(passwordKey);
	}
	
	public PasswordKey findByKeyString(String KeyString) {
		return db.selectPasswordKeyByKeyString(KeyString);
	}
	
}
	/*private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(PasswordKey passwordKey){
		//Based on Example
		//http://www.mkyong.com/spring/maven-spring-jdbc-example/ 
		String sql = "INSERT INTO PasswordKeys " +
				"(Identifier, CreatedOn, KeyString) VALUES (?, ?, ?)";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, passwordKey.getIdentifier());
			ps.setDate(2, passwordKey.getCreatedOn());
			ps.setString(3, passwordKey.getKeyString());
			ps.executeUpdate();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	public void delete(PasswordKey passwordKey) {
		String sql = "DELETE FROM PasswordKeys WHERE KeyString = ?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, passwordKey.getKeyString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
	}
	public PasswordKey findByKeyString(String KeyString){	 
		String sql = "SELECT * FROM PasswordKeys WHERE KeyString = ?";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, KeyString);
			PasswordKey passwordKey = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				passwordKey = new PasswordKey(
					rs.getString("Identifier"),
					rs.getDate("CreatedOn"), 
					rs.getString("KeyString")
				);
			}
			rs.close();
			ps.close();
			return passwordKey;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}
*/
