package com.crowdin.client.core.model;

public enum BooleanInt implements EnumConverter<BooleanInt> {
    TRUE(1), FALSE(0);

    private final int val;

    BooleanInt(int val) {
        this.val = val;
    }

    /**
     * Accepts both numeric ({@code "1"} / {@code "0"}) and boolean ({@code "true"} / {@code "false"}) representations.
     * Any other non-null value is treated as {@link #FALSE}.
     *
     * @param value raw value
     * @return converted value, or {@code null} if {@code value} is {@code null}
     */
    public static BooleanInt from(String value) {
        if (value == null) {
            return null;
        }
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
