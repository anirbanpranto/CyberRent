/**
 * @author Anirban Bala
 */
public class Main {

  public static void main(String[] args) {

    Program program = Program.getInstance();
    try {
      program.run();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
      return;
    }
    
  }

}