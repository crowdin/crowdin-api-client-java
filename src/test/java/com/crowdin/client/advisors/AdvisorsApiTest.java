package com.crowdin.client.advisors;

import com.crowdin.client.advisors.model.AdvisorAiMode;
import com.crowdin.client.advisors.model.AdvisorCheck;
import com.crowdin.client.advisors.model.AdvisorInsight;
import com.crowdin.client.advisors.model.AdvisorInsightMetric;
import com.crowdin.client.advisors.model.AdvisorInsightOutcome;
import com.crowdin.client.advisors.model.AdvisorInsightPagination;
import com.crowdin.client.advisors.model.AdvisorInsightRecommendation;
import com.crowdin.client.advisors.model.AdvisorInsightSeverity;
import com.crowdin.client.advisors.model.AdvisorInsightStatus;
import com.crowdin.client.advisors.model.AdvisorInspectorOptions;
import com.crowdin.client.advisors.model.AdvisorMetricSource;
import com.crowdin.client.advisors.model.AdvisorMetricTone;
import com.crowdin.client.advisors.model.AdvisorMetricUnit;
import com.crowdin.client.advisors.model.AdvisorRefreshPolicy;
import com.crowdin.client.advisors.model.ListAdvisorInsightsParams;
import com.crowdin.client.advisors.model.RunAdvisorCheckInspector;
import com.crowdin.client.advisors.model.RunAdvisorCheckRequest;
import com.crowdin.client.advisors.model.UpsertAdvisorInsightRequest;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdvisorsApiTest extends TestClient {

    private final Long projectId = 8L;
    private final String checkId = "550e8400-e29b-41d4-a716-446655440000";
    private final Long insightId = 10L;
    private final String applicationIdentifier = "my-app";
    private final String moduleKey = "my-inspector";
    private final String inspectorKey = "string_context_relevance";

    @Override
    public List<RequestMock> getMocks() {
        Map<String, Object> listParams = new HashMap<>();
        listParams.put("limit", 25);
        listParams.put("offset", 0);
        listParams.put("isDismissed", false);
        listParams.put("status", "done,outdated");
        listParams.put("outcome", "flagged,not_applicable");

        String projectUrl = this.url + "/projects/" + projectId;
        return Arrays.asList(
                RequestMock.build(projectUrl + "/advisors/checks", HttpPost.METHOD_NAME, "api/advisors/runAdvisorCheckRequest.json", "api/advisors/advisorCheck.json"),
                RequestMock.build(projectUrl + "/advisors/checks/" + checkId, HttpGet.METHOD_NAME, "api/advisors/advisorCheck.json"),
                RequestMock.build(projectUrl + "/advisors/insights", HttpGet.METHOD_NAME, "api/advisors/listAdvisorInsights.json", listParams),
                RequestMock.build(projectUrl + "/advisors/insights/" + insightId, HttpPatch.METHOD_NAME, "api/advisors/editAdvisorInsightRequest.json", "api/advisors/advisorInsight.json"),
                RequestMock.build(projectUrl + "/applications/" + applicationIdentifier + "/modules/" + moduleKey + "/advisors/insights", HttpPut.METHOD_NAME, "api/advisors/upsertAdvisorInsightRequest.json", (String) null)
        );
    }

    @Test
    public void runAdvisorCheckTest() {
        AdvisorInspectorOptions options = new AdvisorInspectorOptions();
        options.setMode(AdvisorAiMode.AUTO);
        options.setPromptId(42L);
        RunAdvisorCheckInspector inspector = new RunAdvisorCheckInspector();
        inspector.setKey(inspectorKey);
        inspector.setOptions(options);
        RunAdvisorCheckRequest request = new RunAdvisorCheckRequest();
        request.setInspectors(singletonList(inspector));

        ResponseObject<AdvisorCheck> response = this.getAdvisorsApi().runAdvisorCheck(projectId, request);
        assertAdvisorCheck(response.getData());
    }

    @Test
    public void getAdvisorCheckTest() {
        ResponseObject<AdvisorCheck> response = this.getAdvisorsApi().getAdvisorCheck(projectId, checkId);
        assertAdvisorCheck(response.getData());
    }

    @Test
    public void listAdvisorInsightsTest() {
        ListAdvisorInsightsParams params = new ListAdvisorInsightsParams();
        params.setLimit(25);
        params.setOffset(0);
        params.setIsDismissed(false);
        params.setStatus(Arrays.asList(AdvisorInsightStatus.DONE, AdvisorInsightStatus.OUTDATED));
        params.setOutcome(Arrays.asList(AdvisorInsightOutcome.FLAGGED, AdvisorInsightOutcome.NOT_APPLICABLE));

        ResponseList<AdvisorInsight> response = this.getAdvisorsApi().listAdvisorInsights(projectId, params);
        assertEquals(2, response.getData().size());
        assertInsight(response.getData().get(0).getData());

        AdvisorInsight removed = response.getData().get(1).getData();
        assertEquals(11L, removed.getId());
        assertTrue(removed.getIsDismissed());
        assertNull(removed.getStatus());
        assertNull(removed.getOutcome());
        assertNull(removed.getSeverity());
        assertNull(removed.getRefreshPolicy());
        assertNull(removed.getCategory());
        assertNull(removed.getLastAiRun());
        assertNull(removed.getPayload());
        assertNull(removed.getRecommendations().get(0).getParams());

        assertTrue(response.getPagination() instanceof AdvisorInsightPagination);
        AdvisorInsightPagination pagination = (AdvisorInsightPagination) response.getPagination();
        assertEquals(0, pagination.getOffset());
        assertEquals(25, pagination.getLimit());
        assertEquals(2, pagination.getTotal());
    }

    @Test
    public void editAdvisorInsightTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setPath("/isDismissed");
        request.setValue(true);

        ResponseObject<AdvisorInsight> response = this.getAdvisorsApi().editAdvisorInsight(projectId, insightId, singletonList(request));
        assertInsight(response.getData());
    }

    @Test
    public void upsertApplicationAdvisorInsightTest() {
        AdvisorInsightMetric metric = new AdvisorInsightMetric();
        metric.setKey("quality_score");
        metric.setValue(12.0);
        metric.setUnit(AdvisorMetricUnit.COUNT);
        metric.setThreshold(50.0);
        metric.setTone(AdvisorMetricTone.DEFAULT);
        metric.setSource(AdvisorMetricSource.APP);

        AdvisorInsightRecommendation recommendation = new AdvisorInsightRecommendation();
        recommendation.setId("fix_strings");
        recommendation.setPrimary(true);
        recommendation.setParams(Collections.<String, Object>singletonMap("projectId", 8));

        UpsertAdvisorInsightRequest request = new UpsertAdvisorInsightRequest();
        request.setOutcome(AdvisorInsightOutcome.FLAGGED);
        request.setCheckedAt(Date.from(Instant.parse("2026-05-21T14:30:00Z")));
        request.setMetrics(singletonList(metric));
        request.setRecommendations(singletonList(recommendation));
        request.setPayload(Collections.<String, Object>singletonMap("foo", "bar"));

        this.getAdvisorsApi().upsertApplicationAdvisorInsight(projectId, applicationIdentifier, moduleKey, request);
    }

    /**
     * The core DateDeserializer parses timestamps in the JVM default time zone (offset ignored).
     */
    private static Date localDate(String dateTime) {
        return Date.from(LocalDateTime.parse(dateTime).atZone(ZoneId.systemDefault()).toInstant());
    }

    private void assertAdvisorCheck(AdvisorCheck check) {
        assertEquals(checkId, check.getIdentifier());
        assertEquals("created", check.getStatus());
        assertEquals(0, check.getProgress());
        assertNull(check.getAttributes().getCategory());
        assertEquals(inspectorKey, check.getAttributes().getInspectors().get(0).getKey());
        assertEquals(localDate("2026-04-16T12:00:00"), check.getCreatedAt());
        assertEquals(localDate("2026-04-16T12:00:00"), check.getUpdatedAt());
        assertNull(check.getStartedAt());
        assertNull(check.getFinishedAt());
    }

    private void assertInsight(AdvisorInsight insight) {
        Date checkedAt = localDate("2026-04-06T10:30:00");
        assertEquals(insightId, insight.getId());
        assertEquals(inspectorKey, insight.getInspectorKey());
        assertEquals("context", insight.getCategory());
        assertFalse(insight.getIsDismissed());
        assertEquals(AdvisorInsightStatus.DONE, insight.getStatus());
        assertEquals(AdvisorInsightOutcome.NOT_APPLICABLE, insight.getOutcome());
        assertEquals(AdvisorInsightSeverity.HIGH, insight.getSeverity());
        assertEquals(AdvisorRefreshPolicy.HOURLY, insight.getRefreshPolicy());

        AdvisorInsightMetric metric = insight.getMetrics().get(0);
        assertEquals("quality_score", metric.getKey());
        assertEquals(12.5, metric.getValue());
        assertEquals(AdvisorMetricUnit.PERCENT, metric.getUnit());
        assertEquals(50.0, metric.getThreshold());
        assertEquals(AdvisorMetricTone.DANGER, metric.getTone());
        assertEquals(AdvisorMetricSource.DETERMINISTIC, metric.getSource());
        assertEquals(checkedAt, metric.getCheckedAt());

        AdvisorInsightRecommendation recommendation = insight.getRecommendations().get(0);
        assertEquals("run_ai_context_validation", recommendation.getId());
        assertTrue(recommendation.getPrimary());
        assertEquals(8, recommendation.getParams().get("projectId"));

        assertEquals(checkedAt, insight.getCheckedAt());
        assertEquals(AdvisorAiMode.AUTO, insight.getLastAiRun().getMode());
        assertEquals(42L, insight.getLastAiRun().getPromptId());
        assertEquals(checkedAt, insight.getLastAiRun().getAt());
        assertEquals(Arrays.asList(1, 2), insight.getPayload().get("stringIds"));
    }
}
