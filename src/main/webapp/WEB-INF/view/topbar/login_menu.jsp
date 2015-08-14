<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar-collapse collapse">
  <form class="navbar-form navbar-right" role="form" method="POST" action="/login">
    <div class="form-group">
      <input type="text" name="username" placeholder="Email" class="form-control" >
    </div>
    <div class="form-group">
      <input type="password" name="password" placeholder="Password" class="form-control">
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit" class="btn btn-success">Sign in</button>
  </form>
  <div class="navbar-form navbar-right" style="width:200px">
      <div class="input-group input-group-sm pull-right">
        <form name="post_search_form" action="/gqsearch" method="GET">
          <input type="text" name="gq" class="form-control" placeholder="Search a post, enter keywords"/>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        <span class="input-group-addon" onclick="document.post_search_form.submit()">
          <span class="glyphicon glyphicon-search"></span>
        </span>
      </div>
  </div>
</div>
