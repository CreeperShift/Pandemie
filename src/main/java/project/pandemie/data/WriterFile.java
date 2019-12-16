package project.pandemie.data;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;

public class WriterFile {

    private String path;
    private boolean append_to_file = false;

    public WriterFile(String file_path){
        path =file_path;
    }

    public WriterFile(String file_path, boolean append_value){
        path=file_path;
        append_to_file = append_value;
    }

    /**
     * Schreibt den übergebenen String in ein .txt Dokument
     * @param Text übergebener String der geschrieben werden soll
     * @throws IOException
     */

    public void writeToFile(String Text) throws IOException{
     FileWriter file = new FileWriter(path, append_to_file);
     PrintWriter print_line = new PrintWriter(file);
     print_line.printf("%s" + "%n",Text);

     print_line.close();
    }

    /**
     * Schreibt die übergebene Stadt in ein extrenes .txt dokument
     *
     * @param city Stadt welche in die Datei geschrieben werden soll.
     * @throws IOException
     */
    public void writeCity(City city) throws IOException{
        FileWriter file = new FileWriter(path, append_to_file);
        PrintWriter print_line = new PrintWriter(file);

        print_line.printf("Name: "+"%s" + "%n",city.getName());
        print_line.printf("Latitude: "+"%s" + "%n",Double.toString(city.getLatitude()));
        print_line.printf("Longitude: "+"%s" + "%n",Double.toString(city.getLongitude()));
        print_line.printf("Population: "+"%s" + "%n",Integer.toString(city.getPopulation()));
        print_line.printf("Connections: " +"%s" + "%n",city.getConnections());
        print_line.printf("Economy: "+"%s" + "%n",city.getEconomy());
        print_line.printf("Government: "+"%s" + "%n",city.getGovernment());
        print_line.printf("Hygiene: "+"%s" + "%n",city.getHygiene());
        print_line.printf("Awareness: "+"%s" + "%n",city.getAwareness());
        print_line.printf("Events: "+"%s" + "%n", Arrays.toString(city.getEvents()));
        print_line.printf("%n");
        print_line.close();
    }
}
