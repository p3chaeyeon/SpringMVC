<%-- SpringProject/src/main/webapp/WEB-INF/index.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="./image/sakura_favicon.png" type="image/png">
<link rel="stylesheet" href="./css/index.css">
<title>Bitcamp Spring</title>
</head>
<body>
<canvas id="sakuraCanvas"></canvas>
<a id="logo" href="/spring/">
	<img src="./image/sakura_logo.png" alt="logo" />
</a>

<div id="container">
	<div class="menuContainer">
	    <p><button type="button" class="menuLink" onclick="location.href='/spring/user/writeForm'">회원가입</button></p>
	    <p><button type="button" class="menuLink" onclick="location.href='/spring/user/login'">로그인</button></p>
	    <p><button type="button" class="menuLink" onclick="location.href='/spring/user/list'">회원목록</button></p>
		<p><button type="button" class="menuLink" onclick="location.href='/spring/user/uploadForm'">파일 업로드</button></p>
		<p><button type="button" class="menuLink" onclick="location.href='/spring/user/uploadFormAjax'">파일 업로드 Ajax</button></p>
		<p><button type="button" class="menuLink" onclick="location.href='/spring/user/uploadList'">파일 목록</button></p>
	</div>
</div>

<script src="./js/sakura.js"></script>

</body>
</html>