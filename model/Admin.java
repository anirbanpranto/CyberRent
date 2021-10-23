package model;

public class Admin extends User {
    public Admin(int id, String username, String password){
        super(id, "fullname", username, password, "admin@email.com", "inactive", "admin", "phoneNumber");
    }
}
