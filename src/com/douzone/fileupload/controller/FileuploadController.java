package com.douzone.fileupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.fileupload.service.FileuploadService;

@Controller
public class FileuploadController 
{
	@Autowired
	private FileuploadService fileuploadService;
	
	@RequestMapping("/form")
	public String form()
	{
		return "form";
	}
	
	@RequestMapping("/upload")
	public String upload(
			@RequestParam(value = "email", required = true, defaultValue = "") String email,
			@RequestParam(value = "upload-file") MultipartFile multipartFile, // 여기에 파일에 대한 정보가 다들어있다
			Model model
			)
	{
		System.out.println( "email : " + email);
		// 파일이름을 오리지널 이름으로 저장하면안됨 다른 누군가가 덮어쓸수도 있음
		
		String url = fileuploadService.restore( multipartFile);
		
		model.addAttribute("url", url);
		return "result";
	}
}
