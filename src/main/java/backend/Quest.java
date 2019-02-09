package backend;
import java.util.*;

public class Quest extends StoryCard {

    //Private Attributes
    private int numberOfStages;
    private String name;
    private ArrayList <String> questFoes;


    //Public Methods
    public Quest(int aNumberOfStages, String aName, ArrayList<String> aFoes){
        //questFoes = new ArrayList<>();
        this.numberOfStages= aNumberOfStages;
        this.name= aName;
        this.questFoes= aFoes;
    }


    public int getNumberOfStages (){
        return numberOfStages;
    }

    public String getName(){
        return name;
    }
    @Override
    public String toString() {
        return String.format("Name: " + name + ", Number of stages: " + numberOfStages + ", Quest Foes: " + String.join(", ", questFoes));
    }
}
