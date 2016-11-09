/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machines;

import estructures.Nodo;

/**
 *
 * @author JohnBarbosa
 */
public class Corner extends Nodo{

    private int gift;
    private String name;
    
    public int x, y;

    public Corner(String name, int x, int y) {
        this.name = name;
        gift = 0;
        this.x = x;
        this.y = y;
    }

    public Corner(String name, int number, int gift, int x, int y) {
        this.name = name;
        this.gift = gift;
        this.x = x;
        this.y = y;
    }

    //GETTERS SETTERS
    public int getGift() {
        return gift;
    }

    public void setGift(int gift) {
        this.gift = gift;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
