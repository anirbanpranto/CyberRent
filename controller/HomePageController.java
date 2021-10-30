package controller;

import java.util.*;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import model.GlobalState;
import model.SearchEngine;
import model.Property;

public class HomePageController {

    @FXML
    private Button profileButton;
    @FXML
    private Button favouriteButton;
    @FXML
    private MenuButton facilities;
    @FXML
    private MenuButton floorSize;
    @FXML
    private TextField floorSize_Max;
    @FXML
    private TextField floorSize_Min;
    @FXML
    private MenuButton keyFeatures;
    @FXML
    private Button loginButton;
    @FXML
    private ImageView menuItem;
    @FXML
    private MenuButton numberOfBedroom;
    @FXML
    private MenuButton priceRange;
    @FXML
    private TextField price_Max;
    @FXML
    private TextField price_Min;
    @FXML
    private MenuButton propertyType;
    @FXML
    private MenuButton psf;
    @FXML
    private MenuItem psfScene;
    @FXML
    private TextField psf_Max;
    @FXML
    private TextField psf_Min;
    @FXML
    private Button registerButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button manageButton;
    @FXML
    private TextField searchTextField;

    //search criteria
    private String search_projectName;
    private String search_propertyType = "All Residential";
    private int search_price_Min = 0;
    private int search_price_Max = 99999;
    private int search_floorSize_Min = 0;
    private int search_floorSize_Max = 99999;
    private double search_psf_Min = 0;
    private double search_psf_Max = 10;
    private String search_numberOfBedRoom = "Any";
    private ArrayList<String> search_facilities = new ArrayList<>();
    private ArrayList<String> search_keyFeatures = new ArrayList<>();

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

    @FXML
    public void search(ActionEvent event) throws IOException{

        // TextFields
        search_projectName = searchTextField.getText();

        ArrayList<Property> p = SearchEngine.search(search_projectName, search_propertyType, search_price_Min, search_price_Max, 
                                                    search_floorSize_Min, search_floorSize_Max, search_psf_Min, search_psf_Max, 
                                                    search_numberOfBedRoom, search_facilities, search_keyFeatures);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/propertylist.fxml"));
        Parent root = loader.load();

        PropertyListController propertyListController = loader.getController();
        propertyListController.setProperties(p);
        
        Stage mainStage = GlobalState.getInstance().getStage();
        mainStage.setScene(new Scene(root, 1280, 720));
    }

    @FXML
    public void price_applyFilter(ActionEvent event){
        calculateMinMax("Price (RM)", priceRange, price_Min, price_Max, search_price_Min, search_price_Max);
        try{
            search_price_Min = Integer.parseInt(price_Min.getText());
        }catch(NumberFormatException e){
            search_price_Min = 0;
        }
        try{
            search_price_Max = Integer.parseInt(price_Max.getText());
        }catch(NumberFormatException e){
            search_price_Max = 99999;
        }
    }

    @FXML
    public void floorSize_applyFilter(ActionEvent event){
        calculateMinMax("Built up Size (sq ft)",floorSize, floorSize_Min, floorSize_Max, search_floorSize_Min, search_floorSize_Max);
        try{
            search_floorSize_Min = Integer.parseInt(floorSize_Min.getText());
        }catch(NumberFormatException e){
            search_floorSize_Min = 0;
        }
        try{
            search_floorSize_Max = Integer.parseInt(floorSize_Max.getText());
        }catch(NumberFormatException e){
            search_floorSize_Max = 99999;
        }
    }

    @FXML
    public void psf_applyFilter(ActionEvent event){
        calculateMinMax("PSF (RM)", psf, psf_Min, psf_Max, search_psf_Min, search_psf_Max);
        try{
            search_psf_Min = Double.parseDouble(psf_Min.getText());
        }catch(NumberFormatException e){
            search_psf_Min = 0;
        }
        try{
            search_psf_Max = Double.parseDouble(psf_Max.getText());
        }catch(NumberFormatException e){
            search_psf_Max = 99999;
        }
    }
    
