package com.crowdin.client.core.model;

/**
 * Enum json converter. Enum which implements such interface should also contain static method for deserialization.
 * See {@link PatchOperation#from}
 * It is needed just to avoid hard dependencies on Jackson library
 *
 * @param <T> enum type
 */
public interface EnumConverter<T extends Enum<T>> {
    String to(T v);
}
