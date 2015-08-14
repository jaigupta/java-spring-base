<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="post_menu.jsp" %>
<div class="jumbotron">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Your list of existing posts!</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${postList}" var="post">
				<tr onclick="document.location='/post/get/${post.id}'">
					<td>${post.title}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<style>
.post-menu {
background-color: #222;
padding: 5px 5px 5px 5px;
}
</style>