package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.GlobalState;
import model.Property;
import model.Agent;
import model.Owner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class view_propertyController {

    private Property current_property;

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
    private Button switchToEditPost;

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

    public void initialize(){
        current_property = GlobalState.getInstance().getSelected();
        property1_name.setText(current_property.getProjectName());
        Label[] labels = {property1_Facilities_1, property1_Facilities_2, property1_Facilities_3};
        for(int i = 0; i < current_property.getFacilities().size(); i++){
            if(i < 3){
                labels[i].setText(current_property.getFacilities().get(i));
            }
        }
        Label[] labels2 = {property1_Features_1, property1_Features_2, property1_Features_3, property1_Features_4, property1_Features_5};
        for(int i = 0; i < current_property.getKeyFeatures().size(); i++){
            if(i < 5){
                labels2[i].setText(current_property.getKeyFeatures().get(i));
            }
        }
        if(current_property.getKeyFeatures().size() < 5){
            for(int i = current_property.getKeyFeatures().size(); i < 5; i++){
                labels2[i].setText(null);
            }
        }
        if(current_property.getFacilities().size() < 3){
            for(int i = current_property.getFacilities().size(); i < 3; i++){
                labels[i].setText(null);
            }
        }
        property1_bed.setText(Integer.toString(current_property.getNumberOfBedroom()));
        property1_address.setText(current_property.getAddress());
        property1_toilet.setText(Integer.toString(current_property.getNumberOfBathroom()));
        property1_floorSize.setText(Integer.toString(current_property.getFloorSize()));
        property1_PSF.setText(Double.toString(current_property.getpsf()));
        property1_furnishStatus.setText(current_property.getFurnishStatus());
        property1_type.setText(current_property.getPropertyType());
        property1_price.setText(Integer.toString(current_property.getRental_price()));
        try {
            ImageView[] images = {property1_image_1, property1_image_2, property1_image_3};
            for(int i = 0; i < current_property.getPhoto().size(); i++){
                if(i < 3){
                    String imgPath = current_property.getPhoto().get(i);
                    imgPath = "../view/" + imgPath;
                    Image img1;
                    img1 = new Image(getClass().getResource(imgPath).toURI().toString());
                    images[i].setImage(img1);
                }
            }
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int id = current_property.getListerID();
        String role = current_property.getListerType();
        if(role.equals("Agent")){
            Agent agent = GlobalState.getInstance().getAgent(id);
            if(agent==null){
                lister_name.setText(null);
                lister_phone.setText(null);
                lister_agentLicense.setText(null);
            }
            else{
                lister_name.setText(agent.getName());
                lister_phone.setText(agent.getPhone());
                lister_agentLicense.setText(agent.getLicenseNo());
            }
        }
        if(role.equals("Owner")){
            Owner owner = GlobalState.getInstance().getOwner(id);
            if(owner==null){
                lister_name.setText(null);
                lister_phone.setText(null);
                lister_agentLicense.setText(null);
            }
            else{
                lister_name.setText(owner.getName());
                lister_phone.setText(owner.getPhone());
                lister_agentLicense.setText(null);
            }
        }
        switchToEditPost.setVisible(GlobalState.getInstance().getIsPersonal());
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
