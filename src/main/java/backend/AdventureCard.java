package backend;

public abstract class AdventureCard extends Cards{

    //Protected Attributes
    protected int battlePoints;
    protected String name;
    protected String type;


    //Public Methods
    public int getBattlePoints() {return battlePoints;}
    public String getName() {return name;}
    public String getType() {return type;}


}
