<div class="jumbotron">
  <div class="container">
    <div class="row">
      <div class="col-sm-12 col-md-offset-2 col-md-8">
        <form method="POST" role="form" action="/todo/new" class="form-horizontal" id="register-form">
          <div class="form-group">
            <label class="control-label" for="author">Author</label>
            <input type="text" class="form-control" disabled="disabled" id="author" placeholder="Author" name="author" value="${sessionScope.firstName} (${sessionScope.username})">
            <span class="help-inline">${form.error.password }</span>
          </div>
          <div class="form-group">
            <label class="control-label" for="todo_title">Title</label>
            <input type="text" class="form-control" id="todo_title" placeholder="Title" name="todo_title" value="${param.todo_title}">
            <span class="help-inline">${form.error.todo_title }</span>
          </div>
          <div class="form-group">
            <textarea class="form-control field span6" id="todo_value" placeholder="What's up?" name="todo_value" style="resize:vertical;" rows=12>${param.todo_value } </textarea>
            <span class="help-inline">${form.error.todo_value }</span>
          </div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <button type="submit" class="btn btn-success">Post it!</button>
        </form>
      </div>
    </div>
  </div>
</div>
