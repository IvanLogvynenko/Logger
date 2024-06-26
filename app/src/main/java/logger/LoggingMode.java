package logger;

public enum LoggingMode {
    INFO(0),
    DEBUG(1),
    FULL(2),
    WARNING(3),
    ERROR(4);

    private int value;

    private LoggingMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
