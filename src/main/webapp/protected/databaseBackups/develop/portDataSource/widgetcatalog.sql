INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('messages_system','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">System Messages</property>
<property key="it">Messaggi di Sistema</property>
</properties>',NULL,NULL,NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('login_form','<?xml version="1.0" encoding="UTF-8"?>
<properties><property key="en">Login Form</property><property key="it">Form di Login</property></properties>
',NULL,NULL,NULL,NULL,1,'free');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('formAction','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Internal Servlet</property>
<property key="it">Invocazione di una Servlet Interna</property>
</properties>','<config>
	<parameter name="actionPath">
		Path to an action or to a JSP. You must prepend ''/ExtStr2'' to any Struts2 action path
	</parameter>
	<action name="configSimpleParameter"/>
</config>',NULL,NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('entando_apis','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">APIs</property>
<property key="it">APIs</property>
</properties>
',NULL,NULL,'formAction','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="actionPath">/ExtStr2/do/Front/Api/Resource/list.action</property>
</properties>
',1,'free');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('userprofile_editCurrentUser','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Edit Current User</property>
<property key="it">Edita Utente Corrente</property>
</properties>',NULL,NULL,'formAction','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="actionPath">/ExtStr2/do/Front/CurrentUser/edit.action</property>
</properties>',1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('userprofile_editCurrentUser_password','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Edit Current User Password</property>
<property key="it">Edita Password Utente Corrente</property>
</properties>',NULL,NULL,'formAction','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="actionPath">/ExtStr2/do/Front/CurrentUser/editPassword.action</property>
</properties>',1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('userprofile_editCurrentUser_profile','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Edit Current User Profile</property>
<property key="it">Edita Profilo Utente Corrente</property>
</properties>',NULL,NULL,'formAction','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="actionPath">/ExtStr2/do/Front/CurrentUser/Profile/edit.action</property>
</properties>',1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('content_viewer','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Contents - Publish a Content</property>
<property key="it">Contenuti - Pubblica un Contenuto</property>
</properties>','<config>
	<parameter name="contentId">Content ID</parameter>
	<parameter name="modelId">Content Model ID</parameter>
	<action name="viewerConfig"/>
</config>','jacms',NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('search_result','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Search - Search Result</property>
<property key="it">Ricerca - Risultati della Ricerca</property>
</properties>',NULL,'jacms',NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('content_viewer_list','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Contents - Publish a List of Contents</property>
<property key="it">Contenuti - Pubblica una Lista di Contenuti</property>
</properties>','<config>
	<parameter name="contentType">Content Type (mandatory)</parameter>
	<parameter name="modelId">Content Model</parameter>
	<parameter name="userFilters">Front-End user filter options</parameter>
	<parameter name="category">Content Category **deprecated**</parameter>
	<parameter name="categories">Content Category codes (comma separeted)</parameter>
	<parameter name="orClauseCategoryFilter" />
	<parameter name="maxElemForItem">Contents for each page</parameter>
	<parameter name="maxElements">Number of contents</parameter>
	<parameter name="filters" />
	<parameter name="title_{lang}">Widget Title in lang {lang}</parameter>
	<parameter name="pageLink">The code of the Page to link</parameter>
	<parameter name="linkDescr_{lang}">Link description in lang {lang}</parameter>
	<action name="listViewerConfig"/>
</config>','jacms',NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('row_content_viewer_list','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Contents - Publish Contents</property>
<property key="it">Contenuti - Pubblica Contenuti</property>
</properties>','<config>
	<parameter name="contents">Contents to Publish (mandatory)</parameter>
	<parameter name="maxElemForItem">Contents for each page</parameter>
	<parameter name="title_{lang}">Widget Title in lang {lang}</parameter>
	<parameter name="pageLink">The code of the Page to link</parameter>
	<parameter name="linkDescr_{lang}">Link description in lang {lang}</parameter>
	<action name="rowListViewerConfig" />
</config>','jacms',NULL,NULL,1,'free');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('bpm-datatype-form','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">BPM-Form by DataType</property>
<property key="it">BPM-Form by DataType</property>
</properties>','<config>
	<parameter name="dataTypeCode">Data Type Code</parameter>
	<parameter name="dataUxId">Data Ux ID</parameter>
	<parameter name="widgetInfoId">Widget Info ID</parameter>
	<action name="jpkiebpmBpmFormWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('bpm-datatable-task-list','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">BPM-Datatable Task List</property>
<property key="it">BPM-Datatable Task List</property>
</properties>','<config>
	<parameter name="widgetInfoId">WidgetInfoID</parameter>
	<action name="jpkiebpmBpmDatatableWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('bpm-datatable-process-list','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">BPM-Process list</property>
<property key="it">BPM-Lista processi</property>
</properties>','<config>
	<parameter name="widgetInfoId">WidgetInfoID</parameter>
	<action name="jpkiebpmBpmProcessDatatableWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('bpm-case-progress-status','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">BPM-Case progress status</property>
<property key="it">BPM-Case stato del processo</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndMilestonesData">frontEndMilestonesData</parameter>
	<action name="jpkiebpmBpmCaseProgressWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('bpm-case-instance-selector','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">BPM-Case instance selector</property>
<property key="it">Selettore di istanza Case BPM</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndCaseData">frontEndCaseData</parameter>
	<action name="jpkiebpmBpmCaseInstanceSelectorWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('bpm-case-comments','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">BPM-Case comments</property>
<property key="it">Commenti del caso BPM</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndCaseData">frontEndCaseData</parameter>
	<action name="jpkiebpmBpmCaseInstanceCommentsWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('bpm-case-details','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">BPM-Case details</property>
<property key="it">Dettagli caso BPM</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndCaseData">frontEndCaseData</parameter>
	<action name="jpkiebpmBpmCaseInstanceDetailsWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('bpm-case-actions','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">BPM-Case actions</property>
<property key="it">Azioni caso BPM</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndCaseData">frontEndCaseData</parameter>
	<action name="jpkiebpmBpmCaseInstanceActionsWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('bpm-case-roles','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">BPM-Case roles</property>
<property key="it">Ruoli caso BPM</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndCaseData">frontEndCaseData</parameter>
	<action name="jpkiebpmBpmCaseInstanceRolesWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('bpm-case-chart','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">BPM-Case chart</property>
<property key="it">BPM-Case chart</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndCaseData">frontEndCaseData</parameter>
	<action name="jpkiebpmBpmCaseInstanceChartWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('bpm-process-diagram','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">BPM-Process diagram</property>
<property key="it">Diagramma di processo BPM</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndCaseData">frontEndCaseData</parameter>
	<action name="jpkiebpmBpmProcessDiagramWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('bpm-case-file','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">BPM-Case file</property>
<property key="it">File caso BPM</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndCaseData">frontEndCaseData</parameter>
	<action name="jpkiebpmBpmCaseInstanceFileWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('fsi-credit-dispute-customer','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">fsi-credit-dispute-customer</property>
<property key="it">fsi-credit-dispute-customer</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
</config>','creditdisputecustomer',NULL,NULL,1,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('fsi','<?xml version="1.0" encoding="UTF-8"?>
<properties><property key="en">fsi-credit-card-dispute-customer</property><property key="it">fsi-credit-card-dispute-customer</property></properties>
',NULL,NULL,NULL,NULL,0,'free');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('FSI_Footer','<?xml version="1.0" encoding="UTF-8"?>
<properties><property key="en">FSI Footer</property><property key="it">FSI Footer</property></properties>
',NULL,NULL,NULL,NULL,0,'free');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('fsi-greeting','<?xml version="1.0" encoding="UTF-8"?>
<properties><property key="en">fsi-greeting</property><property key="it">fsi-greeting</property></properties>
',NULL,NULL,NULL,NULL,0,'free');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('home_page_cards','<?xml version="1.0" encoding="UTF-8"?>
<properties><property key="en">home_page_cards</property><property key="it">home_page_cards</property></properties>
',NULL,NULL,NULL,NULL,0,'free');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('fsi-header','<?xml version="1.0" encoding="UTF-8"?>
<properties><property key="en">FSI Header</property><property key="it">FSI Header</property></properties>
',NULL,NULL,NULL,NULL,0,'free');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('fsi-navbar','<?xml version="1.0" encoding="UTF-8"?>
<properties><property key="en">FSI Navbar</property><property key="it">FSI Navbar</property></properties>
',NULL,NULL,NULL,NULL,0,'free');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('fsi-case-details','<?xml version="1.0" encoding="UTF-8"?>
<properties><property key="en">FSI CASE DETAILS</property><property key="it">FSI CASE DETAILS</property></properties>
',NULL,NULL,NULL,NULL,0,'free');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('fsi-transaction-history','<?xml version="1.0" encoding="UTF-8"?>
<properties><property key="en">FSI Transaction History</property><property key="it">FSI Transaction History</property></properties>
',NULL,NULL,NULL,NULL,0,'free');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('fsi-workflow','<?xml version="1.0" encoding="UTF-8"?>
<properties><property key="en">FSI Workflow</property><property key="it">FSI Workflow</property></properties>
',NULL,NULL,NULL,NULL,0,'free');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('fsi-case-instance-info','<?xml version="1.0" encoding="UTF-8"?>
<properties><property key="en">FSI Case Instance Info</property><property key="it">FSI Case Instance Info</property></properties>
',NULL,NULL,NULL,NULL,0,'free');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('fsi-ccd-customer-js-activity-log','<?xml version="1.0" encoding="UTF-8"?>
<properties><property key="en">FSI CASE ACTIVITY LOG</property><property key="it">FSI CASE ACTIVITY LOG</property></properties>
',NULL,NULL,NULL,NULL,0,'free');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('fsi-login','<?xml version="1.0" encoding="UTF-8"?>
<properties><property key="en">FSI LOGIN</property><property key="it">FSI LOGIN</property></properties>
',NULL,NULL,NULL,NULL,0,'free');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup) VALUES ('fsi-login-header','<?xml version="1.0" encoding="UTF-8"?>
<properties><property key="en">FSI Login Header</property><property key="it">FSI Login Header</property></properties>
',NULL,NULL,NULL,NULL,0,'free');
