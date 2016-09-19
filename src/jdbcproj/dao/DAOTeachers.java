package jdbcproj.dao;


import java.sql.SQLException;
import java.util.List;

import jdbcproj.data.Group;
import jdbcproj.data.Teacher;


public interface DAOTeachers {
	
	/**
	 * This method insert data into teachers table.
	 *
	 *  @see DAOPerson#add(Person person)
	 *
	 *  @param person Person who will added in teachers table
	 *  @throws SQLException
	 *  @return Nothing.
	 * */
	public void add(String name, String familyName, Group... groups) throws SQLException;

	public void addGroup(String name, String familyName, String groupName) throws SQLException;
	
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
	public void update(String oldName, String oldFamilyName, String newName, String newFamilyName) throws SQLException;

	/**
	 * This method delete data from teachers table.
	 *
	 * @see DAOPerson#delete(Person person)
	 *
	 * @param person Person who will deleted from teachers table
	 * @throws SQLException
	 * @return Nothing
	 * */
	public void delete(String name, String familyName) throws SQLException;

	public void deleteCurator(String name, String familyName, String groupName) throws SQLException;

	/**
	 * This method return list of all teachers who have a specific name.
	 * 
	 * @param name Name of teacher for whom there is a search
	 * @throws SQLException
	 * @return List of teachers who have a specific name
	 * */
	public List<Teacher> getByName(String name) throws SQLException;

	/**
	 * This method return list of all teachers who have a specific family name.
	 * 
	 * @param familyName Family name of teacher for whom there is a search
	 * @throws SQLException
	 * @return List of teachers who have a specific family name
	 * */
	public List<Teacher> getByFamilyName(String familyName) throws SQLException;

	/**
	 * Method return list of teachers who have specific name and specific family name
	 *
	 * @param name Name of teacher
	 * @param familyName Family name of teachers
	 * @throws SQLException
	 * @return List of teachers who have specific name and specific family name
	 * */
	public List<Teacher> getTeacher(String name, String familyName) throws SQLException;
	

	/**
	 * This method return list of all teachers.
	 *
	 * @throws SQLException
	 * @return List of teachers
	 * */
	public List<Teacher> getAll() throws SQLException;
	
	public List<Teacher> getByGroup(String groupName) throws SQLException;
}
