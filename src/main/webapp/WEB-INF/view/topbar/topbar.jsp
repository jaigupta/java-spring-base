<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
    <%@include file="company_logo.jsp"%>
    <c:choose>
      <c:when test="${sessionScope.isAuthenticated}">
        <%@include file="user_menu.jsp"%>
      </c:when>
      <c:otherwise>
        <%@include file="login_menu.jsp"%>
      </c:otherwise>
    </c:choose>
  </div>
</div>