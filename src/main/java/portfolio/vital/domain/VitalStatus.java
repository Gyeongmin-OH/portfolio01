package portfolio.vital.domain;

public enum VitalStatus {

    NORMAL("정상"),
    CAUTION("주의"),
    DANGER("위험");

    private final String description;

    VitalStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}