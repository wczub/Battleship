
import java.util.Scanner;
import java.util.Character.toLowerCase;

public class Driver{
    public static void main(String[] args){
        
        do{
            welcome();
            Game g;
            g.start();
        }while(replay());
        
    }
    
    public welcome(){
        
        System.out.print("Welcome to Battlefield!");
        
        return;
    }
    
    // Asks if the user wants to play the game again
    public boolean replay(){
        // Loops until they enter proper input 
        while (true){
            char temp = "";
        
            System.out.print("Would you like to play again? (y/n)");
            
            Scanner scanner = new Scaner(System.in);
        
            // Ensures that the input is proper and acceptable.
            try{
                // Gets a single character, and then converts to lowercase.
                temp = scanner.next().trim().charAt(0);
                temp = Character.toLowerCase(temp);
                
                if (temp != "y" && temp != "n")
                    throw 11;
                    
            } catch (Exception e){
                System.out.println("Error: Invalid input!\nPlease enter a 'y' or an 'n' only.");
            }
            return temp;
        }
        
    }
}
