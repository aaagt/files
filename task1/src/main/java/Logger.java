import java.util.Date;

public class Logger {
    private final StringBuilder log = new StringBuilder();

    public void info(String message) {
        log.append(String.format("INFO [%s] %s\n", new Date(), message));
    }

    public String getLog() {
        return log.toString();
    }
}
