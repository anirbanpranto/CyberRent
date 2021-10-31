package model;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Node;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;

public abstract class User {
    protected int Id;
    protected String fullName;
    protected String password;
    protected String email;
    protected String role;
    protected String phoneNumber;

    public User(int Id, String fullName, String password, String email, String role, String phoneNumber){
        this.Id = Id;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId(){
        return this.Id;
    }
    
    public String getName(){
        return this.fullName;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getRole(){
        return this.role;
    }
   
    public String getPhone(){
        return this.phoneNumber;
    }
    
    public void setName(String fullname){
        this.fullName = fullname;
    }

    public void setPassword(String password){
        this.password = password;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    //this model will have all the public data performed without login
}
