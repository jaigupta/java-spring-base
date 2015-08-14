<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="jumbotron">
	<h2>${post.title}</h2>
	<br /> ${post.data} <br /> <br /> 
  <button class="btn btn-link btn-sm" onclick="upvote(${post.id}, this)">
    <span class="glyphicon glyphicon-thumbs-up"></span>
    <span class="upvote_count">${post.upvoteCount}</span>
  </button>
  <button class="btn btn-link btn-sm" onclick="downvote(${post.id}, this)">
    <span class="glyphicon glyphicon-thumbs-down"></span>
    <span class="downvote_count">${post.downvoteCount}</span>
  </button>
	<footer class="pull-right">~ <a href="/user/id/${post.authId}">${post.name}</a></footer>
	<hr />
  <c:forEach items="${commentList}" var="comment">
  <blockquote>
  <p><small>${comment.data }</small></p>
  <footer class="pull-right"><a href="/user/id/${comment.authId}">${comment.name }</a></footer>
  </blockquote>
  </c:forEach>

  <%-- Block for posting comments --%>
  <form method="POST" role="form" action="/post/comment/new">
    <input type="hidden" name="post_id" value="${post.id}" />
    <div class="form-group">
      <textarea class="form-control field span6" id="data"
        placeholder="What's up?" name="data" style="resize: vertical;"
        rows=2> </textarea>
      <span class="help-inline">${form.error.post_value }</span>
    </div>
   	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    
    <c:choose>
      <c:when test="${sessionScope.isAuthenticated}">
    <button type="submit" class="btn btn-success pull-right">Comment</button>
      </c:when>
      <c:otherwise>
    <button type="submit" class="btn btn-success pull-right disabled">Login to comment!</button>
      </c:otherwise>
    </c:choose>
  </form>
</div>
