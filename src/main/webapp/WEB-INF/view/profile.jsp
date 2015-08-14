<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="jumbotron jumbotron-description">
  <div class="col-md-3 col-sm-12">
    <c:choose>
      <c:when test="${profile.sex == 'M'}">
        <img class="img=responsive img-thumbnail"
          src="/images/default_profile_male.jpeg" />
      </c:when>
      <c:when test="${profile.sex == 'F'}">
        <img class="img=responsive img-thumbnail"
          src="/images/default_profile_female.jpeg" />
      </c:when>
    </c:choose>
  </div>
  <div class="col-md-6 col-sm-12">
    <h3>${profile.firstName} ${profile.lastName}
    <a href="/user/edit" class="btn btn-link btn-xs pull-right"> <span
      class="glyphicon glyphicon-edit" style="vertical-align: middle"></span>
    </a></h3>
    <table class="table">
      <tbody>
        <tr>
          <td>Email</td>
          <td>add extra email
        </tr>
      </tbody>
    </table>
  </div>
</div>