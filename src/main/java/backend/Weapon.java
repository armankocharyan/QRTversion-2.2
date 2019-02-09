package backend;

public class Weapon extends AdventureCard {
    //Private Attributes
    private int bid;

    //Public Methods

    public Weapon (String name, int battlePoints, int bid){
        type = "Weapon";
        this.name = name;
        this.battlePoints = battlePoints;
        this.bid = bid;
    }

    public int getBid(){return bid;}

    @Override
    public String toString() {
        return String.format("Name: " + name + ", Battle Points: " + battlePoints + ", Bid: " + bid + ", Type: " + type);
    }
}
