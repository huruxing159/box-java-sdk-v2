package com.box.boxjavalibv2.requests;

import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to get info of a file or folder.
 */
public class GetItemRequest extends DefaultBoxRequest {

    private static final String URI = "/%s/%s";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param id
     *            id of the item
     * @param type
     *            resource type
     * @param requestObject
     *            request object
     * @throws BoxRestException
     *             excepition
     */
    public GetItemRequest(final IBoxConfig config, final ObjectMapper objectMapper, final String id, final BoxResourceType type,
        BoxDefaultRequestObject requestObject) throws BoxRestException {
        super(config, objectMapper, getUri(id, type), RestMethod.GET, requestObject);
    }

    /**
     * Get uri.
     * 
     * @param id
     *            id of the item
     * @param type
     *            resource type
     * @return uri
     */
    public static String getUri(final String id, final BoxResourceType type) {
        return String.format(URI, type.toPluralString(), id);
    }
}
