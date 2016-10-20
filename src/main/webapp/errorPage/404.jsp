<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
    <title>树袋熊</title>
     <!-- CSS Global Compulsory -->
    <link rel="stylesheet" href="{resRoot}/assets/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="{resRoot}/assets/css/style.css">

    <!-- CSS Page Style -->    
    <link rel="stylesheet" href="{resRoot}/assets/css/pages/page_404_error1.css">

    <!-- CSS Customization -->
    <link rel="stylesheet" href="{resRoot}/assets/css/custom.css">
</head>
<body>
	<div class="container">
    <!--Error Block-->
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="error-v2">
                <span class="error-v2-title">404</span>
                <span>That’s an error!</span>
                <p>The requested URL was not found on this server. <br> That’s all we know.</p>
            </div>
        </div>
    </div>
    <!--End Error Block-->
</div><!--/container-->
<!--=== End Content Part ===-->

<!-- JS Global Compulsory -->           
<script type="text/javascript" src="{resRoot}/assets/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript" src="{resRoot}/assets/plugins/jquery/jquery-migrate.min.js"></script>
<script type="text/javascript" src="{resRoot}/assets/plugins/bootstrap/js/bootstrap.min.js"></script> 
<!-- JS Implementing Plugins -->           
<script type="text/javascript" src="{resRoot}/assets/plugins/back-to-top.js"></script>
<script type="text/javascript" src="{resRoot}/assets/plugins/backstretch/jquery.backstretch.min.js"></script>
<!-- JS Customization -->
<script type="text/javascript" src="{resRoot}/assets/js/custom.js"></script>
<!-- JS Page Level -->           
<script type="text/javascript" src="{resRoot}/assets/js/app.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
        App.init();
        });
</script>
<script type="text/javascript">
    $.backstretch([
      "${resRoot}/assets/img/bg/2.jpg",
      "${resRoot}/assets/img/bg/8.jpg",
      ], {
        fade: 1000,
        duration: 7000
    });
</script>
<!--[if lt IE 9]>
    <script src="${resRoot}/assets/plugins/respond.js"></script>
    <script src="${resRoot}/assets/plugins/html5shiv.js"></script>
    <script src="${resRoot}/assets/plugins/placeholder-IE-fixes.js"></script>
<![endif]-->

</body>
</html>