/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructures.linkedList;

import estructures.Nodo;

/**
 *
 * @author JohnBarbosa
 */
public class Card extends Nodo{
    
    public Card link;

    private int cardNumber;
    private String type;
    private String title;
    private String Clausule;
    private String text;

    public Card(int cardNumber, String type, String title, String Clausule, String text) {
        this.cardNumber = cardNumber;
        this.type = type;
        this.title = title;
        this.Clausule = Clausule;
        this.text = text;
    }

    //GETTERS SETTERS
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClausule() {
        return Clausule;
    }

    public void setClausule(String Clausule) {
        this.Clausule = Clausule;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

}
