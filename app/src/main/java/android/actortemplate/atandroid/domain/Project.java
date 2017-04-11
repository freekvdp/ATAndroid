package android.actortemplate.atandroid.domain;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private String name;
    private List<String> analists = new ArrayList<String>();
    private List<String> members = new ArrayList<String>();

    private Boolean archived = true;
    public String key$;

    public Project(){
        Log.d("LOG", "Project aangemaakt");
    }

    public Project(String name, Boolean archived, ArrayList<String> analists, ArrayList<String> members){
        this.name = name;
        this.analists = analists;
        this.members = members;
        this.archived = archived;
    }

    public String getKey() {
        return key$;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAnalists() {
        return analists;
    }

    public void addAnalist(String analists) {
        this.analists.add(analists);
    }

    public List<String> getMembers() {
        return members;
    }

    public void addMember(String member) {
        this.members.add(member);
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public String toString(){
        return this.name+ " is archived: " + this.archived
                + " aantal analisten: " + this.analists.size()
                + " aantal teamleden: " + this.members.size();
    }
}
