// SpringProject/src/main/java/com/user/controller/UserController.java
package com.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import user.bean.UserDTO;
import user.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/writeForm", method = RequestMethod.GET)
	public String writeForm() {
		return "/user/writeForm";
	}
	
    @ResponseBody // dispatcher 타는 것을 방지; 단순 문자열이 아니라 .jsp 파일명으로 인식하는 것을 해결
    @RequestMapping(value = "/getExistId", method = RequestMethod.POST) 
    public String getExistId(String id) throws Exception {
        return userService.getExistId(id);
    }
    
    @ResponseBody
    @RequestMapping(value = "/write", method = RequestMethod.POST) 
    public void write(@ModelAttribute UserDTO userDTO) {
    	userService.write(userDTO);
    }
    
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@RequestParam(required = false, defaultValue = "1") String pg, Model model) {
		Map<String, Object> map2 = userService.list(pg);
		
		map2.put("pg", pg);
		model.addAttribute("map2", map2);
		
		return "/user/list";
	}
	
	@RequestMapping(value = "/updateForm", method = RequestMethod.GET)
	public String updateForm(@RequestParam String id, @RequestParam String pg, ModelMap modelMap) {
		UserDTO userDTO = userService.getUser(id);
		
		modelMap.put("userDTO", userDTO);
		modelMap.put("pg", pg);
		
		return "/user/updateForm";
	}
	
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST) 
    public void update(@ModelAttribute UserDTO userDTO) {
    	userService.update(userDTO);
    }
    
    
    // SpringProject/src/main/java/com/user/controller/UserController.java
    // ...
	@RequestMapping(value = "/deleteForm", method = RequestMethod.GET)
	public String deleteForm(@RequestParam String id, @RequestParam String pg, ModelMap modelMap) {
		UserDTO userDTO = userService.getUser(id);
		
		modelMap.put("userDTO", userDTO);
		modelMap.put("pg", pg);
		
		return "/user/deleteForm";
	}
	
	@ResponseBody
    @RequestMapping(value = "/getExistPwd", method = RequestMethod.POST) 
	public UserDTO getExistPwd(@RequestParam String id, Model model) {
		//스프링은 객체를 자동으로 JSON으로 변경해준다.
		
		return userService.getExistPwd(id);
	}
	
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST) 
    public void delete(@RequestParam String id) {
    	userService.delete(id);
    }
}
