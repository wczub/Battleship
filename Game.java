

public class Game{
    Player p1;
    Player p2;
    Computer c;
    boolean comp;
    
    
    Game(int x){
        
        p1 = new Player();
        if (x == 1){
            p2 = new Player();
            comp = false;
        } else {
            c = new Computer();
            comp = true;
        }
    }  
    
    public void start(){
        
        
        
    }
    
    
}