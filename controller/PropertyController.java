package controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.CheckMenuItem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.GlobalState;

import javafx.scene.control.TextField;
import javafx.scene.control.MenuButton;
import model.Database;
import model.Property;

public class PropertyController {
    private ArrayList<String> keyFacilities = new ArrayList<>();
    private ArrayList<String> keyFeatures = new ArrayList<>();
    @FXML
    private TextField projectName;
    @FXML
    private TextField floorSize;
    @FXML
    private TextField psq;
    @FXML
    private TextField bedroom;
    @FXML
    private TextField bathroom;
    @FXML
    private TextField rentalPrice;
    @FXML
    private TextField propertyType;
    @FXML
    private TextField address;
    @FXML
    private TextField city;
    @FXML
    private TextField state;
    private String furnishStatus;
    private ArrayList<String> photos = new ArrayList<>();

    public void chooseImage(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Image Files", Arrays.asList("*.jpeg", "*.jpg", "*.png")));
        List<File> f = fc.showOpenMultipleDialog(null);
        for(File i:f){
            File source = new File(i.getAbsolutePath());
            File dest = new File("./view/image/StoredImages/" + i.getName());
            this.photos.add("image/StoredImages/" + i.getName());
            try
            {
                System.out.println("Uploading Files into StoredImages Directory");
                Database.copyFileUsingStream(source, dest);
            }
            catch (java.io.IOException ioe)
            {
                System.out.println("File Copy Crash " + "./StoredImages/" + i.getName());
            }
        }
    }

    public void save(ActionEvent event){
        System.out.println("Property is being created");
        //create a property
        /*
        ArrayList<String> f = new ArrayList<>();
        ArrayList<String> k = new ArrayList<>();
        ArrayList<String> photos = new ArrayList<>();
        Property p = new Property.Builder().projectName("ABC").floorSize(1000).psf(0.9).furnishStatus("Fully").
                                         numberOfBedroom("2").numberOfBathroom(1).facilities(f).
                                         keyFeatures(k).rental_price(1000).address("address").
                                         city("Cyberjaya").state("Selangor").propertyType("condominium").
                                         photo(photos).build("Owner",1);
    */
        GlobalState state = GlobalState.getInstance();
        Property p = new Property.Builder().projectName(this.projectName.getText()).floorSize(Integer.parseInt(this.floorSize.getText())).psf(Double.parseDouble(this.psq.getText())).furnishStatus(this.furnishStatus).
                                         numberOfBedroom(Integer.parseInt(this.bedroom.getText())).numberOfBathroom(Integer.parseInt(this.bathroom.getText())).facilities(this.keyFacilities).
                                         keyFeatures(this.keyFeatures).rental_price(Integer.parseInt(this.rentalPrice.getText())).address(this.address.getText()).
                                         city(this.city.getText()).state(this.state.getText()).propertyType(this.propertyType.getText()).
                                         photo(this.photos).createProperty(state.getRole(),state.getLoggedInId());
                                         //Note need to put actual Role and ID that is stored in the login session later
        p.writeFile(); //save this entry
        //save this object into the global list of objects in GlobalState
        //save this object into list of user properties in GlobalState as well
        GlobalState.getInstance().Init();
        GlobalState.getInstance().LoadAttributes();
        switchToHomePage(event);
        System.out.println("Done creating");
    }

    @FXML
    public void switchToMyList(ActionEvent event){
        //test
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/personalpropertyList.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void sp(ActionEvent event){
        CheckMenuItem item = (CheckMenuItem) event.getSource();
        if(item.isSelected()){
            keyFacilities.add("Swimming Pool");
        }
        else{
            keyFacilities.remove("Swimming Pool");
        }
    }

    public void gym(ActionEvent event){
        CheckMenuItem item = (CheckMenuItem) event.getSource();
        if(item.isSelected()){
            keyFacilities.add("Gym");
        }
        else{
            keyFacilities.remove("Gym");
        }
    }

    public void parking(ActionEvent event){
        CheckMenuItem item = (CheckMenuItem) event.getSource();
        if(item.isSelected()){
            keyFacilities.add("Parking");
        }
        else{
            keyFacilities.remove("Parking");
        }
    }

    public void aircon(ActionEvent event){
        CheckMenuItem item = (CheckMenuItem) event.getSource();
        if(item.isSelected()){
            keyFeatures.add("Air Conditioner");
        }
        else{
            keyFeatures.remove("Air Conditioner");
        }
    }

    public void wm(ActionEvent event){
        CheckMenuItem item = (CheckMenuItem) event.getSource();
        if(item.isSelected()){
            keyFeatures.add("Washing Machine");
        }
        else{
            keyFeatures.remove("Washing Machine");
        }
    }

    public void wh(ActionEvent event){
        CheckMenuItem item = (CheckMenuItem) event.getSource();
        if(item.isSelected()){
            keyFeatures.add("Water Heater");
        }
        else{
            keyFeatures.remove("Water Heater");
        }
    }

    public void wp(ActionEvent event){
        CheckMenuItem item = (CheckMenuItem) event.getSource();
        if(item.isSelected()){
            keyFeatures.add("Water Purifier");
        }
        else{
            keyFeatures.remove("Water Purifier");
        }
    }

    public void balcony(ActionEvent event){
        CheckMenuItem item = (CheckMenuItem) event.getSource();
        if(item.isSelected()){
            keyFeatures.add("Balcony");
        }
        else{
            keyFeatures.remove("Balcony");
        }
    }

    public void full(ActionEvent event){
        this.furnishStatus = "Fully Furnished";
    }

    public void partial(ActionEvent event){
        this.furnishStatus = "Partially Furnished";
    }

    public void non(ActionEvent event){
        this.furnishStatus = "Non Furnished";
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
    public void switchToHomePage(ActionEvent event){
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/homepage.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void cancel(ActionEvent event){
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/personalpropertyList.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
