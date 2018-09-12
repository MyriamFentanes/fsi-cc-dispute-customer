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
package org.entando.entando.plugins.jpkiebpm.aps.system.services.kie;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.model.KieProcessInstance;
import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.model.KieTask;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author E.Santoboni
 */
public class KieFormService implements IKieFormService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private IKieFormManager kieFormManager;

    @Override
    public String runAdditionalInfoRules(String jsonBody, String instance) {
        try {
            return this.getKieFormManager().runAdditionalInfoRules(jsonBody, instance);
        } catch (Exception e) {
            throw new RuntimeException("Error invoking runAdditionalInfoRules", e);
        }
    }

    @Override
    public String executeStartCase(String json, String container, String instance) {
        try {
            return this.getKieFormManager().executeStartCase(json, container, instance);
        } catch (Exception e) {
            throw new RuntimeException("Error invoking executeStartCase", e);
        }
    }

    @Override
    public List<KieProcessInstance> getAllProcessInstancesList() {
        try {
            this.getKieFormManager().loadFirstConfigurations();
            return this.getKieFormManager().getAllProcessInstancesList(new HashMap<>());
        } catch (Exception e) {
            throw new RuntimeException("Error invoking getAllProcessInstancesList", e);
        }
    }


    @Override
    public JSONObject getAllCases(String containerId, String status) {

        try {
            this.getKieFormManager().loadFirstConfigurations();
            return this.getKieFormManager().getAllCases(containerId, status);
        } catch (Exception e) {
            logger.error("failed to fetch cases ", e);
            throw new RuntimeException("Error invoking getAllCases", e);
        }
    }

    @Override
    public JSONArray getAllActiveHumanTasks() {

        JSONArray humanTasksJS = new JSONArray();

        try {
            List<KieTask> humanTasks;
            Map<String, String> headersMap = new HashMap<>();

            headersMap.put("status", "Ready");

            this.getKieFormManager().loadFirstConfigurations();

            humanTasks = this.getKieFormManager().getHumanTaskList(null, headersMap);

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
    public String completeTask(String payload, String container, String taskId) {
        String kieResponse = "";
        try {
            this.getKieFormManager().loadFirstConfigurations();

            this.getKieFormManager().startTask(payload, container, taskId);
            kieResponse = this.getKieFormManager().submitTask(payload, container, taskId);
            this.getKieFormManager().completeTask(payload, container, taskId);

        } catch (Exception e) {
            logger.error("failed to complete task ", e);
            throw new RuntimeException("Error invoking completeTask", e);
        }

        return kieResponse;
    }

    @Override
    public String getTaskDetails(String taskId) {
        JSONObject kieResponse = new JSONObject();

        try {
            this.getKieFormManager().loadFirstConfigurations();

            kieResponse = this.getKieFormManager().getTaskDetails(taskId);

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

}
