package controllers;

import constants.Messages;
import models.Tour;
import services.FileServices;
import utils.InputHelper;
import utils.PrintHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TourManagement implements FileServices {
    private List<Tour> tours = new ArrayList<>();

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    private List<Tour> tourAvailable = new ArrayList<>();

    public List<Tour> getTourAvailable() {
        return tourAvailable;
    }

    public void setTourAvailable(List<Tour> tourAvailable) {
        this.tourAvailable = tourAvailable;
    }

    private List<Tour> tourBooked = new ArrayList<>();

    public List<Tour> getTourBooked() {
        return tourBooked;
    }

    public void setTourBooked(List<Tour> tourBooked) {
        this.tourBooked = tourBooked;
    }

    private List<Tour> tourCanceled = new ArrayList<>();

    public List<Tour> getTourCanceled() {
        return tourCanceled;
    }

    public void setTourCanceled(List<Tour> tourCanceled) {
        this.tourCanceled = tourCanceled;
    }

    public Tour createNewTour() {
        boolean checkDuplicateTour = false;
        String tourId;
        do {
            tourId = InputHelper.getTourId(Messages.INPUT_ID_TOUR, Messages.SUCCESS, Messages.INVALID);
            for (Tour tour : tours) {
                if (tour.getTourId().equals(tourId)) {
                    checkDuplicateTour = true;
                }
            }
        } while (checkDuplicateTour);
        String tourName = InputHelper.getString(Messages.INPUT_NAME_TOUR, Messages.SUCCESS, Messages.INVALID);
        String tourDestination = InputHelper.getString(Messages.INPUT_DESTINATION_TOUR, Messages.SUCCESS, Messages.INVALID);
        String tourDuration = InputHelper.getString(Messages.INPUT_DURATION_TOUR, Messages.SUCCESS, Messages.INVALID);
        String tourDescription = InputHelper.getString(Messages.INPUT_DESCRIPTION_TOUR, Messages.SUCCESS, Messages.INVALID);
        String tourPrice = InputHelper.getString(Messages.INPUT_PRICE_TOUR, Messages.SUCCESS, Messages.INVALID);
        String tourInclusions = InputHelper.getString(Messages.INPUT_INCLUSIONS_TOUR, Messages.SUCCESS, Messages.INVALID);
        String tourExclusions = InputHelper.getString(Messages.INPUT_EXCLUSIONS_TOUR, Messages.SUCCESS, Messages.INVALID);
        return new Tour(tourId, tourName, tourDestination, tourDuration, tourDescription, Double.parseDouble(tourPrice), tourInclusions, tourExclusions);
    }

    public void createNewTourPackage() {
        System.out.println(Messages.CREATE_TOUR_MESSAGE);
        Tour tour = createNewTour();
        if (tour != null) {
            tours.add(tour);
            System.out.println(Messages.CREATE_TOUR_SUCCESS);
        } else System.out.println(Messages.CREATE_HOTEL_ERROR);
    }

    public void updateTour(Tour updateTour) {
        int choice;
        boolean checked;
        do {
            System.out.println(Messages.UPDATE_TOUR_MESSAGE);
            System.out.println("1. " + Messages.UPDATE_TOUR_NAME);
            System.out.println("2. " + Messages.UPDATE_TOUR_DESTINATION);
            System.out.println("3. " + Messages.UPDATE_TOUR_DURATION);
            System.out.println("4. " + Messages.UPDATE_TOUR_DESCRIPTION);
            System.out.println("5. " + Messages.UPDATE_TOUR_PRICE);
            System.out.println("6. " + Messages.UPDATE_TOUR_INCLUSIONS);
            System.out.println("7. " + Messages.UPDATE_TOUR_EXCLUSIONS);
            System.out.println("8. " + Messages.UPDATE_TOUR_CANCEL);
            System.out.println(Messages.SELECTION_NUMBER_MESSAGE);
            choice = InputHelper.getUserChoice();
            switch (choice) {
                case 1: {
                    String newName = InputHelper.getStringUpdate(Messages.INPUT_NAME_TOUR, Messages.SUCCESS, Messages.INVALID);
                    if (!newName.isEmpty()) {
                        updateTour.setTourName(newName);
                        System.out.println(Messages.UPDATE_TOUR_SUCCESS);
                    }
                    break;
                }
                case 2: {
                    String newDestination = InputHelper.getStringUpdate(Messages.INPUT_DESTINATION_TOUR, Messages.SUCCESS, Messages.INVALID);
                    if (!newDestination.isEmpty()) {
                        updateTour.setTourDestination(newDestination);
                        System.out.println(Messages.UPDATE_TOUR_SUCCESS);
                    }
                    break;
                }
                case 3: {
                    String newDuration = InputHelper.getStringUpdate(Messages.INPUT_DURATION_TOUR + "NEW", Messages.SUCCESS, Messages.INVALID);
                    if (!newDuration.isEmpty()) {
                        updateTour.setTourDuration(newDuration);
                        System.out.println(Messages.UPDATE_TOUR_SUCCESS);
                    }
                    break;
                }
                case 4: {
                    String newDescription = InputHelper.getStringUpdate(Messages.INPUT_DESCRIPTION_TOUR, Messages.SUCCESS, Messages.INVALID);
                    if (!newDescription.isEmpty()) {
                        updateTour.setTourDescription(newDescription);
                        System.out.println(Messages.UPDATE_TOUR_SUCCESS);
                    }
                    break;
                }
                case 5: {
                    String newPrice = InputHelper.getStringUpdatePrice(Messages.INPUT_PRICE_TOUR, Messages.SUCCESS, Messages.INVALID);
                    if (!newPrice.isEmpty() && Double.parseDouble(newPrice) >= 0) {
                        updateTour.setTourPrice(Double.parseDouble(newPrice));
                        System.out.println(Messages.UPDATE_TOUR_SUCCESS);
                    }
                    break;
                }
                case 6: {
                    String newInclusions = InputHelper.getStringUpdate(Messages.INPUT_INCLUSIONS_TOUR, Messages.SUCCESS, Messages.INVALID);
                    if (!newInclusions.isEmpty()) {
                        updateTour.setTourInclusions(newInclusions);
                        System.out.println(Messages.UPDATE_TOUR_SUCCESS);
                    }
                    break;
                }
                case 7: {
                    String newExclusions = InputHelper.getStringUpdate(Messages.INPUT_EXCLUSIONS_TOUR, Messages.SUCCESS, Messages.INVALID);
                    if (!newExclusions.isEmpty()) {
                        updateTour.setTourExclusions(newExclusions);
                        System.out.println(Messages.UPDATE_TOUR_SUCCESS);
                    }
                    break;
                }
                case 8: {
                    System.out.println(Messages.UPDATE_TOUR_CANCEL);
                    break;
                }
                default: {
                    System.out.println(Messages.SELECTION_NUMBER_ERROR);
                    break;
                }
            }
            System.out.println(Messages.CONTINUE_MESSAGE);
            checked = InputHelper.getUserSelection("", Messages.SUCCESS, Messages.INVALID);
        } while (checked);
    }

    public void updateTourPackage() {
        System.out.println(Messages.UPDATE_TOUR_MESSAGE);
        viewTour();
        String updateTourId = InputHelper.getTourId(Messages.INPUT_ID_TOUR, Messages.SUCCESS, Messages.INVALID);
        Tour updateTour = tours.stream().filter(tour -> tour.getTourId().equals(updateTourId)).findFirst().orElse(null);
        if (updateTour != null) {
            updateTour(updateTour);
            System.out.println(Messages.UPDATE_TOUR_SUCCESS);
        } else {
            System.out.println(Messages.UPDATE_TOUR_NOT_FOUND);
        }
    }

    public void searchTourByDestination(String destination) {
        List<Tour> destinations = new ArrayList<>();

        for (Tour tour : tours) {
            if (tour.getTourDestination().toUpperCase().trim().contains(destination.toUpperCase().trim())) {
                destinations.add(tour);
            }
        }
        if (!destinations.isEmpty()) {
            PrintHelper.printTourTitleFormat();
            for (Tour tour : destinations) {
                PrintHelper.printTourFormat(tour);
            }
        } else System.out.println(Messages.SEARCH_TOUR_NOT_FOUND);
        
    }

    public void searchTourByDuration(String duration) {
        List<Tour> durations = new ArrayList<>();

        for (Tour tour : tours) {
            if (tour.getTourDuration().toUpperCase().trim().contains(duration.toUpperCase().trim())) {
                durations.add(tour);
            }
        }
        if (!durations.isEmpty()) {
            PrintHelper.printTourTitleFormat();
            for (Tour tour : durations) {
                PrintHelper.printTourFormat(tour);
            }
        } else {
            System.out.println(Messages.SEARCH_TOUR_NOT_FOUND);
        }
    }

    public void searchTourByPrice(double price) {
        PrintHelper.printTourTitleFormat();
        for (Tour tour : tours) {
            if (tour.getTourPrice() == price) {
                PrintHelper.printTourFormat(tour);
            } else {
                System.out.println(Messages.SEARCH_TOUR_ERROR);
                break;
            }
        }
    }

    public void searchTourByName(String name) {
        List<Tour> names = new ArrayList<>();
        for (Tour tour : tours) {
            if (tour.getTourName().toUpperCase().trim().contains(name.toUpperCase().trim())) {
                names.add(tour);
            }
        }
        if (!names.isEmpty()) {
            PrintHelper.printTourTitleFormat();
            for (Tour tour : names) {
                PrintHelper.printTourFormat(tour);
            }
        } else {
            System.out.println(Messages.SEARCH_TOUR_NOT_FOUND);
        }
    }

    public void searchTourPackage() {
        System.out.println(Messages.SEARCH_TOUR_MESSAGE);
        int choice;
        System.out.println("1. " + Messages.SEARCH_TOUR_DESTINATION);
        System.out.println("2. " + Messages.SEARCH_TOUR_NAME);
        System.out.println("3. " + Messages.SEARCH_TOUR_PRICE);
        System.out.println("4. " + Messages.SEARCH_TOUR_CANCELLED);
        System.out.println(Messages.SELECTION_NUMBER_MESSAGE);
        choice = InputHelper.getUserChoice();
        switch (choice) {
            case 1: {
                String destination = InputHelper.getString(Messages.INPUT_DESTINATION_TOUR, Messages.SUCCESS, Messages.INVALID);
                searchTourByDestination(destination);
                break;
            }
            case 2: {
                String name = InputHelper.getString(Messages.INPUT_NAME_TOUR, Messages.SUCCESS, Messages.INVALID);
                searchTourByName(name);
                break;
            }
            case 3: {
                String price = InputHelper.getString(Messages.INPUT_PRICE_TOUR, Messages.SUCCESS, Messages.INVALID);
                searchTourByPrice(Double.parseDouble(price));
                break;
            }
            case 4: {
                System.out.println(Messages.SEARCH_TOUR_CANCELLED);
                break;
            }
            default: {
                System.out.println(Messages.SELECTION_NUMBER_ERROR);
                break;
            }
        }
    }

    public void viewTour() {
        System.out.println(Messages.VIEW_TOUR_PACKAGES);
        if (!tours.isEmpty()) {
            PrintHelper.printTourTitleFormat();
            for (Tour tour : tours) {
                if (tour != null) {
                    PrintHelper.printTourFormat(tour);
                } else {
                    System.out.println(Messages.VIEW_TOUR_FOUND_ERROR);
                    break;
                }
            }
        }
    }

    public void deleteTourPackage() {
        System.out.println(Messages.DELETE_TOUR_MESSAGE);
        if (!tours.isEmpty()) {
            System.out.println(Messages.SELECTION_BELOW_MESSAGE);
            viewTour();
            String deleteTourId = InputHelper.getTourId(Messages.INPUT_ID_TOUR, Messages.SUCCESS, Messages.INVALID);
            boolean existed = checkTourExisted(deleteTourId);
            if (existed) {
                Tour deleteTour = tours.stream().filter(tour -> tour.getTourId().equals(deleteTourId)).findFirst().orElse(null);
                if (deleteTour == null) {
                    System.out.println(Messages.DELETE_TOUR_NOT_FOUND);
                } else {
                    System.out.println(Messages.CONFIRM_DELETE_MESSAGE + "Y/N");
                    boolean confirmed = InputHelper.getUserSelection("", Messages.SUCCESS, Messages.INVALID);
                    if (confirmed) { // if true
                        tours.remove(deleteTour);
                        System.out.println(Messages.DELETE_TOUR_SUCCESS);
                        // Show result after change
                        viewTour();
                    } else {
                        System.out.println(Messages.DELETE_TOUR_CANCELLED);
                        return;
                    }
                }
            } else {
                System.out.println(Messages.INPUT_ID_TOUR_NOT_EXISTED);
                return;
            }
        }
    }

    public Tour findTourById(String tourId) {
        Tour findTour = null;
        findTour = tours.stream().filter(tour -> tour.getTourId().equals(tourId)).findFirst().orElse(findTour);
        return findTour;
    }

    public boolean checkTourExisted(String tourId) {
        Tour findTour = findTourById(tourId);
        return findTour != null;
    }

    public boolean isTourAvailable() throws Exception {
        setTourAvailable(tours);
        return !tourAvailable.isEmpty();
    }

    public void changeTourBookings(Tour oldTour, Tour newTour) throws Exception {
        try {
            tourBooked.remove(oldTour);
            tourBooked.add(newTour);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void loadDataObject(String filePath) {
        try {
            if (!tours.isEmpty()) {
                tours.clear();
            }
            File file = new File(filePath);
            if (!file.exists()) {
                throw new Exception(Messages.FILE_NOT_FOUND_ERROR);
            }
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Tour tour;
            try {
                while ((tour = (Tour) ois.readObject()) != null) {
                    tours.add(tour);
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
            for (Tour tour : tours) {
                oos.writeObject(tour);
            }
            oos.close();
            fos.close();
            System.out.println(Messages.FILE_WRITTEN_SUCCESSFULLY);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
