package Models;


/**
 * Abstract class User - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class User
{
    // instance variables - replace the example below with your own
    protected String userId;
    protected String email;
    protected String password;
    protected String name;
    
    public abstract void setName(String name);
    
    public abstract String getName();
}
