<div class="navbar-collapse collapse">
  <div class="navbar-right">    
    <div style="margin-top:15px; margin-left:15px;" class="inline text-info">
      Welcome, <strong class="text-primary">${sessionScope.firstName}</strong>
      <a href="/login/invalidate" class="btn btn-link btn-xs inline">
        <span class="glyphicon glyphicon-log-out" style="vertical-align:middle"></span>
      </a>
    </div>
  </div>
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