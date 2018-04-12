<%--
  Created by IntelliJ IDEA.
  User: MinGOOD
  Date: 2018-04-12
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <title>User Index</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <h1>User 첫 페이지</h1>
    <hr/>

    <table class="table table-bordered">
        <tr>
            <td>로그인 ID</td>
            <td><sec:authentication property="user.loginId"></sec:authentication></td>
        </tr>
        <tr>
            <td>이름</td>
            <td><sec:authentication property="user.name"></sec:authentication></td>
        </tr>
        <tr>
            <td>이메일</td>
            <td><sec:authentication property="user.email"></sec:authentication></td>
        </tr>
    </table>

    <a href="logout_processing" class="btn btn-default">로그아웃</a>
</div>
</body>
</html>
