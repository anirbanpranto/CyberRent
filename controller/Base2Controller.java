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
public class Base2Controller {
  public void down(ActionEvent event){
    Stage mainStage = PublicModel.getInstance().getStage();
    try
    {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Base.fxml"));
        mainStage.setScene(new Scene(root));
    }
    catch (java.io.IOException ioe)
    {
        ioe.printStackTrace();
    }
  }
}
