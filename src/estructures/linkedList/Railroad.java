/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructures.linkedList;

import estructures.Nodo;
import estructures.linkedList.Player;

/**
 *
 * @author JohnBarbosa
 */
public class Railroad extends Nodo{

    public Railroad link;
    
    private Player owner;

    private String railroadName;

    private int price;
    private int rentPrice;
    private int rent2Railroad;
    private int rent3Railroad;
    private int rent4Railroad;
    private int mortgage;

    public Railroad(String railroadName) {
        this.railroadName = railroadName;
        price = 200;
        rentPrice = 25;
        rent2Railroad = 50;
        rent3Railroad = 100;
        rent4Railroad = 200;
        mortgage = 100;
    }

    public Railroad(Player owner, String railroadName, int price, int rentPrice, int rent2Railroad, int rent3Railroad, int rent4Railroad, int mortgage) {
        this.owner = owner;
        this.railroadName = railroadName;
        this.price = price;
        this.rentPrice = rentPrice;
        this.rent2Railroad = rent2Railroad;
        this.rent3Railroad = rent3Railroad;
        this.rent4Railroad = rent4Railroad;
        this.mortgage = mortgage;
    }

    //GETTERS SETTERS
    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getRailroadName() {
        return railroadName;
    }

    public void setRailroadName(String railroadName) {
        this.railroadName = railroadName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getRent2Railroad() {
        return rent2Railroad;
    }

    public void setRent2Railroad(int rent2Railroad) {
        this.rent2Railroad = rent2Railroad;
    }

    public int getRent3Railroad() {
        return rent3Railroad;
    }

    public void setRent3Railroad(int rent3Railroad) {
        this.rent3Railroad = rent3Railroad;
    }

    public int getRent4Railroad() {
        return rent4Railroad;
    }

    public void setRent4Railroad(int rent4Railroad) {
        this.rent4Railroad = rent4Railroad;
    }

    public int getMortgage() {
        return mortgage;
    }

    public void setMortgage(int mortgage) {
        this.mortgage = mortgage;
    }
}
