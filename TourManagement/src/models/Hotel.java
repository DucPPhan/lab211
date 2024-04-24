/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 * @author ThitXaoBoa
 */
public class Hotel implements Serializable {
    private String hotelId;
    private String hotelName;
    private String hotelLocation;
    private int hotelAvailableRooms;
    private String hotelAmenities;
    private double hotelPrice;

    public Hotel() {
    }

    public Hotel(String hotelId, String hotelName, String hotelLocation, int hotelAvailableRooms, String hotelAmenities, double hotelPrice) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelLocation = hotelLocation;
        this.hotelAvailableRooms = hotelAvailableRooms;
        this.hotelAmenities = hotelAmenities;
        this.hotelPrice = hotelPrice;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public int getHotelAvailableRooms() {
        return hotelAvailableRooms;
    }

    public void setHotelAvailableRooms(int hotelAvailableRooms) {
        this.hotelAvailableRooms = hotelAvailableRooms;
    }

    public String getHotelAmenities() {
        return hotelAmenities;
    }

    public void setHotelAmenities(String hotelAmenities) {
        this.hotelAmenities = hotelAmenities;
    }

    public double getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(double hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public void bookRooms(int numberOfRooms) {
        if (hotelAvailableRooms >= numberOfRooms) {
            hotelAvailableRooms -= numberOfRooms; // Reduce available rooms by the number booked
        } else {
            System.out.println("Not enough available rooms to book.");
        }
    }
}
