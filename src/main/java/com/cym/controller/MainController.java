package com.cym.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cym.utils.BaseController;
import com.cym.utils.JsonResult;

import cn.hutool.core.io.FileUtil;

@RequestMapping("")
@Controller
public class MainController extends BaseController{


	@RequestMapping("")
	public ModelAndView index(ModelAndView modelAndView, String keywords) {

		modelAndView.setViewName("redirect:/adminPage/admin/");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/upload")
	public JsonResult upload(@RequestParam("file") MultipartFile file) {
		String path = FileUtil.getUserHomePath() + File.separator + System.currentTimeMillis() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		
		File dest = new File(path); 
		// 保存文件
		try {
			file.transferTo(dest);
			return renderSuccess(dest.getPath().replace("\\", "/")); 
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
					
		return renderError();
	}
}