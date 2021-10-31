package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.GlobalState;

import java.io.IOException;

public class view_propertyController {



    @FXML
    private Button currentPage;

    @FXML
    private Button favouriteButton;

    @FXML
    private Label lister_agentLicense;

    @FXML
    private Label lister_name;

    @FXML
    private Label lister_phone;

    @FXML
    private Label lister_role;

    @FXML
    private Button loginButton;

    @FXML
    private Button page_Back;

    @FXML
    private Button page_Next;

    @FXML
    private Button profileButton;

    @FXML
    private Label property1_Facilities_1;

    @FXML
    private Label property1_Facilities_2;

    @FXML
    private Label property1_Facilities_3;

    @FXML
    private Label property1_Features_1;

    @FXML
    private Label property1_Features_2;

    @FXML
    private Label property1_Features_3;

    @FXML
    private Label property1_Features_4;

    @FXML
    private Label property1_Features_5;

    @FXML
    private Label property1_PSF;

    @FXML
    private Label property1_address;

    @FXML
    private Label property1_bed;

    @FXML
    private Label property1_floorSize;

    @FXML
    private Label property1_furnishStatus;

    @FXML
    private ImageView property1_image_1;

    @FXML
    private ImageView property1_image_2;

    @FXML
    private ImageView property1_image_3;

    @FXML
    private Label property1_name;

    @FXML
    private AnchorPane property1_pane;

    @FXML
    private Label property1_price;

    @FXML
    private Label property1_toilet;

    @FXML
    private Label property1_type;

    @FXML
    private Button registerButton;

    @FXML
    private Button switchToCreatePost;

    @FXML
    void addFavourite(ActionEvent event) {
        //no need
    }

    @FXML
    void switchToEditPost(ActionEvent event) {
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/EditProperty.fxml"));
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

    @FXML
    void switchToHomePage(ActionEvent event) {
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    @FXML
    void switchToLogin(ActionEvent event) {
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/loginpage.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    @FXML
    void switchToProfile(ActionEvent event) {
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/view_profile.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    @FXML
    void switchToRegister(ActionEvent event) {
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/registerpage.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    @FXML
    void viewProperty_1(ActionEvent event) {

    }

}
