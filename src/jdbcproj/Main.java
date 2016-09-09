package jdbcproj;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbcproj.dao.togroup.*;
import jdbcproj.dao.toperson.*;
import jdbcproj.data.person.Person;
import jdbcproj.data.person.Student;
import jdbcproj.data.group.Group;


/**
 * This Class using for testing other classes.
 * 
 * @author Lebedev Alexander
 * @since 2016-09-03
 * */
public class Main{
	public static void main(String[] args){

		DAOPerson daoStud = new DAOStudents();
		DAOGroup daoGroup = new DAOGroup();

		try{
			Student newStudent1 = new Student("test1", "test1", "141");
			Student newStudent2 = new Student("test2", "test2", "141");
			Student newStudent3 = new Student("test3", "test3", "141");
			
			daoStud.delete(newStudent1);
			daoStud.delete(newStudent2);
			daoStud.delete(newStudent3);
			
			daoStud.add(newStudent1);
			daoStud.add(newStudent2);
			daoStud.add(newStudent3);
			
		}catch(SQLException e){
			e.printStackTrace();
		}

	}
}