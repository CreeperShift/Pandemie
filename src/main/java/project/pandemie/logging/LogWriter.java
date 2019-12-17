package project.pandemie.logging;

import project.pandemie.data.City;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;

public class LogWriter {

    private String path;
    private boolean appendToFile = false;

    public LogWriter(String filePath) {
        path = filePath;
    }

    public LogWriter(String filePath, boolean appendValue) {
        path = filePath;
        appendToFile = appendValue;
    }

    /**
     * Schreibt den übergebenen String in ein .txt Dokument
     *
     * @param Text übergebener String der geschrieben werden soll
     * @throws IOException
     */

    public void writeToFile(String Text) throws IOException {
        FileWriter file = new FileWriter(path, appendToFile);
        PrintWriter printLine = new PrintWriter(file);
        printLine.printf("%s" + "%n", Text);

        printLine.close();
    }

    /**
     * Schreibt die übergebene Stadt in ein extrenes .txt dokument
     *
     * @param city Stadt welche in die Datei geschrieben werden soll.
     * @throws IOException
     */
    public void writeCity(City city) throws IOException {
        FileWriter file = new FileWriter(path, appendToFile);
        PrintWriter printLine = new PrintWriter(file);

        printLine.printf("Name: " + "%s" + "%n", city.getName());
        printLine.printf("Latitude: " + "%s" + "%n", Double.toString(city.getLatitude()));
        printLine.printf("Longitude: " + "%s" + "%n", Double.toString(city.getLongitude()));
        printLine.printf("Population: " + "%s" + "%n", Integer.toString(city.getPopulation()));
        printLine.printf("Connections: " + "%s" + "%n", (Object[]) city.getConnections());
        printLine.printf("Economy: " + "%s" + "%n", city.getEconomy());
        printLine.printf("Government: " + "%s" + "%n", city.getGovernment());
        printLine.printf("Hygiene: " + "%s" + "%n", city.getHygiene());
        printLine.printf("Awareness: " + "%s" + "%n", city.getAwareness());
        printLine.printf("Events: " + "%s" + "%n", Arrays.toString(city.getEvents()));
        printLine.printf("%n");
        printLine.close();
    }
}
