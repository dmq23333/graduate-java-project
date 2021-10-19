package com.store.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.store.common.pojo.JsonUtils;
import com.store.service.PicService;

/**
 * 上传图片处理
 * @author 74160
 *
 */
@Controller
public class PicController {
	
	@Autowired
	private PicService picService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String picUpload(MultipartFile uploadFile) {
		
		Map result = picService.uploadPicture(uploadFile);
		//保证功能的兼容性 需要把result转换为json格式的字符串
		String jsonString = JsonUtils.objectToJson(result);
		return jsonString;
		
	}
}
