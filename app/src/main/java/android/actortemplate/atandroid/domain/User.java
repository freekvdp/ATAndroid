package android.actortemplate.atandroid.domain;

public class User {
    private String name, surname, id, email, position, photo_url, $key, annotation;


    public void setName(String x){
        name = x;
    }
    public String getName() {
        return name;
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
    public void setAnnotation(String x){
        annotation = x;
    }
    public String getAnnotation(){
        return annotation;
    }
    public String getKey(){
        return $key;
    }
    public void setSurName(String x){
        surname = x;
    }
    public String getSurName() {
        return surname;
    }
    public String getId() {
        return id;
    }
}
