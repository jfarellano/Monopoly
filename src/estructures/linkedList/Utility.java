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
public class Utility extends Nodo{
    
    public Utility link;

    private Player owner;

    private String utilityName;

    private int price;
    private boolean rentDicex4;
    private boolean rentDicex10;
    private int mortgage;

    public Utility (String utilityName) {
        this.utilityName = utilityName;
        price = 150;
        mortgage = 75;
        rentDicex4 = false;
        rentDicex10 = false;
    }

    public Utility(String utilityName, int price, int mortgage) {
        this.utilityName = utilityName;
        this.price = price;
        this.mortgage = mortgage;
    }

    public int getRentPrice(int dice) {
        if (rentDicex4) {
            return (dice * 4);
        } else if (rentDicex10) {
            return (dice * 10);
        }
        return 0;
    }

    //GETTERS SETTERS
    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getUtilityName() {
        return utilityName;
    }

    public void setUtilityName(String utilityName) {
        this.utilityName = utilityName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isRentDicex4() {
        return rentDicex4;
    }

    public void setRentDicex4(boolean rentDicex4) {
        this.rentDicex4 = rentDicex4;
    }

    public boolean isRentDicex10() {
        return rentDicex10;
    }

    public void setRentDicex10(boolean rentDicex10) {
        this.rentDicex10 = rentDicex10;
    }

    public int getMortgage() {
        return mortgage;
    }

    public void setMortgage(int mortgage) {
        this.mortgage = mortgage;
    }
}
