<%-- SpringProject/src/main/webapp/WEB-INF/user/uploadView.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="../image/sakura_favicon.png" type="image/png">
<link rel="stylesheet" href="../css/uploadView.css">
<title>파일 조회</title>
</head>
<body>
<div id="header">
	<a href="/spring/">
		<img src="../image/sakura_favicon.png" alt="sakura" alt="logo" width="50" height="50" />
		HOME
	</a>
</div>

<div id="container">
	<div class="formTitle">파일 조회</div>
		<%-- <input type="text" id="pg" value="${pg}"/> --%>
		<table>
			<tr>
				<td rowspan="3">
					<img class="img-fit" 
					src="https://kr.object.ncloudstorage.com/bitcamp-9th-bucket-116/storage/${userUploadDTO.imageFileName }" 
					alt="${userUploadDTO.imageOriginalFileName }"/>
				</td>
				<th class="label">번호</th>
				<td class="info">${userUploadDTO.seq }</td>
			</tr>
			
			
			<tr>
                <th class="label">상품명</th>
                <td class="info">${userUploadDTO.imageName }</td>
            </tr>  
            
			<tr>
                <th class="label">파일명</th>
                <td class="info">${userUploadDTO.imageOriginalFileName }</td>
            </tr> 
 
			<tr>
                <td colspan="3" class="info" id="longText">${userUploadDTO.imageContent }</td>
            </tr>                         
		    
            
            <tr>
                <td colspan="3" height="20">
                    <button type="button" id="listBtn" onclick="location.href='./uploadList?pg=1'">파일 목록</button>
                    <button type="button" id="fileUpdateBtn" onclick="location.href='./uploadUpdateForm?seq=${userUploadDTO.seq }'">파일 수정</button>
                    <button type="button" id="fileDeleteBtn" onclick="location.href='./uploadDeleteForm?seq=${userUploadDTO.seq }'">파일 삭제</button>
                </td>
            </tr>		
		</table>

</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script> 
<script type="text/javascript" src="../js/uploadView.js"></script>
</body>
</html>