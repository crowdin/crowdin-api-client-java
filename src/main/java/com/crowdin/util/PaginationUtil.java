package com.crowdin.util;

import com.crowdin.client.CrowdinRequestBuilder;
import com.crowdin.common.models.Pageable;
import com.crowdin.common.response.Page;

import java.util.ArrayList;
import java.util.List;

public class PaginationUtil {

    private static final int MAX_PAGE_SIZE = 200;

    public static <T> List<T> unpaged(CrowdinRequestBuilder<Page<T>> request, int page_size) {
        int offset = 0;
        boolean isAllPageProceed = false;
        List<T> result = new ArrayList<>();

        while (!isAllPageProceed) {
            Page<T> responseEntity = request.pageable(Pageable.of(offset * page_size, page_size)).getResponseEntity();
            result.addAll(responseEntity.getContent());
            isAllPageProceed = responseEntity.getContent().size() < page_size || responseEntity.getContent().isEmpty();
            offset++;
        }

        return result;
    }

    public static <T> List<T> unpaged(CrowdinRequestBuilder<Page<T>> request) {
        return unpaged(request, MAX_PAGE_SIZE);
    }
}
