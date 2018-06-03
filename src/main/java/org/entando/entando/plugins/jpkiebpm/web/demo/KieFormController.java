/*
 * The MIT License
 *
 * Copyright 2018 Entando Inc..
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
package org.entando.entando.plugins.jpkiebpm.web.demo;

import org.apache.commons.io.IOUtils;
import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.IKieFormService;
import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.model.KieProcessInstance;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class KieFormController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IKieFormService kieFormService;

    public IKieFormService getKieFormService() {
        return kieFormService;
    }

    public void setKieFormService(IKieFormService kieFormService) {
        this.kieFormService = kieFormService;
    }

    //@RestAccessControl(permission = "")
    //@RequestMapping(value = "/kiebpm/runAdditionalInfoRules/{instance}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/kiebpm/runAdditionalInfoRules/{container:.+}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseBody
    public @ResponseBody
    Map<String, Object> runAdditionalInfoRules(@PathVariable String container, HttpServletRequest request) throws IOException {
        String json = IOUtils.toString(request.getInputStream());
        logger.info("Run additional info rules request json {} ",json);
        String response = this.getKieFormService().runAdditionalInfoRules(json, container);

        JSONObject jsonObj = new JSONObject(response);
        return jsonObj.toMap();
    }

    @RequestMapping(value = "/kiebpm/startCase/{container:.+}/{instance:.+}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> executeStartCase(@PathVariable String container, @PathVariable String instance, HttpServletRequest request) throws IOException {
        String json = IOUtils.toString(request.getInputStream());
        logger.info("Start case request json {}" ,json);
        String caseId =  this.getKieFormService().executeStartCase(json, container, instance);

        Map<String, String> caseMap = new HashMap<>();
        caseMap.put("caseId", caseId.replace("\"", ""));
        JSONObject jsonObj = new JSONObject(caseMap);
        return jsonObj.toMap();
    }

    @RequestMapping(value = "/kiebpm/instances", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<KieProcessInstance> getAllProcessInstancesList() {
        return this.getKieFormService().getAllProcessInstancesList();
    }

    @RequestMapping(value = "/kiebpm/{container:.+}/cases/instances", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getAllCases(@PathVariable String container) {
        JSONObject response =  this.getKieFormService().getAllCases(container);
        return response.toMap();
    }
}
