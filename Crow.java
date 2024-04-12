import java.util.ArrayList;
import java.util.Random; 

public class Crow implements Contract{
    
    //attributes
    String name;
    ArrayList<String> cache;
    boolean at_nest;
    double nest_size;

    //constructor
    public Crow(String name){
        this.name = name;
        this.cache = new ArrayList<String>(5);
        this.at_nest = true;
        this.nest_size = 0;
    }
    
    //methods

    /**
     * Puts a given item into the crow's cache.
     * 
     * @param item String which is put into the crow's cache.
     */
    public void grab(String item){
        if (this.cache.size() == 5){
            throw new RuntimeException("Your cache is full. " + item + " won't fit.");
        }
        this.cache.add(item);
        System.out.println("You tucked a " + item + " into your cache.");
    }

    /**
     * Drops a given item from the crow's cache.
     * 
     * @param item String which is dropped from the crow's cache.
     * @return the item that was dropped
     */
    public String drop(String item){
        if (!this.cache.contains(item)){
            throw new RuntimeException(item + " is not contained in your cache. You must scavenge it before dropping it.");
        }
        this.cache.remove(item);
        System.out.println("You dropped a " + item + " from your cache.");
        return item;
    }
    
    /**
     * Crow examines a given item.
     * 
     * @param item String which is examined by crow.
     */
    public void examine(String item){
        System.out.println("You chew on the " + item + ". It's not particularly edible.");
    }

    /**
     * Drops a given item from the crow's cache.
     * 
     * @param item String which is dropped from the crow's cache.
     * @return the item that was dropped
     * @throws RuntimeException if cache doesn't contain item
     */
    public void use(String item){
        if (!this.cache.contains(item)){
            throw new RuntimeException(item + " is not contained in your cache. You must scavenge it before using it.");
        }
        if (item.length() > 5){
            System.out.println(item + " is too big for you to use.");
            this.drop(item);
            return;
        }
        System.out.println("The " + item + " is held in your beak.");
    }
    
    /**
     * Crow walks in a given direction.
     * 
     * @param direction The direction the crow is walking in.
     * @return false - crow is no longer at nest.
     * @throws RuntimeException if direction is not right, left, up, or down.
     */
    public boolean walk(String direction){
        System.out.println("You strut to the " + direction + ".");
        if (!direction.contentEquals("up") && !direction.contentEquals("down") && !direction.contentEquals("left") && !direction.contentEquals("right")){
            throw new RuntimeException("You're a bird. You don't know where " + direction + " is.");
        }
        return false;
    }
    
    /**
     * Crow flies to specified set of coordinates.
     * 
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return false - crow is no longer at nest.
     */
    public boolean fly(int x, int y){
        System.out.println("Flying to " + x + ", " + y + ".");
        return false;
    }

    /**
     * Shrinks the size of the crow's nest.
     * 
     * @return Double equal to 1
     * @throws RuntimeException if crow is not at nest.
     */
    public Double shrink(){
        if (this.at_nest != true){
            throw new RuntimeException("You are not at your nest right now.");
        }
        System.out.println("You threw out the an item from your nest.");
        Double d = new Double(-1);
        return d;
    }
    
    /**
     * Grows the size of the crow's nest.
     * 
     * @return Double equal to -1.
     * @throws RuntimeException if crow is not at the nest.
     * @throws RuntimeException if there is nothing in the crow's cache.
     */
    public Double grow(){
        if (this.at_nest != true){
            throw new RuntimeException("You are not at your nest right now.");
        }
        else if (this.cache.isEmpty()){
            throw new RuntimeException("There is nothing in your cache to grow your nest with.");
        }
        int cache_num = this.getRandomNumber(0,this.cache.size());
        String item = this.cache.get(cache_num);
        use(item);
        if (this.cache.isEmpty()){
            throw new RuntimeException("There is nothing in your cache to grow your nest with.");
        } else {
            System.out.println("You entwined the " + item + " with your nest.");
        }
        this.cache.remove(item);
        Double d = new Double(1);
        return d;
    }

    /**
     * Returns a random number within a range.
     * 
     * @param min The minimum number in range.
     * @param max The maximum number in range.
     * @return random int
     */
    public int getRandomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    
    /**
     * Returns crow home.
     */
    public void rest(){
        System.out.println("Returning home.");
        this.at_nest = true;
    }
    
    /**
     * Crow cannot undoo-doo a car.
     */
    public void undo(){
        System.out.println("You will not un-doo the human's car. You do not regret doo-dooing it.");
    }

    public String toString(){
        return ("This is a crow named " + this.name) + ".";
    }

    public static void main(String[] args) {
        Crow crow = new Crow("button");
        crow.grab("sticks");
        System.out.println(crow);
        //crow.drop("sticks");
        crow.examine("sticks");
        //crow.use("sticks");
        crow.at_nest = crow.walk("left");
        crow.at_nest = crow.fly(3, 5);
        crow.grab("stick");
        crow.nest_size = crow.nest_size + crow.grow();
        System.out.println("The nest is " + crow.nest_size + "items large.");
        crow.nest_size = crow.nest_size + crow.shrink();
        System.out.println("The nest is " + crow.nest_size + "items large.");


    }

}