/* SpringProject/src/main/webapp/WEB-INF/js/uploadAjax.js */
$(function(){

    /** 이미지 등록 */
    $('#camera').click(function() {
    	$('#img').trigger('click'); // 강제 이벤트 발생
    });


	// 이미지 미리보기
	$('#img').change(function() {
	    // 이미지 리스트 초기화
	    $('#showImgList').empty();
	
	    // 선택된 파일들 처리
	    for (var i = 0; i < this.files.length; i++) {
	        readURL(this.files[i]);
	    }
	});
	
	function readURL(file) {
	    var reader = new FileReader();
	
	    reader.onload = function(e) {
	        // 새로운 img 태그 생성
	        var img = document.createElement('img');
	        img.src = e.target.result;
	        img.width = 70;
	        img.height = 70;
	
	        // 미리보기 리스트에 이미지 추가
	        $('#showImgList').append(img);
	    }
	
	    // 파일 읽기 시작
	    reader.readAsDataURL(file);
	}


	$('#uploadAjaxBtn').click(function(){
		let formData = new FormData($('#userUploadForm')[0]);
		
		$.ajax({
			type: 'post',
			enctype: 'multipart/form-data',
			processData: false, 
			//processData: 기본적으로 Query String으로 변환해서 보내진다, 파일 전송시에는 반드시 false로 해야함
			contentType: false,
			//contentType: 파일 전송시에는 'multipart/form-data'로 전송이 될 수 있도록 false로 설정
			url: '/spring/user/upload',
			data: formData,
			success: function(data){
				console.log(data);
				alert("파일 등록 완료");
				location.href='/spring/user/uploadList';
			},
			error: function(xhr, status, error) {
                alert("이미지 등록에 실패했습니다. 다시 시도해 주세요.");
                console.log("이미지 등록 실패:", error); // 상태 코드
                console.log("상태 코드:", xhr.status); // 상태 코드 확인
                console.log("응답 메시지:", xhr.responseText); // 응답 메시지 확인
            }
		});
	
	});

    
	// 입력 내용 초기화
	$('#resetBtn').click(function() {
		$('.validationDiv').hide();
		
		// <form> input 초기화
		$('form[name="userUploadForm"]')[0].reset();
		
		// <form> 이미지 초기화
		$('#showImg').attr('src', '');
		$('#imageOriginalFileName').hide();
	});	    
});
