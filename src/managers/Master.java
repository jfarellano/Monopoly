/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import estructures.DoubleLinkedList;
import estructures.Lista;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;
import machines.Avenue;
import estructures.linkedList.Card;
import estructures.linkedList.Chance;
import estructures.linkedList.Chest;
import estructures.linkedList.Player;
import estructures.linkedList.Railroad;
import estructures.linkedList.Tax;
import estructures.linkedList.Utility;
import javax.swing.JOptionPane;
import machines.Corner;

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
    private Random dice;
    private Random dice2;

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

        playerOnTurn = null;

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

            br.readLine();
            br.readLine();
            
            table.add(new Corner("Go", 1, 200));
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");
                if(fields[0].equals("")) return;
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
                    Railroad r = new Railroad(fields[1]);
                    table.add(r);
                    rail.add(r);
                } else if (name.contains("Works") || name.contains("Company")) {
                    Utility u = new Utility(name);
                    table.add(u);
                    utility.add(u);
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
                CommunityChestCard.add(new Card(Integer.valueOf(fields[0]), "CommunityChest", fields[1], fields[2], fields[3]));
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
                ChanceCard.add(new Card(Integer.valueOf(fields[0]), "Chance", fields[1], fields[2], fields[3]));
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    
}
