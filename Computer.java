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
                System.out.printf("x: %d, y: %d, ori: %d\n", x, y, ori);
                
                // Allows me to not have to change the original x or y value
                int yy = y;
                int xx = x;
                for (int j = 0; j < ship[i].getSize(); j++){
                System.out.println("For loop "  + j);
                    
                    // checks to see if spot it is placed is empty
                    if (!primary.checkEmpty(xx,yy)){
                        nextShip = false;
                        System.out.println("primary check empty");
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
    
    public int[] turn(){
        Random r = new Random();
        int[] coords = new int[2];
        
        // Loops while the random location is not empty
        do{
            coords[0] = r.nextInt(9);
            coords[1] = r.nextInt(9);
        }while(!primary.checkEmpty(coords[0], coords[1]));
        
        char[] letter = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        System.out.printf("\nThe Computer fired at %s%d. ", letter[coords[0]], (coords[1] + 1));
        return coords;
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
    
    private void updateFire(){
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