//package com.store.httpclient;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.junit.Test;
//
//public class HttpClientTest {
//	@Test
//	public void doGet() throws Exception{
//		//创建一个httpclient对象
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		//创建一个get对象
//		HttpGet get = new HttpGet("http://www.baidu.com");
//		///执行请求
//		CloseableHttpResponse response = httpClient.execute(get);
//		//取响应结果
//		int statusCode = response.getStatusLine().getStatusCode();
//		System.out.println(statusCode);
//		HttpEntity entity = response.getEntity();
//		String string = EntityUtils.toString(entity,"utf-8");
//		System.out.println(string);
//		//关闭httpclient
//		response.close();
//		httpClient.close();
//		
//	}
//	
//	@Test
//	public void doGetWithParams() throws Exception{
//		//创建一个httpclient对象
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		//创建一个uri对象
//		URIBuilder uriBuilder = new URIBuilder("http://www.baidu.com/web");
//		//添加参数
//		uriBuilder.addParameter("query", "dmq");
//		//创建get对象
//		HttpGet httpGet = new HttpGet(uriBuilder.build());
//		///执行请求
//		CloseableHttpResponse response = httpClient.execute(httpGet);
//		//取响应结果
//		int statusCode = response.getStatusLine().getStatusCode();
//		System.out.println(statusCode);
//		HttpEntity entity = response.getEntity();
//		String string = EntityUtils.toString(entity,"utf-8");
//		System.out.println(string);
//		//关闭httpclient
//		response.close();
//		httpClient.close();
//		
//	}
//	
//	@Test
//	public void doPost() throws Exception{
//		//创建一个httpclient对象
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		//创建一个post对象
//		HttpPost post = new HttpPost("http://www.baidu.com");
//		///执行请求
//		CloseableHttpResponse response = httpClient.execute(post);
//		//取响应结果
//		int statusCode = response.getStatusLine().getStatusCode();
//		System.out.println(statusCode);
//		HttpEntity entity = response.getEntity();
//		String string = EntityUtils.toString(entity,"utf-8");
//		System.out.println(string);
//		//关闭httpclient
//		response.close();
//		httpClient.close();
//		
//	}
//	
//}
