package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.GlobalState;

import java.io.IOException;

public class edit_profileController {

    @FXML
    private TextField Password_EditProfile;
    @FXML
    private TextField FullName_EditProfile;
    @FXML
    private TextField PhoneButton_EditProfile;

    @FXML
    public void save(ActionEvent event) {
        try {
            GlobalState state = GlobalState.getInstance();
            int id = state.getLoggedInId();
            String role = state.getRole();
            if(role=="Owner"){
                //fetch owner
                //edit the Owner inside the temporary Arraylist
                //edit the Owner inside the temporary Lookuptable
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/view_profile.fxml"));
            Parent root = (Parent)loader.load();
            view_profileController test = loader.getController();
            test.displayProfile(FullName_EditProfile.getText(),PhoneButton_EditProfile.getText());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void cancel(ActionEvent event) throws IOException{
        Stage mainStage = GlobalState.getInstance().getStage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/view_profile.fxml"));
        mainStage.setScene(new Scene(root, 1280, 720));
    }

}
