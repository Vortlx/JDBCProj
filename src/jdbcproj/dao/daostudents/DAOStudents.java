package jdbcproj.dao.daostudents;


import java.sql.SQLException;
import java.util.List;

import jdbcproj.data.Student;

/**
 * This class define CRUD operation for students table.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public interface DAOStudents {

	/**
	 * This method insert data into students table.
	 * 
	 *
	 *  @param name Name of student
	 *  @param familyName Family Name of student
	 *  @param groupName Name of group in which this student
	 *
	 *  @throws SQLException
	 *  @return Nothing.
	 * */
	public void add(String name, String familyName, String groupName) throws SQLException;
	
	/**
	 * This method update data into students table.
	 * 
	 * @param oldName Old name of student
	 * @param oldFamilyName Old family name of student
	 * @param newName New name of student
	 * @param newFamilyName New family name of student
	 *
	 * @throws SQLException
	 * @return Nothing.
	 * */
	public void update(String oldName, String oldFamilyName, String newName, String newFamilyName) throws SQLException;


	/**
	 * Method change current group of student on new group
	 *
	 * @param name Name of student
	 * @param familyName Family name of student
	 * @param newGroupName Name of new group
	 *
	 * @throws SQLException
	 * @return Nothing
	 * */
	public void updateGroup(String name, String familyName, String newGroupName) throws SQLException;

	/**
	 * This method delete data from students table.
	 * 
	 * @param name Name of student
	 * @param familyName Family name of student
	 *
	 * @throws SQLException
	 * @return Nothing
	 * */
	public void delete(String name, String familyName) throws SQLException;

	/**
	 * This method return list of all students.
	 * 
	 * @throws SQLException
	 * @return List of persons
	 * */
	public List<Student> getAll() throws SQLException;

	/**
	 * This method return list of all students who have a specific name.
	 *
	 * @param name Name of student for whom there is a search
	 * @throws SQLException
	 * @return List of students who have a specific name
	 * */
	public List<Student> getByName(String name) throws SQLException;

	/**
	 * This method return list of all students who have a specific family name.
	 * 
	 * @param familyName Family name of student for whom there is a search
	 * @throws SQLException
	 * @return List of students who have a specific family name
	 * */
	public List<Student> getByFamilyName(String familyName) throws SQLException ;
	
	/**
	 * Method return list of students who have specific name and specific family name
	 *
	 * @param name Name of student
	 * @param familyName Family name of student
	 * @throws SQLException
	 * @return List of students who have specific name and specific family name
	 * */
	public List<Student> getStudent(String name, String familyName) throws SQLException;
}
