package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.GlobalState;
import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;

public class view_profileController implements Initializable{

    @FXML
    private Label FullName_Profile;
    @FXML
    private Label PhoneNumber_Profile;

    public void initialize(URL location, ResourceBundle resource){

    }

    @FXML
    public void displayProfile(String fullName, String phoneNumber){
        FullName_Profile.setText(fullName);
        PhoneNumber_Profile.setText(phoneNumber);
    }

    public void edit(ActionEvent event) throws IOException {
        Stage mainStage = GlobalState.getInstance().getStage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/edit_profile.fxml"));
        mainStage.setScene(new Scene(root, 1280, 720));
    }
}
