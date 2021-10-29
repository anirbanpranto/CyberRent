package controller;

import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.GlobalState;
import javafx.scene.*;
import java.io.IOException;
import javafx.scene.control.ComboBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import model.Database;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginPageController {

    ObservableList<String> roleList = FXCollections.observableArrayList("Tenant","Owner","Agent");

    @FXML
    private TextField userInput_Email;

    @FXML
    private PasswordField userInput_Password;

    @FXML
    private ComboBox roleComboBox;

    @FXML
    private void initialize(){
        roleComboBox.setValue("Tenant");
        roleComboBox.setItems(roleList);
    }

    @FXML
    void switchToHomePage(ActionEvent event) throws IOException{
        Stage mainStage = GlobalState.getInstance().getStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));
        mainStage.setScene(new Scene(root, 1280, 720));
    }

    @FXML
    void switchToRegister(ActionEvent event) throws IOException{
        Stage mainStage = GlobalState.getInstance().getStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/registerpage.fxml"));
        mainStage.setScene(new Scene(root, 1280, 720));
    }

    @FXML
    void validate(ActionEvent event) throws Exception{
        List<List<String>> list = Database.readData(roleComboBox.getValue().toString());

        for(int i = 0; i < list.size(); i++){
            // to check whether email and password are matched or not
            if (list.get(i).get(3).equals(userInput_Email.getText())&& (list.get(i).get(2).equals(userInput_Password.getText()))){
                Stage mainStage = GlobalState.getInstance().getStage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));
                mainStage.setScene(new Scene(root, 1280, 720));
            }
            else{
                try
                {
                    displayError();
                    userInput_Email.setText(null);
                    userInput_Password.setText(null);
                    break;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }    
            }
        }

    }

    private void displayError() throws Exception {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input error");
        alert.setHeaderText("Error");
        alert.setContentText("Invalid email or password");
        alert.showAndWait(); 
    }
}
