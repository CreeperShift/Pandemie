package project.pandemie.logging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class LogWriter {

    private String path;
    private LogReader logReader;
    private List<String> log;

    public LogWriter(String path) {
        this.path = path;

        logReader = new LogReader(path);
        try {
            log = logReader.readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Schreibt den übergebenen String in ein .txt Dokument
     *
     * @param text übergebener String der geschrieben werden soll
     * @throws IOException
     */

    public void writeToFile(String text) throws IOException {
        for (String s : log) {
            if (s.equalsIgnoreCase(text)) {
                return;
            }
        }
        Files.writeString(Paths.get(path), text, StandardOpenOption.APPEND);
        readLog();

    }

    private void readLog() {
        try {
            log = logReader.readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
