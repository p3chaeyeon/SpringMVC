/* SpringProject/src/main/webapp/WEB-INF/js/upload.js */
$(function(){

    /** 이미지 등록 */
    $('#img').change(function() {
        readURL(this);
    });

    function readURL(input) {
        // input에 선택된 파일이 있는지 확인
        if (input.files && input.files.length > 0) {
            var reader = new FileReader();

            // 첫 번째 파일을 로드하여 이미지 미리보기 설정
            reader.onload = function(e) {
                $('#showImg').attr('src', e.target.result);
            };

            // 첫 번째 파일을 읽어서 미리보기로 설정
            reader.readAsDataURL(input.files[0]);

            // 파일명 표시 초기화
            $('#imageOriginalFileName').html('');

            // 선택된 파일들의 이름을 모두 표시
            for (var i = 0; i < input.files.length; i++) {
                $('#imageOriginalFileName').append('<div>' + input.files[i].name + '</div>');
            }
        } else {
            // 파일이 없을 때 이미지 및 파일명 초기화
            $('#showImg').attr('src', '');
            $('#imageOriginalFileName').html('');
        }
    }

    
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
