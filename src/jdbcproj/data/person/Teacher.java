package jdbcproj.data.person;

import java.util.ArrayList;

/**
 * This class describe teacher.
 *
 * @author Lebedev Alexander
 * @since 2016-09-05
 * */
public class Teacher extends Person {

    private ArrayList<String> groups;

    /**
     * This constructor create undefined teacher.
     * */
    public Teacher(){
        super();
        groups = new ArrayList<String>();
    }

    /**
     * This constructor create specific teacher without groups.
     *
     * @param name Name of teacher
     * @param familyName Family name of teacher
     * */
    public Teacher(String name, String familyName){
        super(name, familyName);
        groups = new ArrayList<String>();
    }

    /**
     *  This constructor create specific teacher with group.
     *
     *  @param name Name of teacher
     *  @param familyName Family name of teacher
     *  @param groups List of groups which help teacher
     * */
    public Teacher(String name, String familyName, ArrayList<String> groups){
        super(name, familyName);
        this.groups = groups;
    }

    /**
     * This method return list of groups which help teacher.
     *
     * @return List List of group which help teacher
     * */
    public ArrayList<String> getGroups() {
        return groups;
    }


    /**
     *  This method add new group in list of groups.
     *
     *  @param newGroup Name of new group
     *  @return Nothing
     * */
    public void addGroup(String newGroup){
        groups.add(newGroup);
    }

    /**
     * This method change all groups.
     *
     * @param newGroups List of new groups
     * @return Nothing
     * */
    public void set(ArrayList<String> newGroups){
        groups = newGroups;
    }

    /**
     *  This method change old group on new group.
     *
     *  @param oldGroup Number of old group
     *  @param newGroup Number of new group
     *  @return Nothing
     * */
    public void set(int oldGroup, String newGroup){
        if(groups.contains(oldGroup)){
            int idx = groups.indexOf(oldGroup);
            groups.set(idx, newGroup);
        }
    }
}
