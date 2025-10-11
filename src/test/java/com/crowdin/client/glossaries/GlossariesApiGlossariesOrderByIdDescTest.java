package com.crowdin.client.glossaries;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.SortOrder;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.glossaries.model.Glossary;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlossariesApiGlossariesOrderByIdDescTest extends TestClient {
    
    private final Long glossaryId = 2L;
    private final Long glossary2Id = 3L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/glossaries", HttpGet.METHOD_NAME, "api/glossaries/listGlossariesOrderByIdDesc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20desc");
                }})
        );
    }

    @Test
    public void listGlossariesTest_orderByIdDesc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.DESC);

        ResponseList<Glossary> glossaryResponseList = this.getGlossariesApi().listGlossaries(null, null, null, singletonList(orderById));
        assertEquals(2, glossaryResponseList.getData().size());
        assertEquals(glossary2Id, glossaryResponseList.getData().get(0).getData().getId());
        assertEquals(glossaryId, glossaryResponseList.getData().get(1).getData().getId());
    }
}


