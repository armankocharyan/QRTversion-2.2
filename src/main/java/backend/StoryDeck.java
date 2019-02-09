package backend;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class StoryDeck {

    protected ArrayList<StoryCard> deckOfCards;
    protected ArrayList<StoryCard> discardPile;
    protected Random randomGenerator;
    protected int index;

    //Lists for the quests
    protected ArrayList<String> evilKnight;
    protected ArrayList<String> allSaxons;
    protected ArrayList<String> boar;
    protected ArrayList<String> all;
    protected ArrayList<String> dragon;
    protected ArrayList<String> blackKnight;
    protected ArrayList<String> greenKnight;
    protected ArrayList<String > nullList;

    public StoryDeck() {
        deckOfCards = new ArrayList<StoryCard>();
        discardPile = new ArrayList<StoryCard>();
        randomGenerator =  new Random();
        createStoryDeck();
    }


    //private methods

    private int randomNumberGenerator()
    {
        index = randomGenerator.nextInt(deckOfCards.size());
        return index;
    }

    private void shuffle() {
        Collections.shuffle(deckOfCards);
    }

    private void createFoeList() {
        evilKnight = new ArrayList<String>();
        evilKnight.add("EvilKnight");

        allSaxons = new ArrayList<String>();
        allSaxons.add("Saxons");
        allSaxons.add("SaxonKnight");

        boar = new ArrayList<String>();
        boar.add("Boar");

        all = new ArrayList<String>();
        all.add("Dragon");
        all.add("Giant");
        all.add("Mordred");
        all.add("GreenKnight");
        all.add("BlackKnight");
        all.add("RobberKnight");
        all.add("Saxons");
        all.add("SaxonKnight");
        all.add("Boar");
        all.add("Thieves");

        dragon = new ArrayList<String>();
        dragon.add("Dragon");

        blackKnight = new ArrayList<String>();
        blackKnight.add("BlackKnight");

        greenKnight = new ArrayList<String>();
        greenKnight.add("GreenKnight");

        nullList = new ArrayList<String>();
        nullList.removeAll(nullList);

    }

    private void createQuestCards() {
        StoryCard boarHunt1 = new Quest(2, "BoarHunt",  boar);
        StoryCard boarHunt2 = new Quest( 2, "BoarHunt",  boar);
        StoryCard journey1 = new Quest( 3, "JourneyThroughTheEnchantedForest",  evilKnight);
        StoryCard repelSaxons1 = new Quest( 2, "RepelTheSaxonRaiders",  allSaxons);
        StoryCard defendQueen1 = new Quest( 3, "DefendTheQueensHonor",  all);
        StoryCard slayDragon1 = new Quest( 3, "SlayTheDragon",  dragon);
        StoryCard rescueMaiden1 = new Quest(3, "RescueTheFairMaiden",  blackKnight);
        StoryCard holyGrail1 = new Quest( 3, "SearchForTheHolyGrail",  all);
        StoryCard testGreen1 = new Quest( 4, "TestOfTheGreenKnight",  greenKnight);
        StoryCard vanquishArthur1 = new Quest( 3, "VanquishKingArthursEnemies",  nullList);
        StoryCard vanquishArthur2 = new Quest( 3, "VanquishKingArthursEnemies",  nullList);
        StoryCard questingBeast1 = new Quest( 4, "SearchForTheQuestingBeast",  nullList);
        StoryCard questingBeast2 = new Quest( 4, "SearchForTheQuestingBeast",  nullList);


        deckOfCards.add(boarHunt1);
        deckOfCards.add(boarHunt2);
        deckOfCards.add(journey1);
        deckOfCards.add(repelSaxons1);
        deckOfCards.add(slayDragon1);
        deckOfCards.add(defendQueen1);
        deckOfCards.add(rescueMaiden1);
        deckOfCards.add(holyGrail1);
        deckOfCards.add(testGreen1);
        deckOfCards.add(vanquishArthur1);
        deckOfCards.add(vanquishArthur2);
        deckOfCards.add(questingBeast1);
        deckOfCards.add(questingBeast2);

    }

    private void createStoryDeck() {
        createFoeList();
        createQuestCards();
        shuffle();
    }

    //public methods

    public StoryCard fetchCard() {
        int i = randomNumberGenerator();
        StoryCard temp = deckOfCards.get(i);
        discardPile.add(deckOfCards.get(i));
        deckOfCards.remove(i);
        return temp;
    }

    public boolean isEmpty() {
        if (deckOfCards.isEmpty()) {
            System.out.println("List is empty starting to add all the discard cards");
            deckOfCards.addAll(discardPile);
            discardPile.removeAll(discardPile);
            return true;
        }
        System.out.println("list is not empty");
        return false;
    }

    public  StoryCard draw(){
        return deckOfCards.remove(deckOfCards.size() - 1);
        //TODO add drawn cards to the discard pile
    }


    public int sizeOfDeck() {
        return deckOfCards.size();
    }

    public int sizeOfDiscardPile() {
        return discardPile.size();
    }

}
