package com.crowdin.client.tasks.model;

import com.crowdin.client.core.model.EnumConverter;

/**
 * Task type.
 * <ul>
 * <li>0 - translate by own translators ({@link #TRANSLATE})</li>
 * <li>1 - proofread by own proofreaders ({@link #PROOFREAD})</li>
 * <li>2 - translate by vendor ({@link #TRANSLATE_BY_VENDOR})</li>
 * <li>3 - proofread by vendor ({@link #PROOFREAD_BY_VENDOR})</li>
 * </ul>
 * Vendor types are returned in task responses and can be used for pending tasks (type 3).
 * Crowdin vendor task creation requests use {@link TypeVendor}.
 */
public enum Type implements EnumConverter<Type> {
    PROOFREAD(1), TRANSLATE(0), TRANSLATE_BY_VENDOR(2), PROOFREAD_BY_VENDOR(3);

    private final int val;

    Type(int val) {
        this.val = val;
    }

    public static Type from(String value) {
        for (Type type : Type.values()) {
            if (String.valueOf(type.val).equals(value)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public Integer to(Type v) {
        return v.val;
    }
}
