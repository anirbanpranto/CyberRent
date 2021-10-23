package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GlobalState;

/**
 * Provide the slot information of board
 *
 * @author Anirban Bala Pranto
 */
public class Base2Controller {
  public void down(ActionEvent event){
    Stage mainStage = GlobalState.getInstance().getStage();
    try
    {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Base.fxml"));
        mainStage.setScene(new Scene(root, 1280, 720));
    }
    catch (java.io.IOException ioe)
    {
        ioe.printStackTrace();
    }
  }
}
