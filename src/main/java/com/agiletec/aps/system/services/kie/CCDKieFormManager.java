package com.agiletec.aps.system.services.kie;

import com.agiletec.aps.system.exception.ApsSystemException;
import com.agiletec.aps.system.services.CCDKieEndpointDictionary;
import org.apache.commons.lang.StringUtils;
import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.KIEAuthenticationCredentials;
import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.KieClient;
import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.KieRequestBuilder;
import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.model.KieBpmConfig;
import org.entando.entando.plugins.jprestapi.aps.core.Endpoint;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.agiletec.aps.system.services.CCDKieBpmConstants.API_GET_ACTIVITY_LOG;
import static com.agiletec.aps.system.services.CCDKieBpmConstants.API_GET_CASE_TASK_ID;
import static com.agiletec.aps.system.services.CCDKieBpmConstants.API_POST_CASE_ATTACH_FILE;
import static org.entando.entando.plugins.jpkiebpm.aps.system.KieBpmSystemConstants.*;

public class CCDKieFormManager {
    private static final Logger logger = LoggerFactory.getLogger(CCDKieFormManager.class);


    public JSONObject getCaseInstancesDetails(KieBpmConfig config, String containerId, String caseID) throws ApsSystemException {

        Map<String, String> headersMap = new HashMap<>();

        String result;
        JSONObject casesDetails = null;

        if (StringUtils.isBlank(containerId) || StringUtils.isBlank(caseID)) {
            return casesDetails;
        }
        try {
            // process endpoint first
            Endpoint ep = CCDKieEndpointDictionary.create().get(API_GET_CASES_DETAILS).resolveParams(containerId, caseID);
            // add header
            headersMap.put(HEADER_KEY_ACCEPT, HEADER_VALUE_JSON);

            // generate client from the current configuration
            KieClient client = this.getClient(config);
            // perform query
            result = (String) new KieRequestBuilder(client)
                    .setEndpoint(ep)
                    .setHeaders(headersMap)
                    .setDebug(config.getDebug())
                    .doRequest();

            if (!result.isEmpty()) {
                casesDetails = new JSONObject(result);
                logger.debug("received successful message: ", result);
            } else {
                logger.debug("received empty case instance message: ");
            }

        } catch (Throwable t) {
            throw new ApsSystemException("Error getting the cases instance details", t);
        }

        return casesDetails;
    }

    public JSONObject getCaseFile(KieBpmConfig config, String containerId, String caseID) throws ApsSystemException {

        JSONObject caseFile = null;
        Map<String, String> headersMap = new HashMap<>();

        String result;

        if (StringUtils.isBlank(containerId) || StringUtils.isBlank(caseID)) {
            return caseFile;
        }
        try {
            // process endpoint first
            Endpoint ep = CCDKieEndpointDictionary.create().get(API_GET_CASE_FILE).resolveParams(containerId, caseID);
            // add header
            headersMap.put(HEADER_KEY_ACCEPT, HEADER_VALUE_JSON);
            // generate client from the current configuration
            KieClient client = this.getClient(config);
            // perform query
            result = (String) new KieRequestBuilder(client)
                    .setEndpoint(ep)
                    .setHeaders(headersMap)
                    .setDebug(config.getDebug())
                    .doRequest();

            if (!result.isEmpty()) {
                caseFile = new JSONObject(result);

                logger.debug("received successful message: ", result);

            } else {
                logger.debug("received empty case file message: ");
            }

        } catch (Throwable t) {
            throw new ApsSystemException("Error getting the cases roles", t);
        }
        return caseFile;

    }

    public JSONObject getCaseComments(KieBpmConfig config, String containerId, String caseID) throws ApsSystemException {


        JSONObject json = null;
        Map<String, String> headersMap = new HashMap<>();

        String result;

        if (StringUtils.isBlank(containerId) || StringUtils.isBlank(caseID)) {
//            logger.error("container id or case id is empty "+containerId+ ""+caseID);
            return json;
        }
        try {
            // process endpoint first
            Endpoint ep = CCDKieEndpointDictionary.create().get(API_GET_COMMENTS_LIST).resolveParams(containerId, caseID);
            logger.info("endpoint "+ep.getUrl());
            // add header
            headersMap.put(HEADER_KEY_ACCEPT, HEADER_VALUE_JSON);
            headersMap.put(HEADER_KEY_CONTENT_TYPE, HEADER_VALUE_JSON);
            // generate client from the current configuration
            KieClient client = this.getClient(config);
            // perform query
            result = (String) new KieRequestBuilder(client)
                    .setEndpoint(ep)
                    .setHeaders(headersMap)
                    .setDebug(config.getDebug())
                    .doRequest();

            if (!result.isEmpty()) {
                json = new JSONObject(result);
//                commentsList = (JSONArray) json.get("comments");
                logger.info("received successful message: ", json.toString());
                logger.info("received successful message: "+json.toString());
                System.out.println("json.toString() "+json.toString());

            } else {
                logger.info("received empty case instances message: ");
            }

        } catch (Throwable t) {
            throw new ApsSystemException("Error getting the cases list", t);
        }
        return json;
    }

