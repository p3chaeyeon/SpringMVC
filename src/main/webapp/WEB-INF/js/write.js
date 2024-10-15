/* SpringProject/src/main/webapp/WEB-INF/js/write.js */
$(function(){

    // 아이디 중복 체크
    $('#id').focusout(function() {
        let id = $('#id').val().trim();
        let idDiv = $('#idDiv');
    
        idDiv.css('display', '');
    
        // 빈 값일 경우 메시지 표시
        if (id === "") {
            idDiv.html("아이디를 먼저 입력하세요");
            idDiv.css('color', 'red');
            return;
        }
    
        $.ajax({
            type: 'POST',
            url: '/spring/user/getExistId',
            data: { id: id },
            dataType: 'text',
            success: function(data) {
                // 서버로부터의 응답에 따라 처리
                console.log(data); // 응답 값 확인을 위해 로그 출력
                if (data.trim() === 'non_exist') {
                    idDiv.html('사용 가능한 아이디입니다.').css('color', 'green');
                    $('#check').val(id); // 중복 체크 성공 시, 숨겨진 필드에 아이디 저장
                } else {
                    idDiv.html('이미 사용 중인 아이디입니다.');
                    $('#check').val(''); // 중복 체크 실패 시, 숨겨진 필드 초기화
                }
                idDiv.show();
            },
            error: function() {
                console.log("오류 발생"); // 오류 메시지 출력
                idDiv.html('아이디 중복 체크 중 오류가 발생했습니다.');
            }
        });
    });

    // 회원가입 폼(userWriteForm) 유효성 검사 및 폼 제출 처리
    $('#joinBtn').click(function(event) {
	    $('#nameDiv').empty();
	    $('#idDiv').empty();
	    $('#pwdDiv').empty();
    
        let name = $('#name').val().trim();
        let id = $('#id').val().trim();
        let pwd = $('#pwd').val().trim();
        let isValid = true;
    
        // 유효성 검사
        if (name === "") {
            $('#nameDiv').html("이름을 입력하세요");
            isValid = false;
        }
    
        if (id === "") {
            $('#idDiv').html("아이디를 입력하세요");
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
            url: '/spring/user/write',
            data: $('#userWriteForm').serialize(),
            success: function() {
                alert("가입 완료");
                location.href='/spring/user/list';
            },
            error: function(xhr, status, error) {
                alert("회원가입에 실패했습니다. 다시 시도해 주세요.");
                console.log("회원가입 실패:", error); // 상태 코드
                console.log("상태 코드:", xhr.status); // 상태 코드 확인
                console.log("응답 메시지:", xhr.responseText); // 응답 메시지 확인
            }
        });
    
    });

    // 입력 필드에 포커스가 갈 때 오류 메시지 숨기기
    $('#name').focus(function() {
        $('#nameDiv').hide().html("");
    });
    $('#id').focus(function() {
        $('#idDiv').hide().html("");
    });
    $('#pwd').focus(function() {
        $('#pwdDiv').hide().html("");
    });
    
    // 초기화 버튼 클릭 시 오류 메시지 숨기기
    $('#resetBtn').click(function() {
        $('#nameDiv').hide().html("");
        $('#idDiv').hide().html("");
        $('#pwdDiv').hide().html("");    
    });
    
});
