import java.util.Scanner;
import java.lang.Character;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Player{
    
    private Board tracking;
    private Board primary;
    private Ship[] ship;
    private int shipsLeft;
    private String name;
    private char[] findRow;
    private int hit[][];
    
    Player(){
        shipsLeft = 5;
        tracking = new Board("Tracking");
        primary = new Board("Primary");
        ship = new Ship[5];
        ship[0] = new Ship("Aircraft Carrier", 5, 1);
        ship[1] = new Ship("Battleship", 4, 2);
        ship[2] = new Ship("Cruiser", 3, 3);
        ship[3] = new Ship("Submarine", 3, 4);
        ship[4] = new Ship("Destroyer", 2, 5);
        name = "Player 1";
        findRow = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        placeShips();
        hit = new int [10][10];
        popHit();
       
    }
    
    Player(String name){
        shipsLeft = 5;
        tracking = new Board("Tracking");
        primary = new Board("Primary");
        ship = new Ship[5];
        ship[0] = new Ship("Aircraft Carrier", 5, 1);
        ship[1] = new Ship("Battleship", 4, 2);
        ship[2] = new Ship("Cruiser", 3, 3);
        ship[3] = new Ship("Submarine", 3, 4);
        ship[4] = new Ship("Destroyer", 2, 5);
        findRow = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        placeShips();
        this.name = name;
        hit = new int [10][10];
        popHit();

    }
    
    // function that handles most everything for placing ships. 
    private void placeShips(){
        
        System.out.printf("Please place your ships", name);
        primary.print();
        
        
        
        for (int i = 0; i < 5; i++){
            char row, orientation;
            int x, y, ori;
            // do while to keep getting input for single ship until it is placed properly.
            do{
                
                Scanner scanner = new Scanner(System.in);
                
                System.out.printf("Please enter the top-left most spot for %s which is %d spots long.\n", ship[i].getName(), ship[i].getSize());
                
                // Calls helper funtions to get input for coordinates
                y = getRow();
                x = getColumn();
                
                // Looops until they enter a proper column number
                while(true){
                    
                   System.out.print("Would you like the ship placed verticle or horizontal? (v/h):");
                    orientation = Character.toLowerCase(scanner.next(".").charAt(0));
                    
                    if (orientation == 'v'){
                        ori = 0;
                        break;   
                    }else if (orientation == 'h'){
                        ori = 1;
                        break;
                    }
                        
                    System.out.println("Error: Invalid input!\nPlease enter the letter 'v' or 'h'!");
                    
                }
                
            // checks to makes sure that the placement is valid
            }while(!setCheck(x, y, ori, ship[i].getSize()));
            
            // If the code gets to here it means the user entered input properly for all three fields
            // and the placement is in a valid location then it sets the placement of the ship.
            ship[i].setSpot(x, y, ori);  
            primary.setShip(x, y, ori, ship[i].getSize(), ship[i].getLetter());
            
            System.out.println("Here is your updated board!");
            primary.print();
              
        }
        
    }    
    
    // helper function to see if once the user has found the spot all the fields are free
    // and that it doesn't hang off the edge of the board.
    private boolean setCheck(int x, int y, int ori, int size){
        
        // Checks to see if the ship hangs off the edge of the board.
        if ((ori == 0 && (y + size) >= 10) || (ori == 1 && (x + size) >= 10)){
            System.out.println("Error: Ship placment hangs off of grid.");
            return false;
        }
        
        for (int i = 0; i < size; i++){
            
            // if any of the spots are taken, then it returns false
            if (!primary.checkEmpty(x, y)){
                System.out.println("Error: Another ship is placed in the same location.");
                return false;
            }
            
            // if ori is 0 it means verticle so it changes the y value
            // else it changes the x value for horizotal.
            if (ori == 0)
                y++;
            else 
                x++;
            
        }
        
        // If it passes all the checks, then it's safe to place the ship
        return true;
    }
        
    public boolean lose(){
        return (shipsLeft == 0);
    }
    
    public int[] turn(){
        
        int[] coords = new int [2];
        boolean endTurn = false;
        tracking.print();
        System.out.println();
        primary.print();
        System.out.println("It is your turn! ");
        do {
            
            coords[1] = getRow();
            coords[0] = getColumn();
            
            // If the player doesn't choose an empty spot, then they have to enter a new spot
            if (tracking.checkEmpty(coords[0], coords[1]))
                endTurn = true;
            else
                System.out.println("Error: You have already fired there.");
        } while (!endTurn);
        char[] letter = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        System.out.printf("\n\nYou fired at %s%d. ", letter[coords[1]], (coords[0] + 1));
        return coords;
    }

    // Checks to see if a ship is hit, and updates everything.
    public int shotFired(int x, int y){
        
        for (int i = 0; i < 5; i++){
            if (ship[i].checkHit(x, y)){
                System.out.printf("Computer has hit your %s!\n", ship[i].getName());
                primary.update(x, y, 6);
                
                // Updates hit to later be stored back into the file
                hit[y][x] += 1;
                
                if (ship[i].isDead()){
                    shipsLeft--;
                }
                
                // Returns 6 to signal a hit to be placed on their grid
                return 6;
            }
        }
        // Updates your board so you can see where the computer has gone. 
        // This is mostly for debugging, but I may leave it in.
        primary.update(x,y,7);
        // Returns 7 to signal a miss to be placed on the grid
        return 7;
    }
    
    // Updates the grid to show a hit or miss after each turn
    public void setHM(int x, int y, int hm){
        
        tracking.update(x, y, hm);
    }

    private int getRow(){
        int y = 0;
        boolean getInput = true;
        // Loops until they enter a proper row letter. 
        while(getInput){

            char row;
            Scanner scanner = new Scanner(System.in);

            System.out.print("Please enter the row (a-j):");
            row = Character.toLowerCase(scanner.findInLine(".").charAt(0));
                
            // If it makes it past the throw command, it converts the char into a number
            for (int k = 0; k < 10; k++){
                
                if (findRow[k] == row){
                    y = k;
                    getInput = false;
                }
            }
                
        }
        
        return y;
    }

    private int getColumn(){
        int x;
        Scanner scanner = new Scanner(System.in);

        // Looops until they enter a proper column number
        while(true){
            
            System.out.print("Please enter the column number (1-10):");
            x = scanner.nextInt();
            
            if (x >= 1 & x <= 10)
                return x - 1; // x is given 1-10 but needs to be 0-9 for the array
            
        }
    }
    
    public String getName(){
        return name;
    }
    
    // Populates the hit array for use in the turn method
    private void popHit(){
        
        try{
            Scanner h = new Scanner(new File("./stat/hit.txt"));
            
            for (int i = 0; i < 10; i++){
                for (int j = 0; j < 10; j++){
                    hit[i][j] = h.nextInt();
                }
            }
        } catch(IOException e){
            System.out.println("Failed to open hit.txt");
        }
    }
    
    // writes the new hit array to the file to save the info for the next game.
    public void updateHit(){
        try {
            PrintWriter writer = new PrintWriter("./stat/hit.txt", "UTF-8");
            
            for (int i = 0; i < 10; i++){
                for (int j = 0; j < 10; j++){
                    writer.printf("%d ", hit[i][j]);
                }
                writer.println();
            }
            writer.close();
        }catch(IOException e){
            System.out.println("Failed to save Hit to file.");
        }
    }
}