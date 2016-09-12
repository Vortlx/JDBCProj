package jdbcproj.dao.togroup;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import jdbcproj.data.group.Group;
import jdbcproj.data.person.Student;

import static jdbcproj.resources.Resources.getProperty;


/**
 * This class implements CRUD operation for groups table in database.
 * 
 * @author Lebedev Alexander
 * @since 2016-09-07
 * */
public class DAOGroup {
	
	static{
		try{
			Class.forName(getProperty("DRIVER_CLASS"));
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	/**
	 * This method insert data into groups table .
	 *
	 * @param name Name of new group.
	 * @throw SQLException
	 * @return Nothing
	 * */
	public void add(String name) throws SQLException{

		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "INSERT INTO groups (name) VALUES ('" + name + "')";
		Statement statement = conn.createStatement();
		statement.executeUpdate(query);
		
		statement.close();
		conn.close();
	}
	
	/**
	 * This method update data in groups table.
	 * 
	 * @param name Old name of group
	 * @param newName New name of group
	 * @throw SQLException
	 * @return Nothing
	 * */
	public void update(String name, String newName) throws SQLException{
		
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "UPDATE groups SET name = ? WHERE name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(2, name);
		statement.setString(1, newName);
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}
	
	/**
	 * This method delete data from groups table.
	 * 
	 * @param name Name of group which must be deleted.
	 * @throw SQLException
	 * @return Nothing
	 * */
	public void delete(String name) throws SQLException{

		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "DELETE FROM groups WHERE name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}
	

	/**
	 * Return group which have specific name
	 * 
	 * @param name Name of group
	 * @return Group
	 * */
	public Group getByName(String name) throws SQLException{
		
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "SELECT id FROM groups "
						+ "WHERE name = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		ResultSet rs = statement.executeQuery();

		
		Group res = new Group(name);
		
		if(rs.next()){
			int groupID = rs.getInt(1);
			
			query = "SELECT students.name, students.family_name "
					+ "FROM students INNER JOIN student_in_group "
					+ "WHERE students.id = student_in_group.id_student "
					+ "AND student_in_group.id_group = ?";
			PreparedStatement studStat = conn.prepareStatement(query);
			studStat.setInt(1, groupID);
			ResultSet studentsRS = studStat.executeQuery();
			
			while(studentsRS.next()){
				String studentName = studentsRS.getString(1);
				String studentFamilyName = studentsRS.getString(2);
				
				res.addStudent(new Student(studentName, studentFamilyName, name));
			}
			
			studentsRS.close();
			studStat.close();
		}
		
		rs.close();
		statement.close();
		conn.close();
		
		return res;
	}
	
	/**
	 * This method return list of all existing groups.
	 * 
	 * @throw SQLException
	 * @return List of name (String) of all groups
	 * */
	public List<Group> getAll() throws SQLException{
		
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "SELECT name FROM groups";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		List<Group> res = new ArrayList<Group>();
		
		while(rs.next()){
			Group newGroup = getByName(rs.getString("name"));
			res.add(newGroup);
		}
		
		rs.close();
		statement.close();
		conn.close();
		
		return res;
	}
}