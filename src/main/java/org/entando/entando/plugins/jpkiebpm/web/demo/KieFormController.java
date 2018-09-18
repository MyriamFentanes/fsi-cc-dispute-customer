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

import com.agiletec.aps.system.common.entity.model.attribute.AttributeInterface;
import com.agiletec.aps.system.services.user.UserDetails;
import org.apache.commons.io.IOUtils;
import org.entando.entando.aps.system.services.userprofile.model.IUserProfile;
import com.agiletec.aps.system.services.kie.IFsiKieFormService;
import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.model.KieBpmConfig;
import org.entando.entando.plugins.jpkiebpm.aps.system.services.kie.model.KieProcessInstance;
import org.entando.entando.web.common.annotation.RestAccessControl;
import org.json.JSONArray;
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
@SessionAttributes("user")
public class KieFormController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IFsiKieFormService kieFormService;

    public IFsiKieFormService getKieFormService() {
        return kieFormService;
    }

    public void setKieFormService(IFsiKieFormService kieFormService) {
        this.kieFormService = kieFormService;
    }

    @RestAccessControl(permission = "ignore")
    //@RequestMapping(value = "/kiebpm/runAdditionalInfoRules/{instance}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/kiebpm/runAdditionalInfoRules/{container:.+}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Map<String, Object> runAdditionalInfoRules(@PathVariable String container, HttpServletRequest request) throws IOException {
        String json = IOUtils.toString(request.getInputStream());
        logger.info("Run additional info rules request json {} ", json);

        //FIXME The configuration ID used for a partiuclar widget needs to be round tripped so that the right one
        //can be selected when there are many. For today pick the first one in the interest of moving he demo forward
        Map<String, KieBpmConfig> configs = this.kieFormService.getKieServerConfigurations();
        KieBpmConfig config = configs.values().iterator().next();

        String response = this.getKieFormService().runAdditionalInfoRules(config, json, container);

        logger.info("response ", response);
        JSONObject jsonObj = new JSONObject(response);
        return jsonObj.toMap();
    }

    @RestAccessControl(permission = "ignore")
    @RequestMapping(value = "/kiebpm/startCase/{container:.+}/{instance:.+}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> executeStartCase(@PathVariable String container, @PathVariable String instance, HttpServletRequest request) throws IOException {
        String json = IOUtils.toString(request.getInputStream());
        logger.info("Start case request json {}", json);

        //FIXME The configuration ID used for a partiuclar widget needs to be round tripped so that the right one
        //can be selected when there are many. For today pick the first one in the interest of moving he demo forward
        Map<String, KieBpmConfig> configs = this.kieFormService.getKieServerConfigurations();
        KieBpmConfig config = configs.values().iterator().next();

        String caseId = this.getKieFormService().executeStartCase(config, json, container, instance);

        Map<String, String> caseMap = new HashMap<>();
        caseMap.put("caseId", caseId.replace("\"", ""));
        JSONObject jsonObj = new JSONObject(caseMap);
        return jsonObj.toMap();
    }

    @RestAccessControl(permission = "ignore")
    @RequestMapping(value = "/kiebpm/instances", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<KieProcessInstance> getAllProcessInstancesList() {

        //FIXME The configuration ID used for a partiuclar widget needs to be round tripped so that the right one
        //can be selected when there are many. For today pick the first one in the interest of moving he demo forward
        Map<String, KieBpmConfig> configs = this.kieFormService.getKieServerConfigurations();
        KieBpmConfig config = configs.values().iterator().next();

        return this.getKieFormService().getAllProcessInstancesList(config);
    }

    @RestAccessControl(permission = "ignore")
    @RequestMapping(value = "/kiebpm/{container:.+}/cases/instances", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getAllCases(@PathVariable String container, @RequestParam String status) {

        logger.info("Get all Cases request");

        //FIXME The configuration ID used for a partiuclar widget needs to be round tripped so that the right one
        //can be selected when there are many. For today pick the first one in the interest of moving he demo forward
        Map<String, KieBpmConfig> configs = this.kieFormService.getKieServerConfigurations();
        KieBpmConfig config = configs.values().iterator().next();
        JSONObject response = this.getKieFormService().getAllCases(config, container, status);
        return response.toMap();
    }

    @RestAccessControl(permission = "ignore")
    @RequestMapping(value = "/kiebpm/{container:.+}/cases/instances/{caseId:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getCaseDetail(@PathVariable String container, @PathVariable String caseId) {

        logger.info("Get Case details request");

        //FIXME The configuration ID used for a partiuclar widget needs to be round tripped so that the right one
        //can be selected when there are many. For today pick the first one in the interest of moving he demo forward
        Map<String, KieBpmConfig> configs = this.kieFormService.getKieServerConfigurations();
        KieBpmConfig config = configs.values().iterator().next();

        JSONObject response = this.getKieFormService().getCaseDetail(config, container, caseId);
        return response.toMap();
    }

    @RestAccessControl(permission = "ignore")
    @RequestMapping(value = "/kiebpm/{container:.+}/cases/instances/{caseId:.+}/file", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getCaseFile(@PathVariable String container, @PathVariable String caseId) {

        logger.info("Get Case file request");

        //FIXME The configuration ID used for a partiuclar widget needs to be round tripped so that the right one
        //can be selected when there are many. For today pick the first one in the interest of moving he demo forward
        Map<String, KieBpmConfig> configs = this.kieFormService.getKieServerConfigurations();
        KieBpmConfig config = configs.values().iterator().next();

        JSONObject response = this.getKieFormService().getCaseFile(config, container, caseId);
        return response.toMap();
    }

    @RestAccessControl(permission = "ignore")
    @RequestMapping(value = "/kiebpm/{container:.+}/cases/instances/{caseId:.+}/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  Map<String, Object>  getCaseComment(@PathVariable String container, @PathVariable String caseId) {

        logger.info("Get Case comments request");

        //FIXME The configuration ID used for a partiuclar widget needs to be round tripped so that the right one
        //can be selected when there are many. For today pick the first one in the interest of moving he demo forward
        Map<String, KieBpmConfig> configs = this.kieFormService.getKieServerConfigurations();
        KieBpmConfig config = configs.values().iterator().next();

        JSONObject response = this.getKieFormService().getCaseComment(config, container, caseId);
//        logger.info("response"+ response.toString());
        return response.toMap();
    }

    @RestAccessControl(permission = "ignore")
    @RequestMapping(value = "/kiebpm/{container:.+}/cases/instances/{caseId:.+}/comments", method = RequestMethod.POST)
    public String postCaseComment(@PathVariable String container, @PathVariable String caseId, HttpServletRequest request) throws IOException {

        logger.info("Post Case comments request");

        String comment = IOUtils.toString(request.getInputStream());
        logger.info("Complete task request json {}", comment);

        //FIXME The configuration ID used for a partiuclar widget needs to be round tripped so that the right one
        //can be selected when there are many. For today pick the first one in the interest of moving he demo forward
        Map<String, KieBpmConfig> configs = this.kieFormService.getKieServerConfigurations();
        KieBpmConfig config = configs.values().iterator().next();

        String response = this.getKieFormService().postCaseComment(config, container, caseId, comment);
        return response;
    }

    @RestAccessControl(permission = "ignore")
    @RequestMapping(value = "/kiebpm/{container:.+}/cases/instances/{caseId:.+}/activitylog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  Map<String, Object>  getActivityLog(@PathVariable String container, @PathVariable String caseId) {

        logger.info("Get Case activity log request");

        //FIXME The configuration ID used for a partiuclar widget needs to be round tripped so that the right one
        //can be selected when there are many. For today pick the first one in the interest of moving he demo forward
        Map<String, KieBpmConfig> configs = this.kieFormService.getKieServerConfigurations();
        KieBpmConfig config = configs.values().iterator().next();

        JSONObject response = this.getKieFormService().getCaseActivityLog(config, container, caseId);
        return response.toMap();
    }

    @RestAccessControl(permission = "ignore")
    @RequestMapping(value = "/kiebpm/tasks", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String getAllTasks() {

        //FIXME The configuration ID used for a partiuclar widget needs to be round tripped so that the right one
        //can be selected when there are many. For today pick the first one in the interest of moving he demo forward
        Map<String, KieBpmConfig> configs = this.kieFormService.getKieServerConfigurations();
        KieBpmConfig config = configs.values().iterator().next();

        JSONArray response = this.getKieFormService().getAllActiveHumanTasks(config);
        return response.toString();
    }

    @RestAccessControl(permission = "ignore")
    @RequestMapping(value = "/kiebpm/completetask/{container:.+}/{taskid:.+}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String completeTask(@PathVariable String container, @PathVariable String taskid, HttpServletRequest request) throws IOException {

        String json = IOUtils.toString(request.getInputStream());
        logger.info("Complete task request json {}", json);

        //FIXME The configuration ID used for a partiuclar widget needs to be round tripped so that the right one
        //can be selected when there are many. For today pick the first one in the interest of moving he demo forward
        Map<String, KieBpmConfig> configs = this.kieFormService.getKieServerConfigurations();
        KieBpmConfig config = configs.values().iterator().next();

        String response = this.getKieFormService().completeTask(config, json, container, taskid);

        return response;
    }

    @RestAccessControl(permission = "ignore")
    @RequestMapping(value = "/kiebpm/taskdetail/{taskid:.+}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String getTaskDetail(@PathVariable String taskid) {

        //FIXME The configuration ID used for a partiuclar widget needs to be round tripped so that the right one
        //can be selected when there are many. For today pick the first one in the interest of moving he demo forward
        Map<String, KieBpmConfig> configs = this.kieFormService.getKieServerConfigurations();
        KieBpmConfig config = configs.values().iterator().next();

        String response = this.getKieFormService().getTaskDetails(config, taskid);
        return response;
    }

    @RestAccessControl(permission = "ignore")
    @RequestMapping(value = "/kiebpm/userprofile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllTasks(@ModelAttribute("user") UserDetails user) {


        final String PROFILE_TYPE_CODE = "CCD";
        final String PROFILE_ATTRIBUTE_CREDIT_MERIT = "test";

//        return user.getUsername();
//         get the profile
        IUserProfile profile = (IUserProfile) user.getProfile();
        // fetch all the attributes for an easy management
        Map<String, AttributeInterface> attributes = profile.getAttributeMap();

        // perform operations only on the expected profile type
        if (profile.getTypeCode().equals(PROFILE_TYPE_CODE)) {

            JSONObject userProfile = new JSONObject();

            for (String key: attributes.keySet()) {

                userProfile.put(key, attributes.get(key).getValue().toString());

            }

            return userProfile.toString();

        } else {
            logger.warn("unexpected profile type '{}' for user",
                    profile.getTypeCode());

            return "unexpected profile type '{}' for user " + profile.getTypeCode();
        }

    }
}
