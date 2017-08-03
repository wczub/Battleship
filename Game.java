
import java.util.Scanner;

public class Game{
    Player p1;
    Computer c;
    boolean comp;
    
    
    Game(){
        
        String name = "";
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Please enter your name: ");
        name = scanner.next();
        
        p1 = new Player(name);
        c = new Computer();
        
    }  
    
    public void start(){
        
        boolean p1Win = false, cWin = false;
        
        while (true){
            
            // a player takes a turn, and sees if the computer lost
            // if the computer lost, then it breaks the loop 
            
            turn();
            p1Win = c.lose();
            if (p1Win) 
                break;
            
            compTurn();
            cWin = p1.lose();
            if (cWin)
                break;
            
        }
        p1.updateHit();
        if (p1Win && !cWin){
            System.out.printf("Congratulations %s! You beat the computer!\n", p1.getName());
        } else if (!p1Win && cWin){
            System.out.printf("Oh no! You lost to the computer!\n");
        } else {
            System.out.printf("The game has broken!");
        }
    }
    
    private void turn(){
        
        int coords[] = new int [2];
        
        // Gets the coordinates from the player 
        coords = p1.turn();
        p1.setHM(coords[0], coords[1], c.shotFired(coords[0], coords[1]));
        
    }
    
    private void compTurn(){
        
        int coords[] = new int[2];
        
        coords = c.turn();
        c.setHM(coords[0], coords[1], p1.shotFired(coords[0], coords[1]));
        
    }
}