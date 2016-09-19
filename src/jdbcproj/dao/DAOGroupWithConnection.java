package jdbcproj.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import jdbcproj.data.Group;
import jdbcproj.data.Student;

import static jdbcproj.resources.Resources.getProperty;


/**
 * This class implements CRUD operation for groups table in database.
 * 
 * @author Lebedev Alexander
 * @since 2016-09-07
 * */
public class DAOGroupWithConnection implements DAOGroup {
	
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
	@Override
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
	@Override
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
	@Override
	public void delete(String name) throws SQLException{

		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		int groupID = -1;
		
		String getGroupIDQuery = "SELECT id FROM groups WHERE name = '" + name + "'";
		Statement getGroupIDStat = conn.createStatement();
		ResultSet groupRS = getGroupIDStat.executeQuery(getGroupIDQuery);
		if(groupRS.next()){
			groupID = groupRS.getInt(1);
		}else{
			groupRS.close();
			getGroupIDStat.close();
			conn.close();
			throw (new SQLException());
		}
		groupRS.close();
		getGroupIDStat.close();
		
		String getStudentIDQuery = "SELECT id_student FROM student_in_group "
									+ "WHERE id_group = '" + groupID + "'";
		Statement getStudentIDStat = conn.createStatement();
		ResultSet studentRS = getStudentIDStat.executeQuery(getStudentIDQuery);
		
		String deleteStudentQuery = "DELETE FROM students WHERE id = ?";
		PreparedStatement deleteStudentStat = conn.prepareStatement(deleteStudentQuery);
		while(studentRS.next()){
			deleteStudentStat.setInt(1, studentRS.getInt(1));
			deleteStudentStat.executeUpdate();
		}
		studentRS.close();
		deleteStudentStat.close();
		getStudentIDStat.close();
		
		String deleteGroupQuery = "DELETE FROM groups WHERE id = '" + groupID + "'";
		Statement statement = conn.createStatement();
		statement.executeUpdate(deleteGroupQuery);
		
		statement.close();
		conn.close();
	}
	
	/**
	 * Return group which have specific name
	 * 
	 * @param name Name of group
	 * @return Group
	 * */
	@Override
	public Group getByName(String name) throws SQLException{
		
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		Group res;
		String query = "SELECT id FROM groups "
						+ "WHERE name = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		ResultSet rs = statement.executeQuery();

		if(rs.next()){
			int groupID = rs.getInt(1);
			res = new Group(groupID, name);
			
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
				
				res.addStudent(new Student(studentName, studentFamilyName, res));
			}
			
			studentsRS.close();
			studStat.close();
		}else{
			rs.close();
			statement.close();
			conn.close();
			
			throw (new SQLException());
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
	@Override
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