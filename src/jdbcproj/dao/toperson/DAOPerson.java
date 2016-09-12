package jdbcproj.dao.toperson;


import java.sql.SQLException;

/**
 * This interface contain CRUD methods for table which contain something person. 
 * 
 * @author Lebedev Alexander
 * @since 2016-09-07
 * */
public interface DAOPerson {
	
	/**
	 * This method insert data into person table. For example into students.
	 * 
	 *  @param name Name of person.
	 *  @param familyName Family name of person.
	 *  @throw SQLException
	 *  @return Nothing.
	 * */
	public void add(String name, String familyName, String... groups) throws SQLException ;
	
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
	public void update(String oldName, String oldFamilyName, String newName, String newFamilyName) throws SQLException ;
	
	/**
	 * This method delete data from person table. For example from students.
	 * 
	 * @param name Name of person.
	 * @param familyName Family name of person.
	 * @throw SQLException
	 * @return Nothing
	 * */
	public void delete(String name, String familyName) throws SQLException ;
}
