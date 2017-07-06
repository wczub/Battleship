public class Computer{
    
    private Board tracking;
    private Board primary;
    private Ship ship[5];
    private int shipsLeft;
    
    
    Computer(){
        
        tracking = new Board();
        primary = new Board();
        shipsLeft = 5;
        
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