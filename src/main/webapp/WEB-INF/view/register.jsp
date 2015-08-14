<div class="jumbotron">
  <div class="container">
    <div class="row">
      <div class="col-sm-12 col-md-offset-3 col-md-6">
	      <form method="POST" role="form" action="/register" class="form-horizontal" id="register-form">
				  <div class="form-group">
				    <label class="control-label" for="username">Email</label>
				    <input type="text" class="form-control" id="username" placeholder="harry@hogwarts.edu" name="username" value="${param.username}">
				    <span class="help-inline">${form.error.username }</span>
				  </div>
				  <div class="form-group">
				    <label class="control-label" for="password">Password</label>
				    <input type="password" class="form-control" id="password" placeholder="Password" name="password">
				    <span class="help-inline">${form.error.password }</span>
				  </div>
				  <div class="form-group">
				    <label class="control-label" for="passwordagain">Confirm Password</label>
				    <input type="password" class="form-control" id="passwordagain" placeholder="Retype Password" name="passwordagain">
				    <span class="help-inline">${form.error.passwordagain }</span>
				  </div>
				  <div class="form-group">
				    <label class="control-label" for="firstname">First Name</label>
				    <input type="text" class="form-control" id="firstname" placeholder="eg. Harry" name="firstname" value="${param.firstname}" />
				    <span class="help-inline">${form.error.firstname }</span>
				  </div>
				  <div class="form-group">
				    <label class="control-label" for="lastname">Last Name</label>
				    <input type="text" class="form-control" id="lastname" placeholder="eg. Potter" name="lastname" value="${param.lastname}" />
				    <span class="help-inline">${form.error.lastname }</span>
				  </div>
				  <div class="form-group">
				    <label class="control-label" for="sex">Gender</label>
				    <input type="radio" name="sex" value="M" id="sex1" checked="checked" /> Male
				    <input type="radio" name="sex" value="F" id="sex2" /> Female
				    <span class="help-inline">${form.error.sex }</span>
				  </div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				  <button type="submit" class="btn btn-success">Register</button>
        </form>
      </div>
    </div>
  </div>
</div>