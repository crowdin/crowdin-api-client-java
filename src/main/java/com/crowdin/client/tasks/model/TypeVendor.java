package com.crowdin.client.tasks.model;

import com.crowdin.client.core.model.EnumConverter;

public enum TypeVendor implements EnumConverter<TypeVendor> {
    TRANSLATE_BY_VENDOR(2), PROOFREAD_BY_VENDOR(3);

    private final int val;

    TypeVendor(int val) {
        this.val = val;
    }

    public static TypeVendor from(String value) {
        if ("2".equals(value)) {
            return TypeVendor.TRANSLATE_BY_VENDOR;
        }
        if ("3".equals(value)) {
            return TypeVendor.PROOFREAD_BY_VENDOR;
        }
        return null;
    }

    @Override
    public Integer to(TypeVendor v) {
        return v.val;
    }
}
