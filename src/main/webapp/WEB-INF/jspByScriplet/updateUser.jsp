<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.company.model.User" %>
<% Object obj =request.getAttribute("user");
  User  user = (User)obj;
 %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>User</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
  <div class="px-2">
   <ul class="nav nav-tabs">
  <li class="nav-item">
    <a class="nav-link" href="project">Project</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="task">Task</a>
  </li>
  <li class="nav-item">
    <a class="nav-link active" href="#">User</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="/logout">Logout</a>
  </li>
</ul>
	

<form action="/editUser" method="post">
   <div class="form-group">
    <label for="exampleInputEmail1">User old login</label>
    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter name User" name="oldLogin" value="<%=user.getLogin()%>" readonly="true">
  </div>
  <div class="form-group">
    <label for="exampleInputEmail1">User new name</label>
    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter name User" name="newName" value="<%=user.getName()%>">
  </div>
  <div class="form-group">
    <label for="exampleInputEmail1">User new login</label>
    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter description User" name="newLogin" value="<%=user.getLogin()%>">
  </div>
  <div class="form-group">
    <label for="exampleInputEmail1">User new password</label>
    <input type="password" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter description User" name="newPassword" value="<%=user.getPassword()%>">
  </div>
  <button type="submit" class="btn btn-dark">Edit User</button>
</form>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>