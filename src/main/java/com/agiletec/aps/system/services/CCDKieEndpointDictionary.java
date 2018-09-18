package com.agiletec.aps.system.services;

import org.apache.http.HttpStatus;
import org.entando.entando.plugins.jprestapi.aps.core.Endpoint;
import org.entando.entando.plugins.jprestapi.aps.core.IEndpoint;

import java.util.HashMap;

import static com.agiletec.aps.system.services.CCDKieBpmConstants.*;
import static org.entando.entando.plugins.jpkiebpm.aps.system.KieBpmSystemConstants.*;


public class CCDKieEndpointDictionary {

    private static HashMap<String, Endpoint> endpoints;

    private CCDKieEndpointDictionary() {
    }

    private static void init() {
        endpoints = new HashMap<>();
        endpoints.put(API_GET_CASES_DETAILS, new Endpoint(IEndpoint.httpVerb.GET, "/services/rest/server/containers/%s/cases/instances/%s", HttpStatus.SC_OK));
        endpoints.put(API_GET_COMMENTS_LIST, new Endpoint(IEndpoint.httpVerb.GET, "/services/rest/server/containers/%s/cases/instances/%s/comments", HttpStatus.SC_OK));
        endpoints.put(API_POST_COMMENTS, new Endpoint(IEndpoint.httpVerb.POST, "/services/rest/server/containers/%s/cases/instances/%s/comments", HttpStatus.SC_CREATED, true));
        endpoints.put(API_GET_CASE_FILE, new Endpoint(IEndpoint.httpVerb.GET, "/services/rest/server/containers/%s/cases/instances/%s/caseFile", HttpStatus.SC_OK));
        endpoints.put(API_GET_ACTIVITY_LOG, new Endpoint(IEndpoint.httpVerb.GET, "/services/rest/server/containers/%s/cases/instances/%s/nodes/instances", HttpStatus.SC_OK));


    }

    public static HashMap<String, Endpoint> create() {
        init();
        return endpoints;
    }

}
