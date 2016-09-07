package jdbcproj.data.person;


/**
 * This abstract class describe a person. For example student or teacher.
 * 
 * @author Lebedev Alexander
 * @since 2016-09-05
 * */
public abstract class Person {
	
	private String name;
	private String familyName;
	
	/**
	 * This constructor create undefined person. 
	 * */
	public Person(){
		name = "";
		familyName = "";
	}
	
	/**
	 * This constructor create person which have specific name and family name
	 * 
	 * @param name Name of person
	 * @param familyName Family name of person
	 * */
	public Person(String name, String familyName){
		this.name = name;
		this.familyName = familyName;
	}
	
	/**
	 * This method return name of person
	 * 
	 * @return String Name of Person
	 * */
	public String getName() {
		return name;
	}
	
	/**
	 * This method change current name of person on new name.
	 * 
	 * @param name New name of person
	 * @return Nothing
	 * */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method return family name of person
	 * 
	 * @return String Family name of person
	 * */
	public String getFamilyName() {
		return familyName;
	}
	
	/**
	 * This method change current family name of person on new family name.
	 * 
	 * @param familyName New  family name of person
	 * @return Nothing
	 * */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
}
