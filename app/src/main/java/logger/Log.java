package logger;

import java.time.LocalDateTime;

public class Log {
    private String message;

    public Log(String message) {
        this.message = message;
    }

    private final String SEPARATOR = " >>> ";

    private String printTime() {
        var now = LocalDateTime.now();
        return "[" + now.getHour() + ":" + now.getMinute() + ":" + now.getSecond() + "]";
    }

    public String print(String path, LoggingMode mode) {
        if (mode == null) mode = LoggingMode.INFO;
        switch (mode) {
            case INFO:
                return message;
            case DEBUG:
                return path + SEPARATOR + message;
            case FULL:
                return path + SEPARATOR + printTime() + SEPARATOR + message;
            case WARNING:
                return "Warning: " + this.print(path, LoggingMode.FULL);
            case ERROR:
                return "Error: " + this.print(path, LoggingMode.FULL);
            default:
                return path + SEPARATOR + message;
        }
    }
}
