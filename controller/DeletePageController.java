package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Database;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import model.GlobalState;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ComboBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import model.*;

public class DeletePageController {

    ObservableList<String> listType_tempCombo = FXCollections.observableArrayList("Pending Registration", "Active User");
    ObservableList<String> userType_tempCombo = FXCollections.observableArrayList("Tenant", "Owner", "Agent");

    @FXML
    private ComboBox listType_ComboBox;
    @FXML
    private ComboBox userType_ComboBox;
    @FXML
    private ArrayList<Label> info_list;

    @FXML
    private ArrayList<Button> delete_list;

    @FXML
    private ArrayList<Button> accept_list;

    @FXML
    private GridPane userGridPane;

    @FXML
    private Button currentPage;
    @FXML
    private Button page_Back;
    @FXML
    private Button page_Next;

    private int totalResult;
    private List<List<String>> userList;

    @FXML
    public void initialize(){
        listType_ComboBox.setItems(listType_tempCombo);
        userType_ComboBox.setItems(userType_tempCombo);

        selectListType("Tenant", "Pending Registration");
    }

    @FXML
    public void nextPage(ActionEvent event) {
        int maxPage = findTotalPageNumber();
        int pageNumber = Integer.parseInt(currentPage.getText());

        page_Back.setDisable(false);

        if(pageNumber + 1 == maxPage)
            page_Next.setDisable(true);
            currentPage.setText(Integer.toString(pageNumber + 1)); 

        displayList(Integer.parseInt(currentPage.getText()) - 1);
    }

    @FXML
    public void previousPage(ActionEvent event) { 
        int pageNumber = Integer.parseInt(currentPage.getText());

        page_Next.setDisable(false);

        if(pageNumber - 1 == 1)
            page_Back.setDisable(true);
            currentPage.setText(Integer.toString(pageNumber - 1));
            
        displayList(Integer.parseInt(currentPage.getText()) - 1);
    }

    private int findTotalPageNumber(){
        double max = Double.valueOf(totalResult) / 9;
        if((max - (totalResult/9)) == 0)
            return totalResult/9;
        else
            return totalResult/9 + 1;
    }

    @FXML
    void switchToHomePage(ActionEvent event) throws IOException {
        loadFXML("/view/homepage.fxml");
    }

    @FXML
    public void accept(ActionEvent event) throws Exception{
        int page = Integer.parseInt(currentPage.getText()) - 1;
        String role = userType_ComboBox.getValue().toString();
        if(confirmationAlert("accept").equals("yes")){
            Button currentButton = (Button)event.getSource();
            for(int i = 0; i < accept_list.size(); i++){
                if(accept_list.get(i).equals(currentButton)){
                    createAccount(role,(i + (page * 9)));
                    userList.remove(i + (page * 9));
                    Database.writeAllData(role+"_temp", userList);
                    break;
                }
            }
        displayList(0);

        currentPage.setText("1");
        page_Back.setDisable(true);
        if(userList.size() > 9)
            page_Next.setDisable(false);
        }
    }

    @FXML
    public void delete(ActionEvent event) throws Exception {
        int page = Integer.parseInt(currentPage.getText()) - 1;

        if(confirmationAlert("delete").equals("yes")){
            Button currentButton = (Button)event.getSource();
            for(int i = 0; i < delete_list.size(); i++){
                if(delete_list.get(i).equals(currentButton)){
                    userList.remove(i + (page * 9));
                    break;
                }
            }

        if (listType_ComboBox.getValue().equals("Active User")){
            if (userType_ComboBox.getValue().equals("Tenant")){
                Database.writeAllData("Tenant", userList);
                System.out.println(userList);
            }
            else if (userType_ComboBox.getValue().equals("Owner")){
                Database.writeAllData("Owner", userList);
                System.out.println(userList);
            }
            else{
                Database.writeAllData("Agent", userList);
                System.out.println(userList);
            }
        }
        else{ //Pending Registration
            if (userType_ComboBox.getValue().equals("Tenant")){
                Database.writeAllData("Tenant_temp", userList);
                System.out.println(userList);
            }
            else if (userType_ComboBox.getValue().equals("Owner")){
                Database.writeAllData("Owner_temp", userList);
                System.out.println(userList);
            }
            else{
                Database.writeAllData("Agent_temp", userList);
                System.out.println(userList);
            }
        }    

        displayList(0);

        currentPage.setText("1");
        page_Back.setDisable(true);
        if(userList.size() > 9)
            page_Next.setDisable(false);
        }
    }

    @FXML
    public void selectListType(ActionEvent e){
        String role = userType_ComboBox.getValue().toString();
        String listType = listType_ComboBox.getValue().toString();

        if(listType.equals("Pending Registration"))
            userList = Database.readData(role+"_temp");
        else // Active User
            userList = Database.readData(role);
            
        totalResult = userList.size();

        if(totalResult <= 9)
            page_Next.setDisable(true);
        else
            page_Next.setDisable(false);
            
        displayList(0);

    }

    private void createAccount(String role, int index){
        if (role.equals("Tenant")){
            Tenant.createTenant(null, userList.get(index).get(2),userList.get(index).get(3), "Tenant", null);
        }
        else if (role.equals("Owner")){
            Owner.createOwner(null, userList.get(index).get(2), userList.get(index).get(3), null);
        }
        else{
            Agent.createAgent(null, userList.get(index).get(2), userList.get(index).get(3), null, null);
        }
    }

    public void selectListType(String role, String listType){
        userType_ComboBox.setValue(role);
        listType_ComboBox.setValue(listType);

        if(listType.equals("Pending Registration"))
            userList = Database.readData(role+"_temp");
        else // Active User
            userList = Database.readData(role);
        
        totalResult = userList.size();

        if(totalResult <= 9)
            page_Next.setDisable(true);
        else
            page_Next.setDisable(false);
        
        displayList(0);
    }

    public void displayList(int page){
        if(listType_ComboBox.getValue().toString().equals("Active User")) // Active users page doesn't need to accept
            for(int i = 0; i < accept_list.size(); i++)
                accept_list.get(i).setVisible(false);

        for(int i = 0; i < info_list.size(); i++){
            try{
                if(listType_ComboBox.getValue().toString().equals("Pending Registration"))
                    accept_list.get(i).setVisible(true);

                info_list.get(i).setVisible(true);
                delete_list.get(i).setVisible(true);
                info_list.get(i).setText(userList.get(i + (page * 9)).get(3));
            }catch(IndexOutOfBoundsException e){
                if(listType_ComboBox.getValue().toString().equals("Pending Registration"))
                    accept_list.get(i).setVisible(false);

                info_list.get(i).setVisible(false);
                delete_list.get(i).setVisible(false);
            }
        }
    }

    private String confirmationAlert(String type) throws Exception {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(type + " user confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Confirm to " + type + " this user");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
            return "yes";
        else
            return "no";
    }

    private void loadFXML(String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage mainStage = GlobalState.getInstance().getStage();
        mainStage.setScene(new Scene(root, 1280, 720));
    } 
}

