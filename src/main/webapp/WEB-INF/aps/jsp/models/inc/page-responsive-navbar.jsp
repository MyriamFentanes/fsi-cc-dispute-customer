<%@ taglib prefix="wp" uri="/aps-core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
@media (max-width: 1050px) {
    .fsi-header .navbar-header {
      width: 100%;
      min-height: initial;
      position: static!important;
   }
   .fsi-header .collapse {
       display: none !important;
   }
   .fsi-header .collapse.in {
       display: block !important;
   }
   .fsi-header .navbar-toggle  {
       display: block !important;
   }
   .fsi-header .navbar-nav>li {
      float: none;
   }
   .fsi-header .navbar-right {
      float: left !important;
      margin-bottom: 20px !important;
   }
   .fsi-header .navbar-collapse {
      border-top: 1px solid #e7e7e7;
   }
}

.fsi-header .navbar-header {
    min-height: 120px;
    position: absolute;
}

.fsi-header .navbar-right {
   margin-right: 20px !important;
}
.fsi-header .navbar-nav>li>a {
   padding: 0;
   color: #fff;
}

.fsi-header .navbar-toggle,
.fsi-header .navbar-toggle:hover,
.fsi-header .navbar-toggle:focus {
    background: transparent;
    height: 50px;
    margin: 20px;
}
.fsi-header .navbar-toggle .icon-bar{
    background-color: #fff;
}

.fsi-header {
    font-family: "Open Sans", sans-serif;
    font-size: 24px;
    background: #002235;
    color: #ffffff;
    min-height: 120px;
    margin-bottom: 0;
}

.fsi-header .logo-names {
    margin-top: 26px;
    width: 330px;
    margin-left: 30px;
    display: flex;
    align-items: center;
}

.fsi-header .fa.fa-shield {
    font-size: 40px;
}

.fsi-header.logo-names b {
    font-weight: 600;
}
.fsi-header.logo-names span {
    font-weight: 300;
}
.logo-menu-pages {
    height: 22px;
    margin: 37px 5px 0 40px;
    font-size: 16px;
    font-weight: 300;
    font-family: "Open Sans", sans-serif;
    line-height: 22px;
    text-transform: uppercase;
}
</style>
<nav class="navbar navbar-default fsi-header">
    <div class="">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="logo-names">
                <wp:show frame="0" />
                <img alt="image" class="" src="/fsi-credit-card-dispute-customer/resources/static/img/shield-logo.png">&nbsp;
                <b>FORTRESS -</b>&nbsp;
                <span>Bank &amp; Loan</span>
            </div>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="logo-menu-pages">
                    <wp:show frame="1" />
                </li>
                <li class="logo-menu-pages">
                    <wp:show frame="2" />
                </li>
                <li class="logo-menu-pages">
                    <wp:show frame="3" />
                </li>
                <li class="logo-menu-pages">
                    <wp:show frame="4" />
                </li>
                <li class="logo-menu-pages">
                    <wp:show frame="5" />
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
