
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import Models.Owner;

public class App
{
    public static void main(String[] args){
        Owner me = new Owner("Anirban", "12345", "anirban@auronex.com", "password1");
        System.out.println(me.getName());
    }
}
