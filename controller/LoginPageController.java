package controller;

import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.GlobalState;
import javafx.scene.*;
import java.io.IOException;
import javafx.scene.control.ComboBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import model.Database;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginPageController {

    ObservableList<String> roleList = FXCollections.observableArrayList("Tenant", "Owner", "Agent");

    @FXML
    private TextField userInput_Email;

    @FXML
    private PasswordField userInput_Password;

    @FXML
    private ComboBox roleComboBox;

    @FXML
    private void initialize() {
        roleComboBox.setValue("Tenant");
        roleComboBox.setItems(roleList);
    }

    @FXML
    void switchToHomePage(ActionEvent event) throws IOException {
        loadFXML("/view/homepage.fxml");
    }

    @FXML
    void switchToRegister(ActionEvent event) throws IOException {
        loadFXML("/view/registerpage.fxml");
    }

    @FXML
    void validate(ActionEvent event) throws Exception {
        List<List<String>> list = Database.readData(roleComboBox.getValue().toString());
        GlobalState state = GlobalState.getInstance();
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            // to check whether email and password are matched or not
            if (list.get(i).get(3).equals(userInput_Email.getText())
                    && (list.get(i).get(2).equals(userInput_Password.getText()))) {
                GlobalState.getInstance().setLoginStatus();
                flag = true;
                state.setSession(Integer.parseInt(list.get(i).get(0)), list.get(i).get(1), list.get(i).get(2),
                        list.get(i).get(3), roleComboBox.getValue().toString(), list.get(i).get(5));

                if ((roleComboBox.getValue().toString().equals("Agent"))) {
                    state.setAgentLicense(list.get(i).get(7));
                    System.out.println(state.getAgentLicense());
                }

                System.out.println(state.getLoggedInId());
                System.out.println(state.getFullName());
                System.out.println(state.getPassword());
                System.out.println(state.getEmail());
                System.out.println(state.getRole());
                System.out.println(state.getPhoneNumber());

                loadFXML("/view/homepage.fxml");
            }
        }
        if (!flag) {
            try {
                displayError();
                userInput_Email.setText(null);
                userInput_Password.setText(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void displayError() throws Exception {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input error");
        alert.setHeaderText("Error");
        alert.setContentText("Invalid email or password");
        alert.showAndWait();
    }

    private void loadFXML(String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage mainStage = GlobalState.getInstance().getStage();
        mainStage.setScene(new Scene(root, 1280, 720));
    }
}
