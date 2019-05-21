package com.crowdin;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.client.api.LanguagesApi;
import com.crowdin.client.api.ProjectsApi;
import com.crowdin.client.api.TranslationsApi;
import com.crowdin.common.Settings;
import com.crowdin.common.models.Language;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.models.Project;
import com.crowdin.common.models.Translation;
import com.crowdin.common.request.LanguagePayload;
import com.crowdin.common.request.ProjectPayload;
import com.crowdin.common.response.Page;
import com.crowdin.common.response.SimpleResponse;
import com.crowdin.util.PaginationUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Crowdin {

    public static void main(String[] args) {


        Settings settings = Settings.enterprise(
                "44ff059b37bfbf88df8db4ccf0ed1696",
                "nutelka"
        );

        CrowdinRequestBuilder<Page<Translation>> translations = new TranslationsApi(settings)
                .getTranslations("10", Pageable.unpaged());


        List<Translation> unpaged = PaginationUtil.unpaged(translations);
        System.out.println(unpaged.size());
/*
        new TranslationsApi(settings)
                .getTranslationInfo("10", "1")
                .getResponseEntity()
                .doWithEntity(fileRaw -> System.out.println(fileRaw));*/
       /* new TranslationsApi(settings)
                .getTranslationRaw("10")
*/

    }

    private static SimpleResponse<Project> createProject(Settings settings) {
        ProjectsApi projectsApi = new ProjectsApi(settings);
        ProjectPayload projectPayload = new ProjectPayload();
        projectPayload.setName("java_sdk_test_with_language");
        projectPayload.setSourceLanguageId(1);
        projectPayload.setTargetLanguageIds(Collections.singletonList(229L));
        SimpleResponse<Project> responseEntity = projectsApi.createGroupProject("7", projectPayload).getResponseEntity();
        return responseEntity;
    }

    private static void delete(Settings settings) {
        new LanguagesApi(settings).deleteLanguage("305").execute();
    }

    private static void getAll(Settings settings) {
        List<Language> execute = PaginationUtil.unpaged(new LanguagesApi(settings).getLanguages(Pageable.unpaged()));
//        Page<Language> execute = new LanguagesApi(settings).getLanguages(Pageable.of(0, 500)).getResponseEntity();
        List<Language> java = execute.stream().filter(language -> language.getName().contains("Java")).collect(Collectors.toList());
    }

    private static SimpleResponse<Language> create(Settings settings) {
        LanguagePayload testJavaSdk = new LanguagePayload();
        testJavaSdk.setName("testJavaSdk");
        testJavaSdk.setCode("ENG");
        testJavaSdk.setLocaleCode("EN");
        testJavaSdk.setThreeLettersCode("ENG");
        testJavaSdk.setTextDirection("ltr");
        testJavaSdk.setPluralCategoryNames(Arrays.asList("other"));
        return new LanguagesApi(settings).createLanguage(testJavaSdk).getResponseEntity();
    }
}
