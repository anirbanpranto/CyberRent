package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.GlobalState;

import java.io.IOException;

public class edit_profileController {
    GlobalState state = GlobalState.getInstance();

    @FXML
    private Label Email_EditProfile;
    @FXML
    private TextField FullName_EditProfile;
    @FXML
    private TextField PhoneButton_EditProfile;

    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button favouriteButton;

    @FXML
    private Label agentlicense_Label;
    @FXML
    private TextField AgentLicense_EditProfile;

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
            agentlicense_Label.setVisible(true);
            AgentLicense_EditProfile.setVisible(true);
        }else{
            agentlicense_Label.setVisible(false);
            AgentLicense_EditProfile.setVisible(false);
        }

        Email_EditProfile.setText(state.getEmail());
    }

    public void editFullName(){
        String newName = FullName_EditProfile.getText();
        String newPhone = PhoneButton_EditProfile.getText();
        String newAgentLicense = AgentLicense_EditProfile.getText();
        
        if(!newName.equals("")){
            state.setNewFullName(newName);
        }
        if(!newPhone.equals("")){
            state.setNewPhoneNumber(newPhone);
        }
        if(state.getRole().equals("Agent") && !newAgentLicense.equals("")){
            state.setAgentLicense(newAgentLicense);
        }

        state.EditProfilePerformed(state.getRole());
    }

    public void save(ActionEvent event) {
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            editFullName();
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

    @FXML
    void switchToFavourite(ActionEvent event) {
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/favourite_list.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

}
