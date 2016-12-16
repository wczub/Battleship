
import java.util.Scanner;
import java.util.Character.toLowerCase;

public class Driver{
    public static void main(String[] args){
        
        do{
            Game g;
            g.start(welcome());
        }while(replay());
        
    }
    
    public int welcome(){
        int temp = 0;
        // This will print out a better welcome message later
        System.out.print("Welcome to Battlefield!");
        
        // Loops until they choose either 1 or 2.
        while (true){
            System.out.println("What game mode would you like to play?\n1: Single Player\n2: Two Player");
            
            Scanner scanner = new Scanner(System.in);
            
            try{
                temp = scanner.nextInt();
                if (temp != 1 && temp != 2)
                    throw;
                
            } catch(Exception e){
                System.out.println("Error: Invalid input!\nPlease enter a 1 or a 2 only.");
            }
        
            return temp;
        }
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
                    throw;
                    
            } catch (Exception e){
                System.out.println("Error: Invalid input!\nPlease enter a 'y' or an 'n' only.");
            }
            return temp;
        }
        
    }
}
