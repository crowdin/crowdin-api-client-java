package com.crowdin.client.projectsgroups.model;

import com.crowdin.client.core.http.impl.json.EmptyArrayToNullDeserializer;
import com.crowdin.client.languages.model.Language;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProjectSettings extends Project {

    private Integer translateDuplicates;
    private Integer tagsDetection;
    private Boolean glossaryAccess;
    private String glossaryAccessOption;
    private Boolean isMtAllowed;
    private Boolean taskBasedAccessControl;
    private Boolean hiddenStringsProofreadersAccess;
    private Boolean autoSubstitution;
    private Boolean exportTranslatedOnly;
    private Boolean skipUntranslatedStrings;
    private Boolean exportApprovedOnly;
    private Boolean autoTranslateDialects;
    private Boolean useGlobalTm;
    private Boolean showTmSuggestionsDialects;
    private Boolean isSuspended;
    private Boolean qaCheckIsActive;
    private QaCheckCategories qaCheckCategories;
    private QaCheckCategories qaChecksIgnorableCategories;
    private Map<String, Map<String, String>> languageMapping;
    private NotificationSettings notificationSettings;
    private Long defaultTmId;
    private Long defaultGlossaryId;
    @JsonDeserialize(using = EmptyArrayToNullDeserializer.class)
    private Map<String, AssignedTm> assignedTms;
    private List<Long> assignedGlossaries;
    @JsonDeserialize(using = EmptyArrayToNullDeserializer.class)
    private TmPenalties tmPenalties;
    private Boolean normalizePlaceholder;
    @JsonDeserialize(using = EmptyArrayToNullDeserializer.class)
    private TmPreTranslate tmPreTranslate;
    @JsonDeserialize(using = EmptyArrayToNullDeserializer.class)
    private MtPreTranslate mtPreTranslate;
    @JsonDeserialize(using = EmptyArrayToNullDeserializer.class)
    private AiPreTranslate aiPreTranslate;
    private Long assistActionAiPromptId;
    private Long editorSuggestionAiPromptId;
    private Boolean inContext;
    private Boolean inContextProcessHiddenStrings;
    private String inContextPseudoLanguageId;
    private Language inContextPseudoLanguage;
    private Boolean saveMetaInfoInSource;
    private Boolean skipUntranslatedFiles;
    private TmContextType tmContextType;
    //enterprise
    private Long clientOrganizationId;
    private List<Long> taskReviewerIds;
    private Integer exportWithMinApprovalsCount;
    private Boolean exportStringsThatPassedWorkflow;
    private Integer qaApprovalsCount;
    private List<Long> customQaCheckIds;
    private List<Long> externalQaCheckIds;
    private Boolean delayedWorkflowStart;
    private Long alignmentActionAiPromptId;

    @Data
    public static class AssignedTm {

        private Integer priority;
    }

}
