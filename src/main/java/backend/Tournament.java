package backend;

public class Tournament extends StoryCard {

    private int shields;

    public Tournament(String name, int shields){
        this.name = name;
        this.shields = shields;
    }

    public int getShield(){return shields;}

    public String getName(){return name;}

    @Override
    public String toString() {
        return String.format("Tournament name: " + name + ", Shields earned " + shields);
    }
}
