package com.crowdin.client.glossaries.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ExportGlossaryRequest {

    private GlossariesFormat format;
    /**
     * Fields to export (CSV and XLSX formats only), e.g. {@code term}, {@code description}, {@code partOfSpeech}.
     * <p>
     * Enterprise only: a custom field can be selected by its slug &mdash; {@code field_{fieldSlug}} for a term field
     * (exported as one column per language) and {@code conceptField_{fieldSlug}} for a concept field
     * (exported as a single column).
     */
    private List<String> exportFields;
    private ExportType exportType;
    /**
     * Note: must be used together with {@link #text}
     */
    private String text;
    /**
     * Note: must be used together with {@link #text}
     */
    private Boolean caseSensitive;
    /**
     * Note: must be used together with {@link #text}
     */
    private Boolean searchStrict;
    /**
     * Note: must be used together with {@link #text}
     */
    private Boolean searchFullMatch;
    @JsonProperty("statuses")
    private List<ExportTermStatus> termStatuses;
    @JsonProperty("partsOfSpeech")
    private List<ExportPartOfSpeech> termPartsOfSpeech;
    @JsonProperty("types")
    private List<ExportTermType> termTypes;
    @JsonProperty("genders")
    private List<ExportTermGender> termGenders;
    private List<Long> authorIds;
    private List<String> languageIds;
    private Date dateFrom;
    private Date dateTo;

    /**
     * @deprecated the export endpoint expects upper-case values ({@code NOT_RECOMMENDED}), not the term values
     * ({@code not recommended}); use {@link #termStatuses} instead. Kept for compatibility, values set through
     * {@link #setStatuses(List)} are converted into {@link #termStatuses}.
     */
    @Deprecated
    @JsonIgnore
    private List<Status> statuses;
    /**
     * @deprecated the export endpoint expects part of speech tags ({@code ADJ}, {@code PROPN}), not the term values
     * ({@code adjective}, {@code proper noun}); use {@link #termPartsOfSpeech} instead. Kept for compatibility, values
     * set through {@link #setPartsOfSpeech(List)} are converted into {@link #termPartsOfSpeech}.
     */
    @Deprecated
    @JsonIgnore
    private List<PartOfSpeech> partsOfSpeech;
    /**
     * @deprecated the export endpoint expects upper-case values ({@code FULL_FORM}), not the term values
     * ({@code full form}); use {@link #termTypes} instead. Kept for compatibility, values set through
     * {@link #setTypes(List)} are converted into {@link #termTypes}.
     */
    @Deprecated
    @JsonIgnore
    private List<Type> types;
    /**
     * @deprecated the export endpoint expects upper-case values ({@code MASCULINE}), not the term values
     * ({@code masculine}); use {@link #termGenders} instead. Kept for compatibility, values set through
     * {@link #setGenders(List)} are converted into {@link #termGenders}.
     */
    @Deprecated
    @JsonIgnore
    private List<Gender> genders;

    /**
     * @param statuses term statuses, converted to the matching {@link ExportTermStatus} values
     * @deprecated use {@link #termStatuses}
     */
    @Deprecated
    public void setStatuses(List<Status> statuses) {
        if (statuses == null) {
            this.termStatuses = null;
            return;
        }
        List<ExportTermStatus> converted = new ArrayList<>();
        for (Status status : statuses) {
            if (status != null) {
                converted.add(ExportTermStatus.valueOf(status.name()));
            }
        }
        this.termStatuses = converted;
    }

    /**
     * @return {@link #termStatuses} converted to term statuses
     * @deprecated use {@link #termStatuses}
     */
    @Deprecated
    public List<Status> getStatuses() {
        if (this.termStatuses == null) {
            return null;
        }
        List<Status> converted = new ArrayList<>();
        for (ExportTermStatus status : this.termStatuses) {
            if (status != null) {
                converted.add(Status.valueOf(status.name()));
            }
        }
        return converted;
    }

    /**
     * Converts term parts of speech to export tags: adjective &rarr; ADJ, adposition &rarr; ADP, adverb &rarr; ADV,
     * auxiliary &rarr; AUX, coordinating conjunction &rarr; CCONJ, determiner &rarr; DET, interjection &rarr; INTJ,
     * noun &rarr; NOUN, numeral &rarr; NUM, particle &rarr; PRT, pronoun &rarr; PRON, proper noun &rarr; PROPN,
     * subordinating conjunction &rarr; SCONJ, verb &rarr; VERB, other &rarr; X.
     *
     * @param partsOfSpeech term parts of speech
     * @deprecated use {@link #termPartsOfSpeech}
     */
    @Deprecated
    public void setPartsOfSpeech(List<PartOfSpeech> partsOfSpeech) {
        if (partsOfSpeech == null) {
            this.termPartsOfSpeech = null;
            return;
        }
        List<ExportPartOfSpeech> converted = new ArrayList<>();
        for (PartOfSpeech partOfSpeech : partsOfSpeech) {
            ExportPartOfSpeech value = toExportPartOfSpeech(partOfSpeech);
            if (value != null) {
                converted.add(value);
            }
        }
        this.termPartsOfSpeech = converted;
    }

    /**
     * @return {@link #termPartsOfSpeech} converted to term parts of speech ({@link ExportPartOfSpeech#SYM} has no
     * term counterpart and is omitted)
     * @deprecated use {@link #termPartsOfSpeech}
     */
    @Deprecated
    public List<PartOfSpeech> getPartsOfSpeech() {
        if (this.termPartsOfSpeech == null) {
            return null;
        }
        List<PartOfSpeech> converted = new ArrayList<>();
        for (ExportPartOfSpeech partOfSpeech : this.termPartsOfSpeech) {
            PartOfSpeech value = toPartOfSpeech(partOfSpeech);
            if (value != null) {
                converted.add(value);
            }
        }
        return converted;
    }

    /**
     * @param types term types, converted to the matching {@link ExportTermType} values
     * @deprecated use {@link #termTypes}
     */
    @Deprecated
    public void setTypes(List<Type> types) {
        if (types == null) {
            this.termTypes = null;
            return;
        }
        List<ExportTermType> converted = new ArrayList<>();
        for (Type type : types) {
            if (type != null) {
                converted.add(ExportTermType.valueOf(type.name()));
            }
        }
        this.termTypes = converted;
    }

    /**
     * @return {@link #termTypes} converted to term types
     * @deprecated use {@link #termTypes}
     */
    @Deprecated
    public List<Type> getTypes() {
        if (this.termTypes == null) {
            return null;
        }
        List<Type> converted = new ArrayList<>();
        for (ExportTermType type : this.termTypes) {
            if (type != null) {
                converted.add(Type.valueOf(type.name()));
            }
        }
        return converted;
    }

    /**
     * @param genders term genders, converted to the matching {@link ExportTermGender} values
     * @deprecated use {@link #termGenders}
     */
    @Deprecated
    public void setGenders(List<Gender> genders) {
        if (genders == null) {
            this.termGenders = null;
            return;
        }
        List<ExportTermGender> converted = new ArrayList<>();
        for (Gender gender : genders) {
            if (gender != null) {
                converted.add(ExportTermGender.valueOf(gender.name()));
            }
        }
        this.termGenders = converted;
    }

    /**
     * @return {@link #termGenders} converted to term genders ({@link ExportTermGender#COMMON} has no term counterpart
     * and is omitted)
     * @deprecated use {@link #termGenders}
     */
    @Deprecated
    public List<Gender> getGenders() {
        if (this.termGenders == null) {
            return null;
        }
        List<Gender> converted = new ArrayList<>();
        for (ExportTermGender gender : this.termGenders) {
            if (gender != null && gender != ExportTermGender.COMMON) {
                converted.add(Gender.valueOf(gender.name()));
            }
        }
        return converted;
    }

    private static ExportPartOfSpeech toExportPartOfSpeech(PartOfSpeech partOfSpeech) {
        if (partOfSpeech == null) {
            return null;
        }
        switch (partOfSpeech) {
            case ADJECTIVE:
                return ExportPartOfSpeech.ADJ;
            case ADPOSITION:
                return ExportPartOfSpeech.ADP;
            case ADVERB:
                return ExportPartOfSpeech.ADV;
            case AUXILIARY:
                return ExportPartOfSpeech.AUX;
            case COORDINATING_CONJUNCTION:
                return ExportPartOfSpeech.CCONJ;
            case DETERMINER:
                return ExportPartOfSpeech.DET;
            case INTERJECTION:
                return ExportPartOfSpeech.INTJ;
            case NOUN:
                return ExportPartOfSpeech.NOUN;
            case NUMERAL:
                return ExportPartOfSpeech.NUM;
            case PARTICLE:
                return ExportPartOfSpeech.PRT;
            case PRONOUN:
                return ExportPartOfSpeech.PRON;
            case PROPER_NOUN:
                return ExportPartOfSpeech.PROPN;
            case SUBORDINATING_CONJUNCTION:
                return ExportPartOfSpeech.SCONJ;
            case VERB:
                return ExportPartOfSpeech.VERB;
            case OTHER:
                return ExportPartOfSpeech.X;
            default:
                return null;
        }
    }

    private static PartOfSpeech toPartOfSpeech(ExportPartOfSpeech partOfSpeech) {
        if (partOfSpeech == null) {
            return null;
        }
        for (PartOfSpeech value : PartOfSpeech.values()) {
            if (toExportPartOfSpeech(value) == partOfSpeech) {
                return value;
            }
        }
        return null;
    }
}
