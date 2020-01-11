package project.pandemie.util;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Args {

    @Parameter
    private List<String> parameters = new ArrayList<>();

    /*

     */
    @Parameter(names={"-e", "--port"}, description = "Custom port")
    private Integer port = 50123;

    @Parameter(names={"-t", "--sleepTime"}, description = "Time to wait between rounds in ms")
    private Integer sleepTimer = 0;

    @Parameter(names={"-v", "--visual"}, description = "enable Visualization")
    private boolean visualization = false;

    @Parameter(names={"-l", "--logging"}, description = "enable Logging")
    private boolean logging = false;

    public Integer getPort() {
        return port;
    }

    public Integer getSleepTimer() {
        return sleepTimer;
    }

    public boolean doVisualization() {
        return visualization;
    }

    public boolean doLogging() {
        return logging;
    }
}
