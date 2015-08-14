<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon"
  href="http://getbootstrap.com/assets/ico/favicon.ico">

<title>Research Drill</title>

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/jumbotron.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
  <script>
    var onloadfns=[];
    function loadOnStart(f) {
    	onloadfns.push(f);
    }
    </script>
  <%@include file="topbar/topbar.jsp"%>
  <div id="wrapper">
    <!-- Sidebar -->
    <%@include file="sidebar.jsp"%>

    <!-- Page content -->
    <div id="page-content-wrapper">
      <c:choose>
        <c:when test="${empty render_page}">
          <%@include file="landing_content.jsp"%>
        </c:when>
        <c:otherwise>
          <jsp:include page="${render_page}.jsp" flush="true" />
        </c:otherwise>
      </c:choose>
    </div>

  </div>
  <!-- Bootstrap core JavaScript
    ================================================== -->
  <!-- Placed at the end of the document so the pages load faster -->
  <script src="/js/jquery.min.js"></script>
  <script src="/js/bootstrap.min.js"></script>
  <script src="/js/core_djw.js"></script>

  <script>
    for(var i=0; i<onloadfns.length; i++) {
    	onloadfns[i]();
    }
    </script>

</body>
</html>