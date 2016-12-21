
import java.util.Scanner;
import java.util.Character.toLowerCase;


public class Player{
    
    private Board tracking;
    private Board primary;
    private Ship ship[5];
    private int shipsLeft;
    
    Player(){
        shipsLeft = 5;
        tracking = new Board();
        primary = new Board();
        placeShips();
        
    }
    
    private void placeShips(){
        
        System.out.print("Player 1: Please place your ships")
        ship[0] = new Ship("Carrier", 5);
        ship[1] = new Ship("Battleship", 4);
        ship[2] = new Ship("Cruiser", 3);
        ship[3] = new Ship("Submarine", 3);
        ship[4] = new Ship("Destroyer", 2);
        
        for (int i = 0; i < 5; i++){
            
            char row;
            int x, y;
            Scanner scanner = new Scanner(System.in);
            
            System.out.printf("Please enter the left/top most spot for %s which is %d spots long.", ship[i].getName(), ship[i].getSize());
            
            // Loops until they enter a proper row letter. 
            while(true){
                
                try{
                    System.out.print("Please enter the row (a-j):");
                    row = Character.toLowerCase(scanner.next(".").charAt(0));
                    
                    // Throws an exception in they didn't enter a letter that is on the grid
                    if (row < 'a' || row > 'j')
                        throw 12;
                    
                    // Breaks out of the while loop with proper input
                    break;
                } catch(Exception e){
                    System.out.println("Error: Invalid input!\nPlease enter a letter from A-J!");
                }
            }
            
            // Looops until they enter a proper column number
            while(true){
                
                try{
                    System.out.print("Please enter the column number (1-10):");
                    x = scanner.nextInt();
                    
                    // Throws exception if they entered a number that is too small or too large
                    if (x < 1 || x > 10)
                        throw 13;
                    
                    // Breaks out of the while loop with proper input
                    break;
                } catch(Exception e){
                    System.out.println("Error: Invalid input!\nPlease enter a number from 1-10!");
                }
                
            }
                    
        }
        
            
            
            
    }
    
}