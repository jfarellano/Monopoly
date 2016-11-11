package estructures.linkedList;

import estructures.Nodo;
import machines.Avenue;
import machines.Corner;

public class Player extends Nodo {

    public Player link;

    private boolean bankruptcy;

    private String playerName;

    private Avenue property;
    private int houses;
    private int hotels;

    private int money;
    private Avenue playerPlace;

    private boolean inJail;
    private int turnsInJail;
    public int courrentCell;

    public Player(int playerNumber, String playerName) {
        super(playerNumber);
        courrentCell = 1;
        this.playerName = playerName;
        money = 1500;
        bankruptcy = false;
        inJail = false;
        houses = 0;
        hotels = 0;
    }

    //GETTERS SETTERS
    public int getPlayerNumber() {
        return id;
    }

    public void setPlayerNumber(int playerNumber) {
        this.id = playerNumber;
    }

    public int getCourrentCell() {
        return courrentCell;
    }

    public void setCourrentCell(int courrentCell) {
        this.courrentCell = courrentCell;
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
