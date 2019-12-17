package project.pandemie.logging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class LogReader {
    private String path;

    public LogReader(String filePath) {
        path = filePath;
    }

    public List<String> readFromFile() throws IOException {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

}
