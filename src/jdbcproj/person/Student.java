package jdbcproj.person;

/**
 * This class describe student.
 *
 * @author Lebedev Alexander
 * @since 2016-09-05
 * */
public class Student extends Person {

    private int group;

    /**
     * This constructor create undefined student.
     * */
    public Student(){
        super();
        group = -1;
    }

    /**
     * This constructor create specific student.
     *
     * @param name Name of a student
     * @param familyName Family name of student
     * @param group Group in which student is
     * */
    public Student(String name, String familyName, int group){
        super(name, familyName);
        this.group = group;
    }

    /**
     * This method return number of group in which student is.
     *
     * @return int Number of group in which student is
     * */
    public int getGroup() {
        return group;
    }

    /**
     * This method change number of group in which student is on new number.
     *
     * @param group Number of new group
     * @return Nothing
     * */
    public void setGroup(int group) {
        this.group = group;
    }
}
