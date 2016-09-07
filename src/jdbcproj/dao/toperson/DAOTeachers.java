package jdbcproj.dao.toperson;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import jdbcproj.data.person.Person;
import jdbcproj.data.person.Teacher;

import java.util.ArrayList;

import static jdbcproj.resources.Resources.getProperty;

/**
 * This class implements CRUD operation for teachers table.
 * 
 * @author Lebedev Alexander
 * @since 2016-09-07
 * */
public class DAOTeachers implements DAOPerson {

	static{
		try{
			Class.forName(getProperty("DRIVER_CLASS"));
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method insert data into teachers table.
	 * 
	 *  @see DAOPerson#add(String, String)
	 *  
	 *  @param name Name of teacher.
	 *  @param familyName Family name of teacher.
	 *  @throw SQLException
	 *  @return Nothing.
	 * */
	@Override
	public void add(Person person) throws SQLException {
		
		Teacher teacher = (Teacher) person;
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "INSERT INTO teachers (name, family_name) VALUES(?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, teacher.getName());
		statement.setString(2, teacher.getFamilyName());
		statement.executeUpdate();
		statement.close();
		
		query = "INSERT INTO curator(id_group, id_teacher) VALUES(?, ?)";
		statement = conn.prepareStatement(query);
		
		// Get teacher ID
		int teacherID = -1;
		List<String> groups = teacher.getGroups();
		
		String teacherIDQuery = "SELECT id FROM teachers WHERE name = '" + teacher.getName() +
									"' AND family_name = '" + teacher.getFamilyName() + "'";
		Statement getIDTeacher = conn.createStatement();
		ResultSet rs = getIDTeacher.executeQuery(teacherIDQuery);
		if(rs.next()){
			teacherID = rs.getInt(1);
		}
		
		//Get group ID
		String groupIDQuery = "SELECT id FROM groups WHERE name = ?"; 
		PreparedStatement getIDGroup = conn.prepareStatement(groupIDQuery);
		for(String group: groups){
			int groupID = -1;
			getIDGroup.setString(1, group);
			
			rs = getIDGroup.executeQuery(groupIDQuery);
			if(rs.next()){
				groupID = rs.getInt(1);
			}
			
			statement.setInt(groupID, teacherID);
			statement.executeUpdate();
			
		}
		rs.close();
		getIDGroup.close();
		statement.close();
		conn.close();
	}

	/**
	 * This method update data into teachers table.
	 * 
	 * @see DAOPerson#update(String, String, String, String)
	 * 
	 * @param name Old name of teacher.
	 * @param familyName Old family name of teacher.
	 * @param newName New name of teacher.
	 * @param newFamilyName New family name of teacher.
	 * @throw SQLException
	 * @return Nothing.
	 * */
	@Override
	public void update(Person oldPerson, Person newPerson) throws SQLException {

		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "UPDATE teachers SET name = ?, family_name = ? WHERE name = ? AND family_name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(3, oldPerson.getName());
		statement.setString(4, oldPerson.getFamilyName());
		statement.setString(1, newPerson.getName());
		statement.setString(2, newPerson.getFamilyName());
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}

	/**
	 * This method delete data from teachers table.
	 * 
	 * @see DAOPerson#delete(String, String)
	 * 
	 * @param name Name of teacher.
	 * @param familyName Family name of teacher.
	 * @throw SQLException
	 * @return Nothing
	 * */
	@Override
	public void delete(Person person) throws SQLException {
		
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "DELETE FROM teachers WHERE name = ? AND family_name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, person.getName());
		statement.setString(2, person.getFamilyName());
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}

	/**
	 * This method return list of all teachers.
	 * 
	 * @see DAOPerson#getAll()
	 * 
	 * @throw SQLException
	 * @return List of persons
	 * */
	public List<Teacher> getAll() throws SQLException {
		
		List<Teacher> res = new ArrayList<Teacher>();
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "SELECT name, family_name FROM teachers";
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			String name = rs.getString("name");
			String familyName = rs.getString("family_name");
			
			res.add(new Teacher(name, familyName));
		}
		
		statement.close();
		conn.close();
		
		return res;
	}

	/**
	 * This method return list of all teachers who have a specific name.
	 * 
	 * @see DAOPerson#getByName(String)
	 * 
	 * @param name Name of teacher for whom there is a search
	 * @throw SQLException
	 * @return List of person who have a specific name
	 * */
	public List<Teacher> getByName(String name) throws SQLException {

		List<Teacher> res = new ArrayList<Teacher>();
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "SELECT name, family_name FROM teachers WHERE name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			name = rs.getString("name");
			String familyName = rs.getString("family_name");
			
			res.add(new Teacher(name, familyName));
		}
		
		statement.close();
		conn.close();
		
		return res;
	}

	/**
	 * This method return list of all teachers who have a specific family name.
	 * 
	 * @see DAOPerson#getByFamilyName(String)
	 * 
	 * @param familyName Family name of teacher for whom there is a search
	 * @throw SQLException
	 * @return List of person who have a specific family name
	 * */
	public List<Teacher> getByFamilyName(String familyName) throws SQLException {

		List<Teacher> res = new ArrayList<Teacher>();
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "SELECT name, family_name FROM teachers WHERE family_name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, familyName);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			String name = rs.getString("name");
			familyName = rs.getString("family_name");
			
			res.add(new Teacher(name, familyName));
		}
		
		statement.close();
		conn.close();
		
		return res;
	}
}
