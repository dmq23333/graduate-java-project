package com.store.rest.service;

import java.util.List;

import com.store.pojo.TbContent;

public interface ContentService {
	List<TbContent> getContentList(long contentCid);

}
