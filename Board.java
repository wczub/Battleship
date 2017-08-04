public class Board{
    
    private int grid[][];
    private static char key[];
    private String name;
    
    static { 
        // This is used to print out the grid in a visualized manner for the user
        // Order goes: blank, carrier, battleship, cruiser, submarine, destroyer, hit, Miss
        key = new char[] {',', 'A', 'B', 'C', 'S', 'D', 'H', 'M'};
    }
    
    Board(String name){
        // Initializes the whole board to zeros, which in the key is the blank space
        // which is denoted by the ',' symbol.
        grid = new int[10][10];
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                grid[i][j] = 0;
            }
        }
        this.name = name;
    }
    
    // Prints the grid out, and I will update this later.
    public void print(){
        
        char[] letter = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'}; 
        
        // prints out the numbers for the top of the grid
        System.out.println("\n"+ name + " grid.");
        System.out.print("  ");
        for (int i = 0; i < 10; i++)
            System.out.printf("%d ", i + 1);
        System.out.println("");
        
        // prints out the letter, followed by the characters for the grid
        for (int i = 0; i < 10; i++){
            System.out.printf("%c ", letter[i] );
            for (int j = 0; j < 10; j++){
                System.out.printf("%c ", key[grid[i][j]]);
            }
            System.out.println("");
        }
        
    }
    
    // used to update the grid, for placing ships and changing the ship
    // to hit
    public void update(int x, int y, int change){
        
        grid[y][x] = change;
    }
    
    // checks to see if the spot is empty for placing ships, and for fast check to see
    // if the player/computer missed completely.
    public boolean checkEmpty(int x, int y){
        // If statement to deal with bad input from computer.
        if (x >= 0 && x <= 9 && y >= 0 && y <= 9)
            return grid[y][x] == 0;
        else
            return false;
    }
    
    public void setShip(int x, int y, int ori, int size, int ship){
        
        for (int i = 0; i < size; i++){
            grid[y][x] = ship;
            
            // if the orientation is set to verticle (0) then the y value gets increased
            // otherwise if it is set to horizontal (1) then the x value gets increased
            if (ori == 0)
                y++;
            else
                x++;    
        }
    }
    
}