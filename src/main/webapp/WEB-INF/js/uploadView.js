/* SpringProject/src/main/webapp/WEB-INF/js/uploadList.js */
$(function(){
	let pg = $("#pg").val();
	
	function uploadPaging(pg) {
		location.href="${pageContext.request.contextPath }/user/uploadList.do?pg=" + pg;
	}
	
	// 전체 선택 / 전체 해제 이벤트를 동적으로 바인딩
	$(document).on('change', '#all_check', function() {
	    let isChk = $(this).is(':checked');
	    // 전체 선택 체크박스 상태에 따라 모든 체크박스 상태 변경
	    $('.board-list-check').prop('checked', isChk);
	});

	// 개별 체크박스 상태에 따른 전체 선택 체크박스 상태 업데이트
	$(document).on('change', '.board-list-check', function() {
	    let total = $('.board-list-check').length; // 전체 체크박스 수
	    let checked = $('.board-list-check:checked').length; // 체크된 체크박스 수
	    // 체크된 체크박스 수가 전체 체크박스 수와 같으면 전체 선택 체크박스 체크
	    $('#all_check').prop('checked', total === checked);
	});
	
	
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
	        }
	    });
	});
});
