<%-- SpringProject/src/main/webapp/WEB-INF/user/uploadForm.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="../image/sakura_favicon.png" type="image/png">
<link rel="stylesheet" href="../css/upload.css">
<title>파일 업로드</title>
</head>
<body>
<div id="header">
	<a href="/spring/">
		<img src="../image/sakura_favicon.png" alt="sakura" alt="logo" width="50" height="50" />
		HOME
	</a>
</div>

<div id="container">
	<div class="formTitle">파일 업로드</div>
	<form name="userUploadForm" id="userUploadForm" method="post" enctype="multipart/form-data" action="/spring/user/upload">
				<input type="hidden" id="pg" value="${pg}"/>
		<table>
			<tr>
                <th class="label">상품명</th>
                <td class="input">
                	<input type="text" name="imageName" id="imageName" value="" />
                	<div id="imageNameDiv"></div>
                </td>
            </tr>
            <tr>
                <th class="label">내용</th>
                <td class="input">
                    <textarea name="imageContent" id="imageContent" rows="5" cols="40"></textarea>
                    <div id="imageContentDiv"></div>
                </td>
            </tr>
		    
		    <!-- 한번에 1개 또는 여러 개(드래그) 선택 ; 서버에서 list 로 받음-->   
			<tr>
			    <th class="label">이미지</th>
			    <td class="input">
			        <img id="showImg" width="300" height="300" style="object-fit: contain;" />
			        <label for="img">
			            <div id="fileInput">
			                <div class="fileIcon">📁
			                    <!-- 이미지 다중 등록할 때는 name 이 같아야 함; 서버에서 배열로 받음 -->
			                    <input type="file" name="img[]" multiple="multiple" class="movieInput" id="img" style="display: none;" />
			                </div>
			                <div id="fileNotice">이미지 등록</div>
			                <div id="imageOriginalFileName"></div>
			            </div>
			        </label>
			        <div class="validationDiv" id="imgDiv"></div>
			    </td>
			</tr>
            
            <%--
            <tr>
            	<th class="label" rowspan="2">이미지</th>
	            <td class="input">
					<img id="showImg" width="300" height="300" />
                	<label for="img">
		                <div id="fileInput">
	                		<div class="fileIcon">📁
	                			<!-- 이미지 다중 등록할 때는 name 이 같아야함; 서버에서 배열로 받기 때문 -->
			                	<input type="file" name="img" class="movieInput" id="img" style="display: none;"/>
	                		</div>
			                <div id="fileNotice">이미지 등록</div>
			                <div id="imageOriginalFileName"></div>
		                </div>
	                </label>
	    			<div class="validationDiv" id="moviePosterDiv"></div>
	            </td>    
            </tr>		
            <tr>
	            <td class="input">
					<img id="showImg" width="300" height="300" />
                	<label for="img">
		                <div id="fileInput">
	                		<div class="fileIcon">📁
			                	<input type="file" name="img" class="movieInput" id="img" style="display: none;"/>
	                		</div>
			                <div id="fileNotice">이미지 등록</div>
			                <div id="imageOriginalFileName"></div>
		                </div>
	                </label>
	    			<div class="validationDiv" id="moviePosterDiv"></div>
	            </td>    
            </tr>	
             --%>      
                   
            <tr>
                <td colspan="2" height="20">
                    <button type="submit" id="imageboardWriteBtn">이미지 등록</button>
                    <button type="reset" id="resetBtn">초기화</button>
                    <button type="button" onclick="location.href='./imageboardList.do?pg=1'" id="listBtn">이미지 목록</button>
                </td>
            </tr>		
		</table>
	</form>

</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script> 
<script type="text/javascript" src="../js/upload.js"></script>
</body>
</html>