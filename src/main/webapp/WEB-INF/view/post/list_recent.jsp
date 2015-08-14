<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="post-menu">
  <div class="row">
    <div class="col-md-8">
      <button class="btn btn-link btn-sm" onclick="window.location.href='/post/new'">
        <span class="glyphicon glyphicon-edit"></span> New
      </button>
      <button class="btn btn-link btn-sm" onclick="window.location.href='/post/starred'">
        <span class="glyphicon glyphicon-star"></span> Starred
      </button>
      <button class="btn btn-link btn-sm" onclick="window.location.href='/post/list/my'">
        <span class="glyphicon glyphicon-user"></span> My Posts
      </button>
      <button class="btn btn-link btn-sm" onclick="window.location.href='/post/list/recent'">
        <span class="glyphicon glyphicon-globe"></span> Recent Posts
      </button>
    </div>
    <div class="col-md-4">
      <div class="input-group input-group-sm pull-right">
        <input type="text" class="form-control" placeholder="Search a post, enter keywords"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span>
      </div>
    </div>
  </div>
</div>
<div class="jumbotron">
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Posts from the Globe!</th>
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