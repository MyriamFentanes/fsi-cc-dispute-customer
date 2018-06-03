<%@ taglib prefix="wp" uri="/aps-core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>
            <wp:currentPage param="title" /> - <wp:i18n key="PORTAL_TITLE" />
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="icon" href="<wp:info key="systemParam" paramName="applicationBaseURL" />
              favicon.png" type="image/png" />
              <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
              <!--[if lt IE 9]>
              <script src="<wp:resourceURL />static/js/entando-misc-html5-essentials/html5shiv.js"></script>
              <![endif]-->
              <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600" rel="stylesheet">

        <c:import url="/WEB-INF/aps/jsp/models/inc/content_inline_editing.jsp" />
        <c:import url="/WEB-INF/aps/jsp/models/inc/header-inclusions.jsp" />
        <c:import url="/WEB-INF/aps/jsp/models/inc/flash_toast.jsp" />

    </head>
    <body>
        <div id="wrapper">
            <c:import url="/WEB-INF/aps/jsp/models/inc/page-responsive-navbar.jsp" />
            <div class="row header-custom">
                 <div class="row banner-main">
                    <div class="col-md-12">
                        <wp:show frame="6" />
                    </div>
                 </div>

                <div class="container">
                    <div class="row">
                      <div class="col-md-6">
                        <wp:show frame="7" />
                      </div>
                      <div class="col-md-6">
                         <wp:show frame="8" />
                      </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <wp:show frame="9" />
                    </div>
                    <div class="col-md-6">
                        <wp:show frame="10" />
                    </div>
                </div>

                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <wp:show frame="11" />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <wp:show frame="12" />
                    </div>
                </div>

            </div>
    </body>
</html>
