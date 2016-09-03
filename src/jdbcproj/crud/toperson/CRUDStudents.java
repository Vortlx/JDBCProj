package jdbcproj.crud.toperson;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import jdbcproj.person.Person;

import static jdbcproj.resources.Resources.DRIVER_CLASS;
import static jdbcproj.resources.Resources.URL;


public class CRUDStudents implements CRUDPerson{

	static{
		try{
			Class.forName(DRIVER_CLASS);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void add(String name, String familyName) throws SQLException {
		
		Connection conn = DriverManager.getConnection(URL);
		
		String query = "INSERT INTO students (name, family_name) VALUES (?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.setString(2, familyName);
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}

	@Override
	public void update(String name, String familyName, String newName, String newFamilyName) throws SQLException {

		Connection conn = DriverManager.getConnection(URL);
		
		String query = "UPDATE students SET name = ?, family_name = ? WHERE name = ? AND family_name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(3, name);
		statement.setString(4, familyName);
		statement.setString(1, newName);
		statement.setString(2, newFamilyName);
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}

	@Override
	public void delete(String name, String familyName) throws SQLException {
		
		Connection conn = DriverManager.getConnection(URL);
		
		String query = "DELETE FROM students WHERE name = ? AND family_name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.setString(2, familyName);
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}

	@Override
	public List<Person> getAll() throws SQLException {
		
		List<Person> res = new ArrayList<Person>();
		Connection conn = DriverManager.getConnection(URL);
		
		String query = "SELECT name, family_name FROM students";
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			String name = rs.getString("name");
			String familyName = rs.getString("family_name");
			
			res.add(new Person(name, familyName));
		}
		
		statement.close();
		conn.close();
		
		return res;
	}

	@Override
	public List<Person> getByName(String name) throws SQLException {

		List<Person> res = new ArrayList<Person>();
		Connection conn = DriverManager.getConnection(URL);
		
		String query = "SELECT name, family_name FROM students WHERE name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			name = rs.getString("name");
			String familyName = rs.getString("family_name");
			
			res.add(new Person(name, familyName));
		}
		
		statement.close();
		conn.close();
		
		return res;
	}

	@Override
	public List<Person> getByFamilyName(String familyName) throws SQLException {

		List<Person> res = new ArrayList<Person>();
		Connection conn = DriverManager.getConnection(URL);
		
		String query = "SELECT name, family_name FROM students WHERE family_name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, familyName);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			String name = rs.getString("name");
			familyName = rs.getString("family_name");
			
			res.add(new Person(name, familyName));
		}
		
		statement.close();
		conn.close();
		
		return res;
	}
}