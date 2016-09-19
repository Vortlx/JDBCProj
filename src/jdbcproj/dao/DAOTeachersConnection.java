package jdbcproj.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import jdbcproj.data.Group;
import jdbcproj.data.Person;
import jdbcproj.data.Teacher;

import java.util.ArrayList;

import static jdbcproj.resources.Resources.getProperty;

/**
 * This class implements CRUD operation for teachers table.
 * 
 * @author Lebedev Alexander
 * @since 2016-09-07
 * */
public class DAOTeachersConnection implements DAOTeachers{

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
	public void add(String name, String familyName, Group... groups) throws SQLException {

		Connection conn = DriverManager.getConnection(getProperty("URL"));

		String query = "INSERT INTO teachers (name, family_name) VALUES(?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.setString(2, familyName);
		statement.executeUpdate();
		statement.close();

		query = "INSERT INTO curator(id_group, id_teacher) VALUES(?, ?)";
		statement = conn.prepareStatement(query);

		// Get teacher ID
		int teacherID = -1;

		String teacherIDQuery = "SELECT id FROM teachers WHERE name = '" + name +
									"' AND family_name = '" + familyName + "'";
		Statement getIDTeacher = conn.createStatement();
		ResultSet rs = getIDTeacher.executeQuery(teacherIDQuery);
		if(rs.next()){
			teacherID = rs.getInt(1);
		}

		//Get group ID
		String groupIDQuery = "SELECT id FROM groups WHERE name = ?";
		PreparedStatement getIDGroup = conn.prepareStatement(groupIDQuery);
		for(Group group: groups){
			int groupID = -1;
			getIDGroup.setString(1, group.getName());
			rs = getIDGroup.executeQuery(groupIDQuery);

			if(rs.next()){
				groupID = rs.getInt(1);
			}

			statement.setInt(groupID, teacherID);
			statement.executeUpdate();
		}
		rs.close();
		getIDTeacher.close();
		getIDGroup.close();
		statement.close();
		conn.close();
	}

