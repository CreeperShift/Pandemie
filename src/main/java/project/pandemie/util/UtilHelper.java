package project.pandemie.util;

import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.HashMap;

public class UtilHelper {

    private UtilHelper() {
    }

    public static int stringValueToNumeric(String value) {
        switch (value) {
            case "--":
                return -2;
            case "-":
                return -1;
            case "o":
                return 0;
            case "+":
                return 1;
            case "++":
                return 2;
        }
        return 0;
    }

    /*
    Calculates the distance between 2 coordinates;
     */
    public static final double R = 6372.8; // In kilometers

    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }

}
