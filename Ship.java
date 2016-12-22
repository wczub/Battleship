
public class Ship{
    
    // size holds the overall size of the ship
    // life holds how many more hits it can take
    private int size, life;
    private String name;
    
    // together the two arraylists create the coordinates for the ship
    private ArrayList<int> x;
    private ArrayList<int> y;
    
    Ship(String name, int size){
        this.name = name;
        this.size = size;
        life = size;
        x = new ArrayList<int> (size);
        y = new ArrayList<int> (size);
    }
    
    // returns the name of the ship
    public String getName(){
        return name;
    }
    
    // returns the size of the ship. 
    public int getSize(){
        return size;
    }
    
    // sets the initial spot for the ships
    public void setSpot(int xStart, int yStart, int ori){
        
        if (x.isEmpty() && y.isEmpty(){
            for (int i = 0; i < size; i++){
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
    
    public boolean checkHit(int xCo, int yCo){
        
        // checks to make sure that the numbers are possible then it loops
        // through and checks to see if they are a pair and is a hit
        if (x.contains(xCo) && y.contains(yCo){
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