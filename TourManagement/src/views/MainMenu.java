package views;

import constants.Messages;
import constants.Path;
import controllers.Booking;
import controllers.HotelManagement;
import controllers.Reports;
import controllers.TourManagement;
import utils.InputHelper;
import utils.PrintHelper;

public class MainMenu {
    public static void main(String[] args) throws Exception {
        //Access
        HotelManagement hotelManagement = new HotelManagement();
        TourManagement tourManagement = new TourManagement();
        Booking booking = new Booking(hotelManagement, tourManagement);
        Reports reports = new Reports(tourManagement, hotelManagement);


        //Reload data when program starts
        tourManagement.loadDataObject(Path.TOUR_FILE_PATH);
        hotelManagement.loadDataObject(Path.HOTEL_FILE_PATH);
        booking.loadDataObject(Path.BOOKING_FILE_PATH);
        HotelMenu hotelMenu = new HotelMenu(hotelManagement);
        TourMenu tourMenu = new TourMenu(tourManagement);
        BookingMenu bookingMenu = new BookingMenu(booking, reports);

        boolean stop = true;
        do {
            System.out.println("\n" + Messages.MENU_WELCOME_MESSAGE);
            PrintHelper.printMenu("1. Tour Management|2. Hotel Management|3. Customer Booking|0. Quit");
            int choice = InputHelper.getIntegerNumber();
            switch (choice) {
                case 1:
                    tourMenu.processMenuForTour();
                    break;
                case 2:
                    hotelMenu.processMenuForHotel();
                    break;
                case 3:
                    bookingMenu.processMenuForBooking();
                    break;
                case 0:
                    stop = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println(Messages.INVALID_CHOICE_MESSAGE);
                    break;
            }
        } while (stop);
    }
}
