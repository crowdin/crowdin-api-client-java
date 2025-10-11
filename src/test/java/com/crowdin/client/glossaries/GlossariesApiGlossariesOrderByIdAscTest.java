package com.crowdin.client.glossaries;

import com.crowdin.client.core.model.*;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.glossaries.model.*;
import org.apache.http.client.methods.*;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.reverseOrder;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GlossariesApiGlossariesOrderByIdAscTest extends TestClient {

    private final Long glossaryId = 2L;
    private final Long glossary2Id = 3L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/glossaries", HttpGet.METHOD_NAME, "api/glossaries/listGlossariesOrderByIdAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20asc");
                }})
        );
    }

    @Test
    public void listGlossariesTest_orderByIdNull() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        ResponseList<Glossary> glossaryResponseList = this.getGlossariesApi().listGlossaries(null, null, null, singletonList(orderById));
        assertEquals(2, glossaryResponseList.getData().size());
        assertEquals(glossaryId, glossaryResponseList.getData().get(0).getData().getId());
        assertEquals(glossary2Id, glossaryResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listGlossariesTest_orderByIdAsc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.ASC);

        ResponseList<Glossary> glossaryResponseList = this.getGlossariesApi().listGlossaries(null, null, null, singletonList(orderById));
        assertEquals(2, glossaryResponseList.getData().size());
        assertEquals(glossaryId, glossaryResponseList.getData().get(0).getData().getId());
        assertEquals(glossary2Id, glossaryResponseList.getData().get(1).getData().getId());
    }
}


