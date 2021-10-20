package Models;


/**
 * Write a description of class Owner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Owner extends User
{
    // instance variables - replace the example below with your own
    public Owner(String name, String userId, String email, String password){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
}
