//package com.store.controller;
//
//import java.io.File;
//import java.io.FileInputStream;
//
//import org.apache.commons.net.ftp.FTPClient;
//import org.junit.Test;
//
//import com.store.common.utils.FtpUtil;
//
//public class TestFTP {
//	@Test
// public void testFtpClient() throws Exception{
//	 //创建一个FTPClient对象
//		FTPClient ftpClient = new FTPClient();
//	 //连接超时 设置本地passive模式连接
//		ftpClient.enterLocalPassiveMode();
//	 //创建FTP连接
//		ftpClient.connect("192.168.146.128");
//	 //登陆FTP服务器,使用用户名和密码
//		ftpClient.login("ftpuser", "123456");
//	 //读取本地文件
//		FileInputStream fileInputStream = new FileInputStream(new File("E:\\图片\\icon.jpg"));
//	 //设置上传路径
//		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
//	    ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//	 //上传文件,第一个参数为上传文件在服务端文档名
//	 //第二个参数为上传文件的inputStream
//		ftpClient.storeFile("123.jpg", fileInputStream);
//	 //关闭连接
//		ftpClient.logout();
//		
//	
//}
//	@Test
//	public void testFtpUtils() throws Exception{
//		FileInputStream fileInputStream = new FileInputStream(new File("E:\\图片\\icon.jpg"));
//		FtpUtil.uploadFile("192.168.146.128", 21, "ftpuser", "123456", "/home/ftpuser/www/images", "2019/03/07", "124.jpg", fileInputStream);
//	}
//}
