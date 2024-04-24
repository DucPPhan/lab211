package views;

import constants.Messages;
import constants.Path;
import controllers.TourManagement;
import utils.InputHelper;
import utils.PrintHelper;

public class TourMenu {
    TourManagement tourManagement;

    public TourMenu(TourManagement tourManagement) {
        this.tourManagement = tourManagement;
    }

    public void processMenuForTour() {
        boolean stop = true;
        try {
            do {
                System.out.println(Messages.MENU_TOUR_MESSAGE);
                PrintHelper.printMenu("1. Create a new tour package|2. View existing tours|3. Update existing tours|4. Delete a tour package|5. Save Data|6. Search Tour package|0. Back|");
                int choice = InputHelper.getUserChoice();
                switch (choice) {
                    case 1:
                        tourManagement.createNewTourPackage();
                        break;
                    case 2:
                        tourManagement.viewTour();
                        break;
                    case 3:
                        tourManagement.updateTourPackage();
                        break;
                    case 4:
                        tourManagement.deleteTourPackage();
                        break;
                    case 5:
                        tourManagement.saveDataObject(Path.TOUR_FILE_PATH);
                        break;
                    case 6:
                        tourManagement.searchTourPackage();
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
            System.out.println(ex.getMessage());
        }
    }
}
