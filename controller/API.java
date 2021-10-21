package controller;

/**
 * This links the model and view together
 * 
 * @author Anirban Bala
 */
public class API {
  
  private static API instance = null;
  
  /**
   * Singleton design pattern
   * Get instance of the APIs
   */
  public static API getInstance() {
    if (instance == null) {
      instance = new API();
    }
    return instance;
  }
}
