package backend;

import edu.comp3004.websocket.SocketHandler;

import java.util.ArrayList;

public class Game {
    public ArrayList<Player> players = new ArrayList<Player>();
    public SocketHandler controller;
    public String randomString;
    public AdventureDeck adventureDeck = new AdventureDeck();
    private static Game instance = null;

    public Game (SocketHandler controller){
        System.out.println("game constructor called");
        randomString = "Roman";
        this.controller = controller;
    }
    public ArrayList<Player> getPlayers (){return players;}



}
