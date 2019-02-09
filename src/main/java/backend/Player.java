package backend;
import java.util.*;
import org.springframework.web.socket.WebSocketSession;

public class Player {

    //Protected Attributes
    protected String name;
    protected ArrayList<Allies> myAllies;
    protected ArrayList<AdventureCard> hand;
    protected int battlePoints;
    protected int tempBP;
    public int rank;
    public int shields;
    protected int userInput;
    protected Scanner reader;
    private WebSocketSession session;

    //Creating the constructor
    public Player(String aName, WebSocketSession session) {
        this.name = aName;
        this.hand = new ArrayList<AdventureCard>();
        this.myAllies = new ArrayList<Allies>();
        this.rank = 1;
        this.shields = 0;
        this.battlePoints = 5;
        reader = new Scanner(System.in);
        this.session = session;
    }

    public WebSocketSession getWebSocketSession(){
        return this.session;
    }

    public boolean choose() {
        System.out.println(name + " please enter 1 if you would like to sponsor\n");
        userInput = reader.nextInt();
        if(userInput == 1)
            return true;
        return false;
    }

    public void setTempBP(int battlePoints){
        tempBP = battlePoints;
    }

    public int getTempBP(){
        return tempBP;
    }

    public boolean correctPlayer(WebSocketSession aSession){
        if(aSession == session)
            return true;
        return false;
    }

    public boolean toPlay() {
        System.out.println(name + " please enter 1 if you would like to play\n");
        userInput = reader.nextInt();
        if(userInput == 1)
            return true;
        return false;
    }

    //Public Attributes
    public void addCard(AdventureCard card)
    {
        hand.add(card);
        //has to check to see if there are more than 12 cards
    }
    //Check rank method
    public void CheckRank()
    {
        switch (shields)
        {
            case 1:
            case 2:
            case 3:
            case 4:
                System.out.println("You are a squire");
                break;
            case 5:
            case 6:
                rank = 2;
                System.out.println("You are a Knight");
                break;
            case 7:
            case 8:
            case 9:
                rank = 3;
                System.out.println("You are a CHAMPION KNIGHT ");
                break;

            case 10:
                rank = 4;
                System.out.println("YOU ARE THE KNIGHT OF THE ROUND TABLE!!!!!!");
                break;
        }
    }

    public AdventureCard fetchCard(int index){
        AdventureCard tempCard = hand.get(index);
        hand.remove(index);
        return tempCard;
    }
    public String getName() {
        return name;
    }
    //Add shield
    public void AddShield(int numShields)
    {
        shields = shields + numShields;
        CheckRank();
    }
    //Discard Function


    public boolean CheckHand()
    {
        if (hand.size() > 12)
        {
            //checks to see how many cards you have over
            int temp = hand.size() - 12;
            System.out.println("You have " + temp + "cards, than what you should");
            return true;
        }
        return false;
    }

    public void UpdateAllyPoints()
    {
        CheckRank();
        if (myAllies == null)
        {
            for(int i = 0; i < myAllies.size(); i++)
            {
                battlePoints = battlePoints + myAllies.get(i).getBattlePoints();
            }
        }
    }

    public void UpdateBattlePoints()
    {

        switch (rank)
        {
            case 1:
                battlePoints = 5;
                break;
            case 2:
                battlePoints = 10;
                break;
            case 3:
                battlePoints = 20;
                break;
            case 4:
                battlePoints = 35;
                System.out.println("YOU ARE THE KNIGHT OF THE ROUND TABLE!!!!!! GG 2 EASY");
                break;
        }
    }
    public int getBattlePoints(){
        return battlePoints;
    }


    //Making getter method....
    public int GetShield()
    {
        return shields;
    }

    /*
    public int GetBattlePoints()
    {
        return battlePoints;
    }
    */

    public String getRank()
    {
        if(shields < 5)
            return "Squire";
        if(shields < 7)
            return "Knight";
        if (shields < 10)
            return "Champion Knight";
        else if (shields >= 10)
            return "KORT";

        return null;
    }

    public int getShields() {
        return shields;
    }


    public int getNumberOfCards() {
        return hand.size();
    }

    public ArrayList<AdventureCard> getHand(){
        return hand;
    }

    public void setSession(WebSocketSession session) {this.session = session;}
}
