package models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Booking implements Serializable {
    private String bookingId;
    private String tourId;
    private int numberOfSlots;
    private String hotelId;
    private int numberOfRooms;

    public Booking() {
    }

    public Booking(String bookingId, String tourId, int numberOfSlots, String hotelId, int numberOfRooms) {
        this.bookingId = bookingId;
        this.tourId = tourId;
        this.numberOfSlots = numberOfSlots;
        this.hotelId = hotelId;
        this.numberOfRooms = numberOfRooms;
    }

    // Getters and Setters
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    public void setNumberOfSlots(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    @Override
    public String toString() {
        return String.format("| %-36s | %-15s | %-20s | %-15s | %-20s |\n",
                getBookingId(), getTourId(), getNumberOfSlots(), getHotelId(), getNumberOfRooms()
        );
    }
}
