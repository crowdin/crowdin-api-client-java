package com.crowdin.client.stringtranslations;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.stringtranslations.model.*;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringTranslationsApiTest extends TestClient {

    private final Long projectId = 3L;
    private final Long secondProjectId = 4L;
    private final Long thirdProjectId = 5L;
    private final Long approvalId = 190695L;
    private final Long translationId = 190694L;
    private final String text = "Цю стрічку перекладено";
    private final String language = "uk";
    private final Long stringId = 35434L;
    private final Long voteId = 6643L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/approvals", HttpGet.METHOD_NAME, "api/stringtranslations/listApprovals.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/approvals", HttpPost.METHOD_NAME, "api/stringtranslations/addApprovalRequest.json", "api/stringtranslations/approval.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/approvals/" + approvalId, HttpGet.METHOD_NAME, "api/stringtranslations/approval.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/approvals/" + approvalId, HttpDelete.METHOD_NAME),
                RequestMock.build(String.format("%s/projects/%d/languages/%s/translations", this.url, projectId, language), HttpGet.METHOD_NAME, "api/stringtranslations/listLanguageTranslations_plain.json"),
                RequestMock.build(String.format("%s/projects/%d/languages/%s/translations", this.url, secondProjectId, language), HttpGet.METHOD_NAME, "api/stringtranslations/listLanguageTranslations_plural.json"),
                RequestMock.build(String.format("%s/projects/%d/languages/%s/translations", this.url, thirdProjectId, language), HttpGet.METHOD_NAME, "api/stringtranslations/listLanguageTranslations_ICU.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations", HttpGet.METHOD_NAME, "api/stringtranslations/listStringTranslations..json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations", HttpPost.METHOD_NAME, "api/stringtranslations/addTranslationRequest.json", "api/stringtranslations/translation.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations", HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/" + translationId, HttpGet.METHOD_NAME, "api/stringtranslations/translation.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/" + translationId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/" + translationId, HttpPut.METHOD_NAME, "api/stringtranslations/translation.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/votes", HttpGet.METHOD_NAME, "api/stringtranslations/listVotes.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/votes", HttpPost.METHOD_NAME, "api/stringtranslations/addVoteRequest.json", "api/stringtranslations/vote.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/votes/" + voteId, HttpGet.METHOD_NAME, "api/stringtranslations/vote.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/votes/" + voteId, HttpDelete.METHOD_NAME),
                RequestMock.build(String.format("%s/projects/%d/translations/alignment", this.url, projectId), HttpPost.METHOD_NAME, "api/stringtranslations/alignTranslationRequest.json", "api/stringtranslations/alignTranslationResponse.json")
                RequestMock.build(String.format("%s/projects/%d/translations?stringId=%d", this.url, projectId, stringId), HttpDelete.METHOD_NAME),
                RequestMock.build(String.format("%s/projects/%d/translations?stringId=%d&languageId=%s", this.url, projectId, stringId, language), HttpDelete.METHOD_NAME)
                RequestMock.build(String.format("%s/projects/%d/approvals?stringId=%d", this.url, projectId, stringId), HttpDelete.METHOD_NAME),
                RequestMock.build(String.format("%s/projects/%d/approvals", this.url, projectId), HttpGet.METHOD_NAME, "api/stringtranslations/listTranslationApprovals.json"),
                RequestMock.build(String.format("%s/projects/%d/strings/%d", this.url, projectId, stringId), HttpGet.METHOD_NAME, "api/stringtranslations/getStringInfo.json")
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
    }

    @Test
    public void listApprovalsTest() {
        ResponseList<Approval> approvalResponseList = this.getStringTranslationsApi().listTranslationApprovals(projectId, null, null, null, null, null, null, null, null);
        assertEquals(1, approvalResponseList.getData().size());
        assertEquals(approvalId, approvalResponseList.getData().get(0).getData().getId());
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
    }

    @Test
    public void removeApprovalTest() {
        this.getStringTranslationsApi().removeApproval(projectId, approvalId);
    }

    @Test
    public void removeStringApprovalsTest() {
        StringTranslationsApi api = this.getStringTranslationsApi();

        ResponseList<Approval> approvalsBefore = api.listTranslationApprovals(projectId, null, stringId, null, null, null, null, null, null);
        assertFalse(approvalsBefore.getData().isEmpty(), "Approvals should exist before removal");

        assertDoesNotThrow(() -> api.removeStringApprovals(projectId, stringId));

        ResponseList<Approval> approvalsAfter = api.listTranslationApprovals(projectId, null, stringId, null, null, null, null, null, null);
        assertTrue(approvalsAfter.getData().isEmpty(), "Approvals should be empty after removal");

        ResponseObject<SourceString> sourceString = api.getStringInfo(projectId, stringId);
        assertNotNull(sourceString.getData(), "String should still exist after approval removal");
        assertEquals(stringId, sourceString.getData().getId(), "String ID should match");

        assertDoesNotThrow(() -> api.removeStringApprovals(projectId, stringId), "Removing non-existent approvals should not throw an exception");
    }

    @Test
    public void listLanguageTranslationsPlainTest() {
        ResponseList<LanguageTranslations> languageTranslationsList = this.getStringTranslationsApi().listLanguageTranslations(projectId, language, null, null, null, null, null, null, null, null, null);
        assertEquals(1, languageTranslationsList.getData().size());
        assertTrue(languageTranslationsList.getData().get(0).getData() instanceof PlainLanguageTranslations, "Wrong return type, must be PlainLanguageTranslations");
        assertEquals(stringId, ((PlainLanguageTranslations) languageTranslationsList.getData().get(0).getData()).getStringId());
    }

    @Test
    public void listLanguageTranslationsPluralTest() {
        ResponseList<LanguageTranslations> languageTranslationsList = this.getStringTranslationsApi().listLanguageTranslations(secondProjectId, language, null, null, null, null, null, null, null, null, null);
        assertEquals(1, languageTranslationsList.getData().size());
        assertTrue(languageTranslationsList.getData().get(0).getData() instanceof PluralLanguageTranslations, "Wrong return type, must be PluralLanguageTranslations");
        assertEquals(stringId, ((PluralLanguageTranslations) languageTranslationsList.getData().get(0).getData()).getStringId());
    }

    @Test
    public void listLanguageTranslationsICUTest() {
        ResponseList<LanguageTranslations> languageTranslationsList = this.getStringTranslationsApi().listLanguageTranslations(thirdProjectId, language, null, null, null, null, null, null, null, null, null);
        assertEquals(1, languageTranslationsList.getData().size());
        assertTrue(languageTranslationsList.getData().get(0).getData() instanceof ICULanguageTranslations, "Wrong return type, must be ICULanguageTranslations");
        assertEquals(stringId, ((ICULanguageTranslations) languageTranslationsList.getData().get(0).getData()).getStringId());
    }

    @Test
    public void listStringTranslationsTest() {
        ResponseList<StringTranslation> stringTranslationResponseList = this.getStringTranslationsApi().listStringTranslations(projectId, null, null, null, null);
        assertEquals(stringTranslationResponseList.getData().size(), 1);
        assertEquals(stringTranslationResponseList.getData().get(0).getData().getId(), translationId);
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

    @Test
    public void deleteTranslationsTest() {
        this.getStringTranslationsApi().deleteStringTranslations(projectId, null, null);
    }

    @Test
    public void getTranslationTest() {
        ResponseObject<StringTranslation> stringTranslationResponseObject = this.getStringTranslationsApi().getStringTranslation(projectId, translationId);
        assertEquals(stringTranslationResponseObject.getData().getId(), translationId);
    }

    @Test
    public void deleteStringTranslationsTest() {
        this.getStringTranslationsApi().deleteStringTranslations(projectId, stringId);
        this.getStringTranslationsApi().deleteStringTranslations(projectId, stringId, language);
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

}
