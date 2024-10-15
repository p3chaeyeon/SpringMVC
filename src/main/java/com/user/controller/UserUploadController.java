// SpringProject/src/main/java/com/user/controller/UserUploadController.java
package com.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import user.bean.UserUploadDTO;
import user.service.ObjectStorageService;
import user.service.UserUploadService;

@Controller
@RequestMapping(value = "/user")
public class UserUploadController {
	
	@Autowired
	private UserUploadService userUploadService;
	
	@Autowired
	private ObjectStorageService objectStorageService;
	
	private String bucketName = "bitcamp-9th-bucket-116";
	
	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public String uploadForm() {
		return "/user/uploadForm";
	}

	@RequestMapping(value = "/uploadFormAjax", method = RequestMethod.GET)
	public String uploadFormAjax() {
		return "/user/uploadFormAjax";
	}
	
	/* 한글 처리 ; 1. 2. 중 택 1*/
	// 1. produces="text/html; charset=UTF-8" >> 파일명 한글 O, 공백 O
	// 2. URLEncoder.encode(imageOriginalFileName, "UTF-8") >> 파일명 한글 O, 공백 X
	
	/* 이미지 1개 또는 여러 개(드래그) */
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces="text/html; charset=UTF-8")
	public void upload(@ModelAttribute UserUploadDTO userUploadDTO,
						@RequestParam("img[]") List<MultipartFile> list,
						HttpSession session) {
		
		// 세션 생성 안해도 됨 >> upload 함수 인자로 받기 때문
		// HttpSession session = request.getSession()
		
		// 실제 폴더
		String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
		System.out.println("실제 폴더 : " + filePath);
		/**
		D:\Spring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringProject\WEB-INF\storage
		*/
		String imageFileName;
		String imageOriginalFileName;
		File file;
		
		// 파일들을 모아서 DB 로 전달
		List<UserUploadDTO> imageUploadList = new ArrayList<>();
		
		for ( MultipartFile img : list) {
			// imageFileName = UUID.randomUUID().toString();
			
			/** Naver Cloud Platform; Object Storage */
			imageFileName = objectStorageService.uploadFile(bucketName, "storage/", img);
			
			
			imageOriginalFileName = img.getOriginalFilename();
			file = new File(filePath, imageOriginalFileName);
			
			try {
				img.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		

			UserUploadDTO dto = new UserUploadDTO();
			dto.setImageName(userUploadDTO.getImageName());
			dto.setImageContent(userUploadDTO.getImageContent());
            dto.setImageFileName(imageFileName); 
            dto.setImageOriginalFileName(imageOriginalFileName); 			
			
			imageUploadList.add(dto);
		
		}
		
		// DB
//		for (UserUploadDTO uploadDTO : imageUploadList) {
//			userUploadService.imageWrite(uploadDTO);
//		}
		userUploadService.upload(imageUploadList);
		
	}
	
	@RequestMapping(value = "/uploadList", method = RequestMethod.GET)
	public ModelAndView uploadList(@RequestParam(required = false, defaultValue = "1") String pg) {
		List<UserUploadDTO> list = userUploadService.getUploadList();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		mav.addObject("list", list);
		mav.setViewName("/user/uploadList");
		System.out.println(mav);
		
		return mav;
	}
	
	@RequestMapping(value = "/uploadView", method = RequestMethod.GET)
	public String uploadView(@RequestParam String seq, @RequestParam(required = false, defaultValue = "1") String pg, Model model) {
		UserUploadDTO userUploadDTO = userUploadService.getUploadDTO(seq);
		model.addAttribute("userUploadDTO", userUploadDTO);
		
		return "/user/uploadView";
	}	
	
	@RequestMapping(value = "/uploadUpdateForm", method = RequestMethod.GET)
	public String uploadUpdateForm(@RequestParam String seq, Model model) {
		UserUploadDTO userUploadDTO = userUploadService.getUploadDTO(seq);
		model.addAttribute("userUploadDTO", userUploadDTO);
		
		return "/user/uploadUpdateForm";
	}	
	
	@ResponseBody
	@RequestMapping(value = "/uploadUpdate", method = RequestMethod.POST, produces="text/html; charset=UTF-8")
	public void uploadUpdate(@ModelAttribute UserUploadDTO userUploadDTO,
			@RequestParam("img") MultipartFile img) {
		
		userUploadService.uploadUpdate(userUploadDTO, img);
	}
	
	// SpringProject/src/main/java/com/user/controller/UserUploadController.java	
	@ResponseBody
	@RequestMapping(value = "/uploadDelete", method = RequestMethod.POST, produces="text/html; charset=UTF-8")
	public void uploadDelete(@RequestParam String[] check) {
		for (String seq: check) {
			System.out.println("삭제할 파일 번호: " + seq);
		}
		userUploadService.uploadDelete(check);
	}	
	
}	
	
	/* 이미지 2개 이상 등록 
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@ModelAttribute UserUploadDTO userUploadDTO,
						@RequestParam MultipartFile[] img,
						HttpSession session) {
		
		// 세션 생성 안해도 됨 >> upload 함수 인자로 받기 때문
		// HttpSession session = request.getSession()
		
		// 실제 폴더
		String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
		System.out.println("실제 폴더 : " + filePath);
		// D:\Spring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringProject\WEB-INF\storage
		
		String imageOriginalFileName;
		File file;
		String result;
		
		// ---------------------------------------------------------
		
		imageOriginalFileName = img[0].getOriginalFilename();
		file = new File(filePath, imageOriginalFileName);
		
		try {
			img[0].transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		result = "<span>\n"
	              + "<img src='/spring/storage/" + imageOriginalFileName + "' width='300' height='300' style='object-fit: contain;'>\n"
	              + "</span>";

		
		// ---------------------------------------------------------
		
		imageOriginalFileName = img[1].getOriginalFilename();
		file = new File(filePath, imageOriginalFileName);
		
		try {
			img[1].transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		result = "<span>\n"
	              + "<img src='/spring/storage/" + imageOriginalFileName + "' width='300' height='300' style='object-fit: contain;'>\n"
	              + "</span>";		
		
		
		// ---------------------------------------------------------
		
		
		System.out.println(userUploadDTO.getImageName()
						+ userUploadDTO.getImageContent()
						+ userUploadDTO.getImageFileName()
						+ userUploadDTO.getImageOriginalFileName()
				);
		
		userUploadDTO.setImageOriginalFileName(imageOriginalFileName);
		
		return result;
		
	}
	/*
	
	
	
	/* 이미지 1개 등록
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@ModelAttribute UserUploadDTO userUploadDTO,
						@RequestParam MultipartFile img,
						HttpSession session) {
		
		// 세션 생성 안해도 됨 >> upload 함수 인자로 받기 때문
		// HttpSession session = request.getSession()
		
		// 실제 폴더
		String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
		System.out.println("실제 폴더 : " + filePath);
		// D:\Spring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringProject\WEB-INF\storage
		
		String imageOriginalFileName = img.getOriginalFilename();
		
		File file = new File(filePath, imageOriginalFileName);
		
		try {
			img.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String result = "<span>\n"
	              + "<img src='/spring/storage/" + imageOriginalFileName + "' width='300' height='300' style='object-fit: contain;'>\n"
	              + "</span>";

		System.out.println(userUploadDTO.getImageName()
						+ userUploadDTO.getImageContent()
						+ userUploadDTO.getImageFileName()
						+ userUploadDTO.getImageOriginalFileName()
				);
		
		userUploadDTO.setImageOriginalFileName(imageOriginalFileName);
		
		return result;
	}
	*/


