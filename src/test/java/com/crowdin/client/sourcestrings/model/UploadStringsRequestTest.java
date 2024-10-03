package com.crowdin.client.sourcestrings.model;

import com.crowdin.client.sourcefiles.model.UpdateOption;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UploadStringsRequestTest {

    private final UpdateOption defaultOption = UpdateOption.CLEAR_TRANSLATIONS_AND_APPROVALS;

    @Test
    public void updateStringsTest() {
        boolean isUpdateStrings = true;
        UpdateOption updateOption = UpdateOption.KEEP_TRANSLATIONS;

        UploadStringsRequest request = new UploadStringsRequest();
        request.setUpdateStrings(isUpdateStrings);
        request.setUpdateOption(updateOption);

        assertEquals(isUpdateStrings, request.getUpdateStrings());
        assertEquals(updateOption, request.getUpdateOption());
    }

    @Test
    public void defaultUpdateOptionTest() {
        UploadStringsRequest request = new UploadStringsRequest();
        request.setUpdateStrings(true);

        assertEquals(defaultOption, request.getUpdateOption());
    }

    @Test
    public void updateStringsFlagDisabledTest() {
        UploadStringsRequest request = new UploadStringsRequest();
        request.setUpdateStrings(false);

        assertFalse(request.getUpdateStrings());
        assertNull(request.getUpdateOption());
    }

    @Test
    public void updateStringsFlagShouldBeEnabledTest() {
        UpdateOption updateOption = UpdateOption.KEEP_TRANSLATIONS_AND_APPROVALS;

        UploadStringsRequest request = new UploadStringsRequest();
        request.setUpdateOption(updateOption);

        assertTrue(request.getUpdateStrings());
        assertEquals(updateOption, request.getUpdateOption());
    }

}