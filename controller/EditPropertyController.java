package controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.beans.property.FloatProperty;
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

public class EditPropertyController {
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

    @FXML
    private CheckMenuItem item1;
    @FXML
    private CheckMenuItem item2;
    @FXML
    private CheckMenuItem item3;

    @FXML
    private CheckMenuItem item4;
    @FXML
    private CheckMenuItem item5;
    @FXML
    private CheckMenuItem item6;
    @FXML
    private CheckMenuItem item7;

    @FXML
    private Button activateButton;

    private String furnishStatus;
    private ArrayList<String> photos = new ArrayList<>();

    public void initialize(){
        Property p = GlobalState.getInstance().getSelected();
        projectName.setText(p.getProjectName());
        floorSize.setText(Integer.toString(p.getFloorSize()));
        psq.setText(Double.toString(p.getpsf()));
        bedroom.setText(Integer.toString(p.getNumberOfBedroom()));
        bathroom.setText(Integer.toString(p.getNumberOfBathroom()));
        rentalPrice.setText(Integer.toString(p.getRental_price()));
        propertyType.setText(p.getPropertyType());
        address.setText(p.getAddress());
        state.setText(p.getState());
        city.setText(p.getCity());
        if(p.getStatus().equals("active")){
            activateButton.setText("Deactivate");
        }
        if(p.getStatus().equals("inactive")){
            activateButton.setText("Activate");
        }
    }

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
    // Property p = GlobalState.getInstance().getSelected();
    // projectName.setText(p.getProjectName());
    // floorSize.setText(Integer.toString(p.getFloorSize()));
    // psq.setText(Double.toString(p.getpsf()));
    // bedroom.setText(Integer.toString(p.getNumberOfBedroom()));
    // bathroom.setText(Integer.toString(p.getNumberOfBathroom()));
    // rentalPrice.setText(Integer.toString(p.getRental_price()));
    // propertyType.setText(p.getPropertyType());
    // address.setText(p.getAddress());
    // state.setText(p.getState());
    // city.setText(p.getCity());
        GlobalState state = GlobalState.getInstance();
        Property p = state.getSelected();
        p.setAddress(address.getText());
        p.setProjectName(projectName.getText());
        p.setFloorSize(Integer.parseInt(floorSize.getText()));
        p.setpsf(Double.parseDouble(psq.getText()));
        p.setNumberOfBedroom(Integer.parseInt(bedroom.getText()));
        p.setNumberOfBathroom(Integer.parseInt(bathroom.getText()));
        p.setRental_price(Integer.parseInt(rentalPrice.getText()));
        p.setPropertyType(propertyType.getText());
        p.setState(this.state.getText());
        p.setCity(city.getText());
        if(keyFacilities.size() > 0){
            p.setFacilties(keyFacilities);
        }
        if(keyFeatures.size() > 0){
            p.setKeyFeatures(keyFeatures);
        }
        if(photos.size() > 0){
            p.setPhoto(photos);
        }
        //Note need to put actual Role and ID that is stored in the login session later
        state.EditPropertyPerformed(); //save this entry
        //save this object into the global list of objects in GlobalState
        //save this object into list of user properties in GlobalState as well
        System.out.println("Done creating");
    }

    public void activate(ActionEvent event){
        Property p = GlobalState.getInstance().getSelected();
        p.setStatus();
        if(p.getStatus().equals("active")){
            activateButton.setText("Deactivate");
        }
        if(p.getStatus().equals("inactive")){
            activateButton.setText("Activate");
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
