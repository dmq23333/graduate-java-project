package com.store.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.store.search.pojo.SearchResult;

public interface SearchDao {
	public SearchResult search(SolrQuery query) throws Exception;
}
