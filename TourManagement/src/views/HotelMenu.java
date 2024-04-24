package views;

import constants.Messages;
import constants.Path;
import controllers.HotelManagement;
import models.Hotel;
import utils.InputHelper;
import utils.PrintHelper;

public class HotelMenu {
    HotelManagement hotelManagement;

    public HotelMenu(HotelManagement hotelManagement) {
        this.hotelManagement = hotelManagement;
    }

    public void processMenuForHotel() {
        boolean stop = true;
        try {
            do {
                System.out.println(Messages.MENU_HOTEL_MESSAGE);
                PrintHelper.printMenu("1. Add new hotels|2. Update hotels|3. Search a hotel|4. Remove a hotel|5. View list hotels|6. Save Data|0. Back|");
                int choice = InputHelper.getUserChoice();
                switch (choice) {
                    case 1:
                        hotelManagement.addNewHotel();
                        break;
                    case 2:
                        hotelManagement.updateHotelManagement();
                        break;
                    case 3:
                        hotelManagement.searchHotelPackage();
                        break;
                    case 4:
                        hotelManagement.deleteHotel();
                        break;
                    case 5:
                        hotelManagement.viewHotel();
                        break;
                    case 6:
                        hotelManagement.saveDataObject(Path.HOTEL_FILE_PATH);
                        break;
                    case 0:
                        stop = false;
                        break;
                    default:
                        System.out.println(Messages.INVALID_CHOICE_MESSAGE);
                        break;
                }
            } while (stop);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
}
