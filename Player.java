
import java.util.Scanner;
import java.lang.Character;


public class Player{
    
    private Board tracking;
    private Board primary;
    private Ship[] ship;
    private int shipsLeft;
    private String name;
    private char[] findRow;
    
    Player(){
        shipsLeft = 5;
        tracking = new Board("Tracking");
        primary = new Board("Primary");
        ship = new Ship[5];
        ship[0] = new Ship("Carrier", 5, 1);
        ship[1] = new Ship("Battleship", 4, 2);
        ship[2] = new Ship("Cruiser", 3, 3);
        ship[3] = new Ship("Submarine", 3, 4);
        ship[4] = new Ship("Destroyer", 2, 5);
        name = "Player 1";
        findRow = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        placeShips();
       
    }
    
    Player(String name){
        shipsLeft = 5;
        tracking = new Board("Tracking");
        primary = new Board("Primary");
        ship = new Ship[5];
        ship[0] = new Ship("Carrier", 5, 1);
        ship[1] = new Ship("Battleship", 4, 2);
        ship[2] = new Ship("Cruiser", 3, 3);
        ship[3] = new Ship("Submarine", 3, 4);
        ship[4] = new Ship("Destroyer", 2, 4);
        findRow = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        placeShips();
        this.name = name;

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
                
                System.out.printf("Please enter the top-left most spot for %s which is %d spots long.", ship[i].getName(), ship[i].getSize());
                
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
            
            coords[0] = getRow();
            coords[1] = getColumn();
            
            // If the player doesn't choose an empty spot, then they have to enter a new spot
            if (tracking.checkEmpty(coords[0], coords[1]))
                endTurn = true;
            else
                System.out.println("Error: You have already fired there.");
        } while (endTurn);
        return coords;
    }

    // Checks to see if a ship is hit, and updates everything.
    public int shotFired(int x, int y){
        
        for (int i = 0; i < 5; i++){
            if (ship[i].checkHit(x, y)){
                System.out.printf("Computer has hit your %s!", ship[i].getName());
                primary.update(x, y, 7);
                
                // Returns 7 to signal a hit to be placed on their grid
                return 7;
            }
        }
        
        // Returns 8 to signal a miss to be placed on the grid
        return 8;
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
                    System.out.println("here");
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
                return x;
            
        }
    }
    
    public String getName(){
        return name;
    }
}