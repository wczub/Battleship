import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class Computer{
    
    private Board tracking;
    private Board primary;
    private Ship[] ship = new Ship[5];
    private int shipsLeft;
    private int fire[][];
    private int hit[][];
    private int prevCO[];
    private int checkNum;
    private boolean prevHit;
    
    
    Computer(){
        
        tracking = new Board("Tracking");
        primary = new Board("Primary");
        shipsLeft = 5;
        ship[0] = new Ship("Aircraft Carrier", 5, 1);
        ship[1] = new Ship("Battleship", 4, 2);
        ship[2] = new Ship("Cruiser", 3, 3);
        ship[3] = new Ship("Submarine", 3, 4);
        ship[4] = new Ship("Destroyer", 2, 5);
        popFireHit();
        placeShips();
        prevCO = new int[] {-1, -1};
        prevHit = false;
        checkNum = 0;
    }
    
    // Places ships randomly across the grid
    private void placeShips(){
        Random r = new Random();
        boolean nextShip = true;
        for (int i = 0; i < 5; i++){
            
            int x, y, ori;
            do {
                nextShip = true;
                ori = r.nextInt(100) % 2;
                
                // 0 = verticle, 1 = horizontal
                if (ori == 0){
                    y = r.nextInt(9-ship[i].getSize());
                    x = r.nextInt(9);
                }
                else{
                    y = r.nextInt(9);
                    x = r.nextInt(9-ship[i].getSize());
                }
                
                // Allows me to not have to change the original x or y value
                int yy = y;
                int xx = x;
                for (int j = 0; j < ship[i].getSize(); j++){
                    
                    // checks to see if spot it is placed is empty
                    if (!primary.checkEmpty(xx,yy)){
                        nextShip = false;
                        continue;
                    }
                        
                    // if the orientation is set to verticle (0) then the y value gets increased
                    // otherwise if it is set to horizontal (1) then the x value gets increased                    if (ori == 0)
                    if(ori == 0)
                        yy++;
                    else
                        xx++;
                }
            } while(!nextShip);
            
            ship[i].setSpot(x, y, ori);
            primary.setShip(x, y, ori, ship[i].getSize(), ship[i].getLetter());
        }
        
    }
    
    // Searches for ships in a checkerboard pattern. Ex: only looking at the white squares
    // When it finds a ship, it searches the four squares around it.
    public int[] turn(){
        Random r = new Random();
        int[] coords = new int[2];
        
        // Loops while the random location is not empty
        do{
            if (prevHit && checkNum < 4){
                checkNum += 1;
                
                // If there was a previous hit, then it checks the adjacent squares
                // If a square is out of bounds, it goes to the next square.
                // If a spot is not empty it will go back through the while loop
                // This is completely unoptimized. Furture optimizations later
                if (checkNum == 1){
                    coords[0] = prevCO[0] - 1;
                    coords[1] = prevCO[1];
                    if (oneTen(coords[0]) || oneTen(coords[1]))
                        checkNum += 1;
                }
                if (checkNum == 2){
                    coords[0] = prevCO[0] + 1;
                    coords[1] = prevCO[1];
                    if (oneTen(coords[0]) || oneTen(coords[1]))
                        checkNum += 1;
                }
                if (checkNum == 3){
                    coords[0] = prevCO[0];
                    coords[1] = prevCO[1] - 1;
                    if (oneTen(coords[0]) || oneTen(coords[1]))
                        checkNum += 1;
                }
                if (checkNum == 4){
                    coords[0] = prevCO[0];
                    coords[1] = prevCO[1] + 1;
                    if (oneTen(coords[0]) || oneTen(coords[1]))
                        checkNum += 1;
                    prevHit = false;
                    checkNum = 0;
                }
                
            } else {
                // Both numbers need to be odd or both even to stay on the grid
                coords[0] = r.nextInt(5) * 2;
                coords[1] = r.nextInt(5) * 2;
                
                // This allows the numbers to randomly stay odd, or be even.
                if (r.nextInt(9) % 2 == 0){
        
                    coords[0] += 1;
                    coords[1] += 1;
                }
            }
        }while(!tracking.checkEmpty(coords[0], coords[1]));
        
        // prevHit doesn't get reset until after all four spots checked.
        // then and only then will prevCO update with the new random spot.
        if (!prevHit){
            prevCO[0] = coords[0];
            prevCO[1] = coords[1];
        }
        
        char[] letter = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        System.out.printf("\nThe Computer fired at %s%d. ", letter[coords[1]], (coords[0] + 1));
        return coords;
    }
    
    // Used in turn to go to next square to check if it ends up outside the playing field.
    private Boolean oneTen(int a){
        return (a < 0 || a > 9);
    }
    
    // Checks to see if a ship is hit, and updates everything.
    public int shotFired(int x, int y){
        
        // Every time the player fires, it increases one to the fire array.
        fire[y][x] += 1;
        
        for (int i = 0; i < 5; i++){
            if (ship[i].checkHit(x, y)){
                
                primary.update(x, y, 6);
                
                if (ship[i].isDead()){
                    shipsLeft--;
                    
                    // Seperate return statement to give unique message for ship sinking.
                    System.out.printf("Hit and Sunk!");
                    return 6;
                }
                
                // For normal non ship sinking hits.
                System.out.printf("It's a Hit!");
                // Returns 6 to signal a hit to be placed on their grid
                return 6;
            }
        }
    
        // Returns 7 to signal a miss to be placed on the grid
        return 7;
    }
    
    // Updates the grid to show a hit or miss after each turn
    public void setHM(int x, int y, int hm){
     
        // Lets the computer know they got a hit.
        if (hm == 6)
            prevHit = true;
            
        tracking.update(x, y, hm);
    }
    
    public boolean lose(){
        return (shipsLeft == 0);
    }
    
    // Takes in both files Fire and Hit, and store them into arrays.
    // They fire will be update here in computer, and hit will be updated in player
    private void popFireHit(){
        
        try{
            Scanner f = new Scanner(new File("./stat/fire.txt"));
            Scanner h = new Scanner(new File("./stat/hit.txt"));
            
            fire = new int [10][10];
            hit = new int [10][10];
            for (int i = 0; i < 10; i++){
                for (int j = 0; j < 10; j++){
                    fire[i][j] = f.nextInt();
                    hit[i][j] = h.nextInt();
                }
            }
        
        }catch(Exception e){
            System.out.println("Error: Failed to open files");
        }
    }
    
    // Takes the new fire array and stores in in a file.
    public void updateFire(){
        try {
            PrintWriter writer = new PrintWriter("./stat/fire.txt", "UTF-8");
            
            for (int i = 0; i < 10; i++){
                for (int j = 0; j < 10; j++){
                    writer.printf("%d ", fire[i][j]);
                }
                writer.println();
            }
            writer.close();
        }catch(IOException e){
            System.out.println("Failed to save Fire to file.");
        }
    }
}