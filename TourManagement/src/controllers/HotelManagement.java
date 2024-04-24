package controllers;

import constants.Messages;
import models.Hotel;
import services.FileServices;
import utils.InputHelper;
import utils.PrintHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HotelManagement implements FileServices {
    private List<Hotel> hotels = new ArrayList<>();

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    private List<Hotel> hotelAvailable = new ArrayList<>();

    public List<Hotel> getHotelAvailable() {
        return hotelAvailable;
    }

    public void setHotelAvailable(List<Hotel> hotelAvailable) {
        this.hotelAvailable = hotelAvailable;
    }

    private List<Hotel> hotelBooked = new ArrayList<>();

    public List<Hotel> getHotelBooked() {
        return hotelBooked;
    }

    public void setHotelBooked(List<Hotel> hotelBooked) {
        this.hotelBooked = hotelBooked;
    }

    public Hotel createNewHotel() throws Exception {
        boolean checkDuplicatedHotel = false;
        String hotelId;
        do {
            hotelId = InputHelper.getHotelId(Messages.INPUT_ID_HOTEL, Messages.SUCCESS, Messages.INVALID);
            for (Hotel hotel : hotels) {
                if (hotel.getHotelId().equals(hotelId)) {
                    checkDuplicatedHotel = true;
                }
            }
        } while (checkDuplicatedHotel);

        String hotelName = InputHelper.getString(Messages.INPUT_NAME_HOTEL, Messages.SUCCESS, Messages.INVALID);
        String hotelLocation = InputHelper.getString(Messages.INPUT_LOCATION_HOTEL, Messages.SUCCESS, Messages.INVALID);
        int hotelAvailableRooms = InputHelper.getIntegerNumber(Messages.INPUT_AVAILABLE_ROOMS_HOTEL);
        String hotelAmenities = InputHelper.getString(Messages.INPUT_AMENITIES_HOTEL, Messages.SUCCESS, Messages.INVALID);
        String hotelPrice = InputHelper.getString(Messages.INPUT_PRICE_HOTEL, Messages.SUCCESS, Messages.INVALID);
        return new Hotel(hotelId, hotelName, hotelLocation, hotelAvailableRooms, hotelAmenities, Double.parseDouble(hotelPrice));
    }

    public void addNewHotel() throws Exception {
        System.out.println(Messages.CREATE_HOTEL_MESSAGE);
        Hotel hotel = createNewHotel();
        if (hotel != null) {
            hotels.add(hotel);
            System.out.println(Messages.CREATE_HOTEL_SUCCESS);
        } else System.out.println(Messages.CREATE_HOTEL_ERROR);
    }

    public void searchHotelByLocation(String location) throws Exception {
        List<Hotel> locations = new ArrayList<>();

        for (Hotel hotel : hotels) {
            if (hotel.getHotelLocation().toUpperCase().trim().contains(location.toUpperCase().trim())) {
                locations.add(hotel);
            }
        }

        if (!locations.isEmpty()) {
            System.out.println(Messages.SEARCH_HOTEL_LOCATION);
            PrintHelper.printHotelTitleFormat();
            for (Hotel hotel : locations) {
                PrintHelper.printHotelFormat(hotel);
            }
        } else {
            System.out.println(Messages.SEARCH_HOTEL_NOT_FOUND);
        }
    }

    public void searchHotelByAmenities(String amenities) throws Exception {
        List<Hotel> amenitiess = new ArrayList<Hotel>();

        for (Hotel hotel : hotels) {
            if (hotel.getHotelAmenities().toUpperCase().trim().contains(amenities.toUpperCase().trim())) {
                amenitiess.add(hotel);
            }
        }

        if (!amenitiess.isEmpty()) {
            System.out.println(Messages.SEARCH_HOTEL_AMENITIES);
            PrintHelper.printHotelTitleFormat();
            for (Hotel hotel : amenitiess) {
                PrintHelper.printHotelFormat(hotel);
            }
        } else System.out.println(Messages.SEARCH_HOTEL_NOT_FOUND);
    }

    public void searchHotelByNames(String name) throws Exception {
        List<Hotel> amenitiess = new ArrayList<Hotel>();

        for (Hotel hotel : hotels) {
            if (hotel.getHotelName().toUpperCase().trim().contains(name.toUpperCase().trim())) {
                amenitiess.add(hotel);
            }
        }

        if (!amenitiess.isEmpty()) {
            System.out.println(Messages.SEARCH_HOTEL_AMENITIES);
            PrintHelper.printHotelTitleFormat();
            for (Hotel hotel : amenitiess) {
                PrintHelper.printHotelFormat(hotel);
            }
        } else System.out.println(Messages.SEARCH_HOTEL_NOT_FOUND);
    }

    public void searchHotelByRoomAvailable(int roomAvailable) throws Exception {
        List<Hotel> rooms = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.getHotelAvailableRooms() >= roomAvailable) {
                rooms.add(hotel);
            }

        }
        if (!rooms.isEmpty()) {
            PrintHelper.printHotelTitleFormat();
            for (Hotel hotel : rooms) {
                PrintHelper.printHotelFormat(hotel);
            }
        } else System.out.println(Messages.SEARCH_HOTEL_NOT_FOUND);

    }

    public void searchHotelPackage() throws Exception {
        int choice;
        try {
            System.out.println("1. " + Messages.SEARCH_HOTEL_LOCATION);
            System.out.println("2. " + Messages.SEARCH_HOTEL_NAME);
            System.out.println("3. " + Messages.SEARCH_HOTEL_ROOM_AVAILABLE);
            System.out.println("4. " + Messages.SEARCH_TOUR_CANCELLED);
            choice = InputHelper.getIntegerNumber(Messages.SELECTION_NUMBER_MESSAGE);
            switch (choice) {
                case 1:
                    searchHotelByLocation(InputHelper.getString(Messages.SEARCH_HOTEL_LOCATION, Messages.SUCCESS, Messages.INVALID));
                    break;
                case 2:
                    searchHotelByNames(InputHelper.getString(Messages.SEARCH_HOTEL_NAME, Messages.SUCCESS, Messages.INVALID));
                    break;
                case 3:
                    searchHotelByRoomAvailable(InputHelper.getIntegerNumber(Messages.SEARCH_HOTEL_ROOM_AVAILABLE + ". Enter the number: "));
                    break;
                case 4:
                    System.out.println(Messages.SEARCH_TOUR_CANCELLED);
                    break;
                default:
                    System.out.println(Messages.SELECTION_NUMBER_ERROR);
                    break;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateHotelManagement() throws Exception {
        viewHotel();
        String hotelId = InputHelper.getHotelId(Messages.INPUT_ID_HOTEL, Messages.SUCCESS, Messages.INVALID);
        Hotel updateHotel = findHotelById(hotelId);
        if (updateHotel == null) {
            System.out.println(Messages.INPUT_HOTEL_NOT_FOUND);
        } else {
            updateHotel(updateHotel);
        }
    }

    public void updateHotel(Hotel updateHotel) {
        int choice;
        try {
            boolean checked;
            do {
                System.out.println(Messages.UPDATE_HOTEL_MESSAGE);
                System.out.println("1. " + Messages.UPDATE_HOTEL_NAME);
                System.out.println("2. " + Messages.UPDATE_HOTEL_LOCATION);
                System.out.println("3. " + Messages.UPDATE_HOTEL_ROOM_AVAILABILITY);
                System.out.println("4. " + Messages.UPDATE_HOTEL_AMENITIES);
                System.out.println("5. " + Messages.UPDATE_HOTEL_PRICE);
                choice = InputHelper.getIntegerNumber(Messages.SELECTION_NUMBER_MESSAGE);
                switch (choice) {
                    case 1:
                        String newName = InputHelper.getStringUpdate(Messages.INPUT_NAME_HOTEL, Messages.SUCCESS, Messages.INVALID);
                        if (!newName.isEmpty()) {
                            updateHotel.setHotelName(newName);
                            System.out.println(Messages.UPDATE_TOUR_SUCCESS);
                        }
                        break;
                    case 2:
                        String newLocation = InputHelper.getStringUpdate(Messages.INPUT_LOCATION_HOTEL, Messages.SUCCESS, Messages.INVALID);
                        if (!newLocation.isEmpty()) {
                            updateHotel.setHotelLocation(newLocation);
                            System.out.println(Messages.UPDATE_TOUR_SUCCESS);
                        }
                        break;
                    case 3:
                        int newRoomAvailable = InputHelper.getIntegerNumber(Messages.INPUT_AVAILABLE_ROOMS_HOTEL);
                        if (newRoomAvailable > 0) {
                            updateHotel.setHotelAvailableRooms(newRoomAvailable);
                            System.out.println(Messages.UPDATE_TOUR_SUCCESS);
                        }
                        break;
                    case 4:
                        String newAmenities = InputHelper.getStringUpdate(Messages.INPUT_AMENITIES_HOTEL, Messages.SUCCESS, Messages.INVALID);
                        if (!newAmenities.isEmpty()) {
                            updateHotel.setHotelAmenities(newAmenities);
                            System.out.println(Messages.UPDATE_TOUR_SUCCESS);
                        }
                        break;
                    case 5:
                        String newPrice = InputHelper.getStringUpdatePrice(Messages.INPUT_PRICE_HOTEL, Messages.SUCCESS, Messages.INVALID);
                        if (!newPrice.isEmpty() && Double.parseDouble(newPrice) > 0) {
                            updateHotel.setHotelPrice(Double.parseDouble(newPrice));
                            System.out.println(Messages.UPDATE_TOUR_SUCCESS);
                        }
                        break;
                    default:
                        System.out.println(Messages.SELECTION_NUMBER_ERROR);
                        break;
                }
                System.out.println(Messages.CONTINUE_MESSAGE);
                checked = InputHelper.getUserSelection("", Messages.SUCCESS, Messages.INVALID);
            } while (checked);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void removeHotel(Hotel hotel) throws Exception {
        System.out.println(Messages.CONFIRM_DELETE_MESSAGE);
        boolean confirmed = InputHelper.getUserSelection("", Messages.SUCCESS, Messages.INVALID);
        if (confirmed) {
            hotels.remove(hotel);
            System.out.println(Messages.DELETE_HOTEL_SUCCESS);
        } else {
            System.out.println(Messages.DELETE_HOTEL_CANCELLED);
            return;
        }
    }

    public void deleteHotel() throws Exception {
        System.out.println(Messages.DELETE_HOTEL_MESSAGE);
        viewHotel();
        try {
            if (!hotels.isEmpty()) {
                System.out.println(Messages.SELECTION_BELOW_MESSAGE);

                String deleteId = InputHelper.getHotelId(Messages.INPUT_ID_HOTEL, Messages.SUCCESS, Messages.INVALID);
                boolean exists = checkExistsHotel(deleteId);
                if (exists) {
                    Hotel hotelDelete = findHotelById(deleteId);
                    if (hotelDelete != null) {
                        removeHotel(hotelDelete);
                    } else {
                        System.out.println(Messages.SEARCH_HOTEL_NOT_FOUND);
                    }
                } else {
                    System.out.println(Messages.INPUT_HOTEL_ID_NOT_FOUND);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean checkExistsHotel(String hotelId) throws Exception {
        return findHotelById(hotelId) != null;
    }

    public Hotel findHotelById(String hotelId) throws Exception {
        Hotel findHotel = null;
        for (Hotel hotel : hotels) {
            if (hotel.getHotelId().equalsIgnoreCase(hotelId)) {
                findHotel = hotel;
                break;
            }
        }
        return findHotel;
    }

    public void viewHotel() {
        System.out.println(Messages.VIEW_HOTEL_MESSAGE);
        if (!hotels.isEmpty()) {
            PrintHelper.printHotelTitleFormat();
            for (Hotel hotel : hotels) {
                if (hotel != null) {
                    PrintHelper.printHotelFormat(hotel);
                } else {
                    System.out.println(Messages.VIEW_HOTEL_FOUND_ERROR);
                    break;
                }
            }
        }
    }

    public void viewHotelBookings() throws Exception {
        System.out.println(Messages.VIEW_HOTEL_BOOKINGS_MESSAGE);
        if (!hotels.isEmpty()) {
            PrintHelper.printHotelTitleFormat();
            for (Hotel hotel : hotels) {
                if (hotel != null) {
                    PrintHelper.printHotelFormat(hotel);
                } else {
                    System.out.println(Messages.VIEW_HOTEL_FOUND_ERROR);
                    break;
                }
            }
        }
    }

    public boolean isHotelAvailable(String hotelId) throws Exception {
        List<Hotel> hotelsAvailable = getHotelsAvailable();
        return !hotelsAvailable.isEmpty();
    }

    public List<Hotel> getHotelsAvailable() throws Exception {
        for (Hotel hotel : hotels) {
            if (hotel.getHotelAvailableRooms() > 0) {
                hotelAvailable.add(hotel);
            }
        }
        return hotelAvailable;
    }

    public void changeHotelBookings(Hotel oldHotel, Hotel newHotel) throws Exception {
        try {
            hotelBooked.remove(oldHotel);
            hotelBooked.add(newHotel);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void loadDataObject(String filePath) {
        if (!hotels.isEmpty()) {
            hotels.clear();
        }
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println(Messages.FILE_NOT_FOUND_ERROR);
                return;
            }
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Hotel hotel;
            try {
                while ((hotel = (Hotel) ois.readObject()) != null) {
                    hotels.add(hotel);
                }
            } catch (EOFException ex) {
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
            if (hotels.isEmpty()) {
                System.out.println(Messages.FILE_NOT_WRITABLE_ERROR);
                return;
            }
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Hotel hotel : hotels) {
                oos.writeObject(hotel);
            }
            oos.close();
            fos.close();
            System.out.println(Messages.FILE_WRITTEN_SUCCESSFULLY);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
