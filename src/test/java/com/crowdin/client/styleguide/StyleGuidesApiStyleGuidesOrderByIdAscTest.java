package com.crowdin.client.styleguide;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.SortOrder;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.styleguide.model.StyleGuide;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StyleGuidesApiStyleGuidesOrderByIdAscTest extends TestClient {

    private final Long styleGuideId = 2L;
    private final Long styleGuide2Id = 3L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/style-guides", HttpGet.METHOD_NAME, "api/styleguide/listStyleGuidesOrderByIdAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20asc");
                }})
        );
    }

    @Test
    public void listStyleGuidesTest_orderByIdNull() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        ResponseList<StyleGuide> styleGuideResponseList = this.getStyleGuidesApi().listStyleGuide(null, null, null, singletonList(orderById));
        assertEquals(2, styleGuideResponseList.getData().size());
        assertEquals(styleGuideId, styleGuideResponseList.getData().get(0).getData().getId());
        assertEquals(styleGuide2Id, styleGuideResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listStyleGuidesTest_orderByIdAsc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.ASC);

        ResponseList<StyleGuide> styleGuideResponseList = this.getStyleGuidesApi().listStyleGuide(null, null, null, singletonList(orderById));
        assertEquals(2, styleGuideResponseList.getData().size());
        assertEquals(styleGuideId, styleGuideResponseList.getData().get(0).getData().getId());
        assertEquals(styleGuide2Id, styleGuideResponseList.getData().get(1).getData().getId());
    }
}
