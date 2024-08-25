package logger;

import java.time.LocalDateTime;

public class Log {
    private final String message;

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
        return switch (mode) {
            case INFO -> message;
            case DEBUG -> path + SEPARATOR + message;
            case FULL -> path + SEPARATOR + printTime() + SEPARATOR + message;
            case WARNING -> "Warning: " + this.print(path, LoggingMode.FULL);
            case ERROR -> "Error: " + this.print(path, LoggingMode.FULL);
            default -> path + SEPARATOR + message;
        };
    }
}
