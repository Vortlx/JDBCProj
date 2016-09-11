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
	 *  @see DAOPerson#add(Person person)
	 *
	 *  @param person Person who will added in teachers table
	 *  @throws SQLException
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
	 * @see DAOPerson#update(Person oldPerson, Person newPerson)
	 *
	 * @param oldPerson Person who was in table
	 * @param newPerson Person who replace old person
	 * @throws SQLException
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
	 * @see DAOPerson#delete(Person person)
	 *
	 * @param person Person who will deleted from teachers table
	 * @throws SQLException
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
	 * This method return list of all teachers who have a specific name.
	 * 
	 * @param name Name of teacher for whom there is a search
	 * @throws SQLException
	 * @return List of teachers who have a specific name
	 * */
	public List<Teacher> getByName(String name) throws SQLException {

		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		List<Teacher> res = new ArrayList<Teacher>();
		
		String query = "SELECT teachers.id, teachers.name, teachers.family_name "
						+ "FROM teachers WHERE name = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			int teacherID = rs.getInt(1);
			String familyName = rs.getString(3);
			
			Teacher teacher = new Teacher(name, familyName);
			
			String getGroupQuery = "SELECT groups.name "
									+ "FROM groups INNER JOIN curator "
									+ "WHERE groups.id = curator.id_group "
									+ "AND curator.id_teacher = ?";
			
			PreparedStatement getGroupStat = conn.prepareStatement(getGroupQuery);
			getGroupStat.setInt(1, teacherID);
			ResultSet groupRS = getGroupStat.executeQuery();
			while(groupRS.next()){
				String group = groupRS.getString(1);
				teacher.addGroup(group);
			}
			
			res.add(teacher);
			
			groupRS.close();
			getGroupStat.close();
		}
		
		rs.close();
		statement.close();
		conn.close();
		
		return res;
	}

	/**
	 * This method return list of all teachers who have a specific family name.
	 * 
	 * @param familyName Family name of teacher for whom there is a search
	 * @throws SQLException
	 * @return List of teachers who have a specific family name
	 * */
	public List<Teacher> getByFamilyName(String familyName) throws SQLException {

		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		List<Teacher> res = new ArrayList<Teacher>();
		
		String query = "SELECT teachers.id, teachers.name, teachers.family_name "
						+ "FROM teachers WHERE family_name = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, familyName);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			int teacherID = rs.getInt(1);
			String name = rs.getString(2);
			
			Teacher teacher = new Teacher(name, familyName);
			
			String getGroupQuery = "SELECT groups.name "
									+ "FROM groups INNER JOIN curator "
									+ "WHERE groups.id = curator.id_group "
									+ "AND curator.id_teacher = ?";
			
			PreparedStatement getGroupStat = conn.prepareStatement(getGroupQuery);
			getGroupStat.setInt(1, teacherID);
			ResultSet groupRS = getGroupStat.executeQuery();
			while(groupRS.next()){
				String group = groupRS.getString(1);
				teacher.addGroup(group);
			}
			
			res.add(teacher);
			
			groupRS.close();
			getGroupStat.close();
		}
		
		rs.close();
		statement.close();
		conn.close();
		
		return res;
	}

	/**
	 * Method return list of teachers who have specific name and specific family name
	 *
	 * @param name Name of teacher
	 * @param familyName Family name of teachers
	 * @throws SQLException
	 * @return List of teachers who have specific name and specific family name
	 * */
	public List<Teacher> getTeacher(String name, String familyName) throws SQLException{

		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		List<Teacher> res = new ArrayList<Teacher>();
		
		String query = "SELECT teachers.id, teachers.name, teachers.family_name "
						+ "FROM teachers WHERE name = ? AND family_name = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.setString(2, familyName);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			int teacherID = rs.getInt(1);
			
			Teacher teacher = new Teacher(name, familyName);
			
			String getGroupQuery = "SELECT groups.name "
									+ "FROM groups INNER JOIN curator "
									+ "WHERE groups.id = curator.id_group "
									+ "AND curator.id_teacher = ?";
			
			PreparedStatement getGroupStat = conn.prepareStatement(getGroupQuery);
			getGroupStat.setInt(1, teacherID);
			ResultSet groupRS = getGroupStat.executeQuery();
			while(groupRS.next()){
				String group = groupRS.getString(1);
				teacher.addGroup(group);
			}
			
			res.add(teacher);
			
			groupRS.close();
			getGroupStat.close();
		}
		
		rs.close();
		statement.close();
		conn.close();
		
		return res;
	}
	

	/**
	 * This method return list of all teachers.
	 *
	 * @throws SQLException
	 * @return List of teachers
	 * */
	public List<Teacher> getAll() throws SQLException {

		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		List<Teacher> res = new ArrayList<Teacher>();
		
		String query = "SELECT teachers.id, teachers.name, teachers.family_name "
						+ "FROM teachers";
		
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			int teacherID = rs.getInt(1);
			String name = rs.getString(2);
			String familyName = rs.getString(3);
			Teacher teacher = new Teacher(name, familyName);
			
			String getGroupQuery = "SELECT groups.name "
									+ "FROM groups INNER JOIN curator "
									+ "WHERE groups.id = curator.id_group "
									+ "AND curator.id_teacher = ?";
			
			PreparedStatement getGroupStat = conn.prepareStatement(getGroupQuery);
			getGroupStat.setInt(1, teacherID);
			ResultSet groupRS = getGroupStat.executeQuery();
			while(groupRS.next()){
				String group = groupRS.getString(1);
				teacher.addGroup(group);
			}
			
			res.add(teacher);
			
			groupRS.close();
			getGroupStat.close();
		}
		
		rs.close();
		statement.close();
		conn.close();
		
		return res;
	}
}
