package jdbcproj.dao.toperson;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import jdbcproj.data.person.Person;
import jdbcproj.data.person.Student;

import java.util.ArrayList;

import static jdbcproj.resources.Resources.getProperty;

/**
 * This class implements CRUD operation for students table.
 * 
 * @author Lebedev Alexander
 * @since 2016-09-07
 * */
public class DAOStudents implements DAOPerson{

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
	 *  @see DAOPerson#add(String, String)
	 *  
	 *  @param name Name of student.
	 *  @param familyName Family name of student.
	 *  @throw SQLException
	 *  @return Nothing.
	 * */
	@Override
	public void add(Person person) throws SQLException {
		
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		Student student = (Student) person;
		String query = "INSERT INTO students (name, family_name) VALUES (?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, student.getName());
		statement.setString(2, student.getFamilyName());
		statement.executeUpdate();
		statement.close();
		
		int studentID = -1;
		int groupID = -1;
		
		Statement getIDStatement = conn.createStatement();
		String studentIDQuery = "SELECT id FROM students WHERE name = '" + student.getName()
									+ "' AND family_name = '" + student.getFamilyName() + "'";
		ResultSet rs = getIDStatement.executeQuery(studentIDQuery);
		if(rs.next()){
			studentID = rs.getInt(1);
		}
		
		
		String groupIDQuery = "SELECT id FROM groups WHERE name = '" + student.getGroup() + "'";
		rs = getIDStatement.executeQuery(groupIDQuery);
		if(rs.next()){
			groupID = rs.getInt(1);
		}
		rs.close();
		
		query = "INSERT INTO student_in_group (id_student, id_group) VALUES (?, ?)";
		statement = conn.prepareStatement(query);
		statement.setInt(1, studentID);
		statement.setInt(2, groupID);
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}

	/**
	 * This method update data into students table.
	 * 
	 * @see DAOPerson#update(String, String, String, String)
	 * 
	 * @param name Old name of student.
	 * @param familyName Old family name of student.
	 * @param newName New name of student.
	 * @param newFamilyName New family name of student.
	 * @throw SQLException
	 * @return Nothing.
	 * */
	@Override
	public void update(Person oldPerson, Person newPerson) throws SQLException {

		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "UPDATE students SET name = ?, family_name = ? WHERE name = ? AND family_name = ?";
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
	 * This method delete data from students table.
	 * 
	 * @see DAOPerson#delete(String, String)
	 * 
	 * @param name Name of student.
	 * @param familyName Family name of student.
	 * @throw SQLException
	 * @return Nothing
	 * */
	@Override
	public void delete(Person person) throws SQLException {
		
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "DELETE FROM students WHERE name = ? AND family_name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, person.getName());
		statement.setString(2, person.getFamilyName());
		statement.executeUpdate();
		
		statement.close();
		conn.close();
	}

	/**
	 * This method return list of all students.
	 * 
	 * @see DAOPerson#getAll()
	 * 
	 * @throw SQLException
	 * @return List of persons
	 * */
	public List<Student> getAll() throws SQLException {
		
		List<Student> res = new ArrayList<Student>();
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "SELECT name, family_name FROM students";
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			String name = rs.getString("name");
			String familyName = rs.getString("family_name");
			
			res.add(new Student(name, familyName, ""));
		}
		
		statement.close();
		conn.close();
		
		return res;
	}

	/**
	 * This method return list of all students who have a specific name.
	 * 
	 * @see DAOPerson#getByName(String)
	 * 
	 * @param name Name of student for whom there is a search
	 * @throw SQLException
	 * @return List of person who have a specific name
	 * */
	public List<Student> getByName(String name) throws SQLException {

		List<Student> res = new ArrayList<Student>();
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "SELECT name, family_name FROM students WHERE name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			name = rs.getString("name");
			String familyName = rs.getString("family_name");
			
			res.add(new Student(name, familyName, ""));
		}
		
		statement.close();
		conn.close();
		
		return res;
	}

	/**
	 * This method return list of all students who have a specific family name.
	 * 
	 * @see DAOPerson#getByFamilyName(String)
	 * 
	 * @param familyName Family name of student for whom there is a search
	 * @throw SQLException
	 * @return List of person who have a specific family name
	 * */
	public List<Student> getByFamilyName(String familyName) throws SQLException {

		List<Student> res = new ArrayList<Student>();
		Connection conn = DriverManager.getConnection(getProperty("URL"));
		
		String query = "SELECT name, family_name FROM students WHERE family_name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, familyName);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			String name = rs.getString("name");
			familyName = rs.getString("family_name");
			
			res.add(new Student(name, familyName, ""));
		}
		
		statement.close();
		conn.close();
		
		return res;
	}
}