package com.store.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.store.common.utils.FtpUtil;
import com.store.common.utils.IDUtils;
import com.store.service.PicService;
@Service
public class PicServiceImpl implements PicService {
	
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	
	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		
		Map resultMap = new HashMap<>();
		if(null==uploadFile||uploadFile.isEmpty())
		{	
			resultMap.put("error", 1);
			resultMap.put("message", "upload file is valid");
			return resultMap;
		}
		try {
		//生成一个新的文件名
		//取文件的原文件名
		String oldNameString = uploadFile.getOriginalFilename();
		//生成新文件名，保证不重复 使用时间加上随机数
		String newNameString = IDUtils.genImageName();
		newNameString = newNameString + oldNameString.substring(oldNameString.lastIndexOf("."));
		//图片上传
		String imagePathString=new DateTime().toString("/yyyy/MM/dd");
		boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, 
					FTP_PASSWORD, FTP_BASE_PATH, imagePathString,
					newNameString, uploadFile.getInputStream());
		
		//返回结果
		if(!result) {
			resultMap.put("error", 1);
			resultMap.put("message", "upload failed");
			return resultMap;
		}
		resultMap.put("error", 0);
		resultMap.put("url", IMAGE_BASE_URL+imagePathString+"/"+newNameString);
		return resultMap;
		
		} catch (IOException e) {
			e.printStackTrace();
			resultMap.put("error", 1);
			resultMap.put("message", "file upload exception occurred");
			return resultMap;
			
		}
		
	}

}
