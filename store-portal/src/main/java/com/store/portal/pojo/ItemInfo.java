package com.store.portal.pojo;

import com.store.pojo.TbItem;

public class ItemInfo extends TbItem {
	//获取多张图片不显示的bug
		public String[] getImages() {
			String image = getImage();
			if(image!=null) {
				String[] images = image.split(",");
				return images;
			}
			return null;
		}

}
