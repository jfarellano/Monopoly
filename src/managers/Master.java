/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import estructures.Lista;
import estructures.Nodo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;
import estructures.linkedList.Card;
import estructures.linkedList.Chance;
import estructures.linkedList.Chest;
import estructures.linkedList.Player;
import estructures.linkedList.Railroad;
import estructures.linkedList.Tax;
import estructures.linkedList.Utility;
import javafx.scene.control.Cell;
import machines.Avenue;
import machines.Corner;
import sun.reflect.generics.visitor.Reifier;

/**
 *
 * @author JohnBarbosa
 */
public class Master {

    private final String boardTableRoute;
    private final String chanceCardRoute;
    private final String communityChestCardRoute;

    private Lista table;
    private Lista players;
    private Lista rail;
    private Lista utility;
    private Lista CommunityChestCard;
    private Lista ChanceCard;

    private Player playerOnTurn;
    
    private int dice1, dice2;

    public Master() {
        /*String startingRoute = "./resources/txts/;*/
        boardTableRoute = "./resources/txts/Table.txt";
        chanceCardRoute = "./resources/txts/ChanceCards.txt";
        communityChestCardRoute = "./resources/txts/CommunityChestCards.txt";

        table = new Lista();
        players = new Lista();
        rail = new Lista();
        utility = new Lista();
        CommunityChestCard = new Lista();
        ChanceCard = new Lista();

        start();
    }

    //SET UP CODE
    private void start() {
        if (fileCheck(boardTableRoute, boardTableRoute, boardTableRoute)) {
            createCommunityChestList();
            createChanceList();
            createTable();
        }
        
    }

    private void createTable() {
        try {

            FileReader fr = new FileReader(boardTableRoute);
            BufferedReader br = new BufferedReader(fr);
            
            table.add(new Corner("Go", 1, 200));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");
                int number = Integer.valueOf(fields[0]);
                String name = fields[1];
                
                //Number|Name|Color|Price|Rent|1 House|2 Houses|3 Houses|4 Houses|Hotel|Mortgage|Houses Value|Hotels Value|
                
                
                if (name.contains("Avenue") || name.contains("Place") || name.contains("Gardens") || name.contains("walk")) {
                    table.add(new Avenue(line));
                } else if (name.contains("Tax")) {
                    table.add(new Tax(number, Integer.valueOf(fields[2])));
                } else if (number == 11 || number == 21 || number == 31) {
                    table.add(new Corner(fields[1]));
                } else if (name.contains("Railroad")) {
                    Railroad r = new Railroad(fields[1], number);
                    table.add(r);
                    //rail.add(r);
                } else if (name.contains("Works") || name.contains("Company")) {
                    Utility u = new Utility(name, number);
                    table.add(u);
                    //utility.add(u);
                } else if (name.contains("Chance")) {
                    table.add(new Chance(number));
                } else if (name.contains("Chest")) {
                    table.add(new Chest(number));
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createCommunityChestList() {
        try {
            FileReader fr = new FileReader(new File(communityChestCardRoute));
            BufferedReader br = new BufferedReader(fr);

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");
                //System.out.println(fields[1]);
                CommunityChestCard.add(new Card(Integer.valueOf(fields[0]), "CommunityChest", fields[1], fields[2], fields[3], this));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createChanceList() {
        try {
            FileReader fr = new FileReader(new File(chanceCardRoute));
            BufferedReader br = new BufferedReader(fr);

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");
                //System.out.println(fields[1]);
                ChanceCard.add(new Card(Integer.valueOf(fields[0]), "Chance", fields[1], fields[2], fields[3], this));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void nextTurn(){
        Random r = new Random();
        Nodo cell = null;
        if(dice1 != dice2) playerOnTurn = (Player) players.next();
        System.out.println("Player " + playerOnTurn.getId());
        dice1 = r.nextInt(6) + 1;
        dice2 = r.nextInt(6) + 1;
        int m = dice1 + dice2;
        System.out.println("Total dados " + m + " Dados: " + dice1 + " " + dice2);
        Nodo n = table.BuscarConId(playerOnTurn.getCourrentCell());
        for(int i = 0; i < m; i++){
            cell = table.next();
            
        }
        
        System.out.println("Casilla: " + cell.getNombre() + " id: " + cell.getId());
        if(cell.getNombre().equals("Avenue")){
            Avenue courrentCell = (Avenue) cell;
            System.out.println("Nombre de la propiedad: " + courrentCell.getAvenueName());
            playerOnTurn.setCourrentCell(courrentCell.getId());
            if(courrentCell.getOwner() == null){
                
            }else if(courrentCell.getOwner() == playerOnTurn){
                
            }else{
                
            }
        }else if(cell.getNombre().equals("Corner")){
            Corner courrentCell = (Corner) cell;
            playerOnTurn.setMoney(playerOnTurn.getMoney() + courrentCell.getGift());
            playerOnTurn.setCourrentCell(courrentCell.getId());
        }else if(cell.getNombre().equals("Railroad")){
            Railroad courrentCell = (Railroad) cell;
            playerOnTurn.setCourrentCell(courrentCell.getId());
        }else if(cell.getNombre().equals("Tax")){
            Tax courrentCell = (Tax) cell;
            playerOnTurn.setCourrentCell(courrentCell.getId());
        }else if(cell.getNombre().equals("Utility")){
            Utility courrentCell = (Utility) cell;
            playerOnTurn.setCourrentCell(courrentCell.getId());
        }else if(cell.getNombre().equals("Chance")){
            Chance courrentCell = (Chance) cell;
            playerOnTurn.setCourrentCell(courrentCell.getId());
        }else if(cell.getNombre().equals("Chest")){
            Chest courrentCell = (Chest) cell;
            playerOnTurn.setCourrentCell(courrentCell.getId());
        }
    }
    
    private void drawRandomCard(int Chance1Chest2){
        Random r = new Random();
        if(Chance1Chest2 == 1){
            ChanceCard.BuscarConId(r.nextInt(ChanceCard.length()));
        }else{
            CommunityChestCard.BuscarConId(r.nextInt(CommunityChestCard.length()));
        }
    }

    private boolean fileCheck(String route1, String route2, String route3) {
        try {
            File f1 = new File(route1);
            File f2 = new File(route2);
            File f3 = new File(route3);
            if (f1.exists() && f2.exists() && f3.exists()) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Data Base was corrupted!");
        }
        return false;
    }


    //PLAYER CODE

    //AVENUE CODE
    //CARD CODE
    //RAIL CODE

    //UTILITY CODE

    //GETTERS SETTERS

    public Lista getPlayers() {
        return players;
    }

    public Player getPlayerOnTurn() {
        return playerOnTurn;
    }

    public void setPlayerOnTurn(Player playerOnTurn) {
        this.playerOnTurn = playerOnTurn;
    }

    public Lista getTable() {
        return table;
    }
    
    
    
    
}
