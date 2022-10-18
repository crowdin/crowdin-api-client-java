package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.EnumConverter;

public enum PartOfSpeech implements EnumConverter<PartOfSpeech> {
    ADJECTIVE, ADPOSITION, ADVERB, AUXILIARY,
    COORDINATING_CONJUNCTION, DETERMINER, INTERJECTION, NOUN,
    NUMERAL, PARTICLE, PRONOUN, PROPER_NOUN, SUBORDINATING_CONJUNCTION, VERB, OTHER;

    public static PartOfSpeech from(String value) {
        return PartOfSpeech.valueOf(value.toUpperCase().replaceAll(" ", "_"));
    }

    @Override
    public String to(PartOfSpeech v) {
        return v.name().toLowerCase().replaceAll("_", " ");
    }

}
