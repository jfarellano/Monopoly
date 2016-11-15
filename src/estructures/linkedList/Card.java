/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructures.linkedList;

import estructures.Nodo;
import javax.swing.JOptionPane;
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
    private int actionType;

    public Card(int cardNumber, String type, String title, String Clausule, String text, int actionType, int value,Master m) {
        super(cardNumber);
        this.type = type;
        this.title = title;
        this.Clausule = Clausule;
        this.text = text;
        this.m = m;
        this.actionType = actionType;
        this.value = value;
    }
    
    public void showMessageToPlayer(){
        JOptionPane.showMessageDialog(null, "Card Info: \n" + title + "\n" + Clausule + "\n" + actionType + " " + value);
    }
    
    public void accion(){
        if(actionType == 1){
            //Give or take player x amount of money
            System.out.println("Entro a 1");
            if(value > 0 ) m.g.getPlayerOnTurn().setMoney(m.g.getPlayerOnTurn().getMoney() + value);
            else {m.g.getlRentToPay().setText(String.valueOf(-1 * value) + " M"); m.g.setOnAcquirable(true);}
            if(m.g.getlRentToPay().getText().equals("0 M")) m.g.setOnAcquirable(false);
            m.g.getlMoney().setText((String.valueOf(m.g.getPlayerOnTurn().getMoney()) + " M"));
        }
        if(actionType == 2){
            //Give or take player x amount of money per houses and hotels
            System.out.println("Entro a 2");
            if(value > 0 ) m.g.getPlayerOnTurn().setMoney(m.g.getPlayerOnTurn().getMoney() + (m.g.getPlayerOnTurn().getHouses() * value) + (value * m.g.getPlayerOnTurn().getHotels()));
            else {m.g.getlRentToPay().setText(String.valueOf(-1 * ((m.g.getPlayerOnTurn().getHouses() * value) + (value * m.g.getPlayerOnTurn().getHotels()))) + " M"); m.g.setOnAcquirable(true);}
            if(m.g.getlRentToPay().getText().equals("0 M")) m.g.setOnAcquirable(false);
            m.g.getlMoney().setText((String.valueOf(m.g.getPlayerOnTurn().getMoney()) + " M"));
        }
        if(actionType == 3){
            //Give or take money per player in game
            System.out.println("Entro a 3");
            if(value > 0 ) m.g.getPlayerOnTurn().setMoney(m.g.getPlayerOnTurn().getMoney() + value * m.getPlayers().length());
            else {m.g.getlRentToPay().setText(String.valueOf(-1 * value * m.getPlayers().length()) + " M"); m.g.setOnAcquirable(true);}
            if(m.g.getlRentToPay().getText().equals("0 M")) m.g.setOnAcquirable(false);
            m.g.getlMoney().setText((String.valueOf(m.g.getPlayerOnTurn().getMoney()) + " M"));
        }
        if(actionType == 4){
            //Move player to any cell 
            System.out.println("Entro a 4");
            if(m.g.getPlayerOnTurn().getCourrentCell() > value){
                m.g.getPlayerOnTurn().setMoney(m.g.getPlayerOnTurn().getMoney() + 200);
                m.g.getlMoney().setText((String.valueOf(m.g.getPlayerOnTurn().getMoney()) + " M"));
            }
            if(value == 11){
                m.g.getPlayerOnTurn().setInJail(true);
            }
            if(value == 1){
                m.g.getPlayerOnTurn().setMoney(m.g.getPlayerOnTurn().getMoney() + 200);
            }
            m.g.getPlayerOnTurn().setCourrentCell(value);
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
