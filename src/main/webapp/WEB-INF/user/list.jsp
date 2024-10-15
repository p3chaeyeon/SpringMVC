<%-- SpringProject/src/main/webapp/WEB-INF/user/list.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="../image/sakura_favicon.png" type="image/png">
<link rel="stylesheet" href="../css/list.css">
<title>회원목록</title>
</head>
<body>
<div class="userPage">
 	<div id="left">
		<div id="header">
			<a href="/spring/">
				<img src="../image/sakura_favicon.png" alt="sakura" alt="logo" width="50" height="50" />
				HOME
			</a>
		</div>
		<div id="profile">
			<img src="../image/mangom3.png" width="140" height="140" alt="mangom" />
		</div>
		<div id="links">
			<a id="logOutBtn" href="${context }/user/memberLogOut.do">로그아웃</a> |
			<a href="#">고객센터</a> |
			<a href="#">한국어</a>
		</div>
	</div> 

	<div id="right">
		<div id="container">
			<div id="list-header">회원목록</div>
			<div id="table">
			<table>
			    <thead>
			        <tr>
			            <th>이름</th>
			            <th>아이디</th>
			            <th>비밀번호</th>
			        </tr>
			    </thead>
			    <tbody>
			        <c:forEach var="userDTO" items="${map2.list}">
			            <tr>
			                <td>${userDTO.name}</td>
			                <td><a href="/spring/user/updateForm?id=${userDTO.id}&pg=${map2.pg}">${userDTO.id}</a></td>
			                <td>${userDTO.pwd}</td>
			            </tr>
			        </c:forEach>
			    </tbody>
			</table>
			</div>
			<div id="page-block">${map2.userPaging.pagingHTML }</div>
		</div>
		<button type="button" id="menuBtn" onclick="location.href='/spring/'" >메뉴</button>
	</div>
</div>	

<script type="text/javascript">
function userPaging(pg){
    location.href = "/spring/user/list?pg=" + pg;
}
</script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

</body>
</html>