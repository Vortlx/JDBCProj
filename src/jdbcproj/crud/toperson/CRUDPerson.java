package jdbcproj.crud.toperson;

import java.util.List;
import java.sql.SQLException;

import jdbcproj.person.Person;

public interface CRUDPerson {
	
	public void add(String name, String familyName) throws SQLException ;
	public void update(String name, String familyName, String newName, String newFamilyName) throws SQLException ;
	public void delete(String name, String familyName) throws SQLException ;
	
	public List<Person> getAll() throws SQLException;
	public List<Person> getByName(String name) throws SQLException;
	public List<Person> getByFamilyName(String familyName) throws SQLException;
}
