package project.pandemie.util;

import project.pandemie.data.Pathogen;


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


    /*
    Scales our city value to 0-10
     */
    public static int scale(final int valueIn, final int baseMin, final int baseMax) {
        return Math.round((float) 10 * (float) (valueIn - baseMin) / (float) (baseMax - baseMin));
    }

    public static String calculateBiggestThreat(Pathogen a, Pathogen b, String[] priority, boolean flipDuration) {
        if (priority.length != 4) {
            return null;
        }

        int[] pri = {switcheroo(a, b, priority[0], flipDuration), switcheroo(a, b, priority[1], flipDuration), switcheroo(a, b, priority[2], flipDuration), switcheroo(a, b, priority[3], flipDuration)};

        for (int i : pri) {
            if (i == 0) {
            } else if (i > 0) {
                return a.getName();
            } else {
                return b.getName();
            }
        }
        return a.getName();
    }

    private static int switcheroo(Pathogen a, Pathogen b, String priority, boolean flipDuration) {
        switch (priority) {
            case "lethality":
                return a.getLethality() - b.getLethality();
            case "mobility":
                return a.getMobility() - b.getMobility();
            case "duration":
                if (flipDuration) {
                    return (a.getDuration() - b.getDuration()) * -1;
                }
                return a.getDuration() - b.getDuration();
            case "infectivity":
                return a.getInfectivity() - b.getInfectivity();
        }
        return 0;
    }

}
