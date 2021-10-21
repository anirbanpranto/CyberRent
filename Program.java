/**
 * @author Anirban Bala Pranto
 */
public class Program {

  public static Program getInstance() {
    if (instance == null) {
      instance = new Program();
    }
    return instance;
  }

  private static Program instance;

  private Program() {
    //create a main window
  }

  public void run() {
    //start the window
  }

}