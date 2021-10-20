package Views;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;


/**
 * Write a description of JavaFX class MainScreen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainScreen extends Application
{

    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage)
    {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
    
            Scene scene = new Scene(root, 1280, 720);
    
            stage.setTitle("FXML Welcome");
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e){
            
        }
    }
}
