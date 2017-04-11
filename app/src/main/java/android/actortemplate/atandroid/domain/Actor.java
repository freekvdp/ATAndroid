package android.actortemplate.atandroid.domain;

public class Actor {
    private String name, email, position, photo_url, phone, notes, templateid;


    public String getName() {
        return name;
    }
    public void setName(String x){
        name = x;
    }
    public void setPhoto(String x){
        photo_url = x;
    }
    public String getPhoto(){
        return photo_url;
    }
    public void setEmail(String x){
        email = x;
    }
    public String getEmail(){
        return email;
    }
    public void setPosition(String x){
        position = x;
    }
    public String getPosition(){
        return position;
    }
    public void setPhone(String x){
        phone = x;
    }
    public String getPhone(){
        return phone;
    }
    public void setNotes(String x){
        notes = x;
    }
    public String getNotes(){
        return notes;
    }
    public void setTemplateid(String x){
        templateid = x;
    }
    public String getTemplateid(){
        return templateid;
    }
}
