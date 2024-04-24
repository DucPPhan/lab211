package controllers;

import constants.Messages;
import models.Hotel;
import models.Tour;
import utils.InputHelper;
import utils.PrintHelper;

import java.util.*;

public class Reports {
    TourManagement tourManagement;
    HotelManagement hotelManagement;
    Booking booking;


    public Reports(TourManagement tourManagement, HotelManagement hotelManagement) {
        this.tourManagement = tourManagement;
        this.hotelManagement = hotelManagement;
    }

    public Reports(TourManagement tourManagement, HotelManagement hotelManagement, Booking booking) {
        this.tourManagement = tourManagement;
        this.hotelManagement = hotelManagement;
        this.booking = booking;
    }

    public void generateReports() {
        try {
            System.out.println("Generating reports...");
            System.out.println("1. " + Messages.POPULAR_TOURS_MESSAGE);
            System.out.println("2. " + Messages.HOTEL_OCCUPANCY_MESSAGE);
            System.out.println("3. " + Messages.REVENUE_REPORT);
            System.out.println("0. Cancel.");
            int choice = InputHelper.getUserChoice();
            switch (choice) {
                case 1:
                    generatePopularToursReport();
                    break;
                case 2:
                    generateHotelOccupancyReport();
                    break;
                case 3:
                    generateRevenueReport();
                    break;
                case 0:
                    break;
                default:
                    System.out.println(Messages.INVALID_CHOICE_MESSAGE);
                    break;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void generatePopularToursReport() {
        Map<String, Integer> tourBookingsCount = new HashMap<>();
        for (models.Booking booking : booking.getBookingList()) {
            String tourId = booking.getTourId();
            tourBookingsCount.put(tourId, tourBookingsCount.getOrDefault(tourId, 0) + 1);
        }

        // Sort tours by booking count in descending order
        List<Map.Entry<String, Integer>> sortedTours = new ArrayList<>(tourBookingsCount.entrySet());
        sortedTours.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Display the top N tours with the highest booking count
        int topNTours = 3; // Define the number of top tours to display
        System.out.println("Top " + topNTours + " Popular Tours:");
        for (int i = 0; i < Math.min(topNTours, sortedTours.size()); i++) {
            String tourId = sortedTours.get(i).getKey();
            Tour tour = tourManagement.findTourById(tourId);
            int bookingsCount = sortedTours.get(i).getValue();
            System.out.println("Tour Name: " + tour.getTourName() + ", Bookings: " + bookingsCount);
        }
    }

    private void generateHotelOccupancyReport() {
        // Calculate the occupancy rate for each hotel
        System.out.println("Hotel Occupancy Report: ");
        for (Hotel hotel : hotelManagement.getHotels()) {
            int totalRooms = hotel.getHotelAvailableRooms() + 50;
            int availableRooms = hotel.getHotelAvailableRooms();
            double occupancyRate = (totalRooms - availableRooms) / (double) totalRooms * 100;
            System.out.println("Hotel Name: " + hotel.getHotelName() + ", Occupancy Rate: " + occupancyRate + "%");
        }
    }

    private void generateRevenueReport() throws Exception {
        // Calculate total revenue generated from tour and hotel bookings
        double totalRevenue = 0.0;

        // Revenue from tour bookings
        for (models.Booking booking : booking.getBookingList()) {
            Tour tour = tourManagement.findTourById(booking.getTourId());
            double tourPrice = tour.getTourPrice();
            totalRevenue += tourPrice;
        }

        // Revenue from hotel bookings
        for (models.Booking booking : booking.getBookingList()) {
            Hotel hotel = hotelManagement.findHotelById(booking.getHotelId());
            int numberOfRooms = booking.getNumberOfRooms();
            double roomPrice = hotel.getHotelPrice();
            totalRevenue += roomPrice * numberOfRooms;
        }

        System.out.println("Total Revenue Generated: $" + totalRevenue);
    }
}
