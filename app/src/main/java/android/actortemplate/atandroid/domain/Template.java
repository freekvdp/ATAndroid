package android.actortemplate.atandroid.domain;

import java.util.ArrayList;
import java.util.List;

public class Template {

    private String rolename, description, projectkey, key$;
    private List<Actor> persons = new ArrayList<Actor>();

    public void Template(){}

    public void Template(String rolename, String description, String projectkey, ArrayList<Actor> persons){
        this.rolename = rolename;
        this.projectkey = projectkey;
        this.persons = persons;
        this.description = description;
    }

    public String getKey() {
        return key$;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String x) {
        this.rolename = x;
    }

    public String getProjectkey() {
        return projectkey;
    }

    public void setProjectkey(String x) {
        this.projectkey = x;
    }

    public List<Actor> getPersons(){ return this.persons; }

    public void addPerson(Actor a) {
        this.persons.add(a);
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String x) {
        description = x;
    }

}
