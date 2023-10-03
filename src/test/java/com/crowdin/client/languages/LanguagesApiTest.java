package com.crowdin.client.languages;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.languages.model.AddCustomLanguageRequest;
import com.crowdin.client.languages.model.Language;
import com.crowdin.client.languages.model.TextDirection;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguagesApiTest extends TestClient {

    private final String name = "Spanish";
    private final String localeCode = "es-ES";
    private final TextDirection textDirection = TextDirection.LTR;
    private final List<String> pluralCategoryNames = emptyList();
    private final String threeLettersCode = "spa";
    private final String id = "es";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/languages", HttpGet.METHOD_NAME, "api/languages/listLanguages.json"),
                RequestMock.build(this.url + "/languages", HttpPost.METHOD_NAME, "api/languages/addLanguageRequest.json", "api/languages/language.json"),
                RequestMock.build(this.url + "/languages/" + id, HttpGet.METHOD_NAME, "api/languages/language.json"),
                RequestMock.build(this.url + "/languages/" + id, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/languages/" + id, HttpPatch.METHOD_NAME, "api/languages/editLanguage.json", "api/languages/language.json")
        );
    }

    @Test
    public void listLanguagesTest() {
        // given
        Integer limit = null;
        Integer offset = null;

        // when
        ResponseList<Language> languageResponseList = this.getLanguagesApi().listSupportedLanguages(limit, offset);

        // then
        assertEquals(languageResponseList.getData().size(), 1);
        assertLanguage(languageResponseList.getData().get(0).getData());
    }

    @Test
    public void addLanguageTest() {
        // given
        AddCustomLanguageRequest request = new AddCustomLanguageRequest();
        request.setName(name);
        request.setCode(id);
        request.setLocaleCode(localeCode);
        request.setTextDirection(textDirection);
        request.setPluralCategoryNames(pluralCategoryNames);
        request.setThreeLettersCode(threeLettersCode);

        // when
        ResponseObject<Language> languageResponseObject = this.getLanguagesApi().addCustomLanguage(request);

        // then
        assertLanguage(languageResponseObject.getData());
    }

    @Test
    public void getLanguageTest() {
        // given
        final String languageId = id;

        // when
        ResponseObject<Language> language = this.getLanguagesApi().getLanguage(languageId);

        // then
        assertLanguage(language.getData());
    }

    @Test
    public void deleteLanguageTest() {
        this.getLanguagesApi().deleteLanguage(id);
    }

    @Test
    public void editLanguageTest() {
        // given
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue("Spanish");
        request.setPath("/name");

        // when
        ResponseObject<Language> languageResponseObject = this.getLanguagesApi().editLanguage(id, singletonList(request));

        // then
        assertLanguage(languageResponseObject.getData());
    }

    private void assertLanguage(Language actualLanguage) {
        final Language expectedLanguage = new Language();
        expectedLanguage.setId("es");
        expectedLanguage.setName("Spanish");
        expectedLanguage.setEditorCode("es");
        expectedLanguage.setTwoLettersCode("es");
        expectedLanguage.setThreeLettersCode("spa");
        expectedLanguage.setLocale("es-ES");
        expectedLanguage.setAndroidCode("es-rES");
        expectedLanguage.setOsxCode("es.lproj");
        expectedLanguage.setOsxLocale("es");
        expectedLanguage.setPluralCategoryNames(Arrays.asList("one"));
        expectedLanguage.setPluralRules("(n != 1)");
        expectedLanguage.setPluralExamples(Arrays.asList("0, 2-999; 1.2, 2.07..."));
        expectedLanguage.setTextDirection(TextDirection.LTR);
        expectedLanguage.setDialectOf("string");

        assertEquals(expectedLanguage, actualLanguage);
    }
}
