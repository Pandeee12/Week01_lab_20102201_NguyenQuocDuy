package vn.edu.iuh.fit.enums;

public enum GrantValue {
    ENABLE (1),
    DISABLE (0);
    private int value;

    GrantValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
