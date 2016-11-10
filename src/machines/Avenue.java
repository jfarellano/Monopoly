/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machines;

import estructures.Nodo;
import estructures.linkedList.Player;

/**
 *
 * @author JohnBarbosa
 */
public class Avenue extends Nodo{

    private Player owner;
    
    public int x, y;

    private String avenueName;
    private String avenueColor;
    private int price;
    private int rentPrice;
    private int rent1House;
    private int rent2Houses;
    private int rent3Houses;
    private int rent4Houses;
    private int rentHotel;
    private int mortgage;
    private int housesPrice;
    private int hotelsPrice;

    private int houses;
    private int hotels;

    public Avenue(String line) {
        String[] fields = line.split("\\|");
        id = Integer.parseInt(fields[0]);
        avenueName = fields[1];
        avenueColor = fields[2];
        price = Integer.valueOf(fields[3]);
        rentPrice = Integer.valueOf(fields[4]);
        rent1House = Integer.valueOf(fields[5]);
        rent2Houses = Integer.valueOf(fields[6]);
        rent3Houses = Integer.valueOf(fields[7]);
        rent4Houses = Integer.valueOf(fields[8]);
        rentHotel = Integer.valueOf(fields[9]);
        mortgage = Integer.valueOf(fields[10]);
        housesPrice = Integer.valueOf(fields[11]);
        hotelsPrice = Integer.valueOf(fields[12]);
        this.nombre = this.getClass().getSimpleName();
    }

    public Avenue(String avenueName, String avenueColor, int price, int rentPrice, int rent1House, int rent2Houses, int rent3Houses, int rent4Houses, int rentHotel, int mortgage, int housesPrice, int hotelsPrice) {
        this.avenueName = avenueName;
        this.avenueColor = avenueColor;
        this.price = price;
        this.rentPrice = rentPrice;
        this.rent1House = rent1House;
        this.rent2Houses = rent2Houses;
        this.rent3Houses = rent3Houses;
        this.rent4Houses = rent4Houses;
        this.rentHotel = rentHotel;
        this.mortgage = mortgage;
        this.housesPrice = housesPrice;
        this.hotelsPrice = hotelsPrice;
    }

    //GETTERS SETTERS
    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getAvenueName() {
        return avenueName;
    }

    public void setAvenueName(String avenueName) {
        this.avenueName = avenueName;
    }

    public String getAvenueColor() {
        return avenueColor;
    }

    public void setAvenueColor(String avenueColor) {
        this.avenueColor = avenueColor;
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

    public int getRent1House() {
        return rent1House;
    }

    public void setRent1House(int rent1House) {
        this.rent1House = rent1House;
    }

    public int getRent2Houses() {
        return rent2Houses;
    }

    public void setRent2Houses(int rent2Houses) {
        this.rent2Houses = rent2Houses;
    }

    public int getRent3Houses() {
        return rent3Houses;
    }

    public void setRent3Houses(int rent3Houses) {
        this.rent3Houses = rent3Houses;
    }

    public int getRent4Houses() {
        return rent4Houses;
    }

    public void setRent4Houses(int rent4Houses) {
        this.rent4Houses = rent4Houses;
    }

    public int getRentHotel() {
        return rentHotel;
    }

    public void setRentHotel(int rentHotel) {
        this.rentHotel = rentHotel;
    }

    public int getMortgage() {
        return mortgage;
    }

    public void setMortgage(int mortgage) {
        this.mortgage = mortgage;
    }

    public int getHousesPrice() {
        return housesPrice;
    }

    public void setHousesPrice(int housesPrice) {
        this.housesPrice = housesPrice;
    }

    public int getHotelsPrice() {
        return hotelsPrice;
    }

    public void setHotelsPrice(int hotelsPrice) {
        this.hotelsPrice = hotelsPrice;
    }

}
