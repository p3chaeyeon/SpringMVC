// SpringProject/src/main/java/user/bean/UserUploadDTO.java
package user.bean;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class UserUploadDTO {
	private int seq;
	private String imageName;				// 상품명
	private String imageContent;			// 상품내용
	private String imageFileName;			// UUID 에서 얻은 이름
	private String imageOriginalFileName;	// 이미지의 원래 이름
}
