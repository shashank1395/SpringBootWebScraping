package com.boot.webscraping.service;

import java.io.IOException;
import java.util.List;

import com.boot.webscraping.model.NewScrapperItem;

public interface ScrapperService {

	Object getAuthors();

	List<NewScrapperItem> searchAuthors(String author);

	NewScrapperItem searchTitle(String title);

	void getArticles() throws IOException;

}
