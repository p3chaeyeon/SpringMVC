/* SpringProject/src/main/webapp/WEB-INF/js/uploadList.js */
$(function(){
	let pg = $("#pg").val();
	
	function uploadPaging(pg) {
		location.href="${pageContext.request.contextPath }/user/uploadList.do?pg=" + pg;
	}
	
	/*
	// 전체 선택 / 전체 해제 이벤트를 동적으로 바인딩
	$(document).on('change', '#all-check', function() {
	    let isChk = $(this).is(':checked');
	    // 전체 선택 체크박스 상태에 따라 모든 체크박스 상태 변경
	    $('.board-list-check').prop('checked', isChk);
	});

	// 개별 체크박스 상태에 따른 전체 선택 체크박스 상태 업데이트
	$(document).on('change', '.board-list-check', function() {
	    let total = $('.board-list-check').length; // 전체 체크박스 수
	    let checked = $('.board-list-check:checked').length; // 체크된 체크박스 수
	    // 체크된 체크박스 수가 전체 체크박스 수와 같으면 전체 선택 체크박스 체크
	    $('#all-check').prop('checked', total === checked);
	});
	*/
	
	// 전체 선택 / 전체 해제 이벤트를 동적으로 바인딩
	$('#all-check').click(function() {
		if ($(this).prop('checked'))
			$('input[name="check"]').prop('checked', true);
		else
			$('input[name="check"]').prop('checked', false);
	});
	
	// 개별 체크박스 상태에 따른 전체 선택 체크박스 상태 업데이트
	$('input[name="check"]').click(function(){
	    let checkNum = $('input[name="check"]').length; // 전체 체크박스 개수
	    let checkedNum = $('input[name="check"]:checked').length; // 체크된 체크박스 개수
	    
	    $('#all-check').prop('checked', checkNum == checkedNum);
	});

	
$('#deleteBtn').click(function() {
    if ($("input[name='check']:checked").length === 0) {
        alert('삭제할 파일을 선택해 주세요.');
        return;
    }

    $.ajax({
        type: 'POST',
        url: '/spring/user/uploadDelete',
        data: $('#uploadListForm').serialize(), // 폼 데이터 직렬화하여 전송
        success: function() {
            alert('파일 삭제 완료');
            location.href = '/spring/user/uploadList';
        },
        error: function(xhr, status, error) {
            alert('파일 삭제에 실패했습니다.');
            console.log("이미지 삭제 실패:", error); 
            console.log("상태 코드:", xhr.status); 
            console.log("응답 메시지:", xhr.responseText); 
        }
    });
});


	
	
	/**
	// 이미지 체크박스로 삭제
	$('#deleteBtn').click(function() {
		// 체크된 seq들을 배열로 가져오기
	    let mcodeArray = [];
	    $('input[name="mcode"]:checked').each(function() {
	        mcodeArray.push($(this).val().trim());
	    });

		if (mcodeArray.length === 0) {
		    alert("삭제할 영화를 선택하세요.");
		    return;
		}

	    $.ajax({
	        url: context + '/admin/movieDeleteDB.do',
	        type: 'POST',
	        data: {mcode: mcodeArray},
	        traditional: true,  // 배열 데이터를 서버에 전송할 때 필요한 설정
	        success: function() {
	            alert('영화가 삭제되었습니다.');
	            location.reload(); // 페이지 새로고침
	        },
	        error: function() {
	            alert('영화 삭제에 실패했습니다.');
                console.log("이미지 삭제 실패:", error); // 상태 코드
                console.log("상태 코드:", xhr.status); // 상태 코드 확인
                console.log("응답 메시지:", xhr.responseText); // 응답 메시지 확인	            
	        }
	    });
	});
	*/
});
