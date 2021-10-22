package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.PublicModel;

/**
 * Provide the slot information of board
 *
 * @author Anirban Bala Pranto
 */
public class BaseController {

  public void up(ActionEvent event){
    System.out.println("Base Interaction");
    //use this code when you are not sure if the stage is the same as our initial stage
    //Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
    Stage mainStage = PublicModel.getInstance().getStage();
    try
    {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Base2.fxml"));
        mainStage.setScene(new Scene(root, 1280, 720));
    }
    catch (java.io.IOException ioe)
    {
        ioe.printStackTrace();
    }
  }
}
