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

import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.model.KieBpmConfig;
import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.model.KieProcessInstance;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IFsiKieFormService {

    public Map<String, KieBpmConfig> getKieServerConfigurations();

    public String runAdditionalInfoRules(KieBpmConfig config, String jsonBody, String instance);

    public String executeStartCase(KieBpmConfig config, String json, String container, String instance);

    public List<KieProcessInstance> getAllProcessInstancesList(KieBpmConfig config);

    public JSONObject getCases(KieBpmConfig config, String container, String status);

    public JSONObject getAllCases(KieBpmConfig config, String container);

    public JSONObject getCaseDetail(KieBpmConfig config, String container, String caseId);

    public JSONObject getCaseFile(KieBpmConfig config, String container, String caseId);

    public JSONObject getCaseComment(KieBpmConfig config, String container, String caseId);

    public JSONObject getCaseActivityLog(KieBpmConfig config, String container, String caseId);

    public String postCaseComment(KieBpmConfig config, String container, String caseId, String input);
    
    public JSONArray getAllActiveHumanTasks(KieBpmConfig config);

    public String completeTask(KieBpmConfig config, String payload, String container, String caseId, Map<String, String> parm);

    public String getTaskDetails(KieBpmConfig config, String taskId);

    public String postCaseAttachment(KieBpmConfig config, String container, String caseId, String caseFile);

}
