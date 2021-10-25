package model;

public class Admin extends User {
    public Admin(int id, String password){
        super(id, "fullname", password, "admin@email.com", "admin", "phoneNumber");
    }
}
