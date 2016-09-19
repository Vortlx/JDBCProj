package jdbcproj.dao.daostudents;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import jdbcproj.data.Group;
import jdbcproj.data.Person;
import jdbcproj.data.Student;

import java.util.ArrayList;

import static jdbcproj.resources.Resources.getProperty;

/**
 * This class implements CRUD operation for students table.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class DAOStudentsConnection implements DAOStudents{

	static{
		try{
			Class.forName(getProperty("DRIVER_CLASS"));
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	/**
	 * This method insert data into students table.
	 *
	 * @see DAOStudents#add(String, String, String)
	 *
	 *  @param name Name of student
	 *  @param familyName Family Name of student
	 *  @param groupName Name of group in which this student
	 *
	 *  @throws SQLException
	 *  @return Nothing.
	 * */
	@Override
	public void add(String name, String familyName, String groupName) throws SQLException {

		Connection conn = DriverManager.getConnection(getProperty("URL"));

		Group group;
		int studentID = -1;

		String query = "SELECT id FROM groups WHERE name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, groupName);
		ResultSet rs = statement.executeQuery();
		if(rs.next()){
			int groupID = rs.getInt(1);
			group = new Group(groupID, groupName);
		}else{
			rs.close();
			statement.close();
			conn.close();
			throw (new SQLException());
		}

		rs.close();
		statement.close();

		query = "INSERT INTO students (name, family_name) VALUES(?, ?)";
		statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.setString(2, familyName);
		statement.executeUpdate();
		statement.close();

		query = "SELECT id FROM students WHERE name = ? AND family_name = ?";
		statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.setString(2, familyName);
		rs = statement.executeQuery();
		if(rs.next()){
			studentID = rs.getInt(1);
		}

		rs.close();
		statement.close();

		query = "INSERT INTO student_in_group (id_group, id_student) VALUES(?, ?)";
		statement = conn.prepareStatement(query);
		statement.setInt(1, group.getId());
		statement.setInt(2, studentID);
		statement.executeUpdate();

		statement.close();
		conn.close();
	}

	/**
	 * This method update data into students table.
	 *
	 * @see DAOStudents#update(String, String, String, String)
	 *
	 * @param oldName Old name of student
	 * @param oldFamilyName Old family name of student
	 * @param newName New name of student
	 * @param newFamilyName New family name of student
	 *
	 * @throws SQLException
	 * @return Nothing.
	 * */
	@Override
	public void update(String oldName, String oldFamilyName, String newName, String newFamilyName) throws SQLException {

		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "UPDATE students SET name = ?, family_name = ? WHERE name = ? AND family_name = ?";
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
	 * Method change current group of student on new group
	 *
	 * @see DAOStudents#updateGroup(String, String, String)
	 *
	 * @param name Name of student
	 * @param familyName Family name of student
	 * @param newGroupName Name of new group
	 *
	 * @throws SQLException
	 * @return Nothing
	 * */
	@Override
	public void updateGroup(String name, String familyName, String newGroupName) throws SQLException{

		Connection conn = DriverManager.getConnection(getProperty("URL"));

		int studentID = -1;
		int newGroupID = -1;

		String getStudentIDQuery = "SELECT id FROM students "
									+ "WHERE name='" + name + "' AND family_name='" + familyName + "'";
		Statement getStudentIDStat = conn.createStatement();
		ResultSet studentRS = getStudentIDStat.executeQuery(getStudentIDQuery);
		if(studentRS.next()){
			studentID = studentRS.getInt(1);
		}else{
			studentRS.close();
			getStudentIDStat.close();
			conn.close();

			throw (new SQLException());
		}
		studentRS.close();
		getStudentIDStat.close();

		String getGroupIDQuery = "SELECT id FROM groups "
									+ "WHERE name='" + newGroupName + "'";
		Statement getGroupIDStat = conn.createStatement();
		ResultSet groupRS = getGroupIDStat.executeQuery(getGroupIDQuery);
		if(groupRS.next()){
		newGroupID = groupRS.getInt(1);
		}else{
			groupRS.close();
			getGroupIDStat.close();
			conn.close();

			throw (new SQLException());
		}
		groupRS.close();
		getGroupIDStat.close();

		String changeGroupQuery = "UPDATE student_in_group SET id_group = ? "
									+ "WHERE id_student=?";
		PreparedStatement changeGroupStat = conn.prepareStatement(changeGroupQuery);
		changeGroupStat.setInt(1, newGroupID);
		changeGroupStat.setInt(2, studentID);
		changeGroupStat.executeUpdate();

		changeGroupStat.close();
		conn.close();
	}

	/**
	 * This method delete data from students table.
	 *
	 * @see DAOStudents#delete(String, String)
	 *
	 * @param name Name of student
	 * @param familyName Family name of student
	 *
	 * @throws SQLException
	 * @return Nothing
	 * */
	@Override
	public void delete(String name, String familyName) throws SQLException {
		
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "DELETE FROM students WHERE name = ? AND family_name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.setString(2, familyName);
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}

	/**
	 * This method return list of all students.
	 *
	 * @see DAOStudents#getAll()
	 *
	 * @throws SQLException
	 * @return List of persons
	 * */
	@Override
	public List<Student> getAll() throws SQLException {
		Connection conn = DriverManager.getConnection(getProperty("URL"));

		List<Student> res = new ArrayList<Student>();

		String query = "SELECT students.id, students.name, students.family_name, tmp.name, tmp.id "
				+ "FROM students INNER JOIN (SELECT groups.name, groups.id, student_in_group.id_student "
				+ "FROM groups INNER JOIN student_in_group "
				+ "WHERE groups.id = student_in_group.id_group) AS tmp "
				+ "WHERE students.id = tmp.id_student";
		PreparedStatement statement = conn.prepareStatement(query);

		ResultSet rs = statement.executeQuery();
		while(rs.next()){
			int studentID = rs.getInt(1);
			String name = rs.getString(2);
			String familyName = rs.getString(3);
			String groupName = rs.getString(4);
			int groupID = rs.getInt(5);
			
			Group group = new Group(groupID, groupName);
			res.add(new Student(studentID, name, familyName, group));
		}

		rs.close();
		statement.close();
		conn.close();

		return res;
	}

	/**
	 * This method return list of all students who have a specific name.
	 *
	 * @see DAOStudents#getByName(String)
	 *
	 * @param name Name of student for whom there is a search
	 * @throws SQLException
	 * @return List of students who have a specific name
	 * */
	@Override
	public List<Student> getByName(String name) throws SQLException {

		Connection conn = DriverManager.getConnection(getProperty("URL"));

		List<Student> res = new ArrayList<Student>();

		String query = "SELECT students.id, students.name, students.family_name, tmp.name, tmp.id "
				+ "FROM students INNER JOIN (SELECT groups.name, groups.id, student_in_group.id_student "
				+ "FROM groups INNER JOIN student_in_group "
				+ "WHERE groups.id = student_in_group.id_group) AS tmp "
				+ "WHERE students.id = tmp.id_student "
				+ "AND students.name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);

		ResultSet rs = statement.executeQuery();
		while(rs.next()){
			int studentID = rs.getInt(1);
			String familyName = rs.getString(3);
			String groupName = rs.getString(4);
			int groupID = rs.getInt(5);
			
			Group group = new Group(groupID, groupName);
			res.add(new Student(studentID, name, familyName, group));
		}

		rs.close();
		statement.close();
		conn.close();

		return res;
	}

	/**
	 * This method return list of all students who have a specific family name.
	 *
	 * @see DAOStudents#getByFamilyName(String)
	 *
	 * @param familyName Family name of student for whom there is a search
	 * @throws SQLException
	 * @return List of students who have a specific family name
	 * */
	@Override
	public List<Student> getByFamilyName(String familyName) throws SQLException {

		Connection conn = DriverManager.getConnection(getProperty("URL"));

		List<Student> res = new ArrayList<Student>();

		String query = "SELECT students.id, students.name, students.family_name, tmp.name, tmp.id "
				+ "FROM students INNER JOIN (SELECT groups.name, groups.id, student_in_group.id_student "
				+ "FROM groups INNER JOIN student_in_group "
				+ "WHERE groups.id = student_in_group.id_group) AS tmp "
				+ "WHERE students.id = tmp.id_student "
				+ "AND students.family_name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, familyName);

		ResultSet rs = statement.executeQuery();
		while(rs.next()){
			int studentID = rs.getInt(1);
			String name = rs.getString(2);
			String groupName = rs.getString(4);
			int groupID = rs.getInt(5);
			
			Group group = new Group(groupID, groupName);
			res.add(new Student(studentID, name, familyName, group));
		}

		rs.close();
		statement.close();
		conn.close();

		return res;
	}

	/**
	 * Method return list of students who have specific name and specific family name
	 *
	 * @see DAOStudents#getStudent(String, String)
	 *
	 * @param name Name of student
	 * @param familyName Family name of student
	 * @throws SQLException
	 * @return List of students who have specific name and specific family name
	 * */
	@Override
	public List<Student> getStudent(String name, String familyName) throws SQLException{

		Connection conn = DriverManager.getConnection(getProperty("URL"));

		List<Student> res = new ArrayList<Student>();

		String query = "SELECT students.id, students.name, students.family_name, tmp.name, tmp.id "
				+ "FROM students INNER JOIN (SELECT groups.name, groups.id, student_in_group.id_student "
				+ "FROM groups INNER JOIN student_in_group "
				+ "WHERE groups.id = student_in_group.id_group) AS tmp "
				+ "WHERE students.id = tmp.id_student "
				+ "AND students.name = ? AND students.family_name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		statement.setString(2, familyName);

		ResultSet rs = statement.executeQuery();
		while(rs.next()){
			int studentID = rs.getInt(1);
			String groupName = rs.getString(4);
			int groupID = rs.getInt(5);
			
			Group group = new Group(groupID, groupName);
			res.add(new Student(studentID, name, familyName, group));
		}

		rs.close();
		statement.close();
		conn.close();

		return res;
	}
}