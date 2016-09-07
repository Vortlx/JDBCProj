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

		DAOGroup conn = new DAOGroup();

		try{

			List<Student> list = new ArrayList<Student>();
			list.add(new Student("name1", "familyName1", ""));
			list.add(new Student("name2", "familyName2", ""));
			list.add(new Student("name3", "familyName3", ""));
			list.add(new Student("name4", "familyName4", ""));
			list.add(new Student("name5", "familyName5", ""));
			
			Group group = new Group("testGroup", list);
			
			conn.delete(group.getName());
			conn.add(group);
			
			Group test = conn.getByName("testGroup");
			System.out.println("Group name is " + test.getName());
			System.out.println();
			System.out.println("Students:");
			for(Student i: test.getStudents()){
				System.out.println(i.getName() + " " + i.getFamilyName());
			}

		}catch(SQLException e){
			e.printStackTrace();
		}

	}
}