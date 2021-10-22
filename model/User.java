package model;
import java.util.UUID;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Node;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;

public abstract class User {
    private UUID id;
    private String fullName;
    private String userName;
    private String password;
    private String email;
    private String status;
    private String role;
    private String phoneNumber;

    public User(UUID id, String fullName, String userName, String password, String email, String status, String role, String phoneNumber){
      this.id = uuid;
      this.fullName = fullName;
      this.password = password;
      this.status = status;
      this.role = role;
      this.phoneNumber = phoneNumber;
    }

    public static User createUser(String fullName, String userName, String password, String email, String status, String role, String phoneNumber){
      UUID uuid=UUID.randomUUID();
      //create a user in database
      return new User(uuid, fullName, userName, password, email, status, role, phoneNumber);
    }

    public UUID getId(){
      return this.id;
    }
    //this model will have all the public data performed without login
}
