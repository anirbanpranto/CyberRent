package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.GlobalState;

import java.io.IOException;

public class change_passwordController {
    GlobalState state = GlobalState.getInstance();

    @FXML
    private TextField currentPassword;
    @FXML
    private TextField newPassword;
    @FXML
    private TextField confirmNewPassword;
    @FXML
    private Label textCurrentPassword;
    @FXML
    private Label textNewPassword;
    @FXML
    private Label textConfirmNewPassword;

    boolean changeValid = false;

    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button favouriteButton;

    public void initialize(){
        boolean loginStatus = GlobalState.getInstance().getLoginStatus();
        if(loginStatus){
            registerButton.setVisible(false);
            registerButton.setDisable(true);
            loginButton.setText("Logout");
            loginButton.setOnAction(e ->{
                GlobalState.getInstance().setLoginStatus();
                switchToHomePage(e);
            });
        }
        else{
            profileButton.setOnAction(e ->{switchToLogin(e);});
            favouriteButton.setOnAction(e ->{switchToLogin(e);});
        }

    }

    public void verifyPassword(){
        if(state.getPassword().equals(currentPassword.getText())){
            if(newPassword.getText().equals("")) {
                textNewPassword.setText("New Password *Cannot be empty*");
                textConfirmNewPassword.setText("Confirm New Password *Cannot be empty*");
            }else{
                if ((newPassword.getText()).equals(confirmNewPassword.getText())) {
                    changeValid = true;
                    state.setNewPassword(newPassword.getText());
                } else {
                    textNewPassword.setText("New Password *Incorrect*");
                    textConfirmNewPassword.setText("Confirm New Password *Incorrect*");
                }
            }
        }else{
            textCurrentPassword.setText("Current Password *Incorrect*");
        }
    }

    public void save(ActionEvent event){
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            verifyPassword();
            Parent root = FXMLLoader.load(getClass().getResource("/view/view_profile.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void cancel(ActionEvent event) throws IOException{
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/view_profile.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void switchToHomePage(ActionEvent event){
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void switchToLogin(ActionEvent event){
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/loginpage.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

}
