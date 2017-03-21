
import java.util.Scanner;

public class Game{
    Player p1;
    Player p2;
    Computer c;
    boolean comp;
    
    
    Game(int x){
        
        String name = "";
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Please enter your name: ");
        name = scanner.next();
        
        p1 = new Player(name);
        
        
        if (x == 1){
            p2 = new Player();
            comp = false;
        } else {
            c = new Computer();
            comp = true;
        }
    }  
    
    // Starts the game for two player
    public void startTwo(){
        
        boolean p1Win = false, p2Win = false;
        
        while (true){
            
            // a player takes a turn, and sees if the other player lost
            // if the other player lost, then it breaks the loop
            turn(0);
            p1Win = p2.lose();
            if (p1Win) 
                break;
            
            turn(1);
            p2Win = p1.lose();
            if (p2Win)
                break;
            
        }
        
        if (p1Win){
            System.out.printf("Congratulations %s! You beat %s!\n", % p1.getName(), p2.getName());
        } else {
            System.out.printf("Congratulations %s! You beat %s!\n", % p2.getName(), p1.getName());
        }
        
    }
    
    public void startComp(){
        
        boolean p1Win = false, cWin = false;
        
        while (true){
            
            // a player takes a turn, and sees if the other player lost
            // if the other player lost, then it breaks the loop
            turn(2);
            p1Win = c.lose();
            if (p1Win) 
                break;
            
            compTurn();
            cWin = p1.lose();
            if (cWin)
                break;
            
        }
        
        if (p1Win){
            System.out.printf("Congratulations %s! You beat the computer!\n", % p1.getName());
        } else {
            System.out.printf("Oh no! You lost to the computer!\n");
        }
    }
    
    private void turn(int person){
        // if 0 is passed in, player1 takes a turn
        // if 1 is passed in, player2 takes a turn
        // if 2 is passed in, player1 is against a computer
        int coords[] = new int [2];
        
        if (person == 0){
            
            coords = p1.turn();
            p2.updateTrack(coords[0], coords[1]);
            
        } else if (person == 1){
            
            coords = p2.turn();
            p1.updateTrack(coords[0], coords[1]);
        } else if (person == 2){
            
            coords = p1.turn();
            c.updateTrack(coords[0], coords[1]);
        }
        
        
    }   
}