    private void calculateMinMax(String title, MenuButton menuBtn, TextField minTxt, TextField maxTxt, int min, int max){ // for floorSize(int) and price(int)
        String min_String = minTxt.getText();
        String max_String = maxTxt.getText();

        if((min_String.equals("")) && (max_String.equals(""))){ // Both are not filled up
            menuBtn.setText(title);
        }
        else if((min_String.equals("")) && !(max_String.equals(""))){ // Only max is filled up
            max = Integer.parseInt(max_String);
            menuBtn.setText("Below " + max_String); 
        }
        else if(!(min_String.equals("")) && (max_String.equals(""))){ // Only min is filled up
            min = Integer.parseInt(min_String);
            menuBtn.setText("Above " + min_String);
        }
        else{ //Both are not filled up
            min = Integer.parseInt(min_String);
            max = Integer.parseInt(max_String);
            if(min > max){
                min = Integer.parseInt(max_String);
                minTxt.setText(max_String);
                max = Integer.parseInt(min_String);
                maxTxt.setText(min_String);
            }
            menuBtn.setText(Integer.toString(min) + " - " + Integer.toString(max));
        }
    }

    private void calculateMinMax(String title, MenuButton menuBtn, TextField minTxt, TextField maxTxt, double min, double max){ // for psf(double)
        String min_String = minTxt.getText();
        String max_String = maxTxt.getText();

        if((min_String.equals("")) && (max_String.equals(""))){ // Both are not filled up
            menuBtn.setText(title);
        }
        else if((min_String.equals("")) && !(max_String.equals(""))){ // Only max is filled up
            max = Double.parseDouble(max_String);
            menuBtn.setText("Below " + max_String); 
        }
        else if(!(min_String.equals("")) && (max_String.equals(""))){ // Only min is filled up
            min = Double.parseDouble(min_String);
            menuBtn.setText("Above " + min_String);
        }
        else{ //Both are not filled up
            min = Double.parseDouble(min_String);
            max = Double.parseDouble(max_String);
            if(min > max){
                min = Double.parseDouble(max_String);
                minTxt.setText(max_String);
                max = Double.parseDouble(min_String);
                maxTxt.setText(min_String);
            }
            menuBtn.setText(Double.toString(min) + " - " + Double.toString(max));
        }
    }

    @FXML
    public void selectBedroom(ActionEvent event) {
        String selection = ((MenuItem)event.getSource()).getText();
        
        if(selection.charAt(1) == '+'){
            numberOfBedroom.setText(selection);
            search_numberOfBedRoom = Character.toString(selection.charAt(0));
        }
        else{
            numberOfBedroom.setText("Bedroom");
        }
    }

    @FXML
    public void selectFacilities(ActionEvent event) {
        CheckBox current = (CheckBox)event.getSource();

        if(current.isSelected())
            search_facilities.add(current.getText());
        else
            search_facilities.remove(current.getText());
    }

    @FXML
    public void selectKeyFeatures(ActionEvent event) {
        CheckBox current = (CheckBox)event.getSource();

        if(current.isSelected())
            search_keyFeatures.add(current.getText());
        else
            search_keyFeatures.remove(current.getText());
    }

    @FXML
    public void selectPropertyType(ActionEvent event) {
        String selection = ((MenuItem)event.getSource()).getText();
        propertyType.setText(selection);
        search_propertyType = selection;
    }

    @FXML
    public void switchToFavourite(ActionEvent event) {
        /*
        Stage mainStage = GlobalState.getInstance().getStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/favourite.fxml"));
        mainStage.setScene(new Scene(root, 1280, 720));
        */
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

    private void displayError() throws Exception {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login error");
        alert.setHeaderText("Error");
        alert.setContentText("Login is required.");
        alert.showAndWait(); 
    }

}