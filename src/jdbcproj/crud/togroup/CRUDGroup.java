package jdbcproj.crud.togroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import static jdbcproj.resources.Resources.DRIVER_CLASS;
import static jdbcproj.resources.Resources.URL;

public class CRUDGroup {
	
	static{
		try{
			Class.forName(DRIVER_CLASS);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	public void add(String name) throws SQLException{
		
		Connection conn = DriverManager.getConnection(URL);
		
		String query = "INSERT INTO groups (name) VALUES (?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}
	
	public void update(String name, String newName) throws SQLException{
		
		Connection conn = DriverManager.getConnection(URL);
		
		String query = "UPDATE groups SET name = ? WHERE name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(2, name);
		statement.setString(1, newName);
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}
	
	public void delete(String name) throws SQLException{

		Connection conn = DriverManager.getConnection(URL);
		
		String query = "DELETE FROM groups WHERE name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}
	
	public List<String> getAll() throws SQLException{
		
		List<String> res = new ArrayList<String>();
		Connection conn = DriverManager.getConnection(URL);
		
		String query = "SELECT name FROM groups";
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			String name = rs.getString("name");
			
			res.add(name);
		}
		
		statement.close();
		conn.close();
		
		return res;
	}
}