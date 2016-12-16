
public class Ship{
    private int size;
    private String name;
    private ArrayList<int> x;
    private ArrayList<int> y;
    
    Ship(String name, int size){
        this.name = name;
        this.size = size;
        x = new ArrayList<int> (size);
        y = new ArrayList<int> (size);
    }
    
    
    public String getName(){
        return name;
    }
    
    public int getSize(){
        return size;
    }
    
    public void setSpot(int xStart, int yStart){
        
        if (x.isEmpty() && y.isEmpty(){
            for (int i = 0; i < size; i++){
                x.add(xStart + i);
                y.add(yStart + i);
            }
        }
        
    }
    
}