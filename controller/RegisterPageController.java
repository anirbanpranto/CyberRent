package controller;

import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.stage.Stage;
import model.GlobalState;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import model.*;

public class RegisterPageController {

    ObservableList<String> roleList = FXCollections.observableArrayList("Tenant","Owner","Agent");

    @FXML
    private PasswordField userInput_ConfirmPassword;

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
    public void switchToHomePage(ActionEvent event) throws IOException{
        Stage mainStage = GlobalState.getInstance().getStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));
        mainStage.setScene(new Scene(root, 1280, 720));
    }

    @FXML
    public void switchToLogin(ActionEvent event) throws IOException{
        Stage mainStage = GlobalState.getInstance().getStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginpage.fxml"));
        mainStage.setScene(new Scene(root, 1280, 720));
    }

    @FXML
    public void validate(ActionEvent event) throws IOException {
        List<List<String>> list = Database.readData(roleComboBox.getValue().toString());
        if (userInput_Password.getText().equals(userInput_ConfirmPassword.getText())){
            if (list.size() != 0){
                for(int i = 0; i < list.size(); i++){
                    // check if email exists in database
                    if (!(list.get(i).get(3).equals(userInput_Email.getText()))){
                        if (roleComboBox.getValue().equals("Tenant")){
                            createAccount("Tenant");
                        }
                        else if (roleComboBox.getValue().equals("Owner")){
                            createAccount("Owner");
                        }
                        else{
                            createAccount("Agent");
                        }
                        Stage mainStage = GlobalState.getInstance().getStage();
                        Parent root = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));
                        mainStage.setScene(new Scene(root, 1280, 720));
                    }
                    else{
                        try
                        {
                            displayError("emailError");
                            userInput_Email.setText(null);
                            userInput_Password.setText(null);
                            userInput_ConfirmPassword.setText(null);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
            else{
                if (roleComboBox.getValue().equals("Tenant")){
                    createAccount("Tenant");
                }
                else if (roleComboBox.getValue().equals("Owner")){
                    createAccount("Owner");
                }
                else{
                    createAccount("Agent");
                }
                Stage mainStage = GlobalState.getInstance().getStage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));
                mainStage.setScene(new Scene(root, 1280, 720));
                
            }   
        }
            else{
                try
                {
                    displayError("passwordError");
                    userInput_Password.setText(null);
                    userInput_ConfirmPassword.setText(null);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }            
            }
}
    
    private void createAccount(String role){
        if (role.equals("Tenant")){
            Tenant tenants = new Tenant(0, null, userInput_Password.getText(), userInput_Email.getText(), null);
            Tenant.createTenant(null, userInput_Password.getText(), userInput_Email.getText(), null);
        }
        else if (role.equals("Owner")){
            Owner owners = new Owner(0, null, userInput_Password.getText(), userInput_Email.getText(), null);
            Owner.createOwner(null, userInput_Password.getText(), userInput_Email.getText(), null);
        }
        else{
            Agent agents = new Agent(0, null, userInput_Password.getText(), userInput_Email.getText(), null, null);
            Agent.createAgent(null, userInput_Password.getText(), userInput_Email.getText(), null, null);
        }
    }

    private void displayError(String error) throws Exception {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input error");
        alert.setHeaderText("Error");
        if (error.equals("emailError")){
            alert.setContentText("Email is used.");
        }
        else if (error.equals("passwordError")){
            alert.setContentText("Passwords are not matched.");
        }
        alert.showAndWait(); 
    }
}
