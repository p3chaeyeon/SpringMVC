// SpringProject/src/main/java/user/bean/UserDTO.java
package user.bean;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class UserDTO {
	private String name;
	private String id;
	private String pwd;
	
}
