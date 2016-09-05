package jdbcproj;

import java.sql.SQLException;
import java.util.List;
import jdbcproj.crud.toperson.*;
import jdbcproj.crud.togroup.*;
import jdbcproj.person.Person;

/**
 * This Class using for testing other classes.
 * 
 * @author Lebedev Alexander
 * @since 2016-09-03
 * */
public class Main{
	public static void main(String[] args){

		CRUDPerson conn = new CRUDStudents();

		try{

			conn.add("test", "test");
			List<Person> list = conn.getByName("test");

			for(Person i: list){
				System.out.println(i.getName() + " " + i.getFamilyName());
			}

		}catch(SQLException e){
			e.printStackTrace();
		}

	}
}