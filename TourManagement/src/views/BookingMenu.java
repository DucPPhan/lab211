package views;

import constants.Messages;
import constants.Path;
import controllers.Booking;
import controllers.Reports;
import utils.InputHelper;
import utils.PrintHelper;

public class BookingMenu {
    //Access
    private Booking booking;
    private Reports reports;

    public BookingMenu(Booking booking, Reports reports) {
        this.booking = booking;
        this.reports = reports;
    }

    public void processMenuForBooking() throws Exception {
        boolean stop = true;
        do {
            PrintHelper.printMenu("1. Customer Booking|2. View Booking|3. Management Booking|4. Generate reports|5. Save Data|0. Back");
            int choice = InputHelper.getUserChoice();
            switch (choice) {
                case 1:
                    booking.bookingToursAndHotels();
                    break;
                case 2:
                    booking.displayBookings();
                    break;
                case 3:
                    booking.manageBookings();
                    break;
                case 4:
                    reports.generateReports();
                    break;
                case 5:
                    booking.saveDataObject(Path.BOOKING_FILE_PATH);
                case 0:
                    stop = false;
                    break;
                default:
                    System.out.println(Messages.INVALID_CHOICE_MESSAGE);
                    break;
            }
        } while (stop);
    }
}
