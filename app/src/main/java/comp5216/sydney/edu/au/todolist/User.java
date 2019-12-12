package comp5216.sydney.edu.au.todolist;

/**
 * Created by ZhouShujian on 16/08/15.
 */
public class User {
    public String name;
    public String hometown;

    public User(String name, String hometown){
        this.name=name;
        this.hometown=hometown;
    }

    public User(String name){

        this.name=name;
    }

    public String getName(){

        return this.name;
    }

    public String getHometown(){
        return this.hometown;
    }
}
