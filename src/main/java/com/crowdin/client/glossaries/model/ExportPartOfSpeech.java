package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.EnumConverter;

/**
 * Part of speech filter values accepted by the glossary export endpoint
 * (Universal Dependencies style tags, e.g. {@code ADJ}, {@code PROPN}, {@code CCONJ}).
 * <p>
 * {@link #X} means "other" in Universal Dependencies; the deprecated
 * {@link ExportGlossaryRequest#setPartsOfSpeech(java.util.List)} maps {@link PartOfSpeech#OTHER} to it.
 */
public enum ExportPartOfSpeech implements EnumConverter<ExportPartOfSpeech> {
    NOUN, VERB, ADJ, PRON, PROPN, DET, ADV, ADP, CCONJ, SCONJ, NUM, INTJ, AUX, PRT, SYM, X;

    public static ExportPartOfSpeech from(String value) {
        return ExportPartOfSpeech.valueOf(value.toUpperCase());
    }

    @Override
    public String to(ExportPartOfSpeech v) {
        return v.name();
    }
}
