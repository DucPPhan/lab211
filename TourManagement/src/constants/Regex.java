package constants;

public class Regex {
    public static final String TOUR = "^[tT][0-9]+$";

    public static final String HOTEL = "^[hH][0-9]+$";

    public static final String BOOKING = "^[bB][0-9]+$";

    public static final String NAME = "(^[a-zA-Z][a-zA-Z\\s]{0,30}[a-zA-Z ,.'-]+$)"; // Hotel and Tour name

    public static final String PRICE = "/^-?\\d+(,\\d{3})*(\\.\\d{1,2})?$/"; // Price

    public static final String YES_SELECTION = "^(?i)(Yes|Y|yes|y)$";

    public static final String NO_SELECTION = "^(?i)(No|N|no|n)$";

    public static final String INTEGER_NUMBER = "\\d{1,10}";

    public static final String MODIFIED = "^(?i)(M|m|modify|Modify)";

    public static final String CANCEL = "^(?i)(C|c|cancel|Cancel)";
}