    public String postCaseComments(KieBpmConfig config, String containerId, String caseID, String comment) throws ApsSystemException {

        Map<String, String> headersMap = new HashMap<>();
        String json = null;

        if (StringUtils.isBlank(containerId) || StringUtils.isBlank(caseID)) {
            return json;
        }

        try {
            // process endpoint first
            Endpoint ep = CCDKieEndpointDictionary.create().get(API_POST_COMMENTS).resolveParams(containerId, caseID);
            // add header

            headersMap.put(HEADER_KEY_ACCEPT, HEADER_VALUE_JSON);
            headersMap.put(HEADER_KEY_CONTENT_TYPE, HEADER_VALUE_JSON);

            // generate client from the current configuration
            KieClient client = this.getClient(config);

            // perform query
            String result = (String) new KieRequestBuilder(client)
                    .setEndpoint(ep)
                    .setHeaders(headersMap)
                    .setPayload("\"" + comment + "\"")
                    .setDebug(config.getDebug())
                    .doRequest();

            if (!result.isEmpty()) {
//                json = new JSONObject(result);
                json = result;

                logger.debug("received successful message: ", json);
                logger.info("received successful message: "+json);
                System.out.println("json "+json);

            } else {
                logger.debug("received empty case comment message: ");
            }

        } catch (Throwable t) {
            throw new ApsSystemException("Error posting comments", t);
        }
        return json;
    }
    public String postCaseAttachmemt(KieBpmConfig config, String containerId, String caseID, String caseFile) throws ApsSystemException {

        Map<String, String> headersMap = new HashMap<>();
        String json = null;


        if (StringUtils.isBlank(containerId) || StringUtils.isBlank(caseID)) {
            return json;
        }

        try {
            // process endpoint first
            Endpoint ep = CCDKieEndpointDictionary.create().get(API_POST_CASE_ATTACH_FILE).resolveParams(containerId, caseID);
            // add header

            headersMap.put(HEADER_KEY_ACCEPT, HEADER_VALUE_JSON);
            headersMap.put(HEADER_KEY_CONTENT_TYPE, HEADER_VALUE_JSON);

            // generate client from the current configuration
            KieClient client = this.getClient(config);

            // perform query
            String result = (String) new KieRequestBuilder(client)
                    .setEndpoint(ep)
                    .setHeaders(headersMap)
                    .setPayload(caseFile)
                    .setDebug(config.getDebug())
                    .doRequest();

            if (!result.isEmpty()) {
//                json = new JSONObject(result);
                json = result;

                logger.debug("received successful message: ", json);
                logger.info("received successful message: "+json);
                System.out.println("json "+json);

            } else {
                logger.debug("received empty case attachment message: ");
            }

        } catch (Throwable t) {
            throw new ApsSystemException("Error posting attachment file ", t);
        }
        return json;
    }
    public JSONObject getCaseActivityLog(KieBpmConfig config, String containerId, String caseID) throws ApsSystemException {

        JSONObject caseData = null;
        Map<String, String> headersMap = new HashMap<>();

        String result;

        if (StringUtils.isBlank(containerId) || StringUtils.isBlank(caseID)) {
            return caseData;
        }
        try {
            // process endpoint first
            Endpoint ep = CCDKieEndpointDictionary.create().get(API_GET_ACTIVITY_LOG).resolveParams(containerId, caseID);
            // add header
            headersMap.put(HEADER_KEY_ACCEPT, HEADER_VALUE_JSON);
            // generate client from the current configuration
            KieClient client = this.getClient(config);
            // perform query
            result = (String) new KieRequestBuilder(client)
                    .setEndpoint(ep)
                    .setHeaders(headersMap)
                    .setDebug(config.getDebug())
                    .doRequest();

            if (!result.isEmpty()) {
                caseData = new JSONObject(result);

                logger.debug("received successful message: ", result);

            } else {
                logger.debug("received empty case file message: ");
            }

        } catch (Throwable t) {
            throw new ApsSystemException("Error getting the cases roles", t);
        }
        return caseData;

    }
    public String getCaseTaskId(KieBpmConfig config, String caseID, Map<String, String> parm) throws ApsSystemException {

        String caseData = "";
        Map<String, String> headersMap = new HashMap<>();

        String result;

        if (StringUtils.isBlank(caseID)) {
            return caseData;
        }
        try {
            // process endpoint first
            Endpoint ep = CCDKieEndpointDictionary.create().get(API_GET_CASE_TASK_ID).resolveParams(caseID);
            // add header
            headersMap.put(HEADER_KEY_ACCEPT, HEADER_VALUE_JSON);
            // generate client from the current configuration
            KieClient client = this.getClient(config);
            // perform query
            result = (String) new KieRequestBuilder(client)
                    .setEndpoint(ep)
                    .setHeaders(headersMap)
                    .setRequestParams(parm)
                    .setDebug(config.getDebug())
                    .doRequest();

            if (!result.isEmpty()) {
                JSONObject caseDataJS= new JSONObject(result);
                JSONArray taskSummary = (JSONArray) caseDataJS.getJSONArray("task-summary");
                JSONObject taskDetail = (JSONObject) taskSummary.get(0);
                int caseDataInt = taskDetail.getInt("task-id");
                caseData = String.valueOf(caseDataInt);

                logger.info("Task Id result "+result);

            } else {
                logger.debug("received empty case file message: ");
            }

        } catch (Throwable t) {
            throw new ApsSystemException("Error getting the cases roles", t);
        }
        return caseData;

    }
    protected KieClient getClient(KieBpmConfig config) {
        KieClient client = null;
        if (null != config) {
            KIEAuthenticationCredentials credentials = new KIEAuthenticationCredentials(config.getUsername(), config.getPassword());
            client = new KieClient();
            client.setHostname(config.getHostname());
            client.setPort(config.getPort());
            client.setSchema(config.getSchema());
            client.setWebapp(config.getWebapp());
            client.setCredentials(credentials);
            client.setTimeoutMsec(config.getTimeoutMsec());
        }
        return client;
    }

}
