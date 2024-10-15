<%-- SpringProject/src/main/webapp/WEB-INF/user/updateForm.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="../image/sakura_favicon.png" type="image/png">
<link rel="stylesheet" href="../css/update.css">
<title>회원정보수정</title>
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
		<div class="links">
			<a id="logOutBtn" href="${context }/member/memberLogOut.do">로그아웃</a> |
			<a href="#">고객센터</a> |
			<a href="#">한국어</a>
		</div>
	</div>

	<div id="right">
		<div id="container">
			<div id="update-header">회원정보수정</div>
			<form name="userUpdateForm" id="userUpdateForm">
				<input type="hidden" id="pg" value="${pg}"/>
				
				<table>
					<tbody>
					<c:if test="${not empty userDTO}">
					<tr>
					     <th class="label">이름</th>
					     <td class="input">
					        <input type="text" name="name" id="name" value="${userDTO.name}"/>
					       	<div id="nameDiv"></div>
					    </td>
					</tr>
					<tr>
					    <th class="label">아이디</th>
					    <td class="input">
					       <input type="text" name="id" id="id" value="${userDTO.id}" readonly />
					    </td>
					</tr>
					<tr>
					    <th class="label">비밀번호</th>
					    <td class="input">
					       <input type="text" name="pwd" id="pwd" value="${userDTO.pwd}" />
					       <div id="pwdDiv"></div>
					    </td>
					</tr>
					</tbody>
					</c:if>
					
					<tr align="center">
				        <td colspan="2" height="20">
				            <button type="button" id="updateBtn" >회원정보 수정</button>
				            <button type="reset" id="resetBtn">초기화</button>
				            <!-- <button type="button" id="deleteBtn" onclick="location.href='/spring/user/deleteForm'">회원탈퇴</button> -->
				        </td>
				    </tr>
			</table>
		</form>
			
		</div>
		<div id="deleteLinkDiv">
			<a id="deleteLink" href="/spring/user/deleteForm?id=${userDTO.id}&pg=${pg}">회원탈퇴 ></a>
		</div>
		<button type="button" id="menuBtn" onclick="location.href='/spring/user/list?pg=${pg}'" >회원목록</button>
	</div>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script> 
<script type="text/javascript" src="../js/update.js"></script>
</body>
</html>