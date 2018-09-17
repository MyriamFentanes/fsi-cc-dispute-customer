/*
 * The MIT License
 *
 * Copyright 2017 Entando Inc..
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.agiletec.aps.system.services.kie;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.IKieFormManager;
import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.model.KieBpmConfig;
import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.model.KieProcessInstance;
import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.model.KieTask;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author E.Santoboni
 */
public class FsiKieFormService implements IFsiKieFormService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private IKieFormManager kieFormManager;
    private CCDKieFormManager ccdKieFormManager;

    public Map<String, KieBpmConfig> getKieServerConfigurations() {

        try {
            return kieFormManager.getKieServerConfigurations();
        } catch (Exception e) {
            throw new RuntimeException("Error invoking runAdditionalInfoRules", e);
        }
    }

    @Override
    public String runAdditionalInfoRules(KieBpmConfig config, String jsonBody, String instance) {
        try {
            return this.getKieFormManager().runAdditionalInfoRules(config, jsonBody, instance);
        } catch (Exception e) {
            throw new RuntimeException("Error invoking runAdditionalInfoRules", e);
        }
    }

    @Override
    public String executeStartCase(KieBpmConfig config, String json, String container, String instance) {
        try {
            return this.getKieFormManager().executeStartCase(config, json, container, instance);
        } catch (Exception e) {
            throw new RuntimeException("Error invoking executeStartCase", e);
        }
    }

    @Override
    public List<KieProcessInstance> getAllProcessInstancesList(KieBpmConfig config) {
        try {
            return this.getKieFormManager().getAllProcessInstancesList(config, new HashMap<>());
        } catch (Exception e) {
            throw new RuntimeException("Error invoking getAllProcessInstancesList", e);
        }
    }


    @Override
    public JSONObject getAllCases(KieBpmConfig config, String containerId, String status) {

        try {
            return this.getKieFormManager().getAllCases(config, containerId, status);
        } catch (Exception e) {
            logger.error("failed to fetch cases ", e);
            throw new RuntimeException("Error invoking getAllCases", e);
        }
    }

    @Override
    public JSONObject getCaseDetail(KieBpmConfig config, String container, String caseId) {
        try {
            return this.getCcdKieFormManager().getCaseInstancesDetails(config, container, caseId);
        } catch (Exception e) {
            logger.error("failed to fetch cases ", e);
            throw new RuntimeException("Error invoking getAllCases", e);
        }
    }

    @Override
    public JSONObject getCaseFile(KieBpmConfig config, String container, String caseId) {
        try {
            return this.getCcdKieFormManager().getCaseFile(config, container, caseId);
        } catch (Exception e) {
            logger.error("failed to fetch cases ", e);
            throw new RuntimeException("Error invoking getAllCases", e);
        }
    }

    @Override
    public JSONArray getCaseComment(KieBpmConfig config, String container, String caseId) {
        try {
            return this.getCcdKieFormManager().getCaseComments(config, container, caseId);
        } catch (Exception e) {
            logger.error("failed to fetch cases ", e);
            throw new RuntimeException("Error invoking getAllCases", e);
        }
    }

    @Override
    public JSONObject postCaseComment(KieBpmConfig config, String container, String caseId, String input) {
        try {
            return this.getCcdKieFormManager().postCaseComments(config, container, caseId, input);
        } catch (Exception e) {
            logger.error("failed to fetch cases ", e);
            throw new RuntimeException("Error invoking getAllCases", e);
        }
    }

    @Override
    public JSONArray getAllActiveHumanTasks(KieBpmConfig config) {

        JSONArray humanTasksJS = new JSONArray();

        try {
            List<KieTask> humanTasks;
            Map<String, String> headersMap = new HashMap<>();

            headersMap.put("status", "Ready");
            humanTasks = this.getKieFormManager().getHumanTaskList(config, null, headersMap);

            //convert to json

            for (KieTask kieTask : humanTasks) {


                java.io.StringWriter sw = new StringWriter();

                JAXBContext jc = JAXBContext.newInstance(KieTask.class);
                Marshaller marshaller = jc.createMarshaller();
                marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
                marshaller.marshal(kieTask, sw);

                JSONObject swJS = new JSONObject(sw.toString());

                humanTasksJS.put(swJS);
            }

        } catch (Exception e) {
            logger.error("failed to fetch human tasks ", e);
            throw new RuntimeException("Error invoking getAllActiveHumanTasks", e);
        }

        return humanTasksJS;
    }

    @Override
    public String completeTask(KieBpmConfig config, String payload, String container, String taskId) {
        String kieResponse = "";
        try {

            this.getKieFormManager().startTask(config, payload, container, taskId);
            kieResponse = this.getKieFormManager().submitTask(config, payload, container, taskId);
            this.getKieFormManager().completeTask(config, payload, container, taskId);

        } catch (Exception e) {
            logger.error("failed to complete task ", e);
            throw new RuntimeException("Error invoking completeTask", e);
        }

        return kieResponse;
    }

    @Override
    public String getTaskDetails(KieBpmConfig config, String taskId) {
        JSONObject kieResponse = new JSONObject();

        try {
            kieResponse = this.getKieFormManager().getTaskDetails(config, taskId);

        } catch (Exception e) {
            logger.error("failed to complete task ", e);
            throw new RuntimeException("Error invoking completeTask", e);
        }

        String output = kieResponse.toString();

        return output;
    }


    public IKieFormManager getKieFormManager() {
        return kieFormManager;
    }

    public void setKieFormManager(IKieFormManager kieFormManager) {
        this.kieFormManager = kieFormManager;
    }

    public CCDKieFormManager getCcdKieFormManager() {
        return ccdKieFormManager;
    }
    @Autowired
    public void setCcdKieFormManager(CCDKieFormManager ccdKieFormManager) {
        this.ccdKieFormManager = ccdKieFormManager;
    }
}
