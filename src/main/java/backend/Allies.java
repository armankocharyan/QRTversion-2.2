package backend;

public class Allies extends AdventureCard{

    //Private Attributes
    private int bid;

    //Public Methods

    public Allies (String name, int battlePoints, int bid){
        this.name = name;
        this.battlePoints = battlePoints;
        this.bid = bid;
        type = "Ally";
    }

    @Override
    public String toString() {
        return String.format("Name: " + name + ", Battle Points: " + battlePoints + ", Bid: " + bid + ", Type: " + type);
    }
}
