/* SpringProject/src/main/webapp/WEB-INF/js/delete.js */
$(function() {
    // 회원정보수정 폼(userUpdateForm) 유효성 검사 및 폼 제출 처리
    $('#deleteBtn').click(function(event) {
        // 비밀번호 메시지 초기화
        $('#pwdDiv').html("");
        
        // 입력값 가져오기
        let pg = $('#pg').val().trim();
        let id = $('#id').val().trim();
        let pwd = $('#pwd').val().trim();
        let isValid = true;

        // 비밀번호 입력 검사
        if (pwd === "") {
            $('#pwdDiv').html("비밀번호를 입력하세요");
            isValid = false;
        }

        // 비밀번호가 비어있으면 여기서 종료
        if (!isValid) {
            return;
        }

        // 버튼 비활성화
        $('#deleteBtn').prop('disabled', true);

        // 비밀번호 일치 검사
        $.ajax({
            type: 'post',
            url: '/spring/user/getExistPwd', 
            data: 'id=' + $('#id').val(), 	// 서버로 보내는 데이터
            dataType: 'json', 				// 서버로부터 받는 데이터 타입
            success: function(data) {
                console.log("서버 응답 데이터:", data);
                // console.log(JSON.stringify(data));
                if (data !== pwd) {
                    $('#pwdDiv').html("비밀번호가 일치하지 않습니다").show();
                } else {
                    // 비밀번호가 맞을 경우, 회원 탈퇴 요청
                    $.ajax({
                        type: 'post',
                        url: '/spring/user/delete',
                        data: { id: id, pwd: pwd },
                        success: function() {
                            alert("회원탈퇴 완료");
                            location.href = '/spring/user/list?pg=1';
                        },
                        error: function(xhr, status, error) {
                            alert("회원탈퇴에 실패했습니다. 다시 시도해 주세요.");
                            console.log("회원탈퇴 실패:", error);
                            console.log("상태 코드:", xhr.status);
                            console.log("응답 메시지:", xhr.responseText);
                        }
                    });
                }
            },
            error: function(xhr, status, error) {
                alert("비밀번호 검증에 실패했습니다. 다시 시도해 주세요.");
                console.log("비밀번호 검증 실패:", error);
                console.log("상태 코드:", xhr.status);
                console.log("응답 메시지:", xhr.responseText);
            },
            complete: function() {
                // AJAX 요청이 완료되면 버튼 활성화
                $('#deleteBtn').prop('disabled', false);
            }
        });
    });

    // 입력 필드에 포커스가 갈 때 오류 메시지 숨기기
    $('#pwd').focus(function() {
        $('#pwdDiv').hide().html("");
    });

    // 초기화 버튼 클릭 시 오류 메시지 숨기기
    $('#resetBtn').click(function() {
        $('#pwdDiv').hide().html("");    
    });
});
