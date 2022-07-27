package com.koreaIT.java.BAM.service;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Article;

public class ArticleService {

	public List<Article> getForPrintArticles(String searchKeyword) {

		List<Article> articles = Container.articleDao.getArticles(searchKeyword);

		return articles;
	}
}
