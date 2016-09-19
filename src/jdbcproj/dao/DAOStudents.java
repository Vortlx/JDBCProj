package jdbcproj.dao;


import java.sql.SQLException;
import java.util.List;

import jdbcproj.data.Student;


public interface DAOStudents {
	/**
	 * This method insert data into students table.
	 * 
	 *  @see DAOPerson#add(Person person)
	 *  
	 *  @param person Person who will added in students table
	 *  @throws SQLException
	 *  @return Nothing.
	 * */
	public void add(String name, String familyName, String groups) throws SQLException;
	
	/**
	 * This method update data into students table.
	 * 
	 * @see DAOPerson#update(Person oldPerson, Person newPerson)
	 * 
	 * @param oldPerson Person who was in table
	 * @param newPerson Person who replace old person
	 * @throws SQLException
	 * @return Nothing.
	 * */
	public void update(String oldName, String oldFamilyName, String newName, String newFamilyName) throws SQLException;
	
	public void updateGroup(String name, String familyName, String newGroupName) throws SQLException;

	/**
	 * This method delete data from students table.
	 * 
	 * @see DAOPerson#delete(Person person)
	 * 
	 * @param person Person who will deleted from students table
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
