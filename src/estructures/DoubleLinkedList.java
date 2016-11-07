/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructures;

import estructures.linkedList.Railroad;
import estructures.linkedList.Utility;
import machines.Avenue;
import machines.Corner;

/**
 *
 * @author JohnBarbosa
 */
public class DoubleLinkedList {

    public DoubleLinkedList rLink;
    public DoubleLinkedList lLink;

    private int numberOnTable;

    private Avenue avenue;
    private Corner corner;
    private Railroad railroad;
    private Utility utility;
    private boolean chance;
    private boolean communityChest;
    private int tax;

    public DoubleLinkedList(int numberOnTable, Avenue avenue) {
        this.avenue = avenue;
    }

    public DoubleLinkedList(int numberOnTable, Corner corner) {
        this.corner = corner;
    }

    public DoubleLinkedList(int numberOnTable, Railroad railroad) {
        this.railroad = railroad;
    }

    public DoubleLinkedList(int numberOnTable, Utility utility) {
        this.utility = utility;
    }

    public DoubleLinkedList(int numberOnTable, boolean chance) {
        this.chance = chance;
        if (chance) {
            communityChest = false;
        } else {
            communityChest = true;
        }
    }

    public DoubleLinkedList(int numberOnTable, int tax) {
        this.tax = tax;
    }

    //GETTERS SETTERS
    public int getNumberOnTable() {
        return numberOnTable;
    }

    public void setNumberOnTable(int numberOnTable) {
        this.numberOnTable = numberOnTable;
    }

    public Avenue getAvenue() {
        return avenue;
    }

    public void setAvenue(Avenue avenue) {
        this.avenue = avenue;
    }

    public Corner getCorner() {
        return corner;
    }

    public void setCorner(Corner corner) {
        this.corner = corner;
    }

    public Railroad getRailroad() {
        return railroad;
    }

    public void setRailroad(Railroad railroad) {
        this.railroad = railroad;
    }

    public Utility getUtility() {
        return utility;
    }

    public void setUtility(Utility utility) {
        this.utility = utility;
    }

    public boolean isChance() {
        return chance;
    }

    public void setChance(boolean chance) {
        this.chance = chance;
    }

    public boolean isCommunityChest() {
        return communityChest;
    }

    public void setCommunityChest(boolean communityChest) {
        this.communityChest = communityChest;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

}
