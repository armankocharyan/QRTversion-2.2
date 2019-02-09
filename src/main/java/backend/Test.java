package backend;

public class Test extends AdventureCard {
    //Private Attributes
    private int altBid;

    //Public Methods

    public Test (String name, int battlePoints, int altBid){
        type = "Test";
        this.name = name;
        this.battlePoints = battlePoints;
        this.altBid = altBid;
    }

    public int getAltBid(){return altBid;}

    @Override
    public String toString() {
        return String.format("Name: " + name + ", Battle Points: " + battlePoints + ", altBid: " + altBid + ", Type: " + type);
    }
}
