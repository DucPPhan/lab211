package controllers;

import constants.Messages;
import constants.Regex;
import models.Hotel;
import models.Tour;
import services.FileServices;
import utils.InputHelper;
import utils.PrintHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Booking implements FileServices {
    private List<models.Booking> bookingList = new ArrayList<>();
    private HotelManagement hotelManagement;
    private TourManagement tourManagement;

    public Booking(HotelManagement hotelManagement, TourManagement tourManagement) {
        this.hotelManagement = hotelManagement;
        this.tourManagement = tourManagement;
    }

    public List<models.Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<models.Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public List<Tour> browseTours() {
        List<Tour> tours = tourManagement.getTours();
        System.out.println(Messages.CUSTOMER_BOOKING_MESSAGE);
        PrintHelper.printTourTitleFormat();
        for (Tour tour : tours) {
            PrintHelper.printTourFormat(tour);
        }
        return tours;
    }

    public List<Hotel> browseHotels(Tour tour) throws Exception {
        List<Hotel> hotels = hotelManagement.getHotels();

        System.out.println("\n" + Messages.CUSTOMER_BOOKING_MESSAGE);
        boolean hotelAvailable = false;
        PrintHelper.printHotelTitleFormat();
        for (Hotel hotel : hotels) {
            if (hotel.getHotelLocation().contains(tour.getTourDestination())) {
                PrintHelper.printHotelFormat(hotel);
                hotelAvailable = true;
            }
        }
        if (!hotelAvailable) {
            System.out.println("\n" + Messages.HOTEL_NOT_AVAILABLE);
        }
        return hotels;
    }

    public void bookingToursAndHotels() {
        try {
            System.out.println(Messages.BOOKING_TOURS_AND_HOTELS);
            
             String tourId = InputHelper.getTourId(Messages.INPUT_ID_TOUR, Messages.SUCCESS, Messages.INVALID);
            int numberOfSlots = InputHelper.getIntegerNumber("Number of Slots: ");
            if (tourId != null) {
                String tourBookingResult = bookingTours(tourId);
                System.out.println(tourBookingResult);
                Tour tour = tourManagement.findTourById(tourId);
                if (!browseHotels(tour).isEmpty()) {
                    String hotelId = InputHelper.getHotelId(Messages.INPUT_ID_HOTEL, Messages.SUCCESS, Messages.INVALID);
                    int numberOfRooms = InputHelper.getIntegerNumber("Number of Rooms: ");
                    if (hotelId != null) {
                        String hotelBookingResult = bookingHotels(hotelId, numberOfRooms);
                        System.out.println(hotelBookingResult);

                        models.Booking booking = new models.Booking(generateBookingId(), tourId, numberOfSlots, hotelId, numberOfRooms);
                        bookingList.add(booking);
                    } else {
                        System.out.println(Messages.INPUT_HOTEL_ID_NOT_FOUND);
                    }
                } else {
                    System.out.println(Messages.HOTEL_NOT_AVAILABLE);
                }
            } else {
                System.out.println(Messages.INPUT_TOUR_ID_NOT_FOUND);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String bookingTours(String tourId) throws Exception {
        Tour tour = tourManagement.findTourById(tourId);
        if (tour == null) {
            System.out.println(Messages.INPUT_ID_TOUR_NOT_EXISTED);
            return null;
        } else {
            PrintHelper.printTourTitleFormat();
            PrintHelper.printTourFormat(tour);
            return Messages.SUCCESS_BOOKING_TOUR;
        }
    }

    private String generateBookingId() {
        String pattern = "B%03d"; 
        int nextId = 1;

        for (models.Booking booking : bookingList) {
            String currentId = booking.getBookingId().toUpperCase();
            if (currentId.matches(Regex.BOOKING)) {
                int numericPart = Integer.parseInt(currentId.substring(1)); 
                nextId = Math.max(nextId, numericPart + 1); 
            }
        }

        String nextBookingId = String.format(pattern, nextId);
        return nextBookingId;
    }

    public String bookingHotels(String hotelId, int numberOfRooms) throws Exception {
        Hotel hotel = hotelManagement.findHotelById(hotelId);
        if (hotel == null) {
            System.out.println(Messages.INPUT_ID_HOTEL_NOT_EXISTED);
            return null;
        } else {
            hotel.bookRooms(numberOfRooms);
            PrintHelper.printHotelTitleFormat();
            PrintHelper.printHotelFormat(hotel);
            //Return success
            return Messages.SUCCESS_BOOKING_HOTEL;
        }
    }

    public void viewManageBookings() {
        List<Hotel> hotels = hotelManagement.getHotelBooked();
        List<Tour> tours = tourManagement.getTourBooked();

        System.out.println(Messages.CUSTOMER_BOOKING_MESSAGE);
        PrintHelper.printLine();

        System.out.println(Messages.CUSTOMER_DISPLAY_BOOKING_TOURS);
        PrintHelper.printTourTitleFormat();
        for (Tour tour : tours) {
            PrintHelper.printTourFormat(tour);
        }

        PrintHelper.printLine();

        PrintHelper.printHotelTitleFormat();
        for (Hotel hotel : hotels) {
            PrintHelper.printHotelFormat(hotel);
        }
    }

    public void displayBookings() {
        System.out.println("All Bookings:");
        PrintHelper.printBookingTitleFormat();
        for (models.Booking booking : bookingList) {
            System.out.println(booking.toString());
        }
    }

    public void manageBookings() throws Exception {
        displayBookings();
        String bookingId = InputHelper.getString("Enter booking ID to manage:", Messages.SUCCESS, Messages.INVALID);

        boolean bookingFound = false;
        for (models.Booking booking : bookingList) {
            if (booking.getBookingId().equals(bookingId)) {
                System.out.println("Booking found:");
                System.out.println(booking.toString());
                System.out.println(Messages.SELECTION_MODIFY_MESSAGE);
                boolean action = InputHelper.getUserSelection2("", Messages.SUCCESS, Messages.INVALID);

                if (action) {
                    modifyBooking(booking);
                } else {
                    cancelBooking(booking);
                }
                bookingFound = true;
                break;
            }
        }

        if (!bookingFound) {
            System.out.println("Booking with ID " + bookingId + " not found.");
        }
    }

    private void modifyBooking(models.Booking booking) throws Exception {
        System.out.println("Enter new details for the booking:");
        browseTours();
        String newTourId = InputHelper.getTourId(Messages.INPUT_ID_TOUR, Messages.SUCCESS, Messages.INVALID);
        if (newTourId != null) {
            Tour tour = tourManagement.findTourById(newTourId);
            if (!browseHotels(tour).isEmpty()) {
                String newHotelId = InputHelper.getHotelId(Messages.INPUT_ID_HOTEL, Messages.SUCCESS, Messages.INVALID);
                if (newHotelId != null) {
                    booking.setTourId(newTourId);
                    booking.setHotelId(newHotelId);
                } else {
                    System.out.println(Messages.INPUT_HOTEL_ID_NOT_FOUND);
                }
            } else {
                System.out.println(Messages.HOTEL_NOT_AVAILABLE);
            }
        }
    }

    private void cancelBooking(models.Booking booking) {
        if (!bookingList.isEmpty()) {
            if (bookingList.remove(booking)) {
                System.out.println("Booking canceled successfully.");
            } else {
                System.out.println("Failed to cancel booking. Booking not found.");
            }
        } else {
            System.out.println("No bookings to cancel.");
        }
    }


    public void updateHotelBooking(Tour tour) throws Exception {
        browseHotels(tour);
        String idOldHotel = InputHelper.getHotelId(Messages.CHANGE_HOTEL_BOOKING, Messages.SUCCESS, Messages.INVALID);
        Hotel oldHotel = hotelManagement.findHotelById(idOldHotel);

        PrintHelper.printHotelTitleFormat();
        PrintHelper.printHotelFormat(oldHotel);

        List<Hotel> hotels = hotelManagement.getHotelAvailable();
        PrintHelper.printHotelTitleFormat();
        for (Hotel hotel : hotels) {
            PrintHelper.printHotelFormat(hotel);
        }

        String idNewHotel = InputHelper.getHotelId(Messages.CHANGE_HOTEL_BOOKING, Messages.SUCCESS, Messages.INVALID);
        Hotel newHotel = hotelManagement.findHotelById(idNewHotel);
        PrintHelper.printHotelTitleFormat();
        PrintHelper.printHotelFormat(newHotel);


        hotelManagement.changeHotelBookings(oldHotel, newHotel);
    }

    public void updateTourBooking() throws Exception {
        browseTours();
        String idOldTour = InputHelper.getTourId(Messages.CHANGE_TOUR_BOOKING, Messages.SUCCESS, Messages.INVALID);
        Tour oldTour = tourManagement.findTourById(idOldTour);

        PrintHelper.printTourTitleFormat();
        PrintHelper.printTourFormat(oldTour);

        List<Tour> tours = tourManagement.getTourAvailable();
        PrintHelper.printTourTitleFormat();
        for (Tour tour : tours) {
            PrintHelper.printTourFormat(tour);
        }

        String idTour = InputHelper.getTourId(Messages.CHANGE_TOUR_BOOKING, Messages.SUCCESS, Messages.INVALID);
        Tour newTour = tourManagement.findTourById(idTour);
       PrintHelper.printTourTitleFormat();
        PrintHelper.printTourFormat(newTour);
        tourManagement.changeTourBookings(oldTour, newTour);
    }

    public void updateBooking() throws Exception {
        try {
            System.out.println(Messages.UPDATE_BOOKING_MESSAGE);
            PrintHelper.printLine();
            System.out.println("1. " + Messages.CHANGE_HOTEL_BOOKING_MESSAGE);
            System.out.println("2. " + Messages.CHANGE_TOUR_BOOKING_MESSAGE);
            System.out.println("3. " + Messages.CANCEL_BOOKING_MESSAGE);
            int choice = InputHelper.getIntegerNumber(Messages.SELECTION_NUMBER_MESSAGE);
            switch (choice) {
                case 1:
                    Tour tour = tourManagement.findTourById(InputHelper.getTourId(Messages.INPUT_ID_TOUR, Messages.SUCCESS, Messages.INVALID));
                    updateHotelBooking(tour);
                    break;
                case 2:
                    updateTourBooking();
                    break;
                case 3:
                    System.out.println(Messages.CANCEL_BOOKING_MESSAGE);
                    break;
                default:
                    System.out.println(Messages.SELECTION_NUMBER_ERROR);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void loadDataObject(String filePath) {
        try {
            if (!bookingList.isEmpty()) {
                bookingList.clear();
            }
            File file = new File(filePath);
            if (!file.exists()) {
                throw new Exception(Messages.FILE_NOT_FOUND_ERROR);
            }
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            models.Booking booking;
            try {
                while ((booking = (models.Booking) ois.readObject()) != null) {
                    bookingList.add(booking);
                }
            } catch (EOFException e) {
                //Do nothing
            }

            ois.close();
            fis.close();
            System.out.println(Messages.FILE_READ_SUCCESSFULLY + filePath);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void saveDataObject(String filePath) {
        try {
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (models.Booking booking : bookingList) {
                oos.writeObject(booking);
            }
            oos.close();
            fos.close();
            System.out.println(Messages.FILE_WRITTEN_SUCCESSFULLY);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
