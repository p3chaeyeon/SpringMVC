// SpringProject/src/main/java/user/dao/UserUploadDAO.java
package user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import user.bean.UserUploadDTO;

@Mapper
public interface UserUploadDAO {

	public void upload(List<UserUploadDTO> imageUploadList);

	public List<UserUploadDTO> getUploadList();

	public UserUploadDTO getUploadDTO(String seq);

	public String getImageFileName(int seq);

	public void uploadUpdate(UserUploadDTO userUploadDTO);

	public void uploadDelete(List<String> list);


}
