package com.crowdin.client.core.model;

public enum BooleanInt implements EnumConverter<BooleanInt> {
    TRUE(1), FALSE(0);

    private final int val;

    BooleanInt(int val) {
        this.val = val;
    }

    public static BooleanInt from(String value) {
        return value.equals("1") ? BooleanInt.TRUE : BooleanInt.FALSE;
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
