<div class="jumbotron col-md-8">
  <form method="POST" role="form" action="/user/edit"
    class="form-horizontal" id="register-form">
    <div class="form-group">
      <label class="control-label" for="username">Email</label> <input
        type="text" class="form-control" id="username"
        disabled="disabled" placeholder="harry@hogwarts.edu"
        name="username" value="${username}"> <span
        class="help-inline">${form.error.username }</span>
    </div>
    <div class="form-group">
      <label class="control-label" for="firstname">First Name</label> <input
        type="text" class="form-control" id="firstname"
        placeholder="eg. Harry" name="firstname" value="${firstname}" />
      <span class="help-inline">${form.error.firstname }</span>
    </div>
    <div class="form-group">
      <label class="control-label" for="lastname">Last Name</label> <input
        type="text" class="form-control" id="lastname"
        placeholder="eg. Potter" name="lastname" value="${lastname}" />
      <span class="help-inline">${form.error.lastname }</span>
    </div>
    <div class="form-group">
      <label class="control-label" for="sex">Gender</label> <input
        type="radio" name="sex" value="M" id="sexM" /> Male <input
        type="radio" name="sex" value="F" id="sexF" /> Female <span
        class="help-inline">${form.error.sex }</span>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit" class="btn btn-success">Update</button>
  </form>
</div>
<script>
  onloadfns.push(function() {
    $("#sex${sex}").attr('checked', 'checked');
  });
</script>
