package controller;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

import javax.sound.midi.SysexMessage;

import java.io.Console;
import java.io.IOException;
import java.net.URISyntaxException;

import javafx.scene.control.MenuItem;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import model.GlobalState;
import model.Property;

public class PersonalPropertyListController {

    @FXML
    private ArrayList<ArrayList<Label>> property_list;
    @FXML
    private ArrayList<ImageView> property_image;
    @FXML
    private Button favouriteButton;
    @FXML
    private Button loginButton;

    @FXML
    private Button currentPage;
    @FXML
    private Button page_Back;
    @FXML
    private Button page_Next;

    @FXML
    private ArrayList<AnchorPane> property_pane;
    
    @FXML
    private Button profileButton;
    
    @FXML
    private Button registerButton;
    @FXML
    private Label numberOfResults;
    @FXML
    private Button addFavourite_1;
    @FXML
    private Button addFavourite_2;
    @FXML
    private Button addFavourite_3;

    private ArrayList<Property> properties;
    private ArrayList<Property> currentProperties;
    private int totalResult;

    public void initialize(){
        boolean loginStatus = GlobalState.getInstance().getLoginStatus();
        if(loginStatus){
            addFavourite_1.setVisible(false);
            addFavourite_1.setDisable(true);
            addFavourite_2.setVisible(false);
            addFavourite_2.setDisable(true);
            addFavourite_3.setVisible(false);
            addFavourite_3.setDisable(true);
            registerButton.setVisible(false);
            registerButton.setDisable(true);
            loginButton.setText("Logout");
            loginButton.setOnAction(e ->{
                GlobalState.getInstance().setLoginStatus();
                switchToHomePage(e);
            });
            GlobalState state = GlobalState.getInstance();
            ArrayList<Property> tempPersonal = state.getPersonalProperties();
            this.numberOfResults.setText(Integer.toString(tempPersonal.size()));
            System.out.println(tempPersonal.size());
            this.setProperties(tempPersonal);
        }
        else{
            profileButton.setOnAction(e ->{switchToLogin(e);
                try
                {
                    displayError();
                }
                catch (Exception exp)
                {
                    exp.printStackTrace();
                }});
            favouriteButton.setOnAction(e ->{switchToLogin(e);
                try
                {
                    displayError();
                }
                catch (Exception exp)
                {
                    exp.printStackTrace();
                }});
        }
    }
    
    public void setProperties(ArrayList<Property> properties){
        totalResult = properties.size(); 
        
        numberOfResults.setText(totalResult + " results.");
        
        if(totalResult <= 3)
            page_Next.setDisable(true);
        else
            page_Next.setDisable(false);
            
        this.properties = properties;
        displayProperties(this.properties, 0);
        
    }

    public void displayProperties(ArrayList<Property> properties, int page){
        ArrayList<Property> temp = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            try{
            property_list.get(i).get(0).setText(Integer.toString(properties.get(i + (page * 3)).getRental_price())); // monthly price
            property_list.get(i).get(1).setText(properties.get(i + (page * 3)).getProjectName()); // name
            property_list.get(i).get(2).setText(properties.get(i + (page * 3)).getAddress()); // address
            property_list.get(i).get(3).setText(properties.get(i + (page * 3)).getPropertyType()); // property type
            property_list.get(i).get(4).setText(Integer.toString(properties.get(i + (page * 3)).getFloorSize())); // floor size
            property_list.get(i).get(5).setText(Double.toString(properties.get(i + (page * 3)).getpsf())); // psf rate
            property_list.get(i).get(6).setText(properties.get(i + (page * 3)).getFurnishStatus()); // furnish status
            property_list.get(i).get(7).setText(Integer.toString(properties.get(i + (page * 3)).getNumberOfBedroom())); // no. of bedroom
            property_list.get(i).get(8).setText(Integer.toString(properties.get(i + (page * 3)).getNumberOfBathroom())); // no. of bathroom
            String imgPath = properties.get(i + (page * 3)).getPhoto().get(0);
            imgPath = "../view/" + imgPath;
            System.out.println(imgPath);
            Image img1 = new Image(getClass().getResource(imgPath).toURI().toString());
            property_image.get(i).setImage(img1);
            property_pane.get(i).setVisible(true);
            temp.add(properties.get(i + (page * 3)));
            //property_image.get(i).setImage(new Image(properties.get(i).getPhoto().get(0))); // 1st picture as the thumbnail
            }catch(IndexOutOfBoundsException e){
                property_list.get(i).get(0).setText(""); // monthly price
                property_list.get(i).get(1).setText(""); 
                property_list.get(i).get(2).setText("");
                property_list.get(i).get(3).setText(""); 
                property_list.get(i).get(4).setText(""); 
                property_list.get(i).get(5).setText("");
                property_list.get(i).get(6).setText(""); 
                property_list.get(i).get(7).setText("");
                property_list.get(i).get(8).setText("");
                property_pane.get(i).setVisible(false);
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            currentProperties = temp;
        }
    }

