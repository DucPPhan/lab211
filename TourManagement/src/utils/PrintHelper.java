package utils;

import models.Booking;
import models.Hotel;
import models.Tour;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PrintHelper {
    public static void printLine() {
        System.out.println("----------------------------------------------------------------");
    }

    public static void printTourTitleFormat() {
        // Adjust as needed for your content
        int codeWidth = 9;
        int nameWidth = 30;
        int destinationWidth = 15;
        int durationWidth = 9;
        int descriptionWidth = 100;
        int priceWidth = 12; // x.xxx.xxx === 9 + 2 + 1
        int inclusionsWidth = 40;
        int exclusionsWidth = 40;

        System.out.printf("| %-" + codeWidth + "s | %-" + nameWidth + "s | %-" + destinationWidth + "s | %-" + durationWidth + "s | %-" + descriptionWidth + "s | %" + priceWidth + "s | %-" + inclusionsWidth + "s | %-" + exclusionsWidth + "s |\n",
                "Tour ID",
                "Tour Name",
                "Destination",
                "Duration",
                "Description",
                "Price",
                "Inclusions",
                "Exclusions");
    }

    public static void printTourFormat(Tour tour) {
        System.out.printf("| %-9s | %-30s | %-15s | %-9s | %-100s | %12.2f | %-40.70s | %-40.70s |\n",
                tour.getTourId(),
                tour.getTourName(),
                tour.getTourDestination(),
                tour.getTourDuration(),
                tour.getTourDescription(),
                tour.getTourPrice(),
                formatList(tour.getTourInclusions()),
                formatList(tour.getTourExclusions()));
    }

    public static void printTourNameFormat(Tour tour) {
        int nameWidth = 20;
        System.out.println("+-----------------+");
        System.out.printf("| %-" + nameWidth + "s |\n", "Tour Name");
        System.out.printf("| %-15s |\n", tour.getTourName());
        System.out.println("+-----------------+");

    }

    public static void printHotelTitleFormat() {
        // Adjust as needed for your content
        int codeWidth = 9;
        int nameWidth = 30;
        int locationWidth = 30;
        int roomsWidth = 17;
        int amenitiesWidth = 70;
        int priceWidth = 12; // x.xxx.xxx === 9 + 2 + 1

        System.out.printf("| %-" + codeWidth + "s | %-" + nameWidth + "s | %-" + locationWidth + "s | %-" + roomsWidth + "s | %-" + amenitiesWidth + "s | %" + priceWidth + "s |\n",
                "Hotel ID",
                "Hotel Name",
                "Location",
                "Available Rooms",
                "Amenities",
                "Price");
    }

    public static void printHotelFormat(Hotel hotel) {
        System.out.printf("| %-9s | %-30s | %-30s | %-17d | %-70s | %12.2f |\n",
                hotel.getHotelId(),
                hotel.getHotelName(),
                hotel.getHotelLocation(),
                hotel.getHotelAvailableRooms(),
                hotel.getHotelAmenities(),
                hotel.getHotelPrice()
        );
    }

    public static void printHotelNameFormat(Hotel hotel) {
        int nameWidth = 20;
        System.out.println("+-----------------+");
        System.out.printf("| %-" + nameWidth + "s |\n", "Hotel Name");
        System.out.printf("| %-15s |\n", hotel.getHotelName());
        System.out.println("+-----------------+");
    }

    public static void printTourBookings(Tour tour, Map<String, Integer> bookingCounts) {
        System.out.printf("| -%s (%s): %d bookings |",
                tour.getTourName(),
                tour.getTourDestination(),
                bookingCounts.get(tour.getTourId())
        );
    }

    public static void printAverageHotelOccupancy(double averageOccupancy) {
        System.out.printf("| -Average: %.2f%% |",
                averageOccupancy
        );
    }

    public static void printPopularDestianation(String popularDestination) {
        System.out.printf("| Popular Destination: %s |",
                popularDestination
        );
    }

    public static void printMenu(String str) {
        List<String> menuList = Arrays.asList(str.split("\\|"));
        menuList.forEach(menuItem -> {
            if (menuItem.equalsIgnoreCase("Select")) {
                System.out.print(menuItem);
            } else {
                System.out.println(menuItem);
            }

        });
    }

    private static String formatList(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static void printBookingTitleFormat() {
        // Adjust as needed for your content
        int bookingIdWidth = 36;
        int tourIdWidth = 15;
        int numberOfSlotsWidth = 20;
        int hotelIdWidth = 15;
        int numberOfRoomsWidth = 20;

        System.out.printf("| %-" + bookingIdWidth + "s | %-" + tourIdWidth + "s | %-" + numberOfSlotsWidth + "s | %-" + hotelIdWidth + "s | %-" + numberOfRoomsWidth + "s |\n",
                "Booking ID",
                "Tour ID",
                "Number of Slots",
                "Hotel ID",
                "Number of Rooms"
        );
    }

    public static void printBookingFormat(Booking booking) {
        System.out.printf("| %-9s | %-15s | %-20s | %-15s | %-20s |\n",
                booking.getBookingId(),
                booking.getTourId(),
                booking.getNumberOfSlots(),
                booking.getHotelId(),
                booking.getNumberOfRooms()
        );
    }
}
