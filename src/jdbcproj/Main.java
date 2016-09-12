package jdbcproj;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbcproj.dao.togroup.*;
import jdbcproj.dao.toperson.*;
import jdbcproj.data.person.Person;
import jdbcproj.data.person.Student;
import jdbcproj.data.group.Group;

import jdbcproj.controller.*;

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
		DAOTeachers daoTeacher = new DAOTeachers();

		try{
			
			daoStud.delete("test1", "test1");
			daoStud.add("test1", "test1", "141");
			
			daoGroup.delete("testGroup1");
			daoGroup.delete("testGroup2");
			daoGroup.delete("testGroup3");
			daoGroup.add("testGroup1");
			daoGroup.add("testGroup2");
			daoGroup.add("testGroup3");

			daoTeacher.delete("test1", "test1");
			daoTeacher.add("test1", "test1");
		}catch(SQLException e){
			e.printStackTrace();
		}

	}
}