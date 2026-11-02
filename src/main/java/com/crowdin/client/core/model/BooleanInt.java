package com.crowdin.client.core.model;

public enum BooleanInt implements EnumConverter<BooleanInt> {
    TRUE(1), FALSE(0);

    private final int val;

    BooleanInt(int val) {
        this.val = val;
    }

    /**
     * Accepts both numeric ({@code "1"} / {@code "0"}) and boolean ({@code "true"} / {@code "false"}) representations.
     * Any other value is treated as {@link #FALSE}.
     *
     * @param value raw value
     * @return converted value
     */
    public static BooleanInt from(String value) {
        return "1".equals(value) || "true".equalsIgnoreCase(value) ? BooleanInt.TRUE : BooleanInt.FALSE;
    }

    public static BooleanInt fromInt(Integer value) {
        if (value == null) {
            return null;
        }
        return value == 1 ? BooleanInt.TRUE : BooleanInt.FALSE;
    }

    @Override
    public Integer to(BooleanInt v) {
        return v.val;
    }
}
