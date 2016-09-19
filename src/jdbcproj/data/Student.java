package jdbcproj.data;

/**
 * This class describe student.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class Student extends Person {

    private Group group;

    /**
     * This constructor create undefined student.
     * */
    public Student(){
        super();
        group = new Group();
    }

    /**
     * This constructor create specific student.
     *
     * @param name Name of a student
     * @param familyName Family name of student
     * @param group Group in which student is
     * */
    public Student(int id, String name, String familyName, Group group){
        super(id, name, familyName);
        this.group = group;
    }

    /**
     * This method return number of group in which student is.
     *
     * @return int Number of group in which student is
     * */
    public Group getGroup() {
        return group;
    }

    /**
     * This method change number of group in which student is on new number.
     *
     * @param group Number of new group
     * @return Nothing
     * */
    public void setGroup(Group group) {
        this.group = group;
    }
}