
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
        
        char findRow[10] = new char {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'}
        
        for (int i = 0; i < 5; i++){
            
            // do while to keep getting input for single ship until it is placed properly.
            do{
                char row, orientation;
                int x, y, ori;
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
                            
                        // If it makes it past the throw command, it converts the char into a number
                        for (int k = 0; k < 10; k++){
                            if (findRow[k] == row){
                                y = k;
                                break;
                            }
                        }
                        
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
                
                // Looops until they enter a proper column number
                while(true){
                    
                    try{
                       System.out.print("Would you like the ship placed verticle or horizontal? (v/h):");
                        orientation = Character.toLowerCase(scanner.next(".").charAt(0))
                        
                        if (orientation != 'v' && orientation != 'h')
                            throw 14;
                        
                        if (orientation == 'v')
                            ori = 0;
                        else 
                            ori = 1;
                        
                        // Breaks out of the while loop with proper input
                        break;
                    } catch(Exception e){
                        System.out.println("Error: Invalid input!\nPlease enter the letter 'v' or 'h'!");
                    }
                    
                }
                
                
            // checks to makes sure that the placement is valid
            }while(setcheck(x, y, ori, ship[i].getSize());
            // If the code gets to here it means the user entered input properly for all three fields
            // and sets the placement of the ship.
            ship[i].setSpot(x, y, ori;      
              
        }
        
    }    
    
    // helper function to see if once the user has found the spot all the fields are free
    // and that it doesn't hang off the edge of the board.
    private boolean setcheck(int x, int y, int ori, int size){
        
        for (int i = 0; i < size; i++){
            if (!primary.checkEmpty(x, y)
                return false;
            
            // if ori is 0 it means verticle so it changes the y value
            // else if it changes the x value for horizotal.
            if (ori == 0)
                y++;
            else 
                x++;
            
        }
        
        if ((ori == 0 && (y + size) >= 10) || (ori == 1 && (x + size) >= 10)
            return false;
        return true;
    }
        
    
}