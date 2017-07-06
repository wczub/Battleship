import java.util.Random;


public class Computer{
    
    private Board tracking;
    private Board primary;
    private Ship ship[5];
    private int shipsLeft;
    
    
    Computer(){
        
        tracking = new Board();
        primary = new Board();
        shipsLeft = 5;
        ship[0] = new Ship("Carrier", 5, 1);
        ship[1] = new Ship("Battleship", 4, 2);
        ship[2] = new Ship("Cruiser", 3, 3);
        ship[3] = new Ship("Submarine", 3, 4);
        ship[4] = new Ship("Destroyer", 2, 5);
        placeShips();
    }
    
    // Places ships randomly across the grid
    private void placeShips(){
        Random r = new Random();
        
        for (int i = 0; i < 5; i++){
            boolean nextShip = true;
            do {
                int x, y, ori;
                ori = r.nextInt(100) % 2;
                
                // 0 = verticle, 1 = horizontal
                if (ori == 0)
                    y = r.nextInt(9-ship[i].getSize());
                else
                    x = r.nextInt(9-ship[i].getSize());    
                
                for (int j = 0; j < ship[i].getSize(); j++){
                    
                    // Allows me to not have to change the original x or y value
                    int yy = y;
                    int xx = x;
                    // checks to see if spot it is placed is empty
                    if (!primary.checkEmpty(xx,yy)){
                        nextShip = false;
                        continue;
                    }
                        
                    // if the orientation is set to verticle (0) then the y value gets increased
                    // otherwise if it is set to horizontal (1) then the x value gets increased                    if (ori == 0)
                    if
                        yy++;
                    else
                        xx++;
                }
            } while(nextShip);
            
            ship[i].setSpot(x, y, ori);
            primary.setShip(x, y, ori, ship[i].size(), ship[i].getLetter());
        }
        
    }
    
    // Checks to see if a ship is hit, and updates everything.
    public int shotFired(int x, int y){
        
        for (int i = 0; i < 5; i++){
            if (ship[i].checkHit(x, y)){
                System.out.printf("You have hit their %s!", ship[i].getName());
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
}