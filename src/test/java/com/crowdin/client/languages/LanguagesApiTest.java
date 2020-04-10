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
        ResponseList<Language> languageResponseList = this.getLanguagesApi().listSupportedLanguages(null, null);
        assertEquals(languageResponseList.getData().size(), 1);
        assertEquals(languageResponseList.getData().get(0).getData().getId(), id);
        assertEquals(languageResponseList.getData().get(0).getData().getName(), name);
    }

    @Test
    public void addLanguageTest() {
        AddCustomLanguageRequest request = new AddCustomLanguageRequest();
        request.setName(name);
        request.setCode(id);
        request.setLocaleCode(localeCode);
        request.setTextDirection(textDirection);
        request.setPluralCategoryNames(pluralCategoryNames);
        request.setThreeLettersCode(threeLettersCode);
        ResponseObject<Language> languageResponseObject = this.getLanguagesApi().addCustomLanguage(request);
        assertEquals(languageResponseObject.getData().getId(), id);
        assertEquals(languageResponseObject.getData().getName(), name);
    }

    @Test
    public void getLanguageTest() {
        ResponseObject<Language> language = this.getLanguagesApi().getLanguage(id);
        assertEquals(language.getData().getId(), id);
        assertEquals(language.getData().getName(), name);
    }

    @Test
    public void deleteLanguageTest() {
        this.getLanguagesApi().deleteLanguage(id);
    }

    @Test
    public void editLanguageTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue("Spanish");
        request.setPath("/name");
        ResponseObject<Language> languageResponseObject = this.getLanguagesApi().editLanguage(id, singletonList(request));
        assertEquals(languageResponseObject.getData().getId(), id);
        assertEquals(languageResponseObject.getData().getName(), name);
    }
}
