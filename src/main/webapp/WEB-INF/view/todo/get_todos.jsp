<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="jumbotron">
	<h2>${post.title}</h2>
	<br /> ${post.data} <br /> <br /> 
  <button class="btn btn-link btn-sm" onclick="getcall('/post/upvote/${post.id}')">
    <span class="glyphicon glyphicon-thumbs-up"></span> 0
  </button>
  <button class="btn btn-link btn-sm" onclick="getcall('/post/downvote/${post.id}')">
    <span class="glyphicon glyphicon-thumbs-down"></span> 0
  </button>
	<footer class="pull-right">~ <a href="/user/id/${post.authId}">${post.name}</a></footer>
	<hr />
  <c:forEach items="${docList}" var="doc">
  <blockquote>
  <p><small><c:out value="${doc.title}" /></small></p>
  <p><small>${doc.description}</small></p>
  </blockquote>
  </c:forEach>
</div>
