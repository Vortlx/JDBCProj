package jdbcproj.dao;


import java.sql.SQLException;
import java.util.List;
import jdbcproj.data.Group;

/**
 * This class define CRUD operation for groups table in database.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public interface DAOGroup {
	
	/**
	 * This method add new group into groups table .
	 *
	 * @param name Name of new group.
	 * @throw SQLException
	 * @return Nothing
	 * */
	public void add(String name) throws SQLException;
	
	/**
	 * This method set new name of table.
	 * 
	 * @param groupID ID of group
	 * @param newName New name of group
	 * @throw SQLException
	 * @return Nothing
	 * */
	public void update(int groupID, String newName) throws SQLException;
	
	/**
	 * This method delete group with specific name from groups table.
	 * 
	 * @param name Name of group which must be deleted.
	 * @throw SQLException
	 * @return Nothing
	 * */
	public void delete(String name) throws SQLException;
	
	/**
	 * Return group which have specific name
	 * 
	 * @param name Name of group
	 * @return Group
	 * */
	public Group getByName(String name) throws SQLException;
	
	/**
	 * This method return list of all existing groups.
	 * 
	 * @throw SQLException
	 * @return List of name (String) of all groups
	 * */
	public List<Group> getAll() throws SQLException;
}
