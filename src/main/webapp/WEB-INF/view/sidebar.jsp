<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty view_sidebar_disable}">
	<div id="sidebar-wrapper">
		<ul class="sidebar-nav">
		  <li class="sidebar-brand"><a href="/user/me">My Profile</a>
		  </li>
		  <li><a href="/home">Dashboard</a>
		  </li>
		  <li><a href="/post/list/recent">Posts</a>
		  </li>
		  <li><a href="#">Overview</a>
		  </li>
		  <li><a href="#">Events</a>
		  </li>
		  <li><a href="/about">About</a>
		  </li>
		  <li><a href="/services">Services</a>
		  </li>
		  <li><a href="/contact">Contact</a>
		  </li>
		</ul>
	</div>
	<script>
  loadOnStart(function() {
    $("#sidebar-menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("active");
    });
    $("#sidebar-menu-toggle").removeClass("hidden").addClass("inline");
  });
  </script>
  <link href="/css/simple-sidebar.css" rel="stylesheet">
</c:if>