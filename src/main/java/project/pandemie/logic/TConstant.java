package project.pandemie.logic;

public class TConstant {

    public static final int CITY_SCORE_IGNORE = 5;
    public static final int CONNECTION_INFECTIOUS_PATHOGEN = -1;
    public static final int CONNECTION_CLOSE_AIRPORT = 2;
    public static final int CONNECTION_PATHOGEN_THRESHOLD = 0; //TODO: Implement score;


    public static final int AIRPORT_CLOSE_ROUNDS = 2;
    public static final int CONNECTION_CLOSE_ROUNDS = 2;

    public static final float MOBILITY_SPREAD_RADIUS = 500f;


    /*
    Movepoint threshholds
     */
    public static final int MOVE_IGNORE_LOW = 20;
    public static final int MOVE_IGNORE_MED = 10;
    public static final int MOVE_IGNORE_HIGH = 5;


    /*
    City value
     */
    public static final float CITY_VALUE_INFECTED_MULT = 0.2f;
    public static final float CITY_VALUE_NOT_INFECTED_MULT = 1.3f;

    public static final int CITY_HYGIENE_THRESHOLD = 0;
    public static final int CITY_AWARENESS_THRESHOLD = 0;
    public static final int CITY_ANTIVAX_THRESHOLD = 6;
    public static final int INITIAL_CITY_QUARANTINE_LENGTH = 2;
}

