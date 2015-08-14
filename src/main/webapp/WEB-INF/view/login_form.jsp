<form method="POST" role="form" action="/login">
	<div class="form-group">
		<input type="text" placeholder="Username" name="username" class="form-control" path="username" />
	</div>
	<div class="form-group">
		<input type="password" placeholder="password" name="password" class="form-control" path="password" />
	</div>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<button type="submit" class="btn btn-success">Sign in</button>
	<span class="info small"><a href="/register">New? Register here!</a></span>
</form>
