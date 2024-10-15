/* SpringProject/src/main/webapp/WEB-INF/js/update.js */
$(function(){

    // 회원정보수정 폼(userUpdateForm) 유효성 검사 및 폼 제출 처리
    $('#updateBtn').click(function(event) {
	    $('#nameDiv').empty();
	    $('#pwdDiv').empty();
	    let pg = $('#pg').val().trim();
    
        let name = $('#name').val().trim();
        let pwd = $('#pwd').val().trim();
        let isValid = true;
    
        // 유효성 검사
        if (name === "") {
            $('#nameDiv').html("이름을 입력하세요");
            isValid = false;
        }
    
        if (pwd === "") {
            $('#pwdDiv').html("비밀번호를 입력하세요");
            isValid = false;
        }
        
        // 유효성 검사가 실패한 경우 폼 제출 중지
        if (!isValid) {
            return;
        }
        
        // 유효성 검사가 성공하면 AJAX 요청으로 회원가입 처리
        $.ajax({
            type: 'post',
            url: '/spring/user/update',
            data: $('#userUpdateForm').serialize(),
            success: function() {
                alert("회원정보수정 완료");
                location.href='/spring/user/list?pg=' + pg;
            },
            error: function(xhr, status, error) {
                alert("회원정보수정에 실패했습니다. 다시 시도해 주세요.");
                console.log("회원정보수정 실패:", error);
                console.log("상태 코드:", xhr.status); // 상태 코드 확인
                console.log("응답 메시지:", xhr.responseText); // 응답 메시지 확인
            }
        });
    
    });

    // 입력 필드에 포커스가 갈 때 오류 메시지 숨기기
    $('#name').focus(function() {
        $('#nameDiv').hide().html("");
    });
    $('#pwd').focus(function() {
        $('#pwdDiv').hide().html("");
    });
    
    // 초기화 버튼 클릭 시 오류 메시지 숨기기
    $('#resetBtn').click(function() {
        $('#nameDiv').hide().html("");
        $('#pwdDiv').hide().html("");    
    });
    
});
