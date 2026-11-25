package com.crowdin.client.core.model;

import com.crowdin.client.translations.model.Method;
import com.crowdin.client.translationstatus.model.Category;
import com.crowdin.client.translationstatus.model.Validation;
import com.crowdin.client.users.model.Status;
import com.crowdin.client.users.model.TwoFactor;
import com.crowdin.client.webhooks.model.OrganizationEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;

import java.util.Locale;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ResourceLock(Resources.LOCALE)
class EnumConverterLocaleTest {

    @Test
    void convertsApiValuesWithTurkishDefaultLocale() {
        Locale originalLocale = Locale.getDefault();
        Locale originalDisplayLocale = Locale.getDefault(Locale.Category.DISPLAY);
        Locale originalFormatLocale = Locale.getDefault(Locale.Category.FORMAT);

        try {
            Locale.setDefault(Locale.forLanguageTag("tr-TR"));

            assertAll(
                    () -> assertConversion(Priority.HIGH, "high", Priority::from),
                    () -> assertConversion(Method.AI, "ai", Method::from),
                    () -> assertConversion(Category.ICU, "icu", Category::from),
                    () -> assertConversion(Validation.AI_CHECK, "ai_check", Validation::from),
                    () -> assertConversion(Status.ACTIVE, "active", Status::from),
                    () -> assertConversion(TwoFactor.DISABLED, "disabled", TwoFactor::from),
                    () -> assertConversion(OrganizationEvent.PROJECT_CREATED,
                            "project.created", OrganizationEvent::from)
            );
        } finally {
            Locale.setDefault(originalLocale);
            Locale.setDefault(Locale.Category.DISPLAY, originalDisplayLocale);
            Locale.setDefault(Locale.Category.FORMAT, originalFormatLocale);
        }
    }

    private static <T extends Enum<T> & EnumConverter<T>> void assertConversion(
            T value, String apiValue, Function<String, T> from) {
        assertAll(value.getDeclaringClass().getSimpleName(),
                () -> assertEquals(apiValue, value.to(value)),
                () -> assertEquals(value, from.apply(apiValue)),
                () -> assertEquals(value, from.apply((String) value.to(value))));
    }
}
