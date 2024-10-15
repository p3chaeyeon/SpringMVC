// Spring/src/main/java/user/service/impl/UserUploadServiceImpl.java
package user.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import user.bean.UserUploadDTO;
import user.bean.UserUploadPaging;
import user.dao.UserUploadDAO;
import user.service.ObjectStorageService;
import user.service.UserUploadService;


@Service
public class UserUploadServiceImpl implements UserUploadService {

	@Autowired
	private UserUploadDAO userUploadDAO; // UserUploadDAO 주입
	
	@Autowired
	private HttpSession session; // uploadUpdate() 에서 사용

	@Autowired
	private ObjectStorageService objectStorageService;
	
	private String bucketName = "bitcamp-9th-bucket-116";
	
	@Autowired
	private UserUploadPaging userUploadPaging;
	
	@Override
	public void upload(List<UserUploadDTO> imageUploadList) {
		userUploadDAO.upload(imageUploadList);
	}

	@Override
	public List<UserUploadDTO> getUploadList() {
		return userUploadDAO.getUploadList();
	}

	@Override
	public UserUploadDTO getUploadDTO(String seq) {
		return userUploadDAO.getUploadDTO(seq);
	}

	@Override
	public void uploadUpdate(UserUploadDTO userUploadDTO, MultipartFile img) {
		// 실제 폴더
		String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
		System.out.println("실제 폴더 : " + filePath);
		
		UserUploadDTO dto = userUploadDAO.getUploadDTO(userUploadDTO.getSeq()+""); // 기존 DB 에 보관된 1개 정보
		String imageFileName;
		
		// Object Storage(NCP) 는 이미지 덮어쓰기 불가능
		// 수정 : DB 에서 seq 에 해당하는 imageFileName 을 꺼내와서 Object Storage(NCP)의 이미지 삭제 후 재업로드
		
		if (img.getSize() != 0) {
			imageFileName = dto.getImageFileName();
			System.out.println("imageFileName : " + imageFileName);
			
			// Object Storage(NCP) 이미지 삭제
			objectStorageService.deleteFile(bucketName, "storage/", imageFileName);
			
			
			// Object Storage(NCP) 새로운 이미지 업로드
			imageFileName = objectStorageService.uploadFile(bucketName, "storage/", img);
		
			String imageOriginalFileName = img.getOriginalFilename();
			File file = new File(filePath, imageOriginalFileName);
			
			try {
				img.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			userUploadDTO.setImageFileName(imageFileName);
			userUploadDTO.setImageOriginalFileName(imageOriginalFileName);
		}
		
		else { // img 를 수정하지 않을 때
			userUploadDTO.setImageFileName(dto.getImageFileName());
			userUploadDTO.setImageOriginalFileName(dto.getImageOriginalFileName());
		}
		
		// DB
		userUploadDAO.uploadUpdate(userUploadDTO);
		
	}

	// Spring/src/main/java/user/service/impl/UserUploadServiceImpl.java
	@Override
	public void uploadDelete(String[] check) {
		
		// userUploadMapper.xml 에서 forEach 사용하려면 데이터를 List 에 담아야 함
		List<String> list = new ArrayList<>();
		
		
		// Object Storage 에 있는 이미지도 삭제해야함 >> imageFlieName 을 list 에 담기
		for (String seq : check) {
			String imageFileName = userUploadDAO.getImageFileName(Integer.parseInt(seq));
			list.add(imageFileName);
		}
		objectStorageService.deleteFile(bucketName, "storage/", list);
		
		
		// DB 삭제
		userUploadDAO.uploadDelete(list);
		
	}


}