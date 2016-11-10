/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructures.linkedList;

import estructures.Nodo;
import managers.Master;

/**
 *
 * @author JohnBarbosa
 */
public class Card extends Nodo{
    
    int tipo;

    private int cardNumber;
    private String type;
    private String title;
    private String Clausule;
    private String text;
    private Master m;
    private int value;

    public Card(int cardNumber, String type, String title, String Clausule, String text, Master m) {
        this.cardNumber = cardNumber;
        this.type = type;
        this.title = title;
        this.Clausule = Clausule;
        this.text = text;
        this.m = m;
        accion();
    }
    
    
    public void accion(){
        if(tipo == 1){
            m.getPlayerOnTurn().setMoney(m.getPlayerOnTurn().getMoney() + value);
        }
        if(tipo == 2){
            m.getPlayerOnTurn().setMoney((m.getPlayerOnTurn().getHouses() * value) + m.getPlayerOnTurn().getMoney() + (value * m.getPlayerOnTurn().getHotels()));
        }
        if(tipo == 3){
            m.getPlayerOnTurn().setMoney(m.getPlayerOnTurn().getMoney() + value * m.getPlayers().length());
        }
        if(tipo == 4){
            m.getPlayerOnTurn().setCourrentCell(value);
        }
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
