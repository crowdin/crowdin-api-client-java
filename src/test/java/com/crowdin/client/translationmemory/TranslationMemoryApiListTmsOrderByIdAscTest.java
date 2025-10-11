package com.crowdin.client.translationmemory;

import com.crowdin.client.core.model.*;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.translationmemory.model.*;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslationMemoryApiListTmsOrderByIdAscTest extends TestClient {

    private final Long tmId = 4L;
    private final Long tm2Id = 5L;
    private final String name = "Knowledge Base's TM";

    @Override
    public List<RequestMock> getMocks() {
        return singletonList(
                RequestMock.build(this.url + "/tms", HttpGet.METHOD_NAME, "api/translationmemory/listTmsOrderByIdAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20asc");
                }})
        );
    }

    @Test
    public void listTmsTest_orderByIdNull() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        ResponseList<TranslationMemory> translationMemoryResponseList = this.getTranslationMemoryApi().listTms(null, null, null, null, singletonList(orderById));
        assertEquals(2, translationMemoryResponseList.getData().size());
        assertEquals(tmId, translationMemoryResponseList.getData().get(0).getData().getId());
        assertEquals(name, translationMemoryResponseList.getData().get(0).getData().getName());
        assertEquals(tm2Id, translationMemoryResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listTmsTest_orderByIdAsc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.ASC);

        ResponseList<TranslationMemory> translationMemoryResponseList = this.getTranslationMemoryApi().listTms(null, null, null, null, singletonList(orderById));
        assertEquals(2, translationMemoryResponseList.getData().size());
        assertEquals(tmId, translationMemoryResponseList.getData().get(0).getData().getId());
        assertEquals(name, translationMemoryResponseList.getData().get(0).getData().getName());
        assertEquals(tm2Id, translationMemoryResponseList.getData().get(1).getData().getId());
    }
}
