package com.store.portal.service;

import com.store.portal.pojo.SearchResult;

public interface SearchService {
	
	public SearchResult search(String queryString, int page);

}
