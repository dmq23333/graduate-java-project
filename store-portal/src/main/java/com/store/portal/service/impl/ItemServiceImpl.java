package com.store.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.store.common.pojo.JsonUtils;
import com.store.common.pojo.StoreResult;
import com.store.common.utils.ExceptionUtil;
import com.store.common.utils.HttpClientUtil;
import com.store.pojo.TbItem;
import com.store.pojo.TbItemDesc;
import com.store.pojo.TbItemParamItem;
import com.store.portal.pojo.ItemInfo;
import com.store.portal.pojo.ProductsResult;
import com.store.portal.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_DESC_URL}")
	private String ITEM_DESC_URL;
	@Value("${ITEM_PARAM_URL}")
	private String ITEM_PARAM_URL;
	@Value("${ITEM_CID_LIST}")
	private String ITEM_CID_LIST;
	@Value("${ITEM_CAT_NAME}")
	private String ITEM_CAT_NAME;

	@Override
	public ItemInfo getItemById(long itemId) {
		try {
			//调用rest的服务查询商品信息 
		String jsonString = HttpClientUtil.doGet(REST_BASE_URL+ITEM_INFO_URL+itemId);
		if(!StringUtils.isBlank(jsonString)) {
			StoreResult result = StoreResult.formatToPojo(jsonString, ItemInfo.class);
			if(result.getStatus()==200) {
				ItemInfo item = (ItemInfo) result.getData();
				return item;
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getItemDescById(long itemId) {
		try {
			//查询商品描述
			String jsonString = HttpClientUtil.doGet(REST_BASE_URL+ITEM_DESC_URL+itemId);
			//json转化Java对象
			StoreResult storeResult = StoreResult.formatToPojo(jsonString, TbItemDesc.class);
			if(storeResult.getStatus()==200) {
				TbItemDesc tbItemDesc = (TbItemDesc) storeResult.getData();
			//取商品描述信息
				String result = tbItemDesc.getItemDesc();
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String getItemParamById(long itemId) {
		try {
			//查询商品规格参数
			String jsonString = HttpClientUtil.doGet(REST_BASE_URL+ITEM_PARAM_URL+itemId);
			//把json字符串转换成json对象
			StoreResult storeResult = StoreResult.formatToPojo(jsonString, TbItemParamItem.class);
			if(storeResult.getStatus()==200) {
				TbItemParamItem itemParamItem = (TbItemParamItem) storeResult.getData();
				String paramString = itemParamItem.getParamData();
				//生成html
				// 把规格参数json数据转换成java对象
				List<Map> jsonList = JsonUtils.jsonToList(paramString, Map.class);
				StringBuffer sb = new StringBuffer();
				sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
				sb.append("    <tbody>\n");
				for(Map m1:jsonList) {
					sb.append("        <tr>\n");
					sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
					sb.append("        </tr>\n");
					List<Map> list2 = (List<Map>) m1.get("params");
					for(Map m2:list2) {
						sb.append("        <tr>\n");
						sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
						sb.append("            <td>"+m2.get("v")+"</td>\n");
						sb.append("        </tr>\n");
					}
				}
				sb.append("    </tbody>\n");
				sb.append("</table>");
				//返回html片段
				return sb.toString();
			}
				 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";

	}

	@Override
	public ProductsResult getItemListByCid(long cId) {
		String cidString = String.valueOf(cId);
		try {
			//调用rest服务查询
			String jsonString = HttpClientUtil.doGet(REST_BASE_URL+ITEM_CID_LIST+cidString);
			//把json字符串转化为json对象
			StoreResult	storeResult = StoreResult.formatToPojo(jsonString, ProductsResult.class);
			if(storeResult.getStatus()==200) {
				ProductsResult productsResult = (ProductsResult) storeResult.getData();
				return productsResult;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductsResult getItemListByCid(long cId, int page, int rows) {
		// 翻页时的url
		return null;
	}

	@Override
	public String getItemCatNameByCid(long cId) {
		//调用rest服务
		String jsonString = HttpClientUtil.doGet(REST_BASE_URL+ITEM_CAT_NAME+cId);
		//把json字符串转化为json对象
		StoreResult storeResult = StoreResult.format(jsonString);
		if(storeResult.getStatus()==200) {
			String resultString = (String) storeResult.getData();
			return resultString;
		}
		return "";
	}
	
	
}
