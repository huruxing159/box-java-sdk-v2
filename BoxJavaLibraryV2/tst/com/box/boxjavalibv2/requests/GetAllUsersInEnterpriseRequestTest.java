package com.box.boxjavalibv2.requests;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.http.HttpStatus;
import org.junit.Test;

import com.box.boxjavalibv2.BoxConfig;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;

public class GetAllUsersInEnterpriseRequestTest extends RequestTestBase {

    @Test
    public void testUri() {
        Assert.assertEquals("/users", GetAllUsersInEnterpriseRequest.getUri());
    }

    @Test
    public void testRequestIsWellFormed() throws BoxRestException, IllegalStateException, IOException, AuthFatalFailureException {
        int limit = 100;
        int offset = 200;
        String filterTerm = "testfilterterm";
        GetAllUsersInEnterpriseRequest request = new GetAllUsersInEnterpriseRequest(CONFIG, OBJECT_MAPPER, (new BoxDefaultRequestObject()).setPage(limit,
            offset), filterTerm);

        testRequestIsWellFormed(request, BoxConfig.getInstance().getApiUrlAuthority(),
            BoxConfig.getInstance().getApiUrlPath().concat(GetAllUsersInEnterpriseRequest.getUri()), HttpStatus.SC_OK, RestMethod.GET);
        Assert.assertEquals(Integer.toString(limit), request.getQueryParams().get("limit"));
        Assert.assertEquals(Integer.toString(offset), request.getQueryParams().get("offset"));
        Assert.assertEquals(filterTerm, request.getQueryParams().get("filter_term"));
    }

    @Test
    public void testRequestDoesNotHaveFilterTermIfNotSupplied() throws BoxRestException {
        GetAllUsersInEnterpriseRequest request = new GetAllUsersInEnterpriseRequest(CONFIG, OBJECT_MAPPER, (new BoxDefaultRequestObject()).setPage(1, 2), null);
        Assert.assertFalse(request.getQueryParams().containsKey("filter_term"));

    }

}