    @FXML
    void addFavourite(ActionEvent event) {

    }

    @FXML
    public void nextPage(ActionEvent event) {
        int maxPage = findTotalPageNumber();
        int pageNumber = Integer.parseInt(currentPage.getText());

        page_Back.setDisable(false);

        if(pageNumber + 1 == maxPage)
            page_Next.setDisable(true);
            currentPage.setText(Integer.toString(pageNumber + 1)); 

        displayProperties(properties, Integer.parseInt(currentPage.getText()) - 1);
    }

    @FXML
    public void previousPage(ActionEvent event) { 
        int pageNumber = Integer.parseInt(currentPage.getText());

        page_Next.setDisable(false);

        if(pageNumber - 1 == 1)
            page_Back.setDisable(true);
            currentPage.setText(Integer.toString(pageNumber - 1));
            
        displayProperties(properties, Integer.parseInt(currentPage.getText()) - 1);
    }

    private int findTotalPageNumber(){
        double max = Double.valueOf(totalResult) / 3;
        if((max - (totalResult/3)) == 0)
            return totalResult/3;
        else
            return totalResult/3 + 1;
    }

    @FXML
    public void switchToFavourite(ActionEvent event) {
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/favourite_list.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    @FXML
    public void switchToHomePage(ActionEvent event){
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    @FXML
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
    public void switchToProfile(ActionEvent event){
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/view_profile.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    @FXML
    public void switchToRegister(ActionEvent event){
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
        try{
            Property p = this.currentProperties.get(0);
            GlobalState.getInstance().setSelected(p);
            GlobalState.getInstance().setIsPersonal(true);
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/view_property.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    @FXML
    void viewProperty_2(ActionEvent event) {
        Property p = this.currentProperties.get(1);
        GlobalState.getInstance().setSelected(p);
        GlobalState.getInstance().setIsPersonal(true);
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/view_property.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    @FXML
    void viewProperty_3(ActionEvent event) {
        Property p = this.currentProperties.get(2);
        GlobalState.getInstance().setSelected(p);
        GlobalState.getInstance().setIsPersonal(true);
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/view_property.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private void displayError() throws Exception {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login error");
        alert.setHeaderText("Error");
        alert.setContentText("Login is required.");
        alert.showAndWait(); 
    }

    @FXML
    public void switchToCreatePost(ActionEvent event){
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/ListProperty.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

}


/*
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
    private Label property1_name;
    @FXML
    private Label property1_price;
    @FXML
    private Label property1_toilet;
    @FXML
    private Label property1_type;
    @FXML
    private Label property2_PSF;
    @FXML
    private Label property2_address;
    @FXML
    private Label property2_bed;
    @FXML
    private Label property2_floorSize;
    @FXML
    private Label property2_furnishStatus;
    @FXML
    private Label property2_name;
    @FXML
    private Label property2_price;
    @FXML
    private Label property2_toilet;
    @FXML
    private Label property2_type;
    @FXML
    private Label property3_PSF;
    @FXML
    private Label property3_address;
    @FXML
    private Label property3_bed;
    @FXML
    private Label property3_floorSize;
    @FXML
    private Label property3_furnishStatus;
    @FXML
    private Label property3_name;
    @FXML
    private Label property3_price;
    @FXML
    private Label property3_toilet;
    @FXML
    private Label property3_type;

    @FXML
    private ImageView property1_image;
    @FXML
    private ImageView property2_image;
    @FXML
    private ImageView property3_image;
    */
