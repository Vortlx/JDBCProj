package jdbcproj.crud.toperson;

import java.util.List;
import java.sql.SQLException;

import jdbcproj.person.Person;

/**
 * This interface contain CRUD methods for table which contain something person. 
 * 
 * @author Lebedev Alexander
 * @since 2016-09-03
 * */
public interface CRUDPerson {
	
	/**
	 * This method insert data into person table. For example into students.
	 * 
	 *  @param name Name of person.
	 *  @param familyName Family name of person.
	 *  @throw SQLException
	 *  @return Nothing.
	 * */
	public void add(String name, String familyName) throws SQLException ;
	
	/**
	 * This method update data into person table. For example into students.
	 * 
	 * @param name Old name of person.
	 * @param familyName Old family name of person.
	 * @param newName New name of person.
	 * @param newFamilyName New family name of person.
	 * @throw SQLException
	 * @return Nothing.
	 * */
	public void update(String name, String familyName, String newName, String newFamilyName) throws SQLException ;
	
	/**
	 * This method delete data from person table. For example from students.
	 * 
	 * @param name Name of person.
	 * @param familyName Family name of person.
	 * @throw SQLException
	 * @return Nothing
	 * */
	public void delete(String name, String familyName) throws SQLException ;
	
	
	/**
	 * This method return list of all persons.
	 * 
	 * @throw SQLException
	 * @return List of persons
	 * */
	public List<Person> getAll() throws SQLException;
	
	/**
	 * This method return list of all persons who have a specific name.
	 * 
	 * @param name Name of person for whom there is a search
	 * @throw SQLException
	 * @return List of person who have a specific name
	 * */
	public List<Person> getByName(String name) throws SQLException;
	
	/**
	 * This method return list of all persons who have a specific family name.
	 * 
	 * @param familyName Family name of person for whom there is a search
	 * @throw SQLException
	 * @return List of person who have a specific family name
	 * */
	public List<Person> getByFamilyName(String familyName) throws SQLException;
}
