
import java.util.Scanner;
import java.lang.Character;

public class Driver{
    public static void main(String[] args){
        
        do{
            welcome();
            Game g = new Game();
            g.start();
        }while(replay());
        
    }
    
    public static void welcome(){
        
        System.out.print("Welcome to Battlefield!\n");
        
        return;
    }
    
    // Asks if the user wants to play the game again
    public static boolean replay(){
        // Loops until they enter proper input 
        while (true){
            char temp;
        
            System.out.print("Would you like to play again? (y/n)");
            
            Scanner scanner = new Scanner(System.in);
        
            // Ensures that the input is proper and acceptable.
            try{
                // Gets a single character, and then converts to lowercase.
                temp = scanner.next().trim().charAt(0);
                temp = Character.toLowerCase(temp);
                
                if (temp != 'y' && temp != 'n')
                    throw new Error();
                else if (temp == 'y')
                    return true;
                else 
                    return false;
                    
            } catch (Exception e){
                System.out.println("Error: Invalid input!\nPlease enter a 'y' or an 'n' only.");
            }
            
        }
    }
}
