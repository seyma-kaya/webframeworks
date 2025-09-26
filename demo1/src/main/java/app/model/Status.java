package app.model;

public enum Status {
    IDLE,
    INUSE,
    MAINTENANCE;

    public static Status getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }

    public static boolean isInEnum(String value) {
        if (value.equals(IDLE.name()) || value.equals(INUSE.name()) || value.equals(MAINTENANCE.name())) {
            return true;
        }
        return false;
    }

}
