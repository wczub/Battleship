import java.util.ArrayList;

public class Ship{
    
    // size holds the overall size of the ship
    // life holds how many more hits it can take
    private int size, life, letter;
    private String name;
    
    // together the two arraylists create the coordinates for the ship
    private ArrayList<Integer> x;
    private ArrayList<Integer> y;
    
    Ship(String name, int size, int letter){
        this.name = name;
        this.size = size;
        life = size;
        x = new ArrayList<Integer> ();
        y = new ArrayList<Integer> ();
        this.letter = letter;
    }
    
    // returns the name of the ship
    public String getName(){
        return name;
    }
    
    // returns the size of the ship. 
    public int getSize(){
        return size;
    }
    
    // Returns the number for the letter of the ship for the baord
    public int getLetter(){
        return letter;
    }
    
    // sets the initial spot for the ships
    public void setSpot(int xStart, int yStart, int ori){
        
        if (x.isEmpty() && y.isEmpty()){
            for (int i = 0,j = 0, k = 0; i < size; i++){
                x.add(xStart + j);
                y.add(yStart + k);
                
                // if the orientation is set to verticle (0) then the y value gets increased
                // otherwise if it is set to horizontal (1) then the x value gets increased
                if (ori == 0)
                    k++;
                else 
                    j++;
                
            }
        }
        
    }
    
    // Returns true if hit, False if it missed
    public boolean checkHit(int xCo, int yCo){
        
        // checks to make sure that the numbers are possible then it loops
        // through and checks to see if they are a pair and is a hit
        if (x.contains(xCo) && y.contains(yCo)){
            for(int i = 0; i < life; i++){
                if (x.get(i) == xCo && y.get(i) == yCo){
                    x.remove(i);
                    y.remove(i);
                    life--;
                    return true;
                }
            }
        }
        return false;
        
    }
    
    // returns whether the ship is still alive or not
    public boolean isDead(){
        return (life == 0);
    }
    
}