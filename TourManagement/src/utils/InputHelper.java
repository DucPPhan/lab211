package utils;

import constants.Messages;
import constants.Regex;

import java.util.Scanner;

public class InputHelper {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getUserChoice() {
        int numberChoice = 0;
        try {
            numberChoice = getIntegerNumber();
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return numberChoice;
    }

    public static int getIntegerNumber() throws Exception {
        int number = 0;
        String s;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number: ");
        s = sc.nextLine();
        if (!s.matches(Regex.INTEGER_NUMBER)) {
            throw new Exception("Data invalid.");
        } else {
            number = Integer.parseInt(s);
        }
        return number;
    }

    public static int getIntegerNumber(String displayMessage) throws Exception {
        int number = 0;
        String s;
        Scanner sc = new Scanner(System.in);
        System.out.println(displayMessage);
        s = sc.nextLine();
        if (!s.matches(Regex.INTEGER_NUMBER)) {
            throw new Exception(Messages.DATA_INVALID);
        } else {
            number = Integer.parseInt(s);
        }
        return number;
    }

    public static String getDoubleNumber(String displayMessage) throws Exception {
        String str = "";
        boolean check = false;
        try {
            do {
                System.out.println(displayMessage);
                str = scanner.nextLine().trim();
                if (!str.isEmpty() && !str.matches(Regex.PRICE)) {
                    System.out.println(Messages.DATA_INVALID);
                } else {
                    System.out.println(Messages.DATA_VALID);
                    check = true;
                }
            } while (!check);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return str;
    }

    //Get String, Check String isEmpty or not
    public static String getString(String displayMessage, String successMessage, String errorMessage) {
        String str = "";
        boolean exists = true;
        do {
            System.out.println(displayMessage);
            str = scanner.nextLine().trim();
            if (str.isEmpty()) {
                System.out.println(errorMessage);
            } else {
                System.out.println(successMessage);
                exists = false;
            }
        } while (exists);
        return str;
    }

    //Get String and check if it blanks, its will keep the old String
    public static String getStringUpdate(String displayMessage, String successMessage, String errorMessage) {
        String str = "";
        boolean check = true;
        try {
            do {
                System.out.println(displayMessage);
                str = scanner.nextLine().trim();
//                if (!str.isEmpty() && !str.matches(Regex.NAME)) {
                if (str.isEmpty()) {
                    System.out.println(errorMessage);
                } else {
                    System.out.println(successMessage);
                    check = false;
                }
            } while (check);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return str;
    }

    public static String getStringUpdatePrice(String displayMessage, String successMessage, String errorMessage) {
        String str = "";
        boolean check = false;
        try {
            do {
                System.out.println(displayMessage);
                str = scanner.nextLine().trim();
                if (!str.isEmpty() && !str.matches(Regex.PRICE)) {
                    System.out.println(errorMessage);
                } else {
                    System.out.println(successMessage);
                    check = true;
                }
            } while (!check);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return str;
    }

    public static boolean getUserSelection(String displayMessage, String successMessage, String errorMessage) {
        String str = "";
        boolean selected = false;
        try {
            str = scanner.nextLine().trim();
            System.out.println(displayMessage);
            if (str.isEmpty()) {
                System.out.println(errorMessage);
                return false; // return false
            } else if (str.matches(Regex.NO_SELECTION)) {
                System.out.println("Your selection is 'NO'. Please select your next choice below!");
                return false; // return false
            } else if (str.matches(Regex.YES_SELECTION)) {
                System.out.println("Your selection is 'YES'. PLease continue!");
                selected = true; // return true
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return selected;
    }

    public static String getTourId(String displayMessage, String successMessage, String errorMessage) {
        String str = "";
        boolean check = true;
        try {
            do {
                System.out.println(displayMessage);
                str = scanner.nextLine().trim();
                if (!str.isEmpty() && !str.matches(Regex.TOUR)) {
                    System.out.println(errorMessage + " Tour id!");
                } else {
                    System.out.println(successMessage);
                    check = false;
                }
            } while (check);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return str;
    }

    public static String getHotelId(String displayMessage, String successMessage, String errorMessage) {
        String str = "";
        boolean check = true;
        try {
            do {
                System.out.println(displayMessage);
                str = scanner.nextLine().trim();
                if (!str.isEmpty() && !str.matches(Regex.HOTEL)) {
                    System.out.println(errorMessage + " Hotel id!");
                } else {
                    System.out.println(successMessage);
                    check = false;
                }
            } while (check);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return str;
    }

    public static String getBookingId(String displayMessage, String successMessage, String errorMessage) {
        String str = "";
        boolean check = true;
        try {
            do {
                System.out.println(displayMessage);
                str = scanner.nextLine().trim();
                if (!str.isEmpty() && !str.matches(Regex.BOOKING)) {
                    System.out.println(errorMessage + " Booking id!");
                } else {
                    System.out.println(successMessage);
                    check = false;
                }
            } while (check);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return str;
    }

    public static boolean getUserSelection2(String displayMessage, String successMessage, String errorMessage) {
        String str = "";
        boolean selected = false;
        try {
            str = scanner.nextLine().trim();
            System.out.println(displayMessage);
            if (str.isEmpty()) {
                System.out.println(errorMessage);
                return false; // return false
            } else if (str.matches(Regex.CANCEL)) {
                System.out.println("Your selection is 'CANCEL'. Please select your next choice below!");
                return false; // return false
            } else if (str.matches(Regex.MODIFIED)) {
                System.out.println("Your selection is 'MODIFY'. PLease continue!");
                selected = true; // return true
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return selected;
    }

}
