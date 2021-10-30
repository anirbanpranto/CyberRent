package controller;

import java.util.*;
import java.io.IOException;
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

import model.GlobalState;
import model.Property;
import model.SearchEngine;

public class AgentPropertyListController {

    @FXML
    private ArrayList<ArrayList<Label>> property_list;
    @FXML
    private ArrayList<ImageView> property_image;
    @FXML
    private MenuButton facilities;
    @FXML
    private Button favouriteButton;
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
    private MenuButton numberOfBedroom;

    @FXML
    private Button currentPage;
    @FXML
    private Button page_Back;
    @FXML
    private Button page_Next;

    @FXML
    private ArrayList<AnchorPane> property_pane;

    @FXML
    private MenuButton priceRange;
    @FXML
    private TextField price_Max;
    @FXML
    private TextField price_Min;
    
    @FXML
    private Button profileButton;
    
    @FXML
    private MenuButton propertyType;

    @FXML
    private MenuButton psf;
    @FXML
    private TextField psf_Max;
    @FXML
    private TextField psf_Min;
    @FXML
    private Button registerButton;
    @FXML
    private TextField searchTextField;

    @FXML
    private MenuButton sorting;
    @FXML
    private Label numberOfResults;

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
    private String sortType = "Default";

    private ArrayList<Property> properties;
    private int totalResult;

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
            GlobalState state = GlobalState.getInstance();
            state.getPersonalProperties();
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
            property_pane.get(i).setVisible(true);

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
            }
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
    public void sort(ActionEvent event){
        String selection = ((MenuItem)event.getSource()).getText();
        sorting.setText(selection);
        sortType = selection;

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

    @FXML
    void viewProperty(ActionEvent event) {
        /*
        Stage mainStage = GlobalState.getInstance().getStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/property.fxml"));
        mainStage.setScene(new Scene(root, 1280, 720));
        */
    }

    private void displayError() throws Exception {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login error");
        alert.setHeaderText("Error");
        alert.setContentText("Login is required.");
        alert.showAndWait(); 
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
