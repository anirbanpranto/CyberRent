package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Favourite;
import model.GlobalState;
import model.Property;
import model.Database;
import java.util.List;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class favourite_listController {

    GlobalState state = GlobalState.getInstance();

    @FXML
    private Button currentPage;

    @FXML
    private Button favouriteButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label numberOfResults;

    @FXML
    private Button page_Back;

    @FXML
    private Button page_Next;

    @FXML
    private Button profileButton;

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
    private ImageView property1_image;

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
    private ImageView property2_image;

    @FXML
    private Label property2_name;

    @FXML
    private AnchorPane property2_pane;

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
    private ImageView property3_image;

    @FXML
    private Label property3_name;

    @FXML
    private AnchorPane property3_pane;

    @FXML
    private Label property3_price;

    @FXML
    private Label property3_toilet;

    @FXML
    private Label property3_type;

    @FXML
    private Button registerButton;

    private ArrayList<Property> properties;
    private int totalResult;

    @FXML
    private ArrayList<ArrayList<Label>> property_list;
    @FXML
    private ArrayList<ImageView> property_image;
    @FXML
    private ArrayList<AnchorPane> property_pane;

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
            ArrayList<Property> tempFavourite = state.getFavoriteProperties();
            this.numberOfResults.setText(Integer.toString(tempFavourite.size()));
            System.out.println("Current favourite: " + tempFavourite.size());
            this.setProperties(tempFavourite);
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
                String imgPath = properties.get(i + (page * 3)).getPhoto().get(0);
                imgPath = "../view/" + imgPath;
                System.out.println(imgPath);
                Image img1 = new Image(getClass().getResource(imgPath).toURI().toString());
                property_image.get(i).setImage(img1);
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
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @FXML
    void removeFavourite_1(ActionEvent event) {
        int page = Integer.parseInt(currentPage.getText());
        page = page -1;
        ArrayList<Property> favProperties = state.getFavoriteProperties();

        List<List<String>> favList = Database.readData("Favourite");
        for(int i = 0; i < favList.size(); i++){
            if(Integer.parseInt(favList.get(i).get(1)) == state.getLoggedInId())
                if(favList.get(i).get(2).equals(state.getRole()))
                    if(Integer.parseInt(favList.get(i).get(3)) == favProperties.get(0 + (page * 3)).getID()){
                        favList.remove(i);
                        Database.writeAllData("Favourite", favList);
                    }
        }
        state.getFavoriteProperties().remove(0 + (page * 3));
        System.out.println("Deleted favourite");
        //state.updateTempFavouriteList();

        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/favourite_list.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    @FXML
    void removeFavourite_2(ActionEvent event) {
        int page = Integer.parseInt(currentPage.getText());
        page = page -1;

        ArrayList<Property> favProperties = state.getFavoriteProperties();

        List<List<String>> favList = Database.readData("Favourite");
        for(int i = 0; i < favList.size(); i++){
            if(Integer.parseInt(favList.get(i).get(1)) == state.getLoggedInId())
                if(favList.get(i).get(2).equals(state.getRole()))
                    if(Integer.parseInt(favList.get(i).get(3)) == favProperties.get(0 + (page * 3)).getID()){
                        favList.remove(i);
                        Database.writeAllData("Favourite", favList);
                    }
        }
        state.getFavoriteProperties().remove(1 + (page * 3));
        System.out.println("Deleted favourite");
        //state.updateTempFavouriteList();

        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/favourite_list.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    @FXML
    void removeFavourite_3(ActionEvent event) {
        int page = Integer.parseInt(currentPage.getText());
        page = page -1;

        ArrayList<Property> favProperties = state.getFavoriteProperties();

        List<List<String>> favList = Database.readData("Favourite");
        for(int i = 0; i < favList.size(); i++){
            if(Integer.parseInt(favList.get(i).get(1)) == state.getLoggedInId())
                if(favList.get(i).get(2).equals(state.getRole()))
                    if(Integer.parseInt(favList.get(i).get(3)) == favProperties.get(0 + (page * 3)).getID()){
                        favList.remove(i);
                        Database.writeAllData("Favourite", favList);
                    }
        }
        state.getFavoriteProperties().remove(2 + (page * 3));
        System.out.println("Deleted favourite");
        //state.updateTempFavouriteList();

        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/favourite_list.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    @FXML
    void nextPage(ActionEvent event) {
        int maxPage = findTotalPageNumber();
        int pageNumber = Integer.parseInt(currentPage.getText());

        page_Back.setDisable(false);

        if(pageNumber + 1 == maxPage)
            page_Next.setDisable(true);
        currentPage.setText(Integer.toString(pageNumber + 1));

        displayProperties(properties, Integer.parseInt(currentPage.getText()) - 1);
    }

    @FXML
    void previousPage(ActionEvent event) {
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
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/view_property.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    @FXML
    void viewProperty_2(ActionEvent event) {
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
        try{
            Stage mainStage = GlobalState.getInstance().getStage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/view_property.fxml"));
            mainStage.setScene(new Scene(root, 1280, 720));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private void displayError() throws Exception {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login error");
        alert.setHeaderText("Error");
        alert.setContentText("Login is required.");
        alert.showAndWait();
    }

}
