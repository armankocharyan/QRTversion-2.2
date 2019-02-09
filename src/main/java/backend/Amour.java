package backend;

public class Amour extends AdventureCard {
    //Private Attributes
    private int bid;

    //Public Methods

    public Amour (){
        type = "Amour";
        this.name = type;
        this.battlePoints = 10;
        this.bid = 1;
    }

    public int getBid(){return bid;}

    @Override
    public String toString() {
        return String.format("Name: " + name + ", Battle Points: " + battlePoints + ", Bid: " + bid + ", Type: " + type);
    }
}
