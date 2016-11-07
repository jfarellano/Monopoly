/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructures.linkedList;

import estructures.Nodo;
import machines.Avenue;

/**
 *
 * @author JohnBarbosa
 */
public class Player extends Nodo{
    
    public Player link;

    private boolean bankruptcy;

    private int playerNumber;
    private String playerName;

    private Avenue property;
    private int houses;
    private int hotels;

    private int money;
    private Avenue playerPlace;
    
    private boolean inJail;
    private int turnsInJail;

    public Player(int playerNumber, String playerName) {
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        money = 1500;
        bankruptcy = false;
        inJail = false;
        houses = 0;
        hotels = 0;
    }

    //GETTERS SETTERS
    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Avenue getProperty() {
        return property;
    }

    public void setProperty(Avenue property) {
        this.property = property;
    }

    public int getHouses() {
        return houses;
    }

    public void setHouses(int houses) {
        this.houses = houses;
    }

    public int getHotels() {
        return hotels;
    }

    public void setHotels(int hotels) {
        this.hotels = hotels;
    }

    public boolean isBankruptcy() {
        return bankruptcy;
    }

    public void setBankruptcy(boolean bankruptcy) {
        this.bankruptcy = bankruptcy;
    }

    public Avenue getPlayerPlace() {
        return playerPlace;
    }

    public void setPlayerPlace(Avenue playerPlace) {
        this.playerPlace = playerPlace;
    }

}
