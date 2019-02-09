package backend;

public class Foe extends AdventureCard {
    //Private Attributes
    private int bid;
    private int altBattlePoints;

    //Public Methods

    public Foe (String name, int battlePoints, int altBattlePoints, int bid){
        this.name = name;
        this.battlePoints = battlePoints;
        this.altBattlePoints = altBattlePoints;
        this.bid = bid;
        type = "Foe";
    }

    public int getBid() {
        return bid;
    }

    @Override
    public String toString() {
        return String.format("Name: " + name + ", Battle Points: " + battlePoints + ", Alternative Battle Points: "
                + altBattlePoints + ", Bid: " + bid + ", Type: " + type);
    }
}
