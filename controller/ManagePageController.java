package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.GlobalState;
import javafx.fxml.FXMLLoader;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import model.Database;
import java.util.ArrayList;
import javafx.scene.control.Label;
import controller.DeletePageController;

public class ManagePageController {

    ObservableList<String> roleList = FXCollections.observableArrayList("Tenant","Owner","Agent");
    ObservableList<String> typeList = FXCollections.observableArrayList("Pending Registration", "Active User");

    @FXML
    private ComboBox roleComboBox;

    @FXML
    private ComboBox typeListComboBox;

    @FXML
    private ArrayList<Label> info_list; 

    @FXML
    private void initialize(){

        roleComboBox.setValue("Tenant");
        roleComboBox.setItems(roleList);

        typeListComboBox.setValue("Pending Registration");
        typeListComboBox.setItems(typeList);
    }
    @FXML
    public void switchToHomePage(ActionEvent event) throws IOException{
        loadFXML("/view/homepage.fxml");
    }

    public void confirm(ActionEvent event) throws IOException {
        String role = roleComboBox.getValue().toString();
        String listType = typeListComboBox.getValue().toString();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/deletepage.fxml"));
        Parent root = loader.load();

        DeletePageController deletepageController = loader.getController();
        deletepageController.selectListType(role, listType);

        Stage mainStage = GlobalState.getInstance().getStage();
        mainStage.setScene(new Scene(root, 1280, 720));
    }
    
    private void loadFXML(String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage mainStage = GlobalState.getInstance().getStage();
        mainStage.setScene(new Scene(root, 1280, 720));
    } 

}
