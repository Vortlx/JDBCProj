package jdbcproj.dao.daogroup;


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
 * @since 2016-09-19
 * */
public class DAOGroupConnection implements DAOGroup {
	
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
	 * @see DAOGroup#add(String)
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
	 * @see DAOGroup#update(String, String)
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
	 * @see DAOGroup#delete(String)
	 *
	 * @param name Name of group which must be deleted.
	 * @throw SQLException
	 * @return Nothing
	 * */
	@Override
	public void delete(String name) throws SQLException{
		Connection conn = DriverManager.getConnection(getProperty("URL"));

		String getStudentsQuery = "SELECT student_in_group.id_student " +
									"FROM student_in_group INNER JOIN groups " +
									"WHERE groups.name = ? AND " +
									"groups.id = student_in_group.id_group";
		PreparedStatement statement = conn.prepareStatement(getStudentsQuery);
		statement.setString(1, name);
		ResultSet rs = statement.executeQuery();

		String deleteStudentQuery = "DELETE FROM students WHERE id = ?";
		PreparedStatement deleteStudentStat = conn.prepareStatement(deleteStudentQuery);
		while(rs.next()){
			int studentID = rs.getInt(1);
			deleteStudentStat.setInt(1, studentID);
			deleteStudentStat.executeUpdate();
		}
		rs.close();
		deleteStudentStat.close();
		statement.close();

		String deleteGroupQuery = "DELETE FROM groups WHERE name = ?";
		PreparedStatement deleteGroupStat = conn.prepareStatement(deleteGroupQuery);
		deleteGroupStat.setString(1, name);
		deleteGroupStat.executeUpdate();

		deleteGroupStat.close();
		conn.close();
	}
	
	/**
	 * Return group which have specific name
	 *
	 * @see DAOGroup#getByName(String)
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

			query = "SELECT students.id, students.name, students.family_name "
					+ "FROM students INNER JOIN student_in_group "
					+ "WHERE students.id = student_in_group.id_student "
					+ "AND student_in_group.id_group = ?";
			PreparedStatement studStat = conn.prepareStatement(query);
			studStat.setInt(1, groupID);
			ResultSet studentsRS = studStat.executeQuery();

			while(studentsRS.next()){
				int studentID = studentsRS.getInt(1);
				String studentName = studentsRS.getString(2);
				String studentFamilyName = studentsRS.getString(3);

				res.addStudent(new Student(studentID, studentName, studentFamilyName, res));
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
	 * @see DAOGroup#getAll()
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