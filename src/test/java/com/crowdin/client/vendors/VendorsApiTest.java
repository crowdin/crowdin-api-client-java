package com.crowdin.client.vendors;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.vendors.model.Status;
import com.crowdin.client.vendors.model.Vendor;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorsApiTest extends TestClient {

    private final Long vendorId = 52760L;
    private final String name = "John Smith Translation Agency";

    @Override
    public List<RequestMock> getMocks() {
        return singletonList(
                RequestMock.build(this.url + "/vendors", HttpGet.METHOD_NAME, "api/vendors/listVendors.json")
        );
    }

    @Test
    public void listVendorsTest() {
        ResponseList<Vendor> vendorResponseList = this.getVendorsApi().listVendors(null, null);
        assertEquals(vendorResponseList.getData().size(), 1);
        assertEquals(vendorResponseList.getData().get(0).getData().getId(), vendorId);
        assertEquals(vendorResponseList.getData().get(0).getData().getName(), name);
        assertEquals(vendorResponseList.getData().get(0).getData().getStatus(), Status.PENDING);
    }
}
