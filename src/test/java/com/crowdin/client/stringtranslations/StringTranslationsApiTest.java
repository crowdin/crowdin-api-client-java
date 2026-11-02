package com.crowdin.client.stringtranslations;

import com.crowdin.client.core.model.*;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.stringtranslations.model.*;
import org.apache.http.client.methods.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class StringTranslationsApiTest extends TestClient {

    private final Long projectId = 3L;
    private final Long project2Id = 4L;
    private final Long project3Id = 5L;
    private final Long secondProjectId = 4L;
    private final Long thirdProjectId = 5L;
    private final Long fourthProjectId = 6L;
    private final Long fifthProjectId = 7L;
    private final Long approvalId = 190695L;
    private final Long approval2Id = 190696L;
    private final Long translationId = 190694L;
    private final Long translation2Id = 190695L;
    private final Long userId = 19L;
    private final String text = "Цю стрічку перекладено";
    private final String language = "uk";
    private final String projectIds = "8,9,10";
    private final Long stringId = 35434L;
    private final Long string2Id = 35435L;
    private final Long voteId = 6643L;
    private final Long sixthProjectId = 8L;
    private final Long fileId = 10L;
    private final Long correctionId = 5L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/approvals", HttpGet.METHOD_NAME, "api/stringtranslations/listApprovals.json"),
                RequestMock.build(this.url + "/projects/" + project2Id + "/approvals", HttpGet.METHOD_NAME, "api/stringtranslations/listApprovalsOrderByIdAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20asc");
                }}),
                RequestMock.build(this.url + "/projects/" + project3Id + "/approvals", HttpGet.METHOD_NAME, "api/stringtranslations/listApprovalsOrderByIdDesc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20desc");
                }}),
                RequestMock.build(this.url + "/projects/" + project3Id + "/approvals", HttpGet.METHOD_NAME, "api/stringtranslations/listApprovals.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/approvals", HttpPost.METHOD_NAME, "api/stringtranslations/addApprovalRequest.json", "api/stringtranslations/approval.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/approvals/" + approvalId, HttpGet.METHOD_NAME, "api/stringtranslations/approval.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/approvals", HttpDelete.METHOD_NAME, null, Collections.singletonMap("stringId", stringId)),
                RequestMock.build(this.url + "/projects/" + projectId + "/approvals/" + approvalId, HttpDelete.METHOD_NAME),
                RequestMock.build(String.format("%s/projects/%d/languages/%s/translations", this.url, projectId, language), HttpGet.METHOD_NAME, "api/stringtranslations/listLanguageTranslations_plain.json"),
                RequestMock.build(String.format("%s/projects/%d/languages/%s/translations", this.url, secondProjectId, language), HttpGet.METHOD_NAME, "api/stringtranslations/listLanguageTranslations_plural.json"),
                RequestMock.build(String.format("%s/projects/%d/languages/%s/translations", this.url, thirdProjectId, language), HttpGet.METHOD_NAME, "api/stringtranslations/listLanguageTranslations_ICU.json"),

                RequestMock.build(String.format("%s/projects/%d/languages/%s/translations", this.url, fourthProjectId, language), HttpGet.METHOD_NAME, "api/stringtranslations/listLanguageTranslations_plain_orderByIdAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20asc");
                }}),
                RequestMock.build(String.format("%s/projects/%d/languages/%s/translations", this.url, fifthProjectId, language), HttpGet.METHOD_NAME, "api/stringtranslations/listLanguageTranslations_plain_orderByIdDesc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20desc");
                }}),

                RequestMock.build(this.url + "/projects/" + projectId + "/translations", HttpGet.METHOD_NAME, "api/stringtranslations/listStringTranslations..json"),
                RequestMock.build(this.url + "/projects/" + project2Id + "/translations", HttpGet.METHOD_NAME, "api/stringtranslations/listStringTranslationsOrderByIdAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20asc");
                }}),
                RequestMock.build(this.url + "/projects/" + project3Id + "/translations", HttpGet.METHOD_NAME, "api/stringtranslations/listStringTranslationsOrderByIdDesc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20desc");
                }}),

                RequestMock.build(this.url + "/projects/" + projectId + "/translations", HttpPost.METHOD_NAME, "api/stringtranslations/addTranslationRequest.json", "api/stringtranslations/translation.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations", HttpDelete.METHOD_NAME, null, Collections.singletonMap("stringId", stringId)),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/" + translationId, HttpGet.METHOD_NAME, "api/stringtranslations/translation.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/" + translationId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/" + translationId, HttpPut.METHOD_NAME, "api/stringtranslations/translation.json"),
                RequestMock.build(this.url + "/translations", HttpGet.METHOD_NAME, "api/stringtranslations/searchTranslations.json", new HashMap<String, String>() {{
                    put("filter", text);
                    put("projectIds", projectIds);
                    put("languageIds", language);
                }}),
                RequestMock.build(this.url + "/projects/" + projectId + "/votes", HttpGet.METHOD_NAME, "api/stringtranslations/listVotes.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/votes", HttpPost.METHOD_NAME, "api/stringtranslations/addVoteRequest.json", "api/stringtranslations/vote.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/votes/" + voteId, HttpGet.METHOD_NAME, "api/stringtranslations/vote.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/votes/" + voteId, HttpDelete.METHOD_NAME),
                RequestMock.build(String.format("%s/projects/%d/translations/alignment", this.url, projectId), HttpPost.METHOD_NAME, "api/stringtranslations/alignTranslationRequest.json", "api/stringtranslations/alignTranslationResponse.json"),

                RequestMock.build(String.format("%s/projects/%d/languages/%s/translations", this.url, sixthProjectId, language), HttpGet.METHOD_NAME, "api/stringtranslations/listLanguageTranslations_asset.json", new HashMap<String, Object>() {{
                    put("approvedOnly", BooleanInt.TRUE);
                }}),
                RequestMock.build(this.url + "/projects/" + projectId + "/approvals", HttpGet.METHOD_NAME, "api/stringtranslations/listApprovalsFiltered.json", new HashMap<String, Object>() {{
                    put("correctionId", correctionId);
                    put("fileId", fileId);
                    put("languageId", language);
                }}),
                RequestMock.build(this.url + "/projects/" + projectId + "/approvals", HttpPost.METHOD_NAME, "api/stringtranslations/addApprovalCorrectionRequest.json", "api/stringtranslations/approvalCorrection.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/approvals", HttpDelete.METHOD_NAME, null, Collections.singletonMap("fileId", fileId)),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations", HttpDelete.METHOD_NAME, null, new HashMap<String, Object>() {{
                    put("fileId", fileId);
                    put("languageId", language);
                }}),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/" + translationId, HttpGet.METHOD_NAME, "api/stringtranslations/translation.json", Collections.singletonMap("denormalizePlaceholders", BooleanInt.TRUE)),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations", HttpPost.METHOD_NAME, "api/stringtranslations/addTranslationProviderRequest.json", "api/stringtranslations/translation.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations", HttpPost.METHOD_NAME, "api/stringtranslations/addAssetTranslationRequest.json", "api/stringtranslations/assetTranslation.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations", HttpGet.METHOD_NAME, "api/stringtranslations/listStringTranslations..json", new HashMap<String, Object>() {{
                    put("fileId", fileId);
                    put("languageId", language);
                }}),

                RequestMock.build(
                        this.url + "/projects/" + projectId + "/approvals",
                        HttpPatch.METHOD_NAME,
                        "api/stringtranslations/approvalBatchOperationsRequest.json",
                        "api/stringtranslations/approvalBatchOperationsResponse.json"
                ),
                RequestMock.build(
                        this.url + "/projects/" + projectId + "/translations",
                        HttpPatch.METHOD_NAME,
                        "api/stringtranslations/translationBatchOperationsRequest.json",
                        "api/stringtranslations/translationBatchOperationsResponse.json"
                )
        );
    }

    @Test
    public void alignTranslationTest() {
        AlignTranslationRequest request = new AlignTranslationRequest();
        request.setSourceLanguageId("en");
        request.setTargetLanguageId("de");
        request.setText("Your password has been reset successfully!");
        AlignTranslationResponse alignTranslationResponse = this.getStringTranslationsApi().alignTranslation(projectId, request);
        assertEquals(alignTranslationResponse.getData().getWords().size(), 1);
        assertEquals(alignTranslationResponse.getData().getWords().get(0).getText(), "password");
        assertEquals(alignTranslationResponse.getData().getWords().get(0).getAlignments().size(), 1);

        com.crowdin.client.translations.model.Alignment alignment = alignTranslationResponse.getData().getWords().get(0).getAlignments().get(0);
        assertEquals(2.75, alignment.getProbabilityValue());
        // deprecated accessor keeps returning the (truncated) integer value
        assertEquals(2, alignment.getProbability());
    }

    @Test
    public void listApprovalsWithCorrectionAndFileTest() {
        ListTranslationApprovalsOptions options = new ListTranslationApprovalsOptions();
        options.setCorrectionId(correctionId);
        options.setFileId(fileId);
        options.setLanguageId(language);
        ResponseList<Approval> response = this.getStringTranslationsApi().listTranslationApprovals(projectId, options);
        assertEquals(2, response.getData().size());
        assertEquals(correctionId, response.getData().get(0).getData().getCorrectionId());
        assertEquals(fileId, response.getData().get(1).getData().getFileId());
        assertEquals(122L, response.getData().get(1).getData().getWorkflowStepId());
    }

    @Test
    public void addApprovalForCorrectionTest() {
        AddApprovalRequest request = new AddApprovalRequest();
        request.setCorrectionId(correctionId);
        ResponseObject<Approval> response = this.getStringTranslationsApi().addApproval(projectId, request);
        assertEquals(approval2Id, response.getData().getId());
        assertEquals(correctionId, response.getData().getCorrectionId());
        assertNull(response.getData().getTranslationId());
    }

    @Test
    public void removeStringApprovalsByFileTest() {
        this.getStringTranslationsApi().removeStringApprovals(projectId, null, fileId);
    }

    @Test
    public void listLanguageTranslationsAssetTest() {
        ListLanguageTranslationsOptions options = new ListLanguageTranslationsOptions();
        options.setApprovedOnly(BooleanInt.TRUE);
        ResponseList<LanguageTranslations> response = this.getStringTranslationsApi().listLanguageTranslations(sixthProjectId, language, options);
        assertEquals(2, response.getData().size());

        assertTrue(response.getData().get(0).getData() instanceof AssetLanguageTranslations, "Wrong return type, must be AssetLanguageTranslations");
        AssetLanguageTranslations asset = (AssetLanguageTranslations) response.getData().get(0).getData();
        assertEquals("image/jpeg", asset.getContentType());
        assertEquals("https://example.com/file.jpg", asset.getUrl());
        assertEquals("passed", asset.getQaIssuesStatus());

        // asset content type is the MIME type of the asset
        assertTrue(response.getData().get(1).getData() instanceof AssetLanguageTranslations, "Wrong return type, must be AssetLanguageTranslations");
        AssetLanguageTranslations png = (AssetLanguageTranslations) response.getData().get(1).getData();
        assertEquals(string2Id, png.getStringId());
        assertEquals(Long.valueOf(190695), png.getTranslationId());
        assertEquals("image/png", png.getContentType());
        assertEquals("john_doe", png.getUser().getUsername());
        assertEquals("inProgress", png.getQaIssuesStatus());
    }

    @Test
    public void listApprovalsTest() {
        ResponseList<Approval> approvalResponseList = this.getStringTranslationsApi().listTranslationApprovals(projectId, null, null, null, null, null, null, null, null);
        assertEquals(1, approvalResponseList.getData().size());
        assertEquals(approvalId, approvalResponseList.getData().get(0).getData().getId());
    }

    @Test
    public void listApprovalsTest_orderByNull() {
        ResponseList<Approval> approvalResponseList = this.getStringTranslationsApi().listTranslationApprovals(projectId, null, null, null, null, null, null, null, null, null);
        assertEquals(1, approvalResponseList.getData().size());
        assertEquals(approvalId, approvalResponseList.getData().get(0).getData().getId());
    }

    @Test
    public void listApprovalsTest_orderByIdNull() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        ResponseList<Approval> approvalResponseList = this.getStringTranslationsApi().listTranslationApprovals(project2Id, null, null, null, null, null, null, null, null, singletonList(orderById));
        assertEquals(2, approvalResponseList.getData().size());
        assertEquals(approvalId, approvalResponseList.getData().get(0).getData().getId());
        assertEquals(approval2Id, approvalResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listApprovalsTest_orderByIdAsc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.ASC);

        ResponseList<Approval> approvalResponseList = this.getStringTranslationsApi().listTranslationApprovals(project2Id, null, null, null, null, null, null, null, null, singletonList(orderById));
        assertEquals(2, approvalResponseList.getData().size());
        assertEquals(approvalId, approvalResponseList.getData().get(0).getData().getId());
        assertEquals(approval2Id, approvalResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listApprovalsTest_orderByIdDesc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.DESC);

        ResponseList<Approval> approvalResponseList = this.getStringTranslationsApi().listTranslationApprovals(project3Id, null, null, null, null, null, null, null, null, singletonList(orderById));
        assertEquals(2, approvalResponseList.getData().size());
        assertEquals(approval2Id, approvalResponseList.getData().get(0).getData().getId());
        assertEquals(approvalId, approvalResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void addApprovalTest() {
        AddApprovalRequest request = new AddApprovalRequest();
        request.setTranslationId(translationId);
        ResponseObject<Approval> approvalResponseObject = this.getStringTranslationsApi().addApproval(projectId, request);
        assertEquals(approvalResponseObject.getData().getId(), approvalId);
    }

    @Test
    public void getApprovalTest() {
        ResponseObject<Approval> approvalResponseObject = this.getStringTranslationsApi().getApproval(projectId, approvalId);
        assertEquals(approvalResponseObject.getData().getId(), approvalId);
        assertEquals(122L, approvalResponseObject.getData().getWorkflowStepId());
    }

    @Test
    public void removeStringApprovalsTest() {
        this.getStringTranslationsApi().removeStringApprovals(projectId, stringId);
    }

    @Test
    public void removeApprovalTest() {
        this.getStringTranslationsApi().removeApproval(projectId, approvalId);
    }

    @Test
    public void listLanguageTranslationsPlainTest() {
        ResponseList<LanguageTranslations> languageTranslationsList = this.getStringTranslationsApi().listLanguageTranslations(projectId, language, null, null, null, null, null, null, null, null, null);
        assertEquals(1, languageTranslationsList.getData().size());
        assertTrue(languageTranslationsList.getData().get(0).getData() instanceof PlainLanguageTranslations, "Wrong return type, must be PlainLanguageTranslations");
        PlainLanguageTranslations plain = (PlainLanguageTranslations) languageTranslationsList.getData().get(0).getData();
        assertEquals(stringId, plain.getStringId());
        assertEquals("tm", plain.getProvider());
        assertEquals(17L, plain.getProviderId());
        assertTrue(plain.getIsPreTranslated());
        assertEquals(100, plain.getMatchRate());
        assertEquals("perfect", plain.getMatchType());
        assertEquals("passed", plain.getQaIssuesStatus());
    }

    @Test
    public void listLanguageTranslationsPlainTest_orderByNull() {
        ResponseList<LanguageTranslations> languageTranslationsList = this.getStringTranslationsApi().listLanguageTranslations(projectId, language, null, null, null, null, null, null, null, null, null, null);
        assertEquals(1, languageTranslationsList.getData().size());
        assertTrue(languageTranslationsList.getData().get(0).getData() instanceof PlainLanguageTranslations, "Wrong return type, must be PlainLanguageTranslations");
        assertEquals(stringId, ((PlainLanguageTranslations) languageTranslationsList.getData().get(0).getData()).getStringId());
    }

    @Test
    public void listLanguageTranslationsPlainTest_orderByIdNull() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        ResponseList<LanguageTranslations> languageTranslationsList = this.getStringTranslationsApi().listLanguageTranslations(
                fourthProjectId,
                language,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                singletonList(orderById)
        );

        assertEquals(2, languageTranslationsList.getData().size());

        assertEquals(stringId, ((PlainLanguageTranslations) languageTranslationsList.getData().get(0).getData()).getStringId());
        assertEquals(string2Id, ((PlainLanguageTranslations) languageTranslationsList.getData().get(1).getData()).getStringId());
    }

    @Test
    public void listLanguageTranslationsPlainTest_orderByIdAsc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.ASC);

        ResponseList<LanguageTranslations> languageTranslationsList = this.getStringTranslationsApi().listLanguageTranslations(
                fourthProjectId,
                language,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                singletonList(orderById)
        );

        assertEquals(2, languageTranslationsList.getData().size());

        assertEquals(stringId, ((PlainLanguageTranslations) languageTranslationsList.getData().get(0).getData()).getStringId());
        assertEquals(string2Id, ((PlainLanguageTranslations) languageTranslationsList.getData().get(1).getData()).getStringId());
    }

    @Test
    public void listLanguageTranslationsPlainTest_orderByIdDesc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.DESC);

        ResponseList<LanguageTranslations> languageTranslationsList = this.getStringTranslationsApi().listLanguageTranslations(
                fifthProjectId,
                language,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                singletonList(orderById)
        );

        assertEquals(2, languageTranslationsList.getData().size());

        assertEquals(string2Id, ((PlainLanguageTranslations) languageTranslationsList.getData().get(0).getData()).getStringId());
        assertEquals(stringId, ((PlainLanguageTranslations) languageTranslationsList.getData().get(1).getData()).getStringId());
    }

    @Test
    public void listLanguageTranslationsPluralTest() {
        ResponseList<LanguageTranslations> languageTranslationsList = this.getStringTranslationsApi().listLanguageTranslations(secondProjectId, language, null, null, null, null, null, null, null, null, null);
        assertEquals(1, languageTranslationsList.getData().size());
        assertTrue(languageTranslationsList.getData().get(0).getData() instanceof PluralLanguageTranslations, "Wrong return type, must be PluralLanguageTranslations");
        assertEquals(stringId, ((PluralLanguageTranslations) languageTranslationsList.getData().get(0).getData()).getStringId());
        assertEquals("failed", ((PluralLanguageTranslations) languageTranslationsList.getData().get(0).getData()).getQaIssuesStatus());
    }

    @Test
    public void listLanguageTranslationsICUTest() {
        ResponseList<LanguageTranslations> languageTranslationsList = this.getStringTranslationsApi().listLanguageTranslations(thirdProjectId, language, null, null, null, null, null, null, null, null, null);
        assertEquals(1, languageTranslationsList.getData().size());
        assertTrue(languageTranslationsList.getData().get(0).getData() instanceof ICULanguageTranslations, "Wrong return type, must be ICULanguageTranslations");
        assertEquals(stringId, ((ICULanguageTranslations) languageTranslationsList.getData().get(0).getData()).getStringId());
        ICULanguageTranslations icu = (ICULanguageTranslations) languageTranslationsList.getData().get(0).getData();
        assertEquals("inProgress", icu.getQaIssuesStatus());
        assertEquals("tm", icu.getProvider());
        assertEquals(Long.valueOf(17), icu.getProviderId());
        assertEquals(Boolean.TRUE, icu.getIsPreTranslated());
        assertEquals(Integer.valueOf(75), icu.getMatchRate());
        assertEquals("fuzzy", icu.getMatchType());
    }

    @Test
    public void listStringTranslationsByFileTest() {
        ListStringTranslationsOptions options = new ListStringTranslationsOptions();
        options.setFileId(fileId);
        options.setLanguageId(language);
        ResponseList<StringTranslation> response = this.getStringTranslationsApi().listStringTranslations(projectId, options);
        assertEquals(1, response.getData().size());
        assertEquals(translationId, response.getData().get(0).getData().getId());
    }

    @Test
    public void listStringTranslationsTest() {
        ResponseList<StringTranslation> stringTranslationResponseList = this.getStringTranslationsApi().listStringTranslations(projectId, null, null, null, null);
        assertEquals(stringTranslationResponseList.getData().size(), 1);
        assertEquals(stringTranslationResponseList.getData().get(0).getData().getId(), translationId);
    }

    @Test
    public void listStringTranslationsTest_orderByNull() {
        ResponseList<StringTranslation> stringTranslationResponseList = this.getStringTranslationsApi().listStringTranslations(
                projectId,
                null,
                null,
                null,
                null,
                null
        );

        assertEquals(1, stringTranslationResponseList.getData().size());
        assertEquals(translationId, stringTranslationResponseList.getData().get(0).getData().getId());
    }

    @Test
    public void listStringTranslationsTest_orderByIdNull() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        ResponseList<StringTranslation> stringTranslationResponseList = this.getStringTranslationsApi().listStringTranslations(
                project2Id,
                null,
                null,
                null,
                null,
                singletonList(orderById)
        );

        assertEquals(2, stringTranslationResponseList.getData().size());
        assertEquals(translationId, stringTranslationResponseList.getData().get(0).getData().getId());
        assertEquals(translation2Id, stringTranslationResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listStringTranslationsTest_orderByIdAsc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.ASC);

        ResponseList<StringTranslation> stringTranslationResponseList = this.getStringTranslationsApi().listStringTranslations(
                project2Id,
                null,
                null,
                null,
                null,
                singletonList(orderById)
        );

        assertEquals(2, stringTranslationResponseList.getData().size());
        assertEquals(translationId, stringTranslationResponseList.getData().get(0).getData().getId());
        assertEquals(translation2Id, stringTranslationResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listStringTranslationsTest_orderByIdDesc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.DESC);

        ResponseList<StringTranslation> stringTranslationResponseList = this.getStringTranslationsApi().listStringTranslations(
                project3Id,
                null,
                null,
                null,
                null,
                singletonList(orderById)
        );

        assertEquals(2, stringTranslationResponseList.getData().size());
        assertEquals(translation2Id, stringTranslationResponseList.getData().get(0).getData().getId());
        assertEquals(translationId, stringTranslationResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void addTranslationTest() {
        AddStringTranslationRequest request = new AddStringTranslationRequest();
        request.setLanguageId(language);
        request.setStringId(stringId);
        request.setText(text);
        ResponseObject<StringTranslation> stringTranslationResponseObject = this.getStringTranslationsApi().addTranslation(projectId, request);
        assertEquals(stringTranslationResponseObject.getData().getId(), translationId);
    }

    /**
     * Compile-time compatibility with SDK 1.37.0: {@code addTranslation(projectId, null)} must stay unambiguous and
     * resolve to the original {@code addTranslation(Long, AddStringTranslationRequest)} (POST /translations).
     */
    @Test
    public void addTranslationWithNullRequestCompatibilityTest() {
        ResponseObject<StringTranslation> response = this.getStringTranslationsApi().addTranslation(projectId, null);
        assertEquals(translationId, response.getData().getId());
    }

    @Test
    public void addTranslationWithProviderTest() {
        AddStringTranslationRequest request = new AddStringTranslationRequest();
        request.setLanguageId(language);
        request.setStringId(stringId);
        request.setText(text);
        request.setProvider("ai");
        request.setProviderId(3L);
        request.setIsPreTranslated(true);
        ResponseObject<StringTranslation> response = this.getStringTranslationsApi().addTranslation(projectId, request);
        assertEquals(translationId, response.getData().getId());
        assertEquals("ai", response.getData().getProvider());
        assertEquals(3L, response.getData().getProviderId());
    }

    @Test
    public void addAssetTranslationTest() {
        AddAssetTranslationRequest request = new AddAssetTranslationRequest();
        request.setFileId(fileId);
        request.setLanguageId(language);
        request.setStorageId(61L);
        ResponseObject<StringTranslation> response = this.getStringTranslationsApi().addAssetTranslation(projectId, request);
        assertEquals("https://example.com/file.jpg", response.getData().getUrl());
        assertNull(response.getData().getId());
    }

    @Test
    public void deleteTranslationsTest() {
        this.getStringTranslationsApi().deleteStringTranslations(projectId, stringId, null);
    }

    @Test
    public void deleteTranslationsByFileTest() {
        this.getStringTranslationsApi().deleteStringTranslations(projectId, null, language, fileId);
    }

    @Test
    public void getTranslationTest() {
        ResponseObject<StringTranslation> stringTranslationResponseObject = this.getStringTranslationsApi().getStringTranslation(projectId, translationId);
        assertEquals(stringTranslationResponseObject.getData().getId(), translationId);
    }

    @Test
    public void getTranslationDenormalizedTest() {
        StringTranslation translation = this.getStringTranslationsApi().getStringTranslation(projectId, translationId, BooleanInt.TRUE).getData();
        assertEquals(translationId, translation.getId());
        assertEquals("ai", translation.getProvider());
        assertEquals(3L, translation.getProviderId());
        assertTrue(translation.getIsPreTranslated());
        assertEquals(90, translation.getMatchRate());
        assertEquals("fuzzy", translation.getMatchType());
        assertEquals(122L, translation.getWorkflowStepId());
    }

    @Test
    public void deleteTranslationTest() {
        this.getStringTranslationsApi().deleteStringTranslation(projectId, translationId);
    }

    @Test
    public void restoreTranslationTest() {
        ResponseObject<StringTranslation> stringTranslationResponseObject = this.getStringTranslationsApi().restoreStringTranslation(projectId, translationId);
        assertEquals(stringTranslationResponseObject.getData().getId(), translationId);
    }

    @Test
    public void searchTranslationsTest() {
        ResponseList<StringTranslation> stringTranslationResponseList = this.getStringTranslationsApi().searchTranslations(text, projectIds, null, language, null, null, null);

        assertEquals(translation2Id, stringTranslationResponseList.getData().get(0).getData().getId());
        assertEquals(text, stringTranslationResponseList.getData().get(0).getData().getText());
        assertEquals(userId, stringTranslationResponseList.getData().get(0).getData().getUser().getId());
        assertEquals(new Date(119, Calendar.SEPTEMBER, 23, 11, 26, 54), stringTranslationResponseList.getData().get(0).getData().getCreatedAt());
        assertTrue(stringTranslationResponseList.getData().get(0).getData().getIsPreTranslated());
        StringTranslation found = stringTranslationResponseList.getData().get(0).getData();
        assertEquals(17L, found.getProviderId());
        assertEquals(75, found.getMatchRate());
        assertEquals("fuzzy", found.getMatchType());
        assertEquals(8L, found.getProjectId());
        assertEquals(35L, found.getStringId());
        assertEquals(language, found.getLanguageId());
    }


    @Test
    public void listVotesTest() {
        ResponseList<Vote> voteResponseList = this.getStringTranslationsApi().listTranslationVotes(projectId, null, null, null, null, null, null, null);
        assertEquals( 1, voteResponseList.getData().size());
        assertEquals(voteId, voteResponseList.getData().get(0).getData().getId());
    }

    @Test
    public void addVoteTest() {
        AddVoteRequest request = new AddVoteRequest();
        request.setTranslationId(translationId);
        request.setMark(Mark.UP);
        ResponseObject<Vote> voteResponseObject = this.getStringTranslationsApi().addVote(projectId, request);
        assertEquals(voteResponseObject.getData().getId(), voteId);
    }

    @Test
    public void getVoteTest() {
        ResponseObject<Vote> voteResponseObject = this.getStringTranslationsApi().getVote(projectId, voteId);
        assertEquals(voteResponseObject.getData().getId(), voteId);
    }

    @Test
    public void cancelVoteTest() {
        this.getStringTranslationsApi().cancelVote(projectId, voteId);
    }

    @Test
    public void approvalBatchOperationsTest() {
        List<PatchRequest> request = new ArrayList<PatchRequest>() {{
            add(new PatchRequest() {{
                setOp(PatchOperation.ADD);
                setPath("/-");
                setValue(new AddApprovalRequest() {{
                    setTranslationId(200L);
                }});
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.REMOVE);
                setPath("/2815");
            }});
        }};

        ResponseList<Approval> response = this.getStringTranslationsApi().approvalBatchOperations(projectId, request);
        assertNotNull(response);
        assertNotNull(response.getData());

        assertEquals("uk", response.getData().get(0).getData().getLanguageId());
        assertEquals(190695, response.getData().get(0).getData().getTranslationId());
    }

    @Test
    public void translationBatchOperationsTest() {
        List<PatchRequest> request = new ArrayList<PatchRequest>() {{
            add(new PatchRequest() {{
                setOp(PatchOperation.ADD);
                setPath("/-");
                setValue(new AddStringTranslationRequest() {{
                    setStringId(35434L);
                    setLanguageId("fr");
                    setText("Цю стрічку перекладено");
                    setPluralCategoryName(PluralCategoryName.FEW);
                    setAddToTm(false);
                }});
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.REMOVE);
                setPath("/2815");
            }});
        }};

        ResponseList<StringTranslation> response = this.getStringTranslationsApi().translationBatchOperations(projectId, request);
        assertNotNull(response);
        assertNotNull(response.getData());

        assertEquals(190695, response.getData().get(0).getData().getId());
        assertEquals("tm",  response.getData().get(0).getData().getProvider());
    }
}
