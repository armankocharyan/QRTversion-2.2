package edu.comp3004.websocket;

import backend.*;
import ch.qos.logback.core.net.SyslogOutputStream;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {

    ArrayList<Player> players = new ArrayList<Player>();
    private static final Logger logger = LoggerFactory.getLogger(SocketHandler.class);
    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    List<Player> tournamentPlayer = new ArrayList<>();
    int counterBattlePointsCalculated = 0;
    public Tournament card;
    int currentPlayer = 0;

    Game game;

     public SocketHandler(){
         System.out.println("constructor called from socketHandler");
         game = new Game(this);
     }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Map<String, String> value = new Gson().fromJson(message.getPayload(), Map.class);
        Set entrySet = value.entrySet();
        Iterator it = entrySet.iterator();
        while(it.hasNext()){
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            System.out.println("key is " +entry.getKey() + "|" + entry.getValue());
        }
        if(value.get("URL").equals("GameStarted")){
            gameStarted(session, value.get("URL"));

        } else if(value.get("URL").equals("intro")){
            System.out.println("name is "+value.get("name"));
            Player temp = new Player(value.get("name"), session);
            System.out.println("name from player class is "+temp.getName());
            game.players.add(temp);
            System.out.println("size of number of players is "+game.players.size());

        } else if(value.get("URL").equals("selectedCards")){
            System.out.println("You have selected these cards: "+ value.get("arr"));
            arrayIndex(value.get("arr"), session);

        }else if(value.get("URL").equals("Yes")){
            System.out.println("You have decided to play the tournament");
            playTournament(session);
        }
        else if(value.get("URL").equals("No")){
            System.out.println("You are not playing the tournament");
        }
        else if (value.get("URL").equals("yesSponsor")){
            System.out.println("yesSponsor Handler");

           notifyOthers();
           // setUpQuest();
        }
        else if(value.get("URL").equals(("startFunctionQuest"))){
            System.out.println("about to drawQuestCard");
            drawQuestCard();
        }
        else {
            session.sendMessage(new TextMessage("Hello " + value.get("name") + " !"));
        }
    }

    public void gameStarted(WebSocketSession session, String message) throws Exception{

        for(int i = 0; i < game.players.size(); i++){
            String cardNames = "";
            game.adventureDeck.createHand(game.players.get(i).getHand());
            for(int j=0; j < game.players.get(i).getHand().size(); j++){
                cardNames += game.players.get(i).getHand().get(j).getName() + ",";
            }
            JsonObject response = new JsonObject();
            response.addProperty("URL", "gameStarted");
            response.addProperty("cards",cardNames);
            response.addProperty("name", game.players.get(i).getName());
            response.addProperty("shields", game.players.get(i).shields);
            response.addProperty("battlePoints", game.players.get(i).getBattlePoints());
            game.players.get(i).getWebSocketSession().sendMessage(new TextMessage(response.toString()));
        }
        drawStoryCard();

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        InetSocketAddress clientAddress = session.getRemoteAddress();
        HttpHeaders handshakeHeaders = session.getHandshakeHeaders();

        System.out.println("session.size is : "+sessions.size());
        logger.info("Accepted connection from: {}:{}", clientAddress.getHostString(), clientAddress.getPort());
        if(sessions.size() < 4) {
            System.out.println("added session to sessions");
            sessions.add(session);
        }
        else {
            System.out.println("You have reached maximum amount of players\n");
        }
        if (sessions.size() == 1){
            System.out.println("host found");
            JsonObject response = new JsonObject();
            response.addProperty("URL", "isHost");
            response.addProperty("host", "Yes");
            session.sendMessage(new TextMessage(response.toString()));
        }
    }

    //STARTING TO IMPLEMENT QUESTS (BOARHUNT)


    public void drawQuestCard() throws Exception{

         //can pass a quest to this function later
       // System.out.println("Player " + getCurrentPlayerIndex(session) + " is in this method \n");
        //creating a quest
        //int currentPlayerIndex = 0; //getCurrentPlayerIndex(session)
        System.out.println("inside function drawQuestCard");
        Quest currentQuest;
        ArrayList<String> foes= new ArrayList<>();
        String foe = "Boar";
        foes.add(foe);
        Quest boarHunt = new Quest(2,"BoarHunt", foes);
        currentQuest = boarHunt;

        //sending to front end
        JsonObject response = new JsonObject();
        response.addProperty("URL", "quest");
        response.addProperty("cardTypeQuest", boarHunt.getName());
        response.addProperty("numStages", String.valueOf(boarHunt.getNumberOfStages()));
        System.out.println("sent to front end \n");

        JsonObject response2 = new JsonObject(); //for the currentPlayer
        response2.addProperty("URL", "decision");

        //displaying cards to every player
        for(int i =0; i < sessions.size(); i++){
            sessions.get(i).sendMessage(new TextMessage(response.toString()));
        }
        //asking player 1 to sponsor
        sessions.get(currentPlayer).sendMessage(new TextMessage((response2.toString())));


    }

    public void setUpQuest(){
         //do nothing
    }


    public void notifyOthers() throws Exception{
         System.out.println("about to notify some african americans\n");
        JsonObject response = new JsonObject();
        response.addProperty("URL", "printLabel");
        response.addProperty("sponsorName", "Andrew");

        System.out.println(response);

        System.out.println("notifying 1...\n");
        game.players.get(1).getWebSocketSession().sendMessage(new TextMessage((response.toString())));
        System.out.println("notifying 2...\n");
        game.players.get(2).getWebSocketSession().sendMessage(new TextMessage((response.toString())));
        System.out.println("notifying 3...\n");
        game.players.get(3).getWebSocketSession().sendMessage(new TextMessage((response.toString())));

        System.out.println("Messages sent\n");
    }

    //END OF QUEST IMPLEMENTATION

    public void updateAllPlayers()  throws Exception{
        for (int i = 0; i < game.players.size(); i ++){
            updateStat(game.players.get(i).getWebSocketSession());
        }
    }

    public void updateStat(WebSocketSession session) throws Exception{

        int counter = 0;
        int opponentIndex = 0;
        int[] opponentBP = new int[3];
        int[] opponentShields = new int[3];
        System.out.println("Arrays are working\n");
        String[] opponentName = new String [3];
        String[] opponentRank = new String [3];
        System.out.println("Created temp variables\n");

        int size = game.players.size() - 1;

        int currentPlayerIndex = getCurrentPlayerIndex(session) + 1;
        for(int i = 0; i < game.players.size(); i++){
            System.out.println(game.players.get(i).getName());
        }

        while (counter < size){

            if (currentPlayerIndex > size)
                currentPlayerIndex = 0;



            System.out.println("currentPlayer index + " + currentPlayerIndex);
            System.out.println("Name of opponent" + currentPlayerIndex + " is" + game.players.get(currentPlayerIndex).getName()) ;
            opponentBP[opponentIndex] = game.players.get(currentPlayerIndex).getBattlePoints();
            opponentShields[opponentIndex] = game.players.get(currentPlayerIndex).getShields();
            opponentName[opponentIndex] = game.players.get(currentPlayerIndex).getName();
            opponentRank[opponentIndex] = game.players.get(currentPlayerIndex).getRank();

            opponentIndex++;
            currentPlayerIndex++;
            counter ++;

        }

        System.out.println("Outside while loops\n");

        JsonObject response = new JsonObject();
        response.addProperty("URL", "updatePlayer");

        response.addProperty("opponentOneName", opponentName[0]);
        response.addProperty("opponentTwoName", opponentName[1]);
        response.addProperty("opponentThreeName", opponentName[2]);

        response.addProperty("opponentOneBP", String.valueOf(opponentBP[0]));
        response.addProperty("opponentTwoBP", String.valueOf(opponentBP[1]));
        response.addProperty("opponentThreeBP", String.valueOf(opponentBP[2]));

        response.addProperty("opponentOneShield", String.valueOf(opponentShields[0]));
        response.addProperty("opponentTwoShield", String.valueOf(opponentShields[1]));
        response.addProperty("opponentThreeShield", String.valueOf(opponentShields[2]));

        response.addProperty("opponentOneRank", opponentRank[0]);
        response.addProperty("opponentTwoRank", opponentRank[1]);
        response.addProperty("opponentThreeRank", opponentRank[2]);

        System.out.println("current player is " + currentPlayerIndex);
        for (int i = 0; i < opponentName.length; i ++ ){
            System.out.println("Opponent" + i + " Name is: "  + opponentName[i]);
            System.out.println("Opponent" + i + " Rank is: "  + opponentRank[i]);
            System.out.println("Opponent" + i + " BP is: "  + opponentBP[i]);
            System.out.println("Opponent" + i + " Shields is: "  + opponentShields[i]);

        }

        session.sendMessage(new TextMessage(response.toString()));
    }

    public int getCurrentPlayerIndex(WebSocketSession session){
        for(int i = 0; i < game.players.size(); i ++){
            if (game.players.get(i).getWebSocketSession() == session)
                return i;
        }
        return 69;
    }

    public void playTournament (WebSocketSession session){

         for(int i = 0; i < sessions.size(); i++){
             if (game.players.get(i).getWebSocketSession() == session) {
                 System.out.println("tournament player added ");
                 tournamentPlayer.add(game.players.get(i));
             }
         }
    }

    public void drawStoryCard() throws  Exception{
        System.out.println("starting to draw card\n");
         updateAllPlayers();
        Tournament camelot = new Tournament("Camelot", 3);
        card = camelot;
        JsonObject response = new JsonObject();
        response.addProperty("URL", "tournament");
        response.addProperty("cardType", camelot.getName());
        response.addProperty("shield", String.valueOf(camelot.getShield()));
        for(int i = 0; i < game.players.size(); i++){
            WebSocketSession tempWebSocket = game.players.get(i).getWebSocketSession();
            tempWebSocket.sendMessage(new TextMessage(response.toString()));
        }
    }

    public void arrayIndex(String jsonString, WebSocketSession session) throws Exception{
         counterBattlePointsCalculated++;
        List<Integer> indices = new ArrayList<>();
        System.out.println("counterBattlePointsCalculated is "+counterBattlePointsCalculated);
        jsonString = jsonString.replace('[', ' ');
        jsonString = jsonString.replace(']', ' ');
        jsonString = jsonString.replace('"', ' ');
        jsonString = jsonString.replaceAll("\\s+","");

        List<String> items = Arrays.asList(jsonString.split("\\s*,\\s*"));

        for(int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
            indices.add(Integer.parseInt(items.get(i)));
        }
        System.out.println("This is the integer list: ");
        for(int i = 0; i < indices.size(); i++) {
            System.out.println(indices.get(i));
        }

        Collections.sort(indices);
        Collections.reverse(indices);

        calcTournamentBP(indices, session);
    }

    public void calcTournamentBP (List<Integer> indices, WebSocketSession session) throws Exception{
        System.out.println("calcTournamentBp called");
         for (int i = 0; i < game.players.size(); i++){
             if(game.players.get(i).getWebSocketSession() == session){
                 setBP(game.players.get(i), indices);
                 System.out.println("Player "+game.players.get(i).getName()+ "'s rank is : "+game.players.get(i).getTempBP());
             }
         }

        System.out.println("counterbpcalc in calc tournament is "+counterBattlePointsCalculated);
        System.out.println("tournamentPlayer size is "+tournamentPlayer.size());
        if(counterBattlePointsCalculated == tournamentPlayer.size()){
            System.out.println("calculated the bp for all players ");
            compareTournamentBP();
        }


    }

    public void setBP(Player aPlayer, List<Integer> indices){

         int tempBP = aPlayer.getBattlePoints();
         ArrayList<AdventureCard> duplicateCard= new ArrayList<AdventureCard>();

        for(int i = 0; i < indices.size(); i++){
            if(aPlayer.getHand().get(indices.get(i)).getType().equals("Weapon")){
                if (cardExists(aPlayer.getHand().get(indices.get(i)), duplicateCard) == false){
                    tempBP += aPlayer.getHand().get(indices.get(i)).getBattlePoints();
                    duplicateCard.add(aPlayer.fetchCard(i));
                }
            }
            else if(aPlayer.getHand().get(indices.get(i)).getType().equals("Amour")){
                if (cardExists(aPlayer.getHand().get(indices.get(i)), duplicateCard) == false){
                    tempBP += aPlayer.getHand().get(indices.get(i)).getBattlePoints();
                    duplicateCard.add(aPlayer.fetchCard(i));
                }
            }else if(aPlayer.getHand().get(indices.get(i)).getType().equals("Ally")){
                tempBP += tempBP += aPlayer.getHand().get(indices.get(i)).getBattlePoints();
                duplicateCard.add(aPlayer.fetchCard(i));
            }
        }
        aPlayer.setTempBP(tempBP);
    }

    public void compareTournamentBP() throws Exception{
        System.out.println("inside compareTournamentBP");
         int tempMax = 0;
        ArrayList<Player> winners = new ArrayList<Player>();
        for(int i = 0; i < tournamentPlayer.size(); i++){
             if(tournamentPlayer.get(i).getTempBP() > tempMax)
             tempMax = tournamentPlayer.get(i).getTempBP();
         }

         for (int i = 0; i < tournamentPlayer.size(); i++){
            if (tournamentPlayer.get(i).getTempBP() == tempMax){
                winners.add(tournamentPlayer.get(i));
            }
         }

         for(Player p: winners){
            p.shields = card.getShield();
            JsonObject response = new JsonObject();
            response.addProperty("URL", "tournamentWinner");
            response.addProperty("body", "Congrats you are a winner");
            response.addProperty("shields", p.shields);
            p.getWebSocketSession().sendMessage(new TextMessage(response.toString()));
         }

         if (winners.size() == 1){
            System.out.println("Player + " + winners.get(0).getName() + " has won the tournament");
            updateAllPlayers();
         }
         else{
            System.out.println("tieBreaker");
         }
    }

    public boolean cardExists(AdventureCard aCard, ArrayList<AdventureCard> duplicateCard){
         for (int i = 0; i < duplicateCard.size(); i++){
             if (duplicateCard.get(i).getName().equals(aCard.getName()));
             return true;
         }
         return false;
    }

    //{url: 'isHost', host: 'Yes'}

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("Connection closed by {}:{}", session.getRemoteAddress().getHostString(), session.getRemoteAddress().getPort());
        super.afterConnectionClosed(session, status);
    }
}
