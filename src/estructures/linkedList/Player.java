/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructures.linkedList;

import estructures.Nodo;
import machines.Avenue;
import machines.Corner;

/**
 *
 * @author JohnBarbosa
 */
public class Player extends Nodo{
    
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
    
    //Corregir tradeo, implementar compra a banco, compra a otro jugadro, venta a otro jugador, compra, venta con otro jugador, venta al banco. El metodo actual no cubre compra al banco
    
    public void Tradeo(Nodo Propiedad1, Nodo Propiedad2, int valor1, int valor2, Player trader){
        
        if(Propiedad1.getNombre().equals("Avenue")){
            Avenue a = (Avenue) Propiedad1;
            if(trader == null){
                a.setOwner(null);
                money += a.getPrice();
            }else if(a.getOwner() == trader){
                money -= valor1;
                trader.setMoney(trader.getMoney() + valor1);
                money += valor2;
                trader.setMoney(trader.getMoney() - valor2);
                a.setOwner(this);
                if(Propiedad2 != null){
                    Avenue b = (Avenue) Propiedad2;
                    b.setOwner(trader);
                }
            }
        }else if(Propiedad1.getClass() == Railroad.class){
            Railroad a = (Railroad) Propiedad1;
            if(trader == null){
                a.setOwner(null);
                money += a.getPrice();
            }else if(a.getOwner() == trader){
                money -= valor1;
                trader.setMoney(trader.getMoney() + valor1);
                money += valor2;
                trader.setMoney(trader.getMoney() - valor2);
                a.setOwner(this);
                if(Propiedad2 != null){
                    Avenue b = (Avenue) Propiedad2;
                    b.setOwner(trader);
                }
            }
        }else if(Propiedad1.getClass() == Utility.class){
            Utility a = (Utility) Propiedad1;
            if(trader == null){
                a.setOwner(null);
                money += a.getPrice();
            }else if(a.getOwner() == trader){
                money -= valor1;
                trader.setMoney(trader.getMoney() + valor1);
                money += valor2;
                trader.setMoney(trader.getMoney() - valor2);
                a.setOwner(this);
                if(Propiedad2 != null){
                    Avenue b = (Avenue) Propiedad2;
                    b.setOwner(trader);
                }
            }
        }
        
        
    }

    //GETTERS SETTERS
    public int getPlayerNumber() {
        return id;
    }

    public void setPlayerNumber(int playerNumber) {
        this.id = playerNumber;
    }
    
    public int getCourrentCell(){
        return courrentCell;
    }
    
    public void setCourrentCell(int courrentCell){
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
