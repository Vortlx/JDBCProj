package jdbcproj.data.group;


import java.util.List;
import java.util.ArrayList;

import jdbcproj.data.person.Student;


/**
 * This class describe groups in university
 * 
 * @author Lebedev Alexander
 * @since 2016-09-07
 * */
public class Group {

	private String name;
	private List<Student> students;
	
	/**
	 * Create undefined group
	 * */
	public Group(){
		name = "";
		students = new ArrayList<Student>();
	}
	
	/**
	 * Create specific group without students.
	 * 
	 * @param name Name of group
	 * */
	public Group(String name){
		this.name = name;
		students = new ArrayList<Student>();
	}
	
	/**
	 * Create specific group with list of students.
	 * 
	 * @param name Name of group
	 * @param students List of students which in this group
	 * */
	public Group(String name, List<Student> students){
		this.name = name;
		this.students = students;
	}

	public void addStudent(Student student){
		students.add(student);
	}
	
	/**
	 * Return name of group
	 * 
	 * @return String Name of group
	 * */
	public String getName() {
		return name;
	}

	/**
	 * Change old name on new name of group
	 * 
	 * @param name New name of group
	 * @return Nothing
	 * */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return list of students which in this group
	 * 
	 * @return List List of students
	 * */	
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * Change old list students on new list students
	 * 
	 * @param students List of students
	 * @return Nothing
	 * */
	public void setStudents(List<Student> students) {
		this.students = students;
	}
}