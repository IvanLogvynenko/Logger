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
                return SEPARATOR + message;
            case DEBUG:
                return SEPARATOR + path + SEPARATOR + message;
            case FULL:
                return SEPARATOR + path + SEPARATOR + message;
            default:
                return SEPARATOR + path + SEPARATOR + message;
        }
    }
}
