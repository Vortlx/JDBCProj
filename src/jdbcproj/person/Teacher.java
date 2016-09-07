package jdbcproj.person;

import java.util.ArrayList;

/**
 * This class describe teacher.
 *
 * @author Lebedev Alexander
 * @since 2016-09-05
 * */
public class Teacher extends Person {

    private ArrayList<Integer> groups;

    /**
     * This constructor create undefined teacher.
     * */
    public Teacher(){
        super();
        groups = new ArrayList<Integer>();
    }

    /**
     * This constructor create specific teacher without groups.
     *
     * @param name Name of teacher
     * @param familyName Family name of teacher
     * */
    public Teacher(String name, String familyName){
        super(name, familyName);
        groups = new ArrayList<Integer>();
    }

    /**
     *  This constructor create specific teacher with group.
     *
     *  @param name Name of teacher
     *  @param familyName Family name of teacher
     *  @param groups List of groups which help teacher
     * */
    public Teacher(String name, String familyName, ArrayList<Integer> groups){
        super(name, familyName);
        this.groups = groups;
    }

    /**
     * This method return list of groups which help teacher.
     *
     * @return List List of group which help teacher
     * */
    public ArrayList<Integer> getGroups() {
        return groups;
    }


    /**
     *  This method add new group in list of groups.
     *
     *  @param newGroup Name of new group
     *  @return Nothing
     * */
    public void addGroup(int newGroup){
        groups.add(newGroup);
    }

    /**
     * This method change all groups.
     *
     * @param newGroups List of new groups
     * @return Nothing
     * */
    public void set(ArrayList<Integer> newGroups){
        groups = newGroups;
    }

    /**
     *  This method change old group on new group.
     *
     *  @param oldGroup Number of old group
     *  @param newGroup Number of new group
     *  @return Nothing
     * */
    public void set(int oldGroup, int newGroup){
        if(groups.contains(oldGroup)){
            int idx = groups.indexOf(oldGroup);
            groups.set(idx, newGroup);
        }
    }
}
