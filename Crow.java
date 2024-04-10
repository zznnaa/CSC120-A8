import java.util.ArrayList;

public class Crow implements Contract{
    
    //attributes
    String name;
    ArrayList<String> cache;
    private boolean at_nest;

    //constructor
    public Crow(String name){
        this.name = name;
        this.cache = new ArrayList<String>(5);
        this.at_nest = true;
    }
    
    //methods
    public void grab(String item){
        if (this.cache.size() == 5){
            throw new RuntimeException("Your cache is full. " + item + " won't fit.");
        }
        this.cache.add(item);
        System.out.println("You tucked a " + item + " into your cache.");
    }

    public String drop(String item){
        if (!this.cache.contains(item)){
            throw new RuntimeException(item + " is not contained in your cache. You must scavenge it before dropping it.");
        }
        this.cache.remove(item);
        System.out.println("You dropped a " + item + " from your cache.");
        return item;
    }
    
    public void examine(String item){
        System.out.println("You chew on the " + item + ". It's not particularly edible.");
    }

    public void use(String item){
        if (!this.cache.contains(item)){
            throw new RuntimeException(item + " is not contained in your cache. You must scavenge it before using it.");
        }
        if (item.length() > 5){
            System.out.println(item + " is too big for you to use.");
            this.drop(item);
        }
        System.out.println("The " + item + " is held in your beak.");
    }
    
    public boolean walk(String direction){
        System.out.println("You strut to the " + direction + ".");
        return false;
    }
    
    // public boolean fly(int x, int y){

    // }
    
    // public Number shrink(){

    // }
    
    // public Number grow(){

    // }
    
    // public void rest(){

    // }
    
    // public void undo(){
        
    // }

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

    }

}

//crow
//undo takes you back to latest action
//shrink & grow give you temp health benefits?
//shrink & grow for nest?