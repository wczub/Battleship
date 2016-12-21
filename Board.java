public class Board{
    
    private int grid[10][10];
    private static char key[];
    
    static { 
        key = new char[7] {"#", "A", "B", "C", "S", "D", "H"};
    }
    
    Board(){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                grid[i][j] = 0;
            }
        }
    }
    
    public void printGrid(){
        
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                System.out.printf("%c ", % key[grid[i][j])
            }
        }
        
    }
    
}