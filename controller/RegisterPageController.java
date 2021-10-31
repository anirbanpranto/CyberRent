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
        List<List<String>> emailList = Database.readData("email");
        List<List<String>> tempEmailList = Database.readData("temp_email");
        if (userInput_Password.getText().equals(userInput_ConfirmPassword.getText())){
            if (emailList.size() != 0){
                boolean flag = true;
                for(int i = 0; i < emailList.size(); i++){
                    // check if email exists in temp file database
                    if (emailList.get(i).get(0).equals(userInput_Email.getText())){
                        flag = false;
                    }                 
                }
                if (flag == false){
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
                else{
                    writeTempFile();
                    Database.writeData("temp_email",Arrays.asList(userInput_Email.getText()));
                }
            }
            // temp_email
            else if (tempEmailList.size() != 0){
                boolean flag = true;
                for(int i = 0; i < tempEmailList.size(); i++){
                    // check if email exists in temp file database
                    if (tempEmailList.get(i).get(0).equals(userInput_Email.getText())){
                        flag = false;
                    }                 
                }
                if (flag == false){
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
                else{
                    writeTempFile();
                    Database.writeData("temp_email",Arrays.asList(userInput_Email.getText()));
                }
            }
            // email is never used
            else{
                writeTempFile();
                Database.writeData("temp_email",Arrays.asList(userInput_Email.getText()));
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

    private void writeTempFile() throws IOException {
        if (roleComboBox.getValue().equals("Tenant")){
            Database.writeData("Tenant_temp", Arrays.asList(null, null, userInput_Password.getText(), userInput_Email.getText(),"Tenant", null));
        }
        else if (roleComboBox.getValue().equals("Owner")){
            Database.writeData("Owner_temp", Arrays.asList(null, null, userInput_Password.getText(), userInput_Email.getText(),"Agent", null));
        }
        else{
            Database.writeData("Agent_temp", Arrays.asList(null, null, userInput_Password.getText(), userInput_Email.getText(),"Tenant", null, null));
        }
        Stage mainStage = GlobalState.getInstance().getStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));
        mainStage.setScene(new Scene(root, 1280, 720));
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