	@Override
	public void addGroup(String name, String familyName, String groupName) throws SQLException{

		Connection conn = DriverManager.getConnection(getProperty("URL"));

		int teacherID = -1;
		int groupID = -1;

		String getTeacherIDQuery = "SELECT id FROM teachers " +
									"WHERE name = '" + name + "' AND family_name = '" + familyName + "'";
		Statement getTeacherIDStat = conn.createStatement();
		ResultSet teacherRS = getTeacherIDStat.executeQuery(getTeacherIDQuery);
		if(teacherRS.next()){
			teacherID = teacherRS.getInt(1);
		}else{
			teacherRS.close();
			getTeacherIDStat.close();
			conn.close();

			throw (new SQLException());
		}
		teacherRS.close();
		getTeacherIDStat.close();

		String getGroupIDQuery = "SELECT id FROM groups " +
									"WHERE name = '" + groupName + "'";
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

		String query = "INSERT INTO curator(id_group, id_teacher) VALUES(?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, groupID);
		statement.setInt(2, teacherID);
		statement.executeUpdate();

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
	public void update(String oldName, String oldFamilyName, String newName, String newFamilyName) throws SQLException {

		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "UPDATE teachers SET name = ?, family_name = ? WHERE name = ? AND family_name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(3, oldName);
		statement.setString(4, oldFamilyName);
		statement.setString(1, newName);
		statement.setString(2, newFamilyName);
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
	public void delete(String name, String familyName) throws SQLException {
		
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "DELETE FROM teachers WHERE name = ? AND family_name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.setString(2, familyName);
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}

	@Override
	public void deleteCurator(String name, String familyName, String groupName) throws SQLException{

		Connection conn = DriverManager.getConnection(getProperty("URL"));

		int teacherID = -1;
		int groupID = -1;

		String getTeacherIDQuery = "SELECT id FROM teachers " +
				"WHERE name = '" + name + "' AND family_name = '" + familyName + "'";
		Statement getTeacherIDStat = conn.createStatement();
		ResultSet teacherRS = getTeacherIDStat.executeQuery(getTeacherIDQuery);
		if(teacherRS.next()){
			teacherID = teacherRS.getInt(1);
		}else{
			teacherRS.close();
			getTeacherIDStat.close();
			conn.close();

			throw (new SQLException());
		}
		teacherRS.close();
		getTeacherIDStat.close();

		String getGroupIDQuery = "SELECT id FROM groups " +
				"WHERE name = '" + groupName + "'";
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

		String query = "DELETE FROM curator " +
						"WHERE id_group = ? AND id_teacher = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, groupID);
		statement.setInt(2, teacherID);
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
	@Override
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
			
			Teacher teacher = new Teacher(teacherID, name, familyName);
			
			String getGroupQuery = "SELECT groups.name, groups.id "
									+ "FROM groups INNER JOIN curator "
									+ "WHERE groups.id = curator.id_group "
									+ "AND curator.id_teacher = ?";
			
			PreparedStatement getGroupStat = conn.prepareStatement(getGroupQuery);
			getGroupStat.setInt(1, teacherID);
			ResultSet groupRS = getGroupStat.executeQuery();
			while(groupRS.next()){
				String groupName = groupRS.getString(1);
				int groupID = groupRS.getInt(2);
				
				Group group = new Group(groupID, groupName);
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
	@Override
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
			
			Teacher teacher = new Teacher(teacherID, name, familyName);
			
			String getGroupQuery = "SELECT groups.name, groups.id "
									+ "FROM groups INNER JOIN curator "
									+ "WHERE groups.id = curator.id_group "
									+ "AND curator.id_teacher = ?";
			
			PreparedStatement getGroupStat = conn.prepareStatement(getGroupQuery);
			getGroupStat.setInt(1, teacherID);
			ResultSet groupRS = getGroupStat.executeQuery();
			while(groupRS.next()){
				String groupName = groupRS.getString(1);
				int groupID = groupRS.getInt(2);
				
				Group group = new Group(groupID, groupName);
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
	@Override
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
			
			Teacher teacher = new Teacher(teacherID, name, familyName);
			
			String getGroupQuery = "SELECT groups.name, groups.id "
									+ "FROM groups INNER JOIN curator "
									+ "WHERE groups.id = curator.id_group "
									+ "AND curator.id_teacher = ?";
			
			PreparedStatement getGroupStat = conn.prepareStatement(getGroupQuery);
			getGroupStat.setInt(1, teacherID);
			ResultSet groupRS = getGroupStat.executeQuery();
			while(groupRS.next()){
				String groupName = groupRS.getString(1);
				int groupID = groupRS.getInt(2);
				
				Group group = new Group(groupID, groupName);
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
	@Override
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
			Teacher teacher = new Teacher(teacherID, name, familyName);
			
			String getGroupQuery = "SELECT groups.name, groups.id "
									+ "FROM groups INNER JOIN curator "
									+ "WHERE groups.id = curator.id_group "
									+ "AND curator.id_teacher = ?";
			
			PreparedStatement getGroupStat = conn.prepareStatement(getGroupQuery);
			getGroupStat.setInt(1, teacherID);
			ResultSet groupRS = getGroupStat.executeQuery();
			while(groupRS.next()){
				String groupName = groupRS.getString(1);
				int groupID = groupRS.getInt(2);
				
				Group group = new Group(groupID, groupName);
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
	
	@Override
	public List<Teacher> getByGroup(String groupName) throws SQLException{
		
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		int groupID = -1;
		List<Teacher> res = new ArrayList<Teacher>();
		
		String getGroupIDQuery = "SELECT id FROM groups WHERE name = '" + groupName + "'";
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
		
		String getTeacherIDQuery = "SELECT id_teacher FROM curator "
										+ "WHERE id_group = " + groupID;
		Statement getTeacherIDStat = conn.createStatement();
		ResultSet teacherRS = getTeacherIDStat.executeQuery(getTeacherIDQuery);
		
		String getTeachers = "SELECT name, family_name FROM teachers WHERE id = ?";
		PreparedStatement statement = conn.prepareStatement(getTeachers);
		while(teacherRS.next()){
			int teacherID = teacherRS.getInt(1);
			statement.setInt(1, teacherID);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()){
				String teacherName = rs.getString(1);
				String teacherFamilyName = rs.getString(2);
				
				res.add(new Teacher(teacherID, teacherName, teacherFamilyName));
			}
			
			rs.close();
		}
		
		teacherRS.close();
		getTeacherIDStat.close();
		statement.close();
		conn.close();
		
		return res;
	}
}
