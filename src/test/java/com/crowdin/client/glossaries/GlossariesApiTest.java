package com.crowdin.client.glossaries;

import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.crowdin.client.core.model.*;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.glossaries.model.*;
import org.apache.http.client.methods.*;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.TimeZone;
import java.util.Date;
import java.util.Calendar;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GlossariesApiTest extends TestClient {

    private final Long projectId = 2L;
    private final Long glossaryId = 2L;
    private final Long glossary2Id = 3L;
    private final Long glossary3Id = 4L;
    private final Long groupId = 2L;
    private final Long conceptId = 3L;
    private final Long termId = 2L;
    private final Long term2Id = 3L;
    private final String name = "Be My Eyes iOS's Glossary";
    private final String subject = "general";
    private final String text = "Voir";
    private final String exportId = "5ed2ce93-6d47-4402-9e66-516ca835cb20";
    private final String importId = "c050fba2-200e-4ce1-8de4-f7ba8eb58732";
    private final String link = "test.com";
    private final String languageId = "ro";
    private final TimeZone tz = TimeZone.getTimeZone("GMT");

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/concepts", HttpGet.METHOD_NAME, "api/glossaries/listConcepts.json"),
                RequestMock.build(this.url + "/glossaries/" + glossary2Id + "/concepts", HttpGet.METHOD_NAME, "api/glossaries/listConceptsOrderByIdAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20asc");
                }}),
                RequestMock.build(this.url + "/glossaries/" + glossary3Id + "/concepts", HttpGet.METHOD_NAME, "api/glossaries/listConceptsOrderByIdDesc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20desc");
                }}),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/concepts/" + conceptId, HttpGet.METHOD_NAME, "api/glossaries/concept.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/concepts/" + conceptId, HttpPut.METHOD_NAME, "api/glossaries/updateConcept.json", "api/glossaries/concept.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/concepts/" + conceptId, HttpPut.METHOD_NAME, "api/glossaries/updateConceptRequest.json", "api/glossaries/concept.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/concepts/" + conceptId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/glossaries", HttpGet.METHOD_NAME, "api/glossaries/listGlossaries.json"),
                RequestMock.build(this.url + "/glossaries", HttpGet.METHOD_NAME, "api/glossaries/listGlossaries.json", new HashMap<String, Object>() {{
                    put("groupId", groupId);
                    put("filter", "Eyes");
                    put("limit", 10);
                }}),
                RequestMock.build(this.url + "/glossaries", HttpPost.METHOD_NAME, "api/glossaries/addGlossaryRequest.json", "api/glossaries/glossary.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId, HttpGet.METHOD_NAME, "api/glossaries/glossary.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/glossaries/" + glossaryId, HttpPatch.METHOD_NAME, "api/glossaries/editGlossary.json", "api/glossaries/glossary.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/exports", HttpPost.METHOD_NAME, "api/glossaries/exportGlossary.json", "api/glossaries/exportGlossaryStatus.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/exports", HttpPost.METHOD_NAME, "api/glossaries/exportGlossaryFilters.json", "api/glossaries/exportGlossaryStatus.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/exports/" + exportId, HttpGet.METHOD_NAME, "api/glossaries/exportGlossaryStatus.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/exports/" + exportId + "/download", HttpGet.METHOD_NAME, "api/glossaries/downloadLink.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/imports", HttpPost.METHOD_NAME, "api/glossaries/importGlossary.json", "api/glossaries/importGlossaryStatus.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/imports/" + importId, HttpGet.METHOD_NAME, "api/glossaries/importGlossaryStatus.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/terms", HttpGet.METHOD_NAME, "api/glossaries/listTerms.json"),
                RequestMock.build(this.url + "/glossaries/" + glossary2Id + "/terms", HttpGet.METHOD_NAME, "api/glossaries/listTermsOrderByIdAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20asc");
                }}),
                RequestMock.build(this.url + "/glossaries/" + glossary3Id + "/terms", HttpGet.METHOD_NAME, "api/glossaries/listTermsOrderByIdDesc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20desc");
                }}),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/terms", HttpPost.METHOD_NAME, "api/glossaries/addTermRequest.json", "api/glossaries/term.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/terms", HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/terms/" + termId, HttpGet.METHOD_NAME, "api/glossaries/term.json"),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/terms/" + termId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/glossaries/" + glossaryId + "/terms/" + termId, HttpPatch.METHOD_NAME, "api/glossaries/editTerm.json", "api/glossaries/term.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/glossaries/concordance", HttpPost.METHOD_NAME, "api/glossaries/concordanceSearchRequest.json", "api/glossaries/concordanceSearchResponse.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/glossaries/concordance", HttpPost.METHOD_NAME, "api/glossaries/concordanceSearchExpressionsRequest.json", "api/glossaries/concordanceSearchResponse.json"),
                RequestMock.build(this.url + "/glossaries/concordance", HttpPost.METHOD_NAME, "api/glossaries/organizationConcordanceSearchRequest.json", "api/glossaries/concordanceSearchResponse.json")
        );
    }

    @Test
    public void concordanceSearchTest() {
        SearchConcordanceRequest searchConcordanceRequest = new SearchConcordanceRequest();
        searchConcordanceRequest.setSourceLanguageId("en");
        searchConcordanceRequest.setTargetLanguageId("de");
        searchConcordanceRequest.setExpression("Welcome!");
        ResponseList<SearchConcordance> searchConcordanceResponseList = this.getGlossariesApi().searchConcordance(projectId, searchConcordanceRequest);
        assertEquals(searchConcordanceResponseList.getData().size(), 1);
        assertEquals(searchConcordanceResponseList.getData().get(0).getData().getGlossary().getId(), glossaryId);
        assertEquals(searchConcordanceResponseList.getData().get(0).getData().getConcept().getId(), 3);
        assertEquals(searchConcordanceResponseList.getData().get(0).getData().getSourceTerms().get(0).getId(), termId);
        assertEquals(searchConcordanceResponseList.getData().get(0).getData().getTargetTerms().get(0).getId(), termId);
    }

    @Test
    public void concordanceSearchExpressionsTest() {
        SearchConcordanceRequest searchConcordanceRequest = new SearchConcordanceRequest();
        searchConcordanceRequest.setSourceLanguageId("en");
        searchConcordanceRequest.setTargetLanguageId("de");
        searchConcordanceRequest.setExpressions(Arrays.asList("Welcome!", "View"));
        ResponseList<SearchConcordance> searchConcordanceResponseList = this.getGlossariesApi().searchConcordance(projectId, searchConcordanceRequest);
        assertEquals(1, searchConcordanceResponseList.getData().size());
        assertEquals(glossaryId, searchConcordanceResponseList.getData().get(0).getData().getGlossary().getId());
    }

    @Test
    public void searchOrganizationConcordanceTest() {
        SearchOrganizationConcordanceRequest request = new SearchOrganizationConcordanceRequest();
        request.setSourceLanguageId("en");
        request.setTargetLanguageId("de");
        request.setExpressions(singletonList("Welcome!"));
        request.setUserId(12L);
        ResponseList<SearchConcordance> responseList = this.getGlossariesApi().searchOrganizationConcordance(request);
        assertEquals(1, responseList.getData().size());
        assertEquals(glossaryId, responseList.getData().get(0).getData().getGlossary().getId());
    }

    @Test
    public void listConceptsTest() {
        ResponseList<Concept> conceptResponseList = this.getGlossariesApi().listConcepts(glossaryId, null, null);
        assertEquals(conceptResponseList.getData().size(), 1);
        assertEquals(conceptResponseList.getData().get(0).getData().getId(), glossaryId);
        assertEquals(conceptResponseList.getData().get(0).getData().getSubject(), subject);
    }

    @Test
    public void listConceptsTest_orderByNull() {
        ResponseList<Concept> conceptResponseList = this.getGlossariesApi().listConcepts(glossaryId, null, null, null);
        assertEquals(1, conceptResponseList.getData().size());
        assertEquals(glossaryId, conceptResponseList.getData().get(0).getData().getId());
        assertEquals(subject, conceptResponseList.getData().get(0).getData().getSubject());
    }

    @Test
    public void listConceptsTest_orderByIdNull() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        ResponseList<Concept> conceptResponseList = this.getGlossariesApi().listConcepts(glossary2Id, null, null, singletonList(orderById));
        assertEquals(2, conceptResponseList.getData().size());
        assertEquals(glossaryId, conceptResponseList.getData().get(0).getData().getId());
        assertEquals(glossary2Id, conceptResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listConceptsTest_orderByIdAsc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.ASC);

        ResponseList<Concept> conceptResponseList = this.getGlossariesApi().listConcepts(glossary2Id, null, null, singletonList(orderById));
        assertEquals(2, conceptResponseList.getData().size());
        assertEquals(glossaryId, conceptResponseList.getData().get(0).getData().getId());
        assertEquals(glossary2Id, conceptResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listConceptsTest_orderByIdDesc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.DESC);

        ResponseList<Concept> conceptResponseList = this.getGlossariesApi().listConcepts(glossary3Id, null, null, singletonList(orderById));
        assertEquals(2, conceptResponseList.getData().size());
        assertEquals(glossary2Id, conceptResponseList.getData().get(0).getData().getId());
        assertEquals(glossaryId, conceptResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void getConceptTest() {
        TimeZone.setDefault(tz);
        ResponseObject<Concept> conceptResponseObject = this.getGlossariesApi().getConcept(glossaryId, conceptId);
        assertEquals(conceptResponseObject.getData().getId(), glossaryId);
        assertEquals(conceptResponseObject.getData().getSubject(), subject);
        assertEquals(new Date(119,Calendar.SEPTEMBER,23,7,19,47), conceptResponseObject.getData().getCreatedAt());
        assertEquals(true, conceptResponseObject.getData().getTranslatable());
        assertEquals("some value 1", conceptResponseObject.getData().getFields().get("some-field-1"));
        assertEquals(true, conceptResponseObject.getData().getFields().get("some-field-3"));
    }

    @Test
    public void updateConceptTest() {
        Concept request = new Concept();
        request.setSubject(subject);
        request.setDefinition("This is a sample definition.");
        request.setNote("Any concept-level note information");
        request.setUrl("string");
        request.setFigure("string");
        Concept.ConceptLanguagesDetails languagesDetails = new Concept.ConceptLanguagesDetails();
        languagesDetails.setLanguageId("en");
        languagesDetails.setDefinition("This is a sample definition.");
        languagesDetails.setNote("Any kind of note, such as a usage note, explanation, or instruction.");
        request.setLanguagesDetails(Collections.singletonList(languagesDetails));

        ResponseObject<Concept> conceptResponseObject = this.getGlossariesApi().updateConcept(glossaryId, conceptId, request);
        assertEquals(conceptResponseObject.getData().getId(), glossaryId);
        assertEquals(conceptResponseObject.getData().getSubject(), subject);
    }

    @Test
    public void updateConceptWithRequestTest() {
        UpdateConceptRequest request = new UpdateConceptRequest();
        request.setSubject(subject);
        request.setDefinition("This is a sample definition.");
        request.setTranslatable(true);
        request.setNote("Any concept-level note information");
        request.setUrl("www.example.com");
        request.setFigure("www.example.com/image.png");
        UpdateConceptRequest.LanguagesDetails languagesDetails = new UpdateConceptRequest.LanguagesDetails();
        languagesDetails.setLanguageId("en");
        languagesDetails.setDefinition("This is a sample definition.");
        languagesDetails.setNote("Some note");
        request.setLanguagesDetails(singletonList(languagesDetails));
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("some-field-1", "some value 1");
        fields.put("some-field-3", true);
        request.setFields(fields);

        ResponseObject<Concept> conceptResponseObject = this.getGlossariesApi().updateConcept(glossaryId, conceptId, request);
        assertEquals(glossaryId, conceptResponseObject.getData().getId());
        assertEquals(subject, conceptResponseObject.getData().getSubject());
        assertEquals("some value 1", conceptResponseObject.getData().getFields().get("some-field-1"));
    }

    @Test
    public void deleteConceptTest() {
        this.getGlossariesApi().deleteConcept(glossaryId, conceptId);
    }

    @Test
    public void listGlossariesTest() {
        ResponseList<Glossary> glossaryResponseList = this.getGlossariesApi().listGlossaries(null, null, null);
        assertEquals(glossaryResponseList.getData().size(), 1);
        assertEquals(glossaryResponseList.getData().get(0).getData().getId(), glossaryId);
        assertEquals(glossaryResponseList.getData().get(0).getData().getName(), name);
    }

    @Test
    public void addGlossaryTest() {
        AddGlossaryRequest request = new AddGlossaryRequest();
        request.setName(name);
        request.setLanguageId(languageId);
        request.setGroupId(groupId);
        request.setIsShared(false);
        ResponseObject<Glossary> glossaryResponseObject = this.getGlossariesApi().addGlossary(request);
        assertEquals(glossaryResponseObject.getData().getId(), glossaryId);
        assertEquals(glossaryResponseObject.getData().getName(), name);
        assertEquals(false, glossaryResponseObject.getData().getIsShared());
        assertEquals("https://example.crowdin.com/u/glossaries/2", glossaryResponseObject.getData().getWebUrl());
    }

    @Test
    public void listGlossariesWithParamsTest() {
        ListGlossariesParams params = new ListGlossariesParams();
        params.setGroupId(groupId);
        params.setFilter("Eyes");
        params.setLimit(10);
        ResponseList<Glossary> glossaryResponseList = this.getGlossariesApi().listGlossaries(params);
        assertEquals(1, glossaryResponseList.getData().size());
        assertEquals(glossaryId, glossaryResponseList.getData().get(0).getData().getId());
    }

    @Test
    public void getGlossaryTest() {
        ResponseObject<Glossary> glossaryResponseObject = this.getGlossariesApi().getGlossary(glossaryId);
        assertEquals(glossaryResponseObject.getData().getId(), glossaryId);
        assertEquals(glossaryResponseObject.getData().getName(), name);
    }

    @Test
    public void deleteGlossaryTest() {
        this.getGlossariesApi().deleteGlossary(glossaryId);
    }

    @Test
    public void editGlossaryTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(name);
        request.setPath("/name");
        ResponseObject<Glossary> glossaryResponseObject = this.getGlossariesApi().editGlossary(glossaryId, singletonList(request));
        assertEquals(glossaryResponseObject.getData().getId(), glossaryId);
        assertEquals(glossaryResponseObject.getData().getName(), name);
    }

    @Test
    public void exportGlossaryTest() {
        ExportGlossaryRequest request = new ExportGlossaryRequest();
        request.setFormat(GlossariesFormat.CSV);
        request.setExportFields(Arrays.asList("term", "description", "partOfSpeech"));
        request.setExportType(ExportType.TERMS);
        request.setStatuses(Arrays.asList(Status.PREFERRED, Status.ADMITTED));
        request.setPartsOfSpeech(Arrays.asList(PartOfSpeech.NOUN, PartOfSpeech.VERB));
        request.setTypes(singletonList(Type.FULL_FORM));
        request.setGenders(singletonList(Gender.MASCULINE));
        request.setAuthorIds(Arrays.asList(1L, 2L));
        request.setLanguageIds(Arrays.asList("en", "uk"));
        ResponseObject<GlossaryExportStatus> glossaryExportStatusResponseObject = this.getGlossariesApi().exportGlossary(glossaryId, request);
        assertEquals(glossaryExportStatusResponseObject.getData().getIdentifier(), exportId);
        assertEquals(Arrays.asList(ExportTermStatus.PREFERRED, ExportTermStatus.ADMITTED), request.getTermStatuses());
        assertEquals(Arrays.asList(Status.PREFERRED, Status.ADMITTED), request.getStatuses());
        assertEquals(Arrays.asList(PartOfSpeech.NOUN, PartOfSpeech.VERB), request.getPartsOfSpeech());
        assertEquals(singletonList(Type.FULL_FORM), request.getTypes());
        assertEquals(singletonList(Gender.MASCULINE), request.getGenders());
    }

    @Test
    public void exportGlossaryWithFiltersTest() {
        ExportGlossaryRequest request = new ExportGlossaryRequest();
        request.setFormat(GlossariesFormat.XLSX);
        request.setExportFields(Arrays.asList("term", "field_term-field", "conceptField_concept-field"));
        request.setExportType(ExportType.CONCEPTS);
        request.setText("value");
        request.setCaseSensitive(true);
        request.setSearchStrict(false);
        request.setSearchFullMatch(true);
        request.setTermStatuses(Arrays.asList(ExportTermStatus.DRAFT, ExportTermStatus.NOT_RECOMMENDED));
        request.setTermPartsOfSpeech(Arrays.asList(ExportPartOfSpeech.ADJ, ExportPartOfSpeech.PROPN, ExportPartOfSpeech.SYM));
        request.setTermTypes(singletonList(ExportTermType.SHORT_FORM));
        request.setTermGenders(Arrays.asList(ExportTermGender.COMMON, ExportTermGender.NEUTER));
        request.setAuthorIds(singletonList(12L));
        request.setLanguageIds(singletonList("uk"));
        request.setDateFrom(new Date(1705993214000L));
        request.setDateTo(new Date(1727420414000L));
        ResponseObject<GlossaryExportStatus> glossaryExportStatusResponseObject = this.getGlossariesApi().exportGlossary(glossaryId, request);
        assertEquals(exportId, glossaryExportStatusResponseObject.getData().getIdentifier());

        assertEquals(Arrays.asList(Status.DRAFT, Status.NOT_RECOMMENDED), request.getStatuses());
        assertEquals(Arrays.asList(PartOfSpeech.ADJECTIVE, PartOfSpeech.PROPER_NOUN), request.getPartsOfSpeech());
        assertEquals(singletonList(Type.SHORT_FORM), request.getTypes());
        assertEquals(singletonList(Gender.NEUTER), request.getGenders());
    }

    @Test
    public void exportGlossaryRequestDeprecatedSettersConversionTest() {
        ExportGlossaryRequest request = new ExportGlossaryRequest();
        request.setStatuses(Arrays.asList(Status.values()));
        request.setTypes(Arrays.asList(Type.values()));
        request.setGenders(Arrays.asList(Gender.values()));
        request.setPartsOfSpeech(Arrays.asList(PartOfSpeech.values()));

        assertEquals(Arrays.asList(ExportTermStatus.PREFERRED, ExportTermStatus.ADMITTED, ExportTermStatus.NOT_RECOMMENDED,
                ExportTermStatus.OBSOLETE, ExportTermStatus.DRAFT), request.getTermStatuses());
        assertEquals(Arrays.asList(ExportTermType.FULL_FORM, ExportTermType.ACRONYM, ExportTermType.ABBREVIATION,
                ExportTermType.SHORT_FORM, ExportTermType.PHRASE, ExportTermType.VARIANT), request.getTermTypes());
        assertEquals(Arrays.asList(ExportTermGender.MASCULINE, ExportTermGender.FEMININE, ExportTermGender.NEUTER,
                ExportTermGender.OTHER), request.getTermGenders());
        assertEquals(Arrays.asList(ExportPartOfSpeech.ADJ, ExportPartOfSpeech.ADP, ExportPartOfSpeech.ADV,
                ExportPartOfSpeech.AUX, ExportPartOfSpeech.CCONJ, ExportPartOfSpeech.DET, ExportPartOfSpeech.INTJ,
                ExportPartOfSpeech.NOUN, ExportPartOfSpeech.NUM, ExportPartOfSpeech.PRT, ExportPartOfSpeech.PRON,
                ExportPartOfSpeech.PROPN, ExportPartOfSpeech.SCONJ, ExportPartOfSpeech.VERB, ExportPartOfSpeech.X),
                request.getTermPartsOfSpeech());
        assertEquals(Arrays.asList(PartOfSpeech.values()), request.getPartsOfSpeech());

        String json = new JacksonJsonTransformer().convert(request);
        assertEquals("{\"statuses\":[\"PREFERRED\",\"ADMITTED\",\"NOT_RECOMMENDED\",\"OBSOLETE\",\"DRAFT\"],"
                + "\"partsOfSpeech\":[\"ADJ\",\"ADP\",\"ADV\",\"AUX\",\"CCONJ\",\"DET\",\"INTJ\",\"NOUN\",\"NUM\",\"PRT\",\"PRON\",\"PROPN\",\"SCONJ\",\"VERB\",\"X\"],"
                + "\"types\":[\"FULL_FORM\",\"ACRONYM\",\"ABBREVIATION\",\"SHORT_FORM\",\"PHRASE\",\"VARIANT\"],"
                + "\"genders\":[\"MASCULINE\",\"FEMININE\",\"NEUTER\",\"OTHER\"]}", json);

        request.setStatuses(null);
        assertNull(request.getTermStatuses());
        assertNull(request.getStatuses());
    }

    @Test
    public void checkGlossaryExportStatusTest() {
        ResponseObject<GlossaryExportStatus> glossaryExportStatusResponseObject = this.getGlossariesApi().checkGlossaryExportStatus(glossaryId, exportId);
        assertEquals(glossaryExportStatusResponseObject.getData().getIdentifier(), exportId);
        GlossaryExportStatus.Attributes attributes = glossaryExportStatusResponseObject.getData().getAttributes();
        assertEquals(GlossariesFormat.CSV, attributes.getFormat());
        assertEquals(Arrays.asList("term", "description", "partOfSpeech"), attributes.getExportFields());
        assertEquals("value", attributes.getText());
        assertEquals(true, attributes.getCaseSensitive());
        assertEquals(false, attributes.getSearchFullMatch());
        assertEquals(true, attributes.getSearchStrict());
        assertEquals("DRAFT", attributes.getStatus());
        assertEquals("VERB", attributes.getPartOfSpeech());
        assertEquals("ACRONYM", attributes.getType());
        assertEquals("COMMON", attributes.getGender());
        assertEquals(12L, attributes.getAuthorId());
        assertEquals(new Date(1727090814000L), attributes.getDateFrom());
        assertEquals(new Date(1734953214000L), attributes.getDateTo());
    }

    @Test
    public void downloadGlossaryTest() {
        ResponseObject<DownloadLink> downloadLinkResponseObject = this.getGlossariesApi().downloadGlossary(glossaryId, exportId);
        assertEquals(downloadLinkResponseObject.getData().getUrl(), link);
    }

    @Test
    public void importGlossaryTest() {
        ImportGlossaryRequest request = new ImportGlossaryRequest();
        request.setStorageId(12L);
        request.setScheme(Stream.of(new Object[][]{
                {"term_en", 2},
                {"description_en", 1},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])));
        request.setFirstLineContainsHeader(false);
        ResponseObject<GlossaryImportStatus> glossaryImportStatusResponseObject = this.getGlossariesApi().importGlossary(glossaryId, request);
        assertEquals(glossaryImportStatusResponseObject.getData().getIdentifier(), importId);
        assertTrue(glossaryImportStatusResponseObject.getData().getAttributes().isFirstLineContainsHeader());
    }

    @Test
    public void checkGlossaryImportStatusTest() {
        ResponseObject<GlossaryImportStatus> glossaryImportStatusResponseObject = this.getGlossariesApi().checkGlossaryImportStatus(glossaryId, importId);
        assertEquals(glossaryImportStatusResponseObject.getData().getIdentifier(), importId);
    }

    @Test
    public void listTermsTest() {
        TimeZone.setDefault(tz);
        ResponseList<Term> termResponseList = this.getGlossariesApi().listTerms(glossaryId, null, null, null, null, null, null);
        assertEquals(termResponseList.getData().size(), 1);
        assertEquals(termResponseList.getData().get(0).getData().getId(), termId);
        assertEquals(new Date(119,Calendar.SEPTEMBER,23,7,19,47), termResponseList.getData().get(0).getData().getCreatedAt());
    }

    @Test
    public void listTermsTest_orderByNull() {
        TimeZone.setDefault(tz);
        ResponseList<Term> termResponseList = this.getGlossariesApi().listTerms(glossaryId, null, null, null, null, null, null, null);
        assertEquals(1, termResponseList.getData().size());
        assertEquals(termId, termResponseList.getData().get(0).getData().getId());

    }

    @Test
    public void listTermsTest_orderByIdNull() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        TimeZone.setDefault(tz);
        ResponseList<Term> termResponseList = this.getGlossariesApi().listTerms(glossary2Id, null, null, null, null, null, null, singletonList(orderById));
        assertEquals(2, termResponseList.getData().size());
        assertEquals(termId, termResponseList.getData().get(0).getData().getId());
        assertEquals(term2Id, termResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listTermsTest_orderByIdAsc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.ASC);

        ResponseList<Term> termResponseList = this.getGlossariesApi().listTerms(glossary2Id, null, null, null, null, null, null, singletonList(orderById));
        assertEquals(2, termResponseList.getData().size());
        assertEquals(termId, termResponseList.getData().get(0).getData().getId());
        assertEquals(term2Id, termResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listTermsTest_orderByIdDesc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.DESC);

        ResponseList<Term> termResponseList = this.getGlossariesApi().listTerms(glossary3Id, null, null, null, null, null, null, singletonList(orderById));
        assertEquals(2, termResponseList.getData().size());
        assertEquals(term2Id, termResponseList.getData().get(0).getData().getId());
        assertEquals(termId, termResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void addTermTest() {
        AddTermRequest request = new AddTermRequest();
        request.setText(text);
        request.setLanguageId("fr");
        request.setDescription("use for pages only (check screenshots)");
        request.setPartOfSpeech(PartOfSpeech.VERB);
        request.setStatus(Status.PREFERRED);
        request.setType(Type.ABBREVIATION);
        request.setGender(Gender.MASCULINE);
        request.setNote("string");
        request.setUrl("string");
        request.setConceptId(0L);
        request.setTranslationOfTermId(0L);
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("some-field-1", "some value 1");
        fields.put("some-field-2", 12);
        request.setFields(fields);
        ResponseObject<Term> termResponseObject = this.getGlossariesApi().addTerm(glossaryId, request);
        assertEquals(termResponseObject.getData().getId(), termId);
        assertEquals(Status.DRAFT, termResponseObject.getData().getStatus());
        assertEquals("some value 1", termResponseObject.getData().getFields().get("some-field-1"));
        assertEquals(12, termResponseObject.getData().getFields().get("some-field-2"));
    }

    @Test
    public void clearGlossaryTest() {
        this.getGlossariesApi().clearGlossary(glossaryId, null, null, null);
    }

    @Test
    public void getTermTest() {
        ResponseObject<Term> termResponseObject = this.getGlossariesApi().getTerm(glossaryId, termId);
        assertEquals(termResponseObject.getData().getId(), termId);
    }

    @Test
    public void deleteTermTest() {
        this.getGlossariesApi().deleteTerm(glossaryId, termId);
    }

    @Test
    public void editTermTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(text);
        request.setPath("/text");
        ResponseObject<Term> termResponseObject = this.getGlossariesApi().editTerm(glossaryId, termId, singletonList(request));
        assertEquals(termResponseObject.getData().getId(), termId);
    }
}
