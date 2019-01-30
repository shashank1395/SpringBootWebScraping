package com.boot.webscraping.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.boot.webscraping.model.NewScrapperItem;

@Service
public class ScrapperServiceImpl implements ScrapperService {

	private final Logger logger = LoggerFactory.getLogger(ScrapperServiceImpl.class);

	private static List<String> availableAuthors = new ArrayList<>();

	static HashMap<String, NewScrapperItem> articlesMap = new HashMap<>();

	@Override
	public void getArticles() throws IOException {
		logger.debug("start method: getArticles");
		Elements firstPageElements = getDocument("https://www.thehindu.com/archive/")
				.getElementById("archiveWebContainer").select("a");
		boolean sizeReached = false;
		for (Element firstPageElement : firstPageElements) {
			Elements secondPageElements = getDocument(firstPageElement.absUrl("href"))
					.getElementsByClass("ui-state-default");
			for (Element secondPageElement : secondPageElements) {
				Elements thirdPageElements = getDocument(secondPageElement.absUrl("href"))
						.getElementsByClass("archive-list").select("a");
				for (Element articlePageElement : thirdPageElements) {
					if (!articlesMap.containsKey(articlePageElement.text())) {
						Element articleElement = getDocument(articlePageElement.absUrl("href"));
						String title = articlePageElement.text();
						String author = articleElement.getElementsByClass("author-img-name 1").text();
						String description = "";
						Elements articleParagraphElements = articleElement.getElementsByTag("p");
						for (Element paragraphElement : articleParagraphElements) {
							description += paragraphElement.text() + " ";
						}
						if (!StringUtils.isEmpty(author)) {
							availableAuthors.add(author);
							articlesMap.put(title, new NewScrapperItem(title, author, description));
						}
						if (articlesMap.size() == 100) {
							sizeReached = true;
							break;
						}
					}
				}
				if (sizeReached) {
					break;
				}
			}
			if (sizeReached) {
				break;
			}
		}
		logger.debug("end method: getArticles");
	}

	private Document getDocument(String url) throws IOException {
		return Jsoup.connect(url).userAgent("Mozilla/5.0 Chrome/26.0.1410.64 Safari/537.31").timeout(300000)
				.followRedirects(true).maxBodySize(1024 * 1024 * 3).get();
	}

	@Override
	public List<String> getAuthors() {
		HashSet<String> articleSet = new HashSet<>(availableAuthors);
		return new ArrayList<>(articleSet);
	}

	@Override
	public List<NewScrapperItem> searchAuthors(String author) {
		Iterator<Map.Entry<String, NewScrapperItem>> mapItr = articlesMap.entrySet().iterator();
		HashSet<NewScrapperItem> auhtorsData = new HashSet<>();
		while (mapItr.hasNext()) {
			Map.Entry<String, NewScrapperItem> mapEntry = mapItr.next();
			if (mapEntry.getValue().getAuthor().equalsIgnoreCase(author)) {
				auhtorsData.add(mapEntry.getValue());
			}
		}
		return new ArrayList<>(auhtorsData);
	}

	@Override
	public NewScrapperItem searchTitle(String title) {
		if(articlesMap.containsKey(title)) {
			return articlesMap.get(title);
		}
		return new NewScrapperItem();
	}
}