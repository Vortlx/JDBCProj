package jdbcproj.data;

import java.util.ArrayList;

/**
 * This class describe teacher.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public class Teacher extends Person {

    private ArrayList<Group> groups;

    /**
     * This constructor create undefined teacher.
     * */
    public Teacher(){
        super();
        groups = new ArrayList<Group>();
    }

    /**
     * This constructor create specific teacher without groups.
     *
     * @param name Name of teacher
     * @param familyName Family name of teacher
     * */
    public Teacher(int id, String name, String familyName){
        super(id, name, familyName);
        groups = new ArrayList<Group>();
    }

    /**
     *  This constructor create specific teacher with group.
     *
     *  @param name Name of teacher
     *  @param familyName Family name of teacher
     *  @param groups List of groups which help teacher
     * */
    public Teacher(int id, String name, String familyName, ArrayList<Group> groups){
        super(id, name, familyName);
        this.groups = groups;
    }

    /**
     * This method return list of groups which help teacher.
     *
     * @return List List of group which help teacher
     * */
    public ArrayList<Group> getGroups() {
        return groups;
    }


    /**
     *  This method add new group in list of groups.
     *
     *  @param newGroup Name of new group
     *  @return Nothing
     * */
    public void addGroup(Group newGroup){
        groups.add(newGroup);
    }

    /**
     * This method change all groups.
     *
     * @param newGroups List of new groups
     * @return Nothing
     * */
    public void set(ArrayList<Group> newGroups){
        groups = newGroups;
    }

    /**
     *  This method change old group on new group.
     *
     *  @param oldGroup Number of old group
     *  @param newGroup Number of new group
     *  @return Nothing
     * */
    public void set(int oldGroup, Group newGroup){
        if(groups.contains(oldGroup)){
            int idx = groups.indexOf(oldGroup);
            groups.set(idx, newGroup);
        }
    }
}
