package backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class  AdventureDeck {
    protected ArrayList<AdventureCard> deckOfCards;
    protected ArrayList<AdventureCard> discardPile;
    protected Random randomGenerator;
    protected int index;


    public AdventureDeck() {
        deckOfCards = new ArrayList<AdventureCard>();
        randomGenerator =  new Random();
        createAdventureDeck();

    }

    private int randomNumberGenerator()
    {
        index = randomGenerator.nextInt(deckOfCards.size());
        return index;
    }
    private void shuffle() {
        Collections.shuffle(deckOfCards);
    }


    private void addWeapon() {

        //Creating Weapon Cards
        AdventureCard exalibur1 = new Weapon("Excalibur", 30, 1);
        AdventureCard exalibur2 = new Weapon("Excalibur", 30, 1);
        AdventureCard lance1 = new Weapon("Lance", 20, 1);
        AdventureCard lance2 = new Weapon("Lance", 20, 1);
        AdventureCard lance3 = new Weapon("Lance", 20, 1);
        AdventureCard lance4 = new Weapon("Lance", 20, 1);
        AdventureCard lance5 = new Weapon("Lance", 20, 1);
        AdventureCard lance6 = new Weapon("Lance", 20, 1);
        AdventureCard battleAxe1 = new Weapon("BattleAxe", 15, 1);
        AdventureCard battleAxe2 = new Weapon("BattleAxe", 15, 1);
        AdventureCard battleAxe3 = new Weapon("BattleAxe", 15, 1);
        AdventureCard battleAxe4 = new Weapon("BattleAxe", 15, 1);
        AdventureCard battleAxe5 = new Weapon("BattleAxe", 15, 1);
        AdventureCard battleAxe6 = new Weapon("BattleAxe", 15, 1);
        AdventureCard battleAxe7 = new Weapon("BattleAxe", 15, 1);
        AdventureCard battleAxe8 = new Weapon("BattleAxe", 15, 1);
        AdventureCard sword1 = new Weapon("Sword", 10, 1);
        AdventureCard sword2 = new Weapon("Sword", 10, 1);
        AdventureCard sword3 = new Weapon("Sword", 10, 1);
        AdventureCard sword4 = new Weapon("Sword", 10, 1);
        AdventureCard sword5 = new Weapon("Sword", 10, 1);
        AdventureCard sword6 = new Weapon("Sword", 10, 1);
        AdventureCard sword7 = new Weapon("Sword", 10, 1);
        AdventureCard sword8 = new Weapon("Sword", 10, 1);
        AdventureCard sword9 = new Weapon("Sword", 10, 1);
        AdventureCard sword10 = new Weapon("Sword", 10, 1);
        AdventureCard sword11 = new Weapon("Sword", 10, 1);
        AdventureCard sword12 = new Weapon("Sword", 10, 1);
        AdventureCard sword13 = new Weapon("Sword", 10, 1);
        AdventureCard sword14 = new Weapon("Sword", 10, 1);
        AdventureCard sword15 = new Weapon("Sword", 10, 1);
        AdventureCard sword16 = new Weapon("Sword", 10, 1);
        AdventureCard horse1 = new Weapon("Horse", 10, 1);
        AdventureCard horse2 = new Weapon("Horse", 10, 1);
        AdventureCard horse3 = new Weapon("Horse", 10, 1);
        AdventureCard horse4 = new Weapon("Horse", 10, 1);
        AdventureCard horse5 = new Weapon("Horse", 10, 1);
        AdventureCard horse6 = new Weapon("Horse", 10, 1);
        AdventureCard horse7 = new Weapon("Horse", 10, 1);
        AdventureCard horse8 = new Weapon("Horse", 10, 1);
        AdventureCard horse9 = new Weapon("Horse", 10, 1);
        AdventureCard horse10 = new Weapon("Horse", 10, 1);
        AdventureCard horse11 = new Weapon("Horse", 10, 1);
        AdventureCard dagger1 = new Weapon("Dagger", 5, 1);
        AdventureCard dagger2 = new Weapon("Dagger", 5, 1);
        AdventureCard dagger3 = new Weapon("Dagger", 5, 1);
        AdventureCard dagger4 = new Weapon("Dagger", 5, 1);
        AdventureCard dagger5 = new Weapon("Dagger", 5, 1);
        AdventureCard dagger6 = new Weapon("Dagger", 5, 1);

        //Adding Weapon Cards to the list:
        deckOfCards.add(exalibur1);
        deckOfCards.add(exalibur2);
        deckOfCards.add(lance1);
        deckOfCards.add(lance2);
        deckOfCards.add(lance3);
        deckOfCards.add(lance4);
        deckOfCards.add(lance5);
        deckOfCards.add(lance6);
        deckOfCards.add(battleAxe1);
        deckOfCards.add(battleAxe2);
        deckOfCards.add(battleAxe3);
        deckOfCards.add(battleAxe4);
        deckOfCards.add(battleAxe5);
        deckOfCards.add(battleAxe6);
        deckOfCards.add(battleAxe7);
        deckOfCards.add(battleAxe8);
        deckOfCards.add(sword1);
        deckOfCards.add(sword2);
        deckOfCards.add(sword3);
        deckOfCards.add(sword4);
        deckOfCards.add(sword5);
        deckOfCards.add(sword6);
        deckOfCards.add(sword7);
        deckOfCards.add(sword8);
        deckOfCards.add(sword9);
        deckOfCards.add(sword10);
        deckOfCards.add(sword11);
        deckOfCards.add(sword12);
        deckOfCards.add(sword13);
        deckOfCards.add(sword14);
        deckOfCards.add(sword15);
        deckOfCards.add(sword16);
        deckOfCards.add(horse1);
        deckOfCards.add(horse2);
        deckOfCards.add(horse3);
        deckOfCards.add(horse4);
        deckOfCards.add(horse5);
        deckOfCards.add(horse6);
        deckOfCards.add(horse7);
        deckOfCards.add(horse8);
        deckOfCards.add(horse9);
        deckOfCards.add(horse10);
        deckOfCards.add(horse11);
        deckOfCards.add(dagger1);
        deckOfCards.add(dagger2);
        deckOfCards.add(dagger3);
        deckOfCards.add(dagger4);
        deckOfCards.add(dagger5);
        deckOfCards.add(dagger6);

    }

    private void addAllies() {

        AdventureCard sirgalahad1 = new Allies("SirGalahad",15, 1);
        AdventureCard sirLancelot1 = new Allies("SirLancelot",15, 1);
        AdventureCard kingArthur1 = new Allies("KingArthur",10, 1);
        AdventureCard sirTristan1 = new Allies("SirTristan",10, 1);
        AdventureCard sirPellinore1 = new Allies("KingPellinore",10, 1);
        AdventureCard sirGawain1  = new Allies("SirGawain",10, 1);
        AdventureCard sirPercival1 = new Allies("SirPercival",10, 4);
        AdventureCard queenGuinevere1  = new Allies("QueenGuinevere",0, 3);
        AdventureCard queenIseult1 = new Allies("QueenIseult",0, 2);
        AdventureCard merlin1  = new Allies("Merlin",0, 0);

        //Adding Weapon Cards to the list:
        deckOfCards.add(sirgalahad1);
        deckOfCards.add(sirLancelot1);
        deckOfCards.add(kingArthur1);
        deckOfCards.add(sirTristan1);
        deckOfCards.add(sirPellinore1);
        deckOfCards.add(sirGawain1);
        deckOfCards.add(sirPercival1);
        deckOfCards.add(queenGuinevere1);
        deckOfCards.add(queenIseult1);
        deckOfCards.add(merlin1);
    }

    private void addAmour() {
        AdventureCard amour1 = new Amour();
        AdventureCard amour2 = new Amour();
        AdventureCard amour3 = new Amour();
        AdventureCard amour4 = new Amour();
        AdventureCard amour5 = new Amour();
        AdventureCard amour6 = new Amour();
        AdventureCard amour7 = new Amour();
        AdventureCard amour8 = new Amour();

        //Adding amour cards
        deckOfCards.add(amour1);
        deckOfCards.add(amour2);
        deckOfCards.add(amour3);
        deckOfCards.add(amour4);
        deckOfCards.add(amour5);
        deckOfCards.add(amour6);
        deckOfCards.add(amour7);
        deckOfCards.add(amour8);

    }

    private void addFoes() {
        AdventureCard dragon1 = new Foe("Dragon", 50, 70, 1);
        AdventureCard giant1 = new Foe("Giant", 40, 0, 1);
        AdventureCard giant2 = new Foe("Giant", 40, 0, 1);
        AdventureCard mordred1 = new Foe("Mordred", 30, 0, 1);
        AdventureCard mordred2 = new Foe("Mordred", 30, 0, 1);
        AdventureCard mordred3 = new Foe("Mordred", 30, 0, 1);
        AdventureCard mordred4 = new Foe("Mordred", 30, 0, 1);
        AdventureCard greenKnight1 = new Foe("GreenKnight", 25, 40, 1);
        AdventureCard greenKnight2 = new Foe("GreenKnight", 25, 40, 1);
        AdventureCard blackKnight1 = new Foe("GreenKnight", 25, 40, 1);
        AdventureCard blackKnight2 = new Foe("GreenKnight", 25, 40, 1);
        AdventureCard blackKnight3 = new Foe("GreenKnight", 25, 40, 1);
        AdventureCard evilKnight1 = new Foe("EvilKnight", 20, 30, 1);
        AdventureCard evilKnight2 = new Foe("EvilKnight", 20, 30, 1);
        AdventureCard evilKnight3 = new Foe("EvilKnight", 20, 30, 1);
        AdventureCard evilKnight4 = new Foe("EvilKnight", 20, 30, 1);
        AdventureCard evilKnight5 = new Foe("EvilKnight", 20, 30, 1);
        AdventureCard evilKnight6 = new Foe("EvilKnight", 20, 30, 1);
        AdventureCard saxonKnight1 = new Foe("SaxonKnight", 15, 25, 1);
        AdventureCard saxonKnight2 = new Foe("SaxonKnight", 15, 25, 1);
        AdventureCard saxonKnight3 = new Foe("SaxonKnight", 15, 25, 1);
        AdventureCard saxonKnight4 = new Foe("SaxonKnight", 15, 25, 1);
        AdventureCard saxonKnight5 = new Foe("SaxonKnight", 15, 25, 1);
        AdventureCard saxonKnight6 = new Foe("SaxonKnight", 15, 25, 1);
        AdventureCard saxonKnight7 = new Foe("SaxonKnight", 15, 25, 1);
        AdventureCard saxonKnight8 = new Foe("SaxonKnight", 15, 25, 1);
        AdventureCard robberKnight1 = new Foe("RobberKnight", 15, 0,1);
        AdventureCard robberKnight2 = new Foe("RobberKnight", 15, 0,1);
        AdventureCard robberKnight3 = new Foe("RobberKnight", 15, 0,1);
        AdventureCard robberKnight4 = new Foe("RobberKnight", 15, 0,1);
        AdventureCard robberKnight5 = new Foe("RobberKnight", 15, 0,1);
        AdventureCard robberKnight6 = new Foe("RobberKnight", 15, 0,1);
        AdventureCard robberKnight7 = new Foe("RobberKnight", 15, 0,1);
        AdventureCard saxons1 = new Foe("Saxons", 10, 20,1);
        AdventureCard saxons2 = new Foe("Saxons", 10, 20,1);
        AdventureCard saxons3 = new Foe("Saxons", 10, 20,1);
        AdventureCard saxons4 = new Foe("Saxons", 10, 20,1);
        AdventureCard saxons5 = new Foe("Saxons", 10, 20,1);
        AdventureCard boar1 = new Foe("Boar", 5, 15,1);
        AdventureCard boar2 = new Foe("Boar", 5, 15,1);
        AdventureCard boar3 = new Foe("Boar", 5, 15,1);
        AdventureCard boar4 = new Foe("Boar", 5, 15,1);
        AdventureCard thieves1 = new Foe("Thieves", 5, 0,1);
        AdventureCard thieves2 = new Foe("Thieves", 5, 0,1);
        AdventureCard thieves3 = new Foe("Thieves", 5, 0,1);
        AdventureCard thieves4 = new Foe("Thieves", 5, 0,1);
        AdventureCard thieves5 = new Foe("Thieves", 5, 0,1);
        AdventureCard thieves6 = new Foe("Thieves", 5, 0,1);
        AdventureCard thieves7 = new Foe("Thieves", 5, 0,1);
        AdventureCard thieves8 = new Foe("Thieves", 5, 0,1);

        //Adding Foe cards

        deckOfCards.add(dragon1);
        deckOfCards.add(giant1);
        deckOfCards.add(giant2);
        deckOfCards.add(mordred1);
        deckOfCards.add(mordred2);
        deckOfCards.add(mordred3);
        deckOfCards.add(mordred4);
        deckOfCards.add(greenKnight1);
        deckOfCards.add(greenKnight2);
        deckOfCards.add(blackKnight1);
        deckOfCards.add(blackKnight2);
        deckOfCards.add(blackKnight3);
        deckOfCards.add(evilKnight1);
        deckOfCards.add(evilKnight2);
        deckOfCards.add(evilKnight3);
        deckOfCards.add(evilKnight4);
        deckOfCards.add(evilKnight5);
        deckOfCards.add(evilKnight6);
        deckOfCards.add(saxonKnight1);
        deckOfCards.add(saxonKnight2);
        deckOfCards.add(saxonKnight3);
        deckOfCards.add(saxonKnight4);
        deckOfCards.add(saxonKnight5);
        deckOfCards.add(saxonKnight6);
        deckOfCards.add(saxonKnight7);
        deckOfCards.add(saxonKnight8);
        deckOfCards.add(robberKnight1);
        deckOfCards.add(robberKnight2);
        deckOfCards.add(robberKnight3);
        deckOfCards.add(robberKnight4);
        deckOfCards.add(robberKnight5);
        deckOfCards.add(robberKnight6);
        deckOfCards.add(robberKnight7);
        deckOfCards.add(saxons1);
        deckOfCards.add(saxons2);
        deckOfCards.add(saxons3);
        deckOfCards.add(saxons4);
        deckOfCards.add(saxons5);
        deckOfCards.add(boar1);
        deckOfCards.add(boar2);
        deckOfCards.add(boar3);
        deckOfCards.add(boar4);
        deckOfCards.add(thieves1);
        deckOfCards.add(thieves2);
        deckOfCards.add(thieves3);
        deckOfCards.add(thieves4);
        deckOfCards.add(thieves5);
        deckOfCards.add(thieves6);
        deckOfCards.add(thieves7);
        deckOfCards.add(thieves8);
    }

    private void createAdventureDeck()
    {
        addWeapon();
        addAllies();
        addAmour();
        addFoes();
        shuffle();
    }

    //public methods

    public void fetchCard(ArrayList<AdventureCard> aHand) {
        int i = randomNumberGenerator();
        aHand.add(deckOfCards.get(i));
        deckOfCards.remove(i);
    }

    public void createHand(ArrayList<AdventureCard> aHand){
        for (int i = 0; i < 12; i++) {
            fetchCard(aHand);
        }
    }

    public  AdventureCard draw(){
        return deckOfCards.remove(deckOfCards.size() - 1);
        //TODO add drawn cards to the discard pile
    }

    public void discardCard(AdventureCard aCard) {
        discardPile.add(aCard);
    }


    public int sizeOfDeck() {
        return deckOfCards.size();
    }

    public int sizeOfDiscardPile() {
        return discardPile.size();
    }
}
