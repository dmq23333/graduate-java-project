/*
 * package com.store.controller;
 * 
 * import java.util.List;
 * 
 * import org.junit.Test; import org.springframework.context.ApplicationContext;
 * import org.springframework.context.support.ClassPathXmlApplicationContext;
 * 
 * import com.github.pagehelper.PageHelper; import
 * com.github.pagehelper.PageInfo; import com.store.mapper.TbItemMapper; import
 * com.store.pojo.TbItem; import com.store.pojo.TbItemExample;
 * 
 * public class TestPageHelper {
 * 
 * @Test public void testPageHelper() { //创建一个spring容器 ApplicationContext
 * applicationContext = new
 * ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
 * //从spring容器中获得mapper的代理对象 TbItemMapper tbItemMapper =
 * applicationContext.getBean(TbItemMapper.class); //执行查询并分页 TbItemExample
 * tbItemExample = new TbItemExample(); //分页处理 PageHelper.startPage(1, 10);
 * List<TbItem> list = tbItemMapper.selectByExample(tbItemExample); //取商品列表 for
 * (TbItem tbItem : list) { System.out.println(tbItem.getTitle()); } //取分页信息
 * PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list); long total =
 * pageInfo.getTotal(); System.out.println("一共有"+total+"条"); }
 * 
 * }
 */