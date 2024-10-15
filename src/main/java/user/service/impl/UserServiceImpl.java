// Spring/src/main/java/user/service/impl/UserServiceImpl.java
package user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserDTO;
import user.bean.UserPaging;
import user.dao.UserDAO;
import user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO; // UserDAO 주입
	
	@Autowired
	private UserPaging userPaging;
	
	@Override
	public String getExistId(String id) {
		UserDTO userDTO = userDAO.getExistId(id); // 입력한 id로 DB에서 조회

      if (userDTO == null) { // 아이디가 존재하지 않는 경우
          return "non_exist"; // 사용 가능한 아이디; 단순 문자열이 아니라 .jsp 파일명으로 인식
      } else {
          return "exist"; // 이미 사용 중인 아이디
      }
	}

	@Override
	public void write(UserDTO userDTO) {
		userDAO.write(userDTO);
	}

	@Override
	public Map<String, Object> list(String pg) {
		
		// 한페이지 당 5개씩
		int endNum = 5;
		int startNum = (Integer.parseInt(pg)) * endNum - endNum; // (pg - 1) * endNum
		
		Map<String, Integer> map = new HashMap<>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		// 2. DB
		List<UserDTO> list = userDAO.list(map);
		
		// 3. 페이징 처리
		int totalA = userDAO.getTotalA();
		
        userPaging.setCurrentPage(Integer.parseInt(pg));
        userPaging.setPageBlock(3);
        userPaging.setPageSize(5);
        userPaging.setTotalA(totalA);
        userPaging.makePagingHTML();	
		
        Map<String, Object> map2 = new HashMap<>();
		map2.put("list", list);
		map2.put("userPaging", userPaging);
        
		return map2;
	}

	@Override
	public UserDTO getUser(String id) {
		return userDAO.getExistId(id);
	}
	
	@Override
	public void update(UserDTO userDTO) {
		userDAO.update(userDTO);
	}
	
	// Spring/src/main/java/user/service/impl/UserServiceImpl.java
	@Override
	public UserDTO getExistPwd(String id) {
		UserDTO userDTO = userDAO.getExistPwd(id);
		//UserDTO userDTO = userDAO.getUser(id);
		
		return userDTO;
	}

	@Override
	public void delete(String id) {
		userDAO.delete(id);
	}


}
