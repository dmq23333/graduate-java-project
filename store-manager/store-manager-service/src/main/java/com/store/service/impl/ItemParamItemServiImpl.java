package com.store.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.common.pojo.JsonUtils;
import com.store.common.pojo.StoreResult;
import com.store.mapper.TbItemParamItemMapper;
import com.store.pojo.TbItemParamItem;
import com.store.pojo.TbItemParamItemExample;
import com.store.pojo.TbItemParamItemExample.Criteria;
import com.store.service.ItemParamItemService;

@Service
public class ItemParamItemServiImpl implements ItemParamItemService {
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	@Override
	public String getItemParamByItemId(long itemId) {
		//根据商品ID查询规格参数
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		//执行查询
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list==null||list.size()==0) {
			return "";
		}
		//取规格参数信息
		TbItemParamItem tbItemParamItem = list.get(0);
		String paramData = tbItemParamItem.getParamData();
		//生成HTML
		//生成HTML
				// 把规格参数JSON数据转换成java对象
				List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
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
		return sb.toString();
	}

}
