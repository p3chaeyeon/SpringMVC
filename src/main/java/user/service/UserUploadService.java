// Spring/src/main/java/user/service/UserUploadService.java
package user.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import user.bean.UserUploadDTO;

public interface UserUploadService {
	public void upload(List<UserUploadDTO> imageUploadList);
	
	public List<UserUploadDTO> getUploadList();

	public UserUploadDTO getUploadDTO(String seq);

	public void uploadUpdate(UserUploadDTO userUploadDTO, MultipartFile img);

	public void uploadDelete(String[] check);

}
