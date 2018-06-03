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

import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.model.KieProcessInstance;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

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
    public JSONObject getAllCases(String containerId) {

        try {
            this.getKieFormManager().loadFirstConfigurations();
            return this.getKieFormManager().getAllCases(containerId);
        }catch (Exception e) {
            logger.error("failed to fetch cases ",e);
            throw new RuntimeException("Error invoking getAllCases", e);
        }
    }

    public IKieFormManager getKieFormManager() {
        return kieFormManager;
    }

    public void setKieFormManager(IKieFormManager kieFormManager) {
        this.kieFormManager = kieFormManager;
    }

}
