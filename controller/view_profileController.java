package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.GlobalState;
import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;

public class view_profileController{
    GlobalState state = GlobalState.getInstance();

    @FXML
    private Label Email_Profile;
    @FXML
    private Label FullName_Profile;
    @FXML
    private Label PhoneNumber_Profile;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button favouriteButton;

    @FXML
    private Label agentlicense_profile_label;
    @FXML
    private Label agentlicense_profile;
    @FXML
    private Rectangle AgentLicense_Profile_rec;

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

        if(state.getRole().equals("Agent")){
            agentlicense_profile_label.setVisible(true);
            agentlicense_profile.setVisible(true);
            AgentLicense_Profile_rec.setVisible(true);
            agentlicense_profile.setText(state.getAgentLicense());
        }else{
            agentlicense_profile_label.setVisible(false);
            agentlicense_profile.setVisible(false);
            AgentLicense_Profile_rec.setVisible(false);
        }

        Email_Profile.setText(state.getEmail());
        FullName_Profile.setText(state.getFullName());
        PhoneNumber_Profile.setText(state.getPhoneNumber());
    }

    public void edit(ActionEvent event) throws IOException {
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/edit_profile.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void changePassword(ActionEvent event) throws IOException {
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/change_password.fxml"));
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
