package com.crowdin.util;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.response.Page;

import java.util.ArrayList;
import java.util.List;

public class PaginationUtil {

    private static final int MAX_PAGE_SIZE = 200;

    public static <T> List<T> unpaged(CrowdinRequestBuilder<Page<T>> request) {
        int offset = 0;
        boolean isAllPageProceed = false;
        List<T> result = new ArrayList<>();

        while (!isAllPageProceed) {
            Page<T> responseEntity = request.pageable(Pageable.of(offset * MAX_PAGE_SIZE, MAX_PAGE_SIZE)).getResponseEntity();
            result.addAll(responseEntity.getContent());
            isAllPageProceed = responseEntity.getContent().size() < MAX_PAGE_SIZE || responseEntity.getContent().isEmpty();
            offset++;
        }

        return result;
    }
}